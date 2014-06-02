package org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.Instructions;

import org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.CodeBlock;
import org.rascalmpl.library.experiments.Compiler.RVM.ToJVM.BytecodeGenerator;

public class LoadVarRef extends Instruction {

	final String fuid;
	final int pos;

	public LoadVarRef(CodeBlock ins, String fuid, int pos) {
		super(ins, Opcode.LOADVARREF);
		this.fuid = fuid;
		this.pos = pos;
	}

	public String toString() {
		return "LOADVARREF " + fuid + " [ " + codeblock.getFunctionIndex(fuid) + " ] " + ", " + pos;
	}

	public void generate(BytecodeGenerator codeEmittor, boolean dcode) {

		int what = (pos == -1) ? codeblock.getConstantIndex(codeblock.vf.string(fuid)) : codeblock.getFunctionIndex(fuid) ;
		
		codeEmittor.emitCall("insnLOADVARREF", what, pos, CodeBlock.isMaxArg2(pos));
		
		codeblock.addCode2(opcode.getOpcode(), what, pos);
	}
}
