package kut.compiler.parser;

import kut.compiler.error.SyntaxErrorException;
import kut.compiler.parser.ast.AstBinOp;
import kut.compiler.parser.ast.AstIntLiteral;
import kut.compiler.parser.ast.AstNode;
import kut.compiler.tokenizer.Token;
import kut.compiler.tokenizer.Tokenizer;

/**
 * @author hnishino
 *
 */
public class Parser {

	AstNode 	rootNode	;
	Tokenizer	tokenizer	;

	/**
	 * 
	 */
	public Parser(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
		this.rootNode = null;
	}

	
	/**
	 * 
	 */
	public void parse() throws SyntaxErrorException 
	{
		tokenizer.tokenize();

		Token token = this.tokenizer.peekToken();
		if (token.getKlazz() == Token.EOF) {
			return;
		}
		
		this.rootNode =  this.expr();
		
		token = this.tokenizer.peekToken();
		if (token.getKlazz() != Token.EOF) {
			throw new SyntaxErrorException("expecting an EOF token, but found :" + token);
		}
				
		return;
	}
	
	/**
	 * @return
	 */
	public String getTreeString() {
		if (this.rootNode == null) {
			return "";
		}
		return this.rootNode.getTreeString(0);
	}
	
	
	/**
	 * @return
	 * @throws SyntaxErrorException
	 */
	public AstNode expr() throws SyntaxErrorException
	{
		return this.parseAdditiveExpr();
	}
	
	/**
	 * @return
	 * @throws SyntaxErrorException
	 */
	public AstNode parseAdditiveExpr() throws SyntaxErrorException
	{
		AstNode lhs = this.parseMultiplicativeExpr();
		
		while(true) {
			Token token = this.tokenizer.peekToken();
			if (token.getKlazz() == Token.EOF) {
				break;
			}

			if (token.getKlazz() != '+' && token.getKlazz() != '-') {
				throw new SyntaxErrorException("expecting a '+' | '-' token, but found :" + token);			
			}
			this.tokenizer.consumeToken();
			
			AstNode rhs = this.parseMultiplicativeExpr();
			lhs = new AstBinOp(token, lhs, rhs);
		}
		
		return lhs;
	}
	
	/**
	 * @return
	 * @throws SyntaxErrorException
	 */
	public AstNode parseMultiplicativeExpr() throws SyntaxErrorException
	{
		AstNode lhs = this.parseIntLiteral();
		
		while(true) {
			Token token = this.tokenizer.peekToken();
			if (token.getKlazz() == Token.EOF) {
				break;
			}

			if (token.getKlazz() != '*' && token.getKlazz() != '/' && token.getKlazz() != '%') {
				break;		
			}
			this.tokenizer.consumeToken();
			
			AstNode rhs = this.parseIntLiteral();
			lhs = new AstBinOp(token, lhs, rhs);
		}
		
		return lhs;
	}
	
	/**
	 * @return
	 * @throws SyntaxErrorException
	 */
	public AstNode parsePrimaryExpression() throws SyntaxErrorException {
		return this.parseIntLiteral();
	}
	
	/**
	 * @return
	 */
	public AstIntLiteral parseIntLiteral() throws SyntaxErrorException {
		Token token = this.tokenizer.peekToken();
		if (token.getKlazz() != Token.INT_LITERAL) {
			throw new SyntaxErrorException("expecting an INT_LITERAL token, but found :" + token);
		}
		this.tokenizer.consumeToken();
		
		return new AstIntLiteral(token);
	}
}
