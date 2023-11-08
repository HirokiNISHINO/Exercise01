package kut.compiler.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import kut.compiler.tokenizer.Token;
import kut.compiler.tokenizer.Tokenizer;

/**
 * @author hnishino
 *
 */
class TokenizerTest01 {

	@Test
	void noToken() throws Exception {
		//tokenize!
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode01.min");
		tokenizer.tokenize();

		Token tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.EOF) {
			fail("expected an EOF token, but received a different token (" + tkn + ")");			
		}
	}
	
	@Test
	void oneIntLiteralToken() throws Exception {
		//tokenize!
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode02.min");
		tokenizer.tokenize();
				
		Token tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");			
		}
		if ("1234567890".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:1234567890 , but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();
		
		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.EOF) {
			fail("expected an EOF token, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();
	}

	@Test
	void twoIntLiteralTokens() throws Exception {
		//tokenize!
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode03.min");
		tokenizer.tokenize();
		
		Token tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");			
		}
		if ("1234567890".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:1234567890, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();


		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");			
		}
		if ("0987654321".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:0987654321, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();


		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.EOF) {
			fail("expected an EOF token, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();

	}

	@Test
	void threeIntLiteralTokens() throws Exception {
		//tokenize!
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode04.min");
		tokenizer.tokenize();
		
		Token tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");			
		}
		if ("1234567890".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:0123456789, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();


		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");			
		}
		if ("0987654321".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:0987654321, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();


		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");			
		}
		if ("1029384756".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:1029384756, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();

		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.EOF) {
			fail("expected an EOF token, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();
	}
	
	@Test
	void expr01() throws Exception {
		
		//tokenize!
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode05.min");
		tokenizer.tokenize();
		
		Token tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");						
		}
		if ("1".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:1, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();
		
		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != '+') {
			fail("expected an '+' token, but received a different token (" + tkn + ")");									
		}
		tokenizer.consumeToken();
		
		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.INT_LITERAL) {
			fail("expected an INT_LITERAL token, but received a different token (" + tkn + ")");						
		}
		if ("2".equals(tkn.getLexeme()) == false) {
			fail("expected an INT_LITERAL:2, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();

		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.EOF) {
			fail("expected an EOF token, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();

	}
	
	/**
	 * @throws Exception
	 */
	@Test
	void opcode() throws Exception {
		//tokenize!
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode06.min");
		tokenizer.tokenize();
		
		Token tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != '+') {
			fail("expected an '+' token, but received a different token (" + tkn + ")");									
		}
		tokenizer.consumeToken();

		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != '-') {
			fail("expected an '-' token, but received a different token (" + tkn + ")");									
		}
		tokenizer.consumeToken();

		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != '*') {
			fail("expected an '*' token, but received a different token (" + tkn + ")");									
		}
		tokenizer.consumeToken();

		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != '/') {
			fail("expected an '/' token, but received a different token (" + tkn + ")");									
		}
		tokenizer.consumeToken();

		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != '%') {
			fail("expected an '%' token, but received a different token (" + tkn + ")");									
		}
		tokenizer.consumeToken();
		
		tkn = tokenizer.peekToken();
		if (tkn.getKlazz() != Token.EOF) {
			fail("expected an EOF token, but received a different token (" + tkn + ")");			
		}
		tokenizer.consumeToken();

	}
}
