
package org.rascalmpl.ast;


import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.INode;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.Type;
import org.rascalmpl.interpreter.BooleanEvaluator;
import org.rascalmpl.interpreter.Evaluator;
import org.rascalmpl.interpreter.PatternEvaluator;
import org.rascalmpl.interpreter.asserts.Ambiguous;
import org.rascalmpl.interpreter.env.Environment;
import org.rascalmpl.interpreter.matching.IBooleanResult;
import org.rascalmpl.interpreter.matching.IMatchingResult;
import org.rascalmpl.interpreter.result.Result;


public abstract class Command extends AbstractAST {
  public Command(ISourceLocation loc) {
    super(loc);
  }
  

  public boolean hasDeclaration() {
    return false;
  }

  public org.rascalmpl.ast.Declaration getDeclaration() {
    throw new UnsupportedOperationException();
  }

  public boolean hasExpression() {
    return false;
  }

  public org.rascalmpl.ast.Expression getExpression() {
    throw new UnsupportedOperationException();
  }

  public boolean hasCommand() {
    return false;
  }

  public org.rascalmpl.ast.ShellCommand getCommand() {
    throw new UnsupportedOperationException();
  }

  public boolean hasStatement() {
    return false;
  }

  public org.rascalmpl.ast.Statement getStatement() {
    throw new UnsupportedOperationException();
  }

  public boolean hasImported() {
    return false;
  }

  public org.rascalmpl.ast.Import getImported() {
    throw new UnsupportedOperationException();
  }


static public class Ambiguity extends Command {
  private final java.util.List<org.rascalmpl.ast.Command> alternatives;

  public Ambiguity(ISourceLocation loc, java.util.List<org.rascalmpl.ast.Command> alternatives) {
    super(loc);
    this.alternatives = java.util.Collections.unmodifiableList(alternatives);
  }

  @Override
  public Result<IValue> interpret(Evaluator __eval) {
    throw new Ambiguous((IConstructor) this.getTree());
  }
  
  @Override
  public Type typeOf(Environment env) {
    throw new Ambiguous((IConstructor) this.getTree());
  }
  
  @Override
  public IBooleanResult buildBooleanBacktracker(BooleanEvaluator __eval) {
    throw new Ambiguous((IConstructor) this.getTree());
  }

  @Override
  public IMatchingResult buildMatcher(PatternEvaluator __eval) {
    throw new Ambiguous((IConstructor) this.getTree());
  }
  
  public java.util.List<org.rascalmpl.ast.Command> getAlternatives() {
   return alternatives;
  }

  public <T> T accept(IASTVisitor<T> v) {
	return v.visitCommandAmbiguity(this);
  }
}





  public boolean isShell() {
    return false;
  }
  
static public class Shell extends Command {
  // Production: sig("Shell",[arg("org.rascalmpl.ast.ShellCommand","command")])

  
     private final org.rascalmpl.ast.ShellCommand command;
  

  
public Shell(ISourceLocation loc, org.rascalmpl.ast.ShellCommand command) {
  super(loc);
  
    this.command = command;
  
}


  @Override
  public boolean isShell() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitCommandShell(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.ShellCommand getCommand() {
        return this.command;
     }
     
     @Override
     public boolean hasCommand() {
        return true;
     }
  	
}


  public boolean isImport() {
    return false;
  }
  
static public class Import extends Command {
  // Production: sig("Import",[arg("org.rascalmpl.ast.Import","imported")])

  
     private final org.rascalmpl.ast.Import imported;
  

  
public Import(ISourceLocation loc, org.rascalmpl.ast.Import imported) {
  super(loc);
  
    this.imported = imported;
  
}


  @Override
  public boolean isImport() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitCommandImport(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Import getImported() {
        return this.imported;
     }
     
     @Override
     public boolean hasImported() {
        return true;
     }
  	
}


  public boolean isExpression() {
    return false;
  }
  
static public class Expression extends Command {
  // Production: sig("Expression",[arg("org.rascalmpl.ast.Expression","expression")])

  
     private final org.rascalmpl.ast.Expression expression;
  

  
public Expression(ISourceLocation loc, org.rascalmpl.ast.Expression expression) {
  super(loc);
  
    this.expression = expression;
  
}


  @Override
  public boolean isExpression() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitCommandExpression(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Expression getExpression() {
        return this.expression;
     }
     
     @Override
     public boolean hasExpression() {
        return true;
     }
  	
}


  public boolean isStatement() {
    return false;
  }
  
static public class Statement extends Command {
  // Production: sig("Statement",[arg("org.rascalmpl.ast.Statement","statement")])

  
     private final org.rascalmpl.ast.Statement statement;
  

  
public Statement(ISourceLocation loc, org.rascalmpl.ast.Statement statement) {
  super(loc);
  
    this.statement = statement;
  
}


  @Override
  public boolean isStatement() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitCommandStatement(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Statement getStatement() {
        return this.statement;
     }
     
     @Override
     public boolean hasStatement() {
        return true;
     }
  	
}


  public boolean isDeclaration() {
    return false;
  }
  
static public class Declaration extends Command {
  // Production: sig("Declaration",[arg("org.rascalmpl.ast.Declaration","declaration")])

  
     private final org.rascalmpl.ast.Declaration declaration;
  

  
public Declaration(ISourceLocation loc, org.rascalmpl.ast.Declaration declaration) {
  super(loc);
  
    this.declaration = declaration;
  
}


  @Override
  public boolean isDeclaration() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitCommandDeclaration(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Declaration getDeclaration() {
        return this.declaration;
     }
     
     @Override
     public boolean hasDeclaration() {
        return true;
     }
  	
}



}
