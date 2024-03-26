package cvmdecompiler;

import java.util.HashMap;

import ghidra.app.plugin.processors.sleigh.SleighLanguage;
import ghidra.program.model.lang.InjectPayload;
import ghidra.program.model.lang.PcodeInjectLibrary;

public class CvmPcodeInjectLibrary extends PcodeInjectLibrary
{
	private HashMap<String, InjectPayload> payloads = new HashMap<>();
	public CvmPcodeInjectLibrary(SleighLanguage l) 
	{
		super(l);
		payloads.put("vmcall", new CvmCallInjectPayload("cvm_internals", l, 0));
	}

	@Override
	public InjectPayload getPayload(int type, String name)
	{
		if (type == InjectPayload.CALLMECHANISM_TYPE)
			return null;
		if(!payloads.containsKey(name))
			return super.getPayload(type, name);
		
		return payloads.get(name);
		
	}

}
