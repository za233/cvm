package cvmdecompiler;

import java.io.IOException;
import java.util.ArrayList;

import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.StructConverter;

import ghidra.program.model.data.DataType;
import ghidra.util.exception.DuplicateNameException;
import ghidra.program.model.data.Structure;
import ghidra.program.model.data.StructureDataType;
public class CvmGlobalHeader implements StructConverter
{
	
	private int magicNumber;
	private int sectionCount;
	private int maxSpace;
	private int contentSize;
	private int entryPoint;
	private int size;
	private ArrayList<CvmSectionHeader> sections = new ArrayList<>();
	public CvmGlobalHeader(BinaryReader r) throws IOException
	{
		this.magicNumber = r.readNextInt();
		this.sectionCount = r.readNextInt();
		this.maxSpace = r.readNextInt();
		this.contentSize = r.readNextInt();
		this.entryPoint = r.readNextInt();
		for(int i = 0; i < this.sectionCount; i++)
			sections.add(new CvmSectionHeader(r));
		this.size = (5 + this.sectionCount * 5) * 4;
	}
	@Override
	public DataType toDataType() throws DuplicateNameException, IOException 
	{
		Structure header = new StructureDataType("CvmGloalHeader", 0);
		header.add(DWORD, "Magic Number", null);
		header.add(DWORD, "Section Count", null);
		header.add(DWORD, "Max Space", null);
		header.add(DWORD, "Content Size", null);
		header.add(DWORD, "Entry Point", null);
		for(int i = 0; i < this.sectionCount; i++)
		{
			CvmSectionHeader section = sections.get(i);
			header.add(section.toDataType(), section.toDataType().getLength(), "Section_" + i, section.getAttributeName());
		}
			
		return header;
	}
	public int getMagicNumber() 
	{
		return magicNumber;
	}
	public int getSectionCount() 
	{
		return sectionCount;
	}
	public int getMaxSpace() 
	{
		return maxSpace;
	}
	public int getContentSize() 
	{
		return contentSize;
	}
	public int getEntryPoint() 
	{
		return entryPoint;
	}
	public ArrayList<CvmSectionHeader> getSections()
	{
		return this.sections;
	}
	public int getSize()
	{
		return this.size;
	}

}
