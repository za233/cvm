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
import java.math.BigInteger;

import ghidra.app.services.AbstractAnalyzer;
import ghidra.app.services.AnalyzerType;
import ghidra.app.util.importer.MessageLog;
import ghidra.framework.options.Options;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.lang.Register;
import ghidra.program.model.listing.ContextChangeException;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Program;
import ghidra.util.exception.CancelledException;

import ghidra.util.task.TaskMonitor;


/**
 * TODO: Provide class-level documentation that describes what this analyzer does.
 */
public class CvmDecompilerAnalyzer extends AbstractAnalyzer
{

	public CvmDecompilerAnalyzer()
	{

		super("Cvm Function Analyzer", "analyzer for cvm functions", AnalyzerType.FUNCTION_ANALYZER);
	}

	@Override
	public boolean getDefaultEnablement(Program program)
	{

		// TODO: Return true if analyzer should be enabled by default

		return true;
	}

	@Override
	public boolean canAnalyze(Program program) 
	{

		// TODO: Examine 'program' to determine of this analyzer should analyze it.  Return true
		// if it can.
		return true;
	}

	@Override
	public void registerOptions(Options options, Program program) 
	{

	}

	@Override
	public boolean added(Program program, AddressSetView set, TaskMonitor monitor, MessageLog log)
			throws CancelledException
	{

		// TODO: Perform analysis when things get added to the 'program'.  Return true if the
		// analysis succeeded.
		for(Function func : program.getFunctionManager().getFunctions(set, true))
		{
			Register top = program.getRegister("top");
			Address addr = func.getEntryPoint();
			try
			{
				program.getProgramContext().setValue(top, addr, addr, BigInteger.valueOf(0));
			} catch (ContextChangeException e) 
			{
				e.printStackTrace();
			}
			
				
			
		}
		return true;
	}
}
