/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cvmdecompiler;

import java.io.IOException;
import java.util.*;

import cvmdecompiler.CvmImportTable.CvmImportStr;
import ghidra.app.util.MemoryBlockUtils;
import ghidra.app.util.Option;
import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.ByteProvider;
import ghidra.app.util.importer.MessageLog;
import ghidra.app.util.opinion.AbstractProgramWrapperLoader;
import ghidra.app.util.opinion.LoadSpec;
import ghidra.program.database.function.OverlappingFunctionException;
import ghidra.program.flatapi.FlatProgramAPI;
import ghidra.framework.model.DomainObject;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressOutOfBoundsException;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.address.AddressSpace;
import ghidra.program.model.data.DataType;
import ghidra.program.model.data.QWordDataType;
import ghidra.program.model.lang.LanguageCompilerSpecPair;
import ghidra.program.model.listing.Program;
import ghidra.program.model.mem.MemoryAccessException;
import ghidra.program.model.symbol.SourceType;
import ghidra.program.model.util.CodeUnitInsertionException;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;
import ghidra.util.task.TaskMonitor;

/**
 * TODO: Provide class-level documentation that describes what this loader does.
 */
public class CvmDecompilerLoader extends AbstractProgramWrapperLoader 
{


	@Override
	public String getName()
	{
		return "Cvm Bytecode File Loader";
	}

	
	@Override
	public Collection<LoadSpec> findSupportedLoadSpecs(ByteProvider provider) throws IOException 
	{
		List<LoadSpec> loadSpecs = new ArrayList<>();
		BinaryReader br = new BinaryReader (provider, true);
		if(br.readNextInt() == 0xdeadbeef)
		{
			loadSpecs.add(new LoadSpec(this, 0, new LanguageCompilerSpecPair("cvm:LE:64:default", "default"), true));
		}
		return loadSpecs;
	}

	@Override
	protected void load(ByteProvider provider, LoadSpec loadSpec, List<Option> options, Program program, TaskMonitor monitor, MessageLog log)
			throws CancelledException, IOException 
	{
		BinaryReader br = new BinaryReader (provider, true);
		CvmGlobalHeader header = new CvmGlobalHeader(br);
		CvmSectionHeader codeSection = null;
		CvmImportTable imports = null;
		for(CvmSectionHeader section : header.getSections())
		{
			if(section.getAttributeName().equals("data"))
			{
				int ep = header.getEntryPoint();
				if(ep >= section.getLoadBase() && ep < section.getLoadBase() + section.getMemorySize())
				{
					codeSection = section;
					break;
				}
			}
		}
		try 
		{
			loadHeader(br, header, program, log);
			for(CvmSectionHeader section : header.getSections())
			{
				if(section == codeSection)
					continue;
				if(section.getAttributeName().equals("data"))
					loadInitializedSection(br, section, program, ".data", log);
				if(section.getAttributeName().equals("bss"))
					loadUnnitializedSection(section, program, ".bss", log);
				if(section.getAttributeName().equals("import"))
					imports = loadImportSection(br, section, program, log);
			}
			loadInitializedSection(br, codeSection, program, ".code", log);
		} catch (MemoryAccessException | IOException | CodeUnitInsertionException | DuplicateNameException e) 
		{
			e.printStackTrace();
		}
		FlatProgramAPI api = new FlatProgramAPI(program, monitor);
		AddressSpace codeSpace = api.getAddressFactory().getDefaultAddressSpace();
		api.createFunction(codeSpace.getAddress(header.getEntryPoint()), "main");
		if(imports != null)
		{
			AddressSpace plt = api.getAddressFactory().getAddressSpace("plt");
			try 
			{
				api.createMemoryBlock(".plt", plt.getAddress(0), null, imports.getImports().size() * 8, false);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			for(int i = 0; i < imports.getImports().size(); i++)
			{
				Address ptr = plt.getAddress(8 * i);
				try 
				{
					api.createData(ptr, QWordDataType.dataType);
					api.createLabel(ptr, imports.getImports().get(i).getFuncName(), true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	public CvmImportTable loadImportSection(BinaryReader br, CvmSectionHeader section, Program program, MessageLog log) throws IOException
	{
		if(section.getContentSize() == 0)
			return null;
		//Address start = program.getAddressFactory().getAddressSpace("import").getAddress(0);
		//MemoryBlockUtils.createInitializedBlock(program, false, ".import", start, section.getContentSize(), ".import", "", true, false, false, log);
		//br.setPointerIndex(section.getFileOffset());
		//byte[] data = br.readNextByteArray(section.getContentSize());
		//program.getMemory().setBytes(start, data);
		br.setPointerIndex(section.getFileOffset());
		CvmImportTable imports = new CvmImportTable(br, section.getContentSize());
		//program.getListing().createData(start, imports.toDataType(), imports.toDataType().getLength());
		return imports;
	}
	public void loadHeader(BinaryReader br, CvmGlobalHeader header, Program program, MessageLog log) throws MemoryAccessException, IOException, CodeUnitInsertionException, DuplicateNameException
	{
		Address start = program.getAddressFactory().getAddressSpace("header").getAddress(0);
		MemoryBlockUtils.createInitializedBlock(program, false, "FileHeader", start, header.getSize(), "FileHeader", "", true, false, false, log);
		br.setPointerIndex(0);
		byte[] data = br.readNextByteArray(header.getSize());
		program.getMemory().setBytes(start, data);
		program.getListing().createData(start, header.toDataType(), header.toDataType().getLength());
	}
	public void loadInitializedSection(BinaryReader br, CvmSectionHeader section, Program program, String name, MessageLog log) throws IOException, MemoryAccessException
	{
		if(section.getContentSize() == 0)
			return;
		Address start = program.getAddressFactory().getDefaultAddressSpace().getAddress(section.getLoadBase());
		MemoryBlockUtils.createInitializedBlock(program, false, name, start, section.getMemorySize(), name, "", true, true, true, log);
		br.setPointerIndex(section.getFileOffset());
		byte[] data = br.readNextByteArray(section.getContentSize());
		program.getMemory().setBytes(start, data);
	}
	public void loadUnnitializedSection(CvmSectionHeader section, Program program, String name, MessageLog log)
	{
		Address start = program.getAddressFactory().getDefaultAddressSpace().getAddress(section.getLoadBase());
		MemoryBlockUtils.createUninitializedBlock(program, false, name, start, section.getMemorySize(), name, "", true, true, true, log);
	}
	@Override
	public List<Option> getDefaultOptions(ByteProvider provider, LoadSpec loadSpec, DomainObject domainObject, boolean isLoadIntoProgram) 
	{
		List<Option> list = super.getDefaultOptions(provider, loadSpec, domainObject, isLoadIntoProgram);
		return list;
	}

	@Override
	public String validateOptions(ByteProvider provider, LoadSpec loadSpec, List<Option> options, Program program)
	{
		return super.validateOptions(provider, loadSpec, options, program);
	}
}
