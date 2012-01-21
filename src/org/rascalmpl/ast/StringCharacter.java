/*******************************************************************************
 * Copyright (c) 2009-2011 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Tijs van der Storm - Tijs.van.der.Storm@cwi.nl
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Mark Hills - Mark.Hills@cwi.nl (CWI)
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
 *******************************************************************************/
package org.rascalmpl.ast;


import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.interpreter.Evaluator;
import org.rascalmpl.interpreter.asserts.Ambiguous;
import org.rascalmpl.interpreter.env.Environment;
import org.rascalmpl.interpreter.result.Result;

public abstract class StringCharacter extends AbstractAST {
  public StringCharacter(IConstructor node) {
    super();
  }

  

  static public class Ambiguity extends StringCharacter {
    private final java.util.List<org.rascalmpl.ast.StringCharacter> alternatives;
    private final IConstructor node;
           
    public Ambiguity(IConstructor node, java.util.List<org.rascalmpl.ast.StringCharacter> alternatives) {
      super(node);
      this.node = node;
      this.alternatives = java.util.Collections.unmodifiableList(alternatives);
    }
    
    @Override
    public IConstructor getTree() {
      return node;
    }
  
    @Override
    public AbstractAST findNode(int offset) {
      return null;
    }
  
    @Override
    public Result<IValue> interpret(Evaluator __eval) {
      throw new Ambiguous(node);
    }
      
    @Override
    public org.eclipse.imp.pdb.facts.type.Type typeOf(Environment env) {
      throw new Ambiguous(node);
    }
    
    public java.util.List<org.rascalmpl.ast.StringCharacter> getAlternatives() {
      return alternatives;
    }
    
    public <T> T accept(IASTVisitor<T> v) {
    	return v.visitStringCharacterAmbiguity(this);
    }
  }

  static public class Lexical extends StringCharacter {
  private final java.lang.String string;
  public Lexical(IConstructor node, java.lang.String string) {
    super(node);
    this.string = string;
  }
  public java.lang.String getString() {
    return string;
  }

  @Override
  public AbstractAST findNode(int offset) {
    if (src.getOffset() <= offset && offset < src.getOffset() + src.getLength()) {
      return this;
    }
    return null;
  }

  public java.lang.String toString() {
    return string;
  }
  public <T> T accept(IASTVisitor<T> v) {
    return v.visitStringCharacterLexical(this);
  }
}

  
}