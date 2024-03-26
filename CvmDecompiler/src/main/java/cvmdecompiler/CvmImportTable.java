package cvmdecompiler;

import java.io.IOException;
import java.util.ArrayList;
import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.StructConverter;
import ghidra.program.model.data.DataType;
import ghidra.program.model.data.Structure;
import ghidra.program.model.data.StructureDataType;
import ghidra.util.exception.DuplicateNameException;

public class CvmImportTable implements StructConverter
{
	private ArrayList<CvmImportStr> imports = new ArrayList<>();
	class CvmImportStr implements StructConverter
	{
		private String funcName;
		public CvmImportStr(BinaryReader br) throws IOException
		{
			byte size = br.readNextByte();
			byte[] arr = new byte[size];
			for(int i = 0; i < size; i++)
				arr[i] = br.readNextByte();
			this.funcName = new String(arr);
				
		}
		public String getFuncName()
		{
			return this.funcName;
		}
		@Override
		public DataType toDataType() throws DuplicateNameException, IOException 
		{
			Structure s = new StructureDataType("ImportFunction", 0);
			s.add(BYTE, 1, "Name Size", null);
			s.add(STRING, this.funcName.length(), "Function Name", null);
			return s;
		}
		
	}
	public CvmImportTable(BinaryReader br, int size) throws IOException
	{
		int ptr = 0;
		while(br.hasNext() && ptr < size)
		{
			CvmImportStr str = new CvmImportStr(br);
			ptr += str.getFuncName().length() + 1;
			this.imports.add(str);
		}
			
	}
	public ArrayList<CvmImportStr> getImports()
	{
		return imports;
	}
	@Override
	public DataType toDataType() throws DuplicateNameException, IOException 
	{
		Structure s = new StructureDataType("CvmImport", 0);
		for(int i = 0; i < this.imports.size(); i++)
			s.add(this.imports.get(i).toDataType(), this.imports.get(i).toDataType().getLength(), "Import_" + i, null);
		return s;
	}

}
