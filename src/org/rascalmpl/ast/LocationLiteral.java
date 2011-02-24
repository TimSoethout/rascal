
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


public abstract class LocationLiteral extends AbstractAST {
  public LocationLiteral(ISourceLocation loc) {
    super(loc);
  }
  

  public boolean hasProtocolPart() {
    return false;
  }

  public org.rascalmpl.ast.ProtocolPart getProtocolPart() {
    throw new UnsupportedOperationException();
  }

  public boolean hasPathPart() {
    return false;
  }

  public org.rascalmpl.ast.PathPart getPathPart() {
    throw new UnsupportedOperationException();
  }


static public class Ambiguity extends LocationLiteral {
  private final java.util.List<org.rascalmpl.ast.LocationLiteral> alternatives;

  public Ambiguity(ISourceLocation loc, java.util.List<org.rascalmpl.ast.LocationLiteral> alternatives) {
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
  
  public java.util.List<org.rascalmpl.ast.LocationLiteral> getAlternatives() {
   return alternatives;
  }

  public <T> T accept(IASTVisitor<T> v) {
	return v.visitLocationLiteralAmbiguity(this);
  }
}





  public boolean isDefault() {
    return false;
  }
  
static public class Default extends LocationLiteral {
  // Production: sig("Default",[arg("org.rascalmpl.ast.ProtocolPart","protocolPart"),arg("org.rascalmpl.ast.PathPart","pathPart")])

  
     private final org.rascalmpl.ast.ProtocolPart protocolPart;
  
     private final org.rascalmpl.ast.PathPart pathPart;
  

  
public Default(ISourceLocation loc, org.rascalmpl.ast.ProtocolPart protocolPart,  org.rascalmpl.ast.PathPart pathPart) {
  super(loc);
  
    this.protocolPart = protocolPart;
  
    this.pathPart = pathPart;
  
}


  @Override
  public boolean isDefault() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitLocationLiteralDefault(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.ProtocolPart getProtocolPart() {
        return this.protocolPart;
     }
     
     @Override
     public boolean hasProtocolPart() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.PathPart getPathPart() {
        return this.pathPart;
     }
     
     @Override
     public boolean hasPathPart() {
        return true;
     }
  	
}



}
