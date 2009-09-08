package org.meta_environment.rascal.ast; 
import org.eclipse.imp.pdb.facts.INode; 
public abstract class PostPathChars extends AbstractAST { 
static public class Lexical extends PostPathChars {
	private String string;
         public Lexical(INode node, String string) {
		this.node = node;
		this.string = string;
	}
	public String getString() {
		return string;
	}

 	public <T> T accept(IASTVisitor<T> v) {
     		return v.visitPostPathCharsLexical(this);
  	}
}
static public class Ambiguity extends PostPathChars {
  private final java.util.List<org.meta_environment.rascal.ast.PostPathChars> alternatives;
  public Ambiguity(INode node, java.util.List<org.meta_environment.rascal.ast.PostPathChars> alternatives) {
	this.alternatives = java.util.Collections.unmodifiableList(alternatives);
         this.node = node;
  }
  public java.util.List<org.meta_environment.rascal.ast.PostPathChars> getAlternatives() {
	return alternatives;
  }
  
  public <T> T accept(IASTVisitor<T> v) {
     return v.visitPostPathCharsAmbiguity(this);
  }
}
}