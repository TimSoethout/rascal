
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


public abstract class Declaration extends AbstractAST {
  public Declaration(ISourceLocation loc) {
    super(loc);
  }
  

  public boolean hasVisibility() {
    return false;
  }

  public org.rascalmpl.ast.Visibility getVisibility() {
    throw new UnsupportedOperationException();
  }

  public boolean hasFunctionDeclaration() {
    return false;
  }

  public org.rascalmpl.ast.FunctionDeclaration getFunctionDeclaration() {
    throw new UnsupportedOperationException();
  }

  public boolean hasBase() {
    return false;
  }

  public org.rascalmpl.ast.Type getBase() {
    throw new UnsupportedOperationException();
  }

  public boolean hasPatternAction() {
    return false;
  }

  public org.rascalmpl.ast.PatternWithAction getPatternAction() {
    throw new UnsupportedOperationException();
  }

  public boolean hasAlts() {
    return false;
  }

  public java.util.List<org.rascalmpl.ast.Alternative> getAlts() {
    throw new UnsupportedOperationException();
  }

  public boolean hasView() {
    return false;
  }

  public org.rascalmpl.ast.Name getView() {
    throw new UnsupportedOperationException();
  }

  public boolean hasKind() {
    return false;
  }

  public org.rascalmpl.ast.Kind getKind() {
    throw new UnsupportedOperationException();
  }

  public boolean hasName() {
    return false;
  }

  public org.rascalmpl.ast.Name getName() {
    throw new UnsupportedOperationException();
  }

  public boolean hasAnnoType() {
    return false;
  }

  public org.rascalmpl.ast.Type getAnnoType() {
    throw new UnsupportedOperationException();
  }

  public boolean hasType() {
    return false;
  }

  public org.rascalmpl.ast.Type getType() {
    throw new UnsupportedOperationException();
  }

  public boolean hasTypes() {
    return false;
  }

  public java.util.List<org.rascalmpl.ast.Type> getTypes() {
    throw new UnsupportedOperationException();
  }

  public boolean hasTags() {
    return false;
  }

  public org.rascalmpl.ast.Tags getTags() {
    throw new UnsupportedOperationException();
  }

  public boolean hasVariants() {
    return false;
  }

  public java.util.List<org.rascalmpl.ast.Variant> getVariants() {
    throw new UnsupportedOperationException();
  }

  public boolean hasSuperType() {
    return false;
  }

  public org.rascalmpl.ast.Name getSuperType() {
    throw new UnsupportedOperationException();
  }

  public boolean hasVariables() {
    return false;
  }

  public java.util.List<org.rascalmpl.ast.Variable> getVariables() {
    throw new UnsupportedOperationException();
  }

  public boolean hasUser() {
    return false;
  }

  public org.rascalmpl.ast.UserType getUser() {
    throw new UnsupportedOperationException();
  }

  public boolean hasTest() {
    return false;
  }

  public org.rascalmpl.ast.Test getTest() {
    throw new UnsupportedOperationException();
  }

  public boolean hasOnType() {
    return false;
  }

  public org.rascalmpl.ast.Type getOnType() {
    throw new UnsupportedOperationException();
  }


static public class Ambiguity extends Declaration {
  private final java.util.List<org.rascalmpl.ast.Declaration> alternatives;

  public Ambiguity(ISourceLocation loc, java.util.List<org.rascalmpl.ast.Declaration> alternatives) {
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
  
  public java.util.List<org.rascalmpl.ast.Declaration> getAlternatives() {
   return alternatives;
  }

  public <T> T accept(IASTVisitor<T> v) {
	return v.visitDeclarationAmbiguity(this);
  }
}





  public boolean isAlias() {
    return false;
  }
  
static public class Alias extends Declaration {
  // Production: sig("Alias",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.UserType","user"),arg("org.rascalmpl.ast.Type","base")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.UserType user;
  
     private final org.rascalmpl.ast.Type base;
  

  
public Alias(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.UserType user,  org.rascalmpl.ast.Type base) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.user = user;
  
    this.base = base;
  
}


  @Override
  public boolean isAlias() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationAlias(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.UserType getUser() {
        return this.user;
     }
     
     @Override
     public boolean hasUser() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Type getBase() {
        return this.base;
     }
     
     @Override
     public boolean hasBase() {
        return true;
     }
  	
}


  public boolean isData() {
    return false;
  }
  
static public class Data extends Declaration {
  // Production: sig("Data",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.UserType","user"),arg("java.util.List\<org.rascalmpl.ast.Variant\>","variants")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.UserType user;
  
     private final java.util.List<org.rascalmpl.ast.Variant> variants;
  

  
public Data(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.UserType user,  java.util.List<org.rascalmpl.ast.Variant> variants) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.user = user;
  
    this.variants = variants;
  
}


  @Override
  public boolean isData() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationData(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.UserType getUser() {
        return this.user;
     }
     
     @Override
     public boolean hasUser() {
        return true;
     }
  
     @Override
     public java.util.List<org.rascalmpl.ast.Variant> getVariants() {
        return this.variants;
     }
     
     @Override
     public boolean hasVariants() {
        return true;
     }
  	
}


  public boolean isAnnotation() {
    return false;
  }
  
static public class Annotation extends Declaration {
  // Production: sig("Annotation",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.Type","annoType"),arg("org.rascalmpl.ast.Type","onType"),arg("org.rascalmpl.ast.Name","name")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.Type annoType;
  
     private final org.rascalmpl.ast.Type onType;
  
     private final org.rascalmpl.ast.Name name;
  

  
public Annotation(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.Type annoType,  org.rascalmpl.ast.Type onType,  org.rascalmpl.ast.Name name) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.annoType = annoType;
  
    this.onType = onType;
  
    this.name = name;
  
}


  @Override
  public boolean isAnnotation() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationAnnotation(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Type getAnnoType() {
        return this.annoType;
     }
     
     @Override
     public boolean hasAnnoType() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Type getOnType() {
        return this.onType;
     }
     
     @Override
     public boolean hasOnType() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Name getName() {
        return this.name;
     }
     
     @Override
     public boolean hasName() {
        return true;
     }
  	
}


  public boolean isFunction() {
    return false;
  }
  
static public class Function extends Declaration {
  // Production: sig("Function",[arg("org.rascalmpl.ast.FunctionDeclaration","functionDeclaration")])

  
     private final org.rascalmpl.ast.FunctionDeclaration functionDeclaration;
  

  
public Function(ISourceLocation loc, org.rascalmpl.ast.FunctionDeclaration functionDeclaration) {
  super(loc);
  
    this.functionDeclaration = functionDeclaration;
  
}


  @Override
  public boolean isFunction() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationFunction(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.FunctionDeclaration getFunctionDeclaration() {
        return this.functionDeclaration;
     }
     
     @Override
     public boolean hasFunctionDeclaration() {
        return true;
     }
  	
}


  public boolean isRule() {
    return false;
  }
  
static public class Rule extends Declaration {
  // Production: sig("Rule",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Name","name"),arg("org.rascalmpl.ast.PatternWithAction","patternAction")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Name name;
  
     private final org.rascalmpl.ast.PatternWithAction patternAction;
  

  
public Rule(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Name name,  org.rascalmpl.ast.PatternWithAction patternAction) {
  super(loc);
  
    this.tags = tags;
  
    this.name = name;
  
    this.patternAction = patternAction;
  
}


  @Override
  public boolean isRule() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationRule(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Name getName() {
        return this.name;
     }
     
     @Override
     public boolean hasName() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.PatternWithAction getPatternAction() {
        return this.patternAction;
     }
     
     @Override
     public boolean hasPatternAction() {
        return true;
     }
  	
}


  public boolean isDataAbstract() {
    return false;
  }
  
static public class DataAbstract extends Declaration {
  // Production: sig("DataAbstract",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.UserType","user")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.UserType user;
  

  
public DataAbstract(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.UserType user) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.user = user;
  
}


  @Override
  public boolean isDataAbstract() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationDataAbstract(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.UserType getUser() {
        return this.user;
     }
     
     @Override
     public boolean hasUser() {
        return true;
     }
  	
}


  public boolean isVariable() {
    return false;
  }
  
static public class Variable extends Declaration {
  // Production: sig("Variable",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.Type","type"),arg("java.util.List\<org.rascalmpl.ast.Variable\>","variables")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.Type type;
  
     private final java.util.List<org.rascalmpl.ast.Variable> variables;
  

  
public Variable(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.Type type,  java.util.List<org.rascalmpl.ast.Variable> variables) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.type = type;
  
    this.variables = variables;
  
}


  @Override
  public boolean isVariable() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationVariable(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Type getType() {
        return this.type;
     }
     
     @Override
     public boolean hasType() {
        return true;
     }
  
     @Override
     public java.util.List<org.rascalmpl.ast.Variable> getVariables() {
        return this.variables;
     }
     
     @Override
     public boolean hasVariables() {
        return true;
     }
  	
}


  public boolean isTest() {
    return false;
  }
  
static public class Test extends Declaration {
  // Production: sig("Test",[arg("org.rascalmpl.ast.Test","test")])

  
     private final org.rascalmpl.ast.Test test;
  

  
public Test(ISourceLocation loc, org.rascalmpl.ast.Test test) {
  super(loc);
  
    this.test = test;
  
}


  @Override
  public boolean isTest() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationTest(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Test getTest() {
        return this.test;
     }
     
     @Override
     public boolean hasTest() {
        return true;
     }
  	
}


  public boolean isTag() {
    return false;
  }
  
static public class Tag extends Declaration {
  // Production: sig("Tag",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.Kind","kind"),arg("org.rascalmpl.ast.Name","name"),arg("java.util.List\<org.rascalmpl.ast.Type\>","types")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.Kind kind;
  
     private final org.rascalmpl.ast.Name name;
  
     private final java.util.List<org.rascalmpl.ast.Type> types;
  

  
public Tag(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.Kind kind,  org.rascalmpl.ast.Name name,  java.util.List<org.rascalmpl.ast.Type> types) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.kind = kind;
  
    this.name = name;
  
    this.types = types;
  
}


  @Override
  public boolean isTag() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationTag(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Kind getKind() {
        return this.kind;
     }
     
     @Override
     public boolean hasKind() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Name getName() {
        return this.name;
     }
     
     @Override
     public boolean hasName() {
        return true;
     }
  
     @Override
     public java.util.List<org.rascalmpl.ast.Type> getTypes() {
        return this.types;
     }
     
     @Override
     public boolean hasTypes() {
        return true;
     }
  	
}


  public boolean isView() {
    return false;
  }
  
static public class View extends Declaration {
  // Production: sig("View",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.Visibility","visibility"),arg("org.rascalmpl.ast.Name","view"),arg("org.rascalmpl.ast.Name","superType"),arg("java.util.List\<org.rascalmpl.ast.Alternative\>","alts")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.Visibility visibility;
  
     private final org.rascalmpl.ast.Name view;
  
     private final org.rascalmpl.ast.Name superType;
  
     private final java.util.List<org.rascalmpl.ast.Alternative> alts;
  

  
public View(ISourceLocation loc, org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.Visibility visibility,  org.rascalmpl.ast.Name view,  org.rascalmpl.ast.Name superType,  java.util.List<org.rascalmpl.ast.Alternative> alts) {
  super(loc);
  
    this.tags = tags;
  
    this.visibility = visibility;
  
    this.view = view;
  
    this.superType = superType;
  
    this.alts = alts;
  
}


  @Override
  public boolean isView() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitDeclarationView(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Visibility getVisibility() {
        return this.visibility;
     }
     
     @Override
     public boolean hasVisibility() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Name getView() {
        return this.view;
     }
     
     @Override
     public boolean hasView() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.Name getSuperType() {
        return this.superType;
     }
     
     @Override
     public boolean hasSuperType() {
        return true;
     }
  
     @Override
     public java.util.List<org.rascalmpl.ast.Alternative> getAlts() {
        return this.alts;
     }
     
     @Override
     public boolean hasAlts() {
        return true;
     }
  	
}



}
