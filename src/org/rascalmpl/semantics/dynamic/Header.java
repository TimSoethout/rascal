package org.rascalmpl.semantics.dynamic;

import java.util.List;

import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.ast.Import;
import org.rascalmpl.ast.ModuleParameters;
import org.rascalmpl.ast.QualifiedName;
import org.rascalmpl.ast.Tags;
import org.rascalmpl.interpreter.Evaluator;
import org.rascalmpl.interpreter.result.Result;

public abstract class Header extends org.rascalmpl.ast.Header {

	static public class Default extends org.rascalmpl.ast.Header.Default {

		public Default(ISourceLocation __param1, Tags __param2, QualifiedName __param3,
				List<Import> __param4) {
			super(__param1, __param2, __param3, __param4);
		}

		@Override
		public Result<IValue> interpret(Evaluator __eval) {
			visitImports(__eval, this.getImports());
			return org.rascalmpl.interpreter.result.ResultFactory.nothing();
		}
		
		public static void visitImports(Evaluator eval, List<Import> imports) {
			for (Import i : imports) {
				i.interpret(eval);
			}
		}

	}

	static public class Parameters extends org.rascalmpl.ast.Header.Parameters {

		public Parameters(ISourceLocation __param1, Tags __param2,
				QualifiedName __param3, ModuleParameters __param4,
				List<Import> __param5) {
			super(__param1, __param2, __param3, __param4, __param5);
		}

		@Override
		public Result<IValue> interpret(Evaluator __eval) {
			org.rascalmpl.semantics.dynamic.Header.Default.visitImports(__eval, this.getImports());
			return org.rascalmpl.interpreter.result.ResultFactory.nothing();
		}

	}

	public Header(ISourceLocation __param1) {
		super(__param1);
	}
}
