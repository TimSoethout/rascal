package org.rascalmpl.interpreter.matching;

import java.util.Collections;
import java.util.List;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.Type;
import org.rascalmpl.ast.Expression.CallOrTree;
import org.rascalmpl.interpreter.IEvaluatorContext;
import org.rascalmpl.interpreter.asserts.ImplementationError;
import org.rascalmpl.interpreter.env.Environment;
import org.rascalmpl.interpreter.result.Result;
import org.rascalmpl.interpreter.result.ResultFactory;
import org.rascalmpl.interpreter.types.NonTerminalType;
import org.rascalmpl.interpreter.types.RascalTypeFactory;
import org.rascalmpl.values.uptr.Factory;
import org.rascalmpl.values.uptr.ProductionAdapter;
import org.rascalmpl.values.uptr.SymbolAdapter;
import org.rascalmpl.values.uptr.TreeAdapter;

public class ConcreteOptPattern extends AbstractMatchingResult {
	private enum Opt { Exist, NotExist, MayExist }
	private final Opt type;
	private final IConstructor production;
	private final IMatchingResult optArg;

	public ConcreteOptPattern(IEvaluatorContext ctx, CallOrTree x, List<IMatchingResult> list) {
		super(ctx, x);
		
		// retrieve the static value of the production of this pattern
		this.production = getAST()._getProduction();
		
		if (list.size() == 0) {
			type = Opt.NotExist;
			optArg = null;
		}
		else if (list.size() == 1) {
			optArg = list.get(0);
			NonTerminalType nont = (NonTerminalType) optArg.getType(ctx.getCurrentEnvt());
			if (SymbolAdapter.isOpt(nont.getSymbol())) {
				// I think this can only happen when a variable of type opt is there
				type = Opt.MayExist;
			}
			else {
				type = Opt.Exist;
			}
		}
		else {
			throw new ImplementationError("optional with more than one element???", x.getLocation());
		}
	}

	@Override
	public void initMatch(Result<IValue> subject) {
		super.initMatch(subject);
		
		if (!subject.getType().isSubtypeOf(Factory.Tree)) {
			hasNext = false;
			return;
		}
		IConstructor tree = (IConstructor) subject.getValue();
		
		if (tree.getConstructorType() != Factory.Tree_Appl) {
			hasNext = false;
			return;
		}
		
		IConstructor prod = TreeAdapter.getProduction(tree);
		if (!prod.isEqual(production)) {
			hasNext = false;
			return;
		}
		
		IList args = TreeAdapter.getArgs(tree);
		
		switch (type) {
		case MayExist:
			optArg.initMatch(subject);
			hasNext = optArg.hasNext();
			return;
		case Exist:
			if (args.length() == 1) {
				IConstructor arg = (IConstructor) args.get(0);
				Type argType = RascalTypeFactory.getInstance().nonTerminalType(arg);
				Result<IValue> argResult = ResultFactory.makeResult(argType, arg, ctx);
				optArg.initMatch(argResult);
				hasNext = optArg.hasNext();
				return;
			}
			
			hasNext = false;
			return;
		case NotExist:
			hasNext = args.length() == 0;
			return;
		}
		
	}
	
	@Override
	public Type getType(Environment env) {
		return RascalTypeFactory.getInstance().nonTerminalType(ProductionAdapter.getRhs(production));
	}

	@Override
	public boolean hasNext() {
		if (!hasNext) {
			return false;
		}
		
		switch (type) {
		case MayExist:
			return optArg.hasNext();
		case Exist:
			return optArg.hasNext();
		case NotExist:
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public boolean next() {
		if (!hasNext()) {
			return false;
		}
		
		if (optArg != null) {
			return optArg.next();
		}
		
		hasNext = false;
		return true;
	}

	@Override
	public java.util.List<String> getVariables() {
		if (optArg != null) {
			return optArg.getVariables();
		}
		
		return Collections.emptyList();
	}
}