package cvmdecompiler;

import java.io.IOException;

import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.StructConverter;
import ghidra.program.model.data.DataType;
import ghidra.util.exception.DuplicateNameException;
import ghidra.program.model.data.Structure;
import ghidra.program.model.data.StructureDataType;
public class CvmSectionHeader implements StructConverter
{
	private int fileOffset;
	private int loadBase;
	private int attribute;
	private int memorySize;
	private int contentSize;
	public CvmSectionHeader(BinaryReader r) throws IOException
	{
		this.fileOffset = r.readNextInt();
		this.loadBase = r.readNextInt();
		this.attribute = r.readNextInt();
		this.memorySize = r.readNextInt();
		this.contentSize = r.readNextInt();
	}
	@Override
	public DataType toDataType() throws DuplicateNameException, IOException 
	{
		Structure header = new StructureDataType("CvmSection", 0);
		header.add(DWORD, 4, "File Offset", null);
		header.add(DWORD, 4, "Load Base", null);
		header.add(DWORD, 4, "Attribute", null);
		header.add(DWORD, 4, "Memory Size", null);
		header.add(DWORD, 4, "Content Size", null);
		return header;
	}
	public String getAttributeName() 
	{
		if((this.attribute & 4) != 0)
			return "stack";
		else if((this.attribute & 8) != 0)
			return "import";
		else
		{
			if((attribute & 2) == 0)
				return "data";
			return "bss";
				
		}
	}
	public int getFileOffset() 
	{
		return fileOffset;
	}
	public int getLoadBase() 
	{
		return loadBase;
	}
	public int getAttribute() 
	{
		return attribute;
	}
	public int getMemorySize()
	{
		return memorySize;
	}
	public int getContentSize()
	{
		return contentSize;
	}

}
