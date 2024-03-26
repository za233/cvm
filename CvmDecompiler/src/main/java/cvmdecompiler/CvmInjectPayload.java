package cvmdecompiler;

import ghidra.app.plugin.processors.sleigh.SleighLanguage;
import ghidra.program.model.lang.InjectPayload;
import ghidra.program.model.lang.InjectPayloadCallother;


public class CvmInjectPayload extends InjectPayloadCallother
{
	protected long uniqBase;
	protected SleighLanguage language;
	public CvmInjectPayload(String sourceName, SleighLanguage language, long uniqBase)
	{
		super(sourceName);
		this.setLanguage(language);
		this.uniqBase = uniqBase;
	}
	@Override
	public boolean isEquivalent(InjectPayload obj) 
	{
		if (this.getClass() != obj.getClass()) 
			return false;
		
		CvmInjectPayload op2 = (CvmInjectPayload) obj;
		if (uniqBase != op2.uniqBase)
			return false;
		return super.isEquivalent(obj);
	}
	public SleighLanguage getLanguage() 
	{
		return language;
	}
	public void setLanguage(SleighLanguage language) 
	{
		this.language = language;
	}
	

}
