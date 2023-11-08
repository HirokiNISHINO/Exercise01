package kut.compiler.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import kut.compiler.parser.Parser;
import kut.compiler.tokenizer.Tokenizer;

/**
 * @author hnishino
 *
 */
class ParserTest01 {

	@Test
	void noToken() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode01.min");
		
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if ("".equals(tree) == false) {
			fail("parsing failed.");
		}
		
	}
	
	@Test
	void oneIntLiteralToken() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode07.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if ("IntLiteral:1234567890\n".equals(tree) == false) {
			fail("parsing failed.");
		}
	}

	@Test
	void oneAddExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode08.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:+\n"
				+ "  IntLiteral:12345\n"
				+ "  IntLiteral:67890\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void oneSubExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode09.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:-\n"
				+ "  IntLiteral:12345\n"
				+ "  IntLiteral:67890\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void twoAddExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode10.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:+\n"
				+ "  AstBinOp:+\n"
				+ "    IntLiteral:1\n"
				+ "    IntLiteral:2\n"
				+ "  IntLiteral:3\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void twoSubExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode11.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:-\n"
				+ "  AstBinOp:-\n"
				+ "    IntLiteral:1\n"
				+ "    IntLiteral:2\n"
				+ "  IntLiteral:3\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void addSubMix() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode12.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:-\n"
				+ "  AstBinOp:-\n"
				+ "    AstBinOp:+\n"
				+ "      AstBinOp:-\n"
				+ "        AstBinOp:+\n"
				+ "          AstBinOp:-\n"
				+ "            AstBinOp:+\n"
				+ "              IntLiteral:1\n"
				+ "              IntLiteral:2\n"
				+ "            IntLiteral:3\n"
				+ "          IntLiteral:4\n"
				+ "        IntLiteral:5\n"
				+ "      IntLiteral:6\n"
				+ "    IntLiteral:7\n"
				+ "  IntLiteral:8\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void oneMulExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode13.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:*\n"
				+ "  IntLiteral:1\n"
				+ "  IntLiteral:2\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void oneDivExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode14.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:/\n"
				+ "  IntLiteral:1\n"
				+ "  IntLiteral:2\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void oneModExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode15.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:%\n"
				+ "  IntLiteral:1\n"
				+ "  IntLiteral:2\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void mulDivModExpr() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode16.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:*\n"
				+ "  AstBinOp:/\n"
				+ "    AstBinOp:*\n"
				+ "      AstBinOp:*\n"
				+ "        AstBinOp:%\n"
				+ "          AstBinOp:/\n"
				+ "            AstBinOp:*\n"
				+ "              AstBinOp:%\n"
				+ "                IntLiteral:1\n"
				+ "                IntLiteral:2\n"
				+ "              IntLiteral:3\n"
				+ "            IntLiteral:4\n"
				+ "          IntLiteral:5\n"
				+ "        IntLiteral:6\n"
				+ "      IntLiteral:7\n"
				+ "    IntLiteral:8\n"
				+ "  IntLiteral:9\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void binopMix() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode17.min");
			
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:+\n"
				+ "  AstBinOp:-\n"
				+ "    AstBinOp:+\n"
				+ "      IntLiteral:1\n"
				+ "      AstBinOp:*\n"
				+ "        IntLiteral:2\n"
				+ "        IntLiteral:3\n"
				+ "    AstBinOp:%\n"
				+ "      AstBinOp:/\n"
				+ "        IntLiteral:4\n"
				+ "        IntLiteral:5\n"
				+ "      IntLiteral:6\n"
				+ "  AstBinOp:%\n"
				+ "    AstBinOp:*\n"
				+ "      IntLiteral:7\n"
				+ "      IntLiteral:8\n"
				+ "    IntLiteral:9\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void parentheses1() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode19.min");
		
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:+\n"
				+ "  IntLiteral:9\n"
				+ "  IntLiteral:11\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
	
	@Test
	void parentheses2() throws Exception {
		Tokenizer tokenizer = new Tokenizer("src/TestCaseCode/testcode20.min");
		
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		String tree = parser.getTreeString();
		if (("AstBinOp:-\n"
				+ "  AstBinOp:*\n"
				+ "    AstBinOp:+\n"
				+ "      IntLiteral:9\n"
				+ "      IntLiteral:11\n"
				+ "    IntLiteral:99\n"
				+ "  AstBinOp:*\n"
				+ "    AstBinOp:+\n"
				+ "      IntLiteral:2\n"
				+ "      IntLiteral:3\n"
				+ "    IntLiteral:2\n").equals(tree) == false) {
			fail("parsing failed.");
		}
	}
}
