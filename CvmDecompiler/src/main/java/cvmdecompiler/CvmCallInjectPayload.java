package cvmdecompiler;

import ghidra.app.plugin.processors.sleigh.SleighLanguage;
import ghidra.program.model.lang.InjectContext;
import ghidra.program.model.listing.Program;
import ghidra.program.model.pcode.PcodeOp;

public class CvmCallInjectPayload extends CvmInjectPayload
{
	

	public CvmCallInjectPayload(String sourceName, SleighLanguage language, long uniqBase)
	{
		super(sourceName, language, uniqBase);
	}
	@Override
	public PcodeOp[] getPcode(Program program, InjectContext con) 
	{
		System.out.println(con.inputlist.get(0).encodePiece());
		//PcodeOp c = new PcodeOp();
		return null;
	}
	@Override
	public String getName()
	{
		return "vmcall";
	}
}
