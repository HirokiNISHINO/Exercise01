package kut.compiler.parser.ast;

import kut.compiler.tokenizer.Token;

/**
 * @author hnishino
 *
 */
public class AstBinOp extends AstNode
{
	private Token	token;
	private AstNode lhs;
	private AstNode rhs;
	
	
	/**
	 * 
	 */
	public AstBinOp(Token token, AstNode lhs, AstNode rhs) {
		this.token 	= token;
		this.lhs 	= lhs;
		this.rhs 	= rhs;
	}
	

	
	/**
	 *
	 */
	@Override
	public String getTreeString(int indent) {
		String r 	=  this.indent(indent, "AstBinOp:" + this.token.getLexeme())
					+ lhs.getTreeString(indent + 1) 
					+ rhs.getTreeString(indent + 1);
		
		return r;
	}

}
