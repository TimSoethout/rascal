package org.meta_environment.rascal.ast; 
import org.eclipse.imp.pdb.facts.INode; 
public abstract class FunctionType extends AbstractAST { 
public org.meta_environment.rascal.ast.Type getType() { throw new UnsupportedOperationException(); }
	public java.util.List<org.meta_environment.rascal.ast.TypeArg> getArguments() { throw new UnsupportedOperationException(); }
public boolean hasType() { return false; }
	public boolean hasArguments() { return false; }
public boolean isTypeArguments() { return false; }
static public class TypeArguments extends FunctionType {
/** type:Type "(" arguments:{TypeArg ","}* ")" -> FunctionType {cons("TypeArguments")} */
	public TypeArguments(INode node, org.meta_environment.rascal.ast.Type type, java.util.List<org.meta_environment.rascal.ast.TypeArg> arguments) {
		this.node = node;
		this.type = type;
		this.arguments = arguments;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitFunctionTypeTypeArguments(this);
	}

	public boolean isTypeArguments() { return true; }

	public boolean hasType() { return true; }
	public boolean hasArguments() { return true; }

private final org.meta_environment.rascal.ast.Type type;
	public org.meta_environment.rascal.ast.Type getType() { return type; }
	private final java.util.List<org.meta_environment.rascal.ast.TypeArg> arguments;
	public java.util.List<org.meta_environment.rascal.ast.TypeArg> getArguments() { return arguments; }	
}
static public class Ambiguity extends FunctionType {
  private final java.util.List<org.meta_environment.rascal.ast.FunctionType> alternatives;
  public Ambiguity(INode node, java.util.List<org.meta_environment.rascal.ast.FunctionType> alternatives) {
	this.alternatives = java.util.Collections.unmodifiableList(alternatives);
         this.node = node;
  }
  public java.util.List<org.meta_environment.rascal.ast.FunctionType> getAlternatives() {
	return alternatives;
  }
  
  public <T> T accept(IASTVisitor<T> v) {
     return v.visitFunctionTypeAmbiguity(this);
  }
}
}