package kut.compiler.parser.ast;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import kut.compiler.cgen.CodeGenerator;
import kut.compiler.parser.Parser;
import kut.compiler.test.util.CheckOutput;
import kut.compiler.tokenizer.Tokenizer;

/**
 * @author hnishino
 *
 */
class CompilerTest01 {

	@Test
	void oneIntLiteral() throws Exception {
		
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode21");
	}
	
	@Test
	void oneAdd() throws Exception {
		
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode22");
	}
	
	@Test
	void oneSub() throws Exception {
		
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode23");
	}
	
	@Test
	void oneMul() throws Exception {
		
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode24");
	}

	@Test
	void oneDiv() throws Exception {
		
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode25");
	}
	
	@Test
	void oneMod() throws Exception {
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode26");
	}
	
	@Test
	void expr01() throws Exception {
		String testname = Thread.currentThread().getStackTrace()[1].getMethodName();
		test(testname, "testcode27");
	}

	/**
	 * @param testname
	 * @param codeFilename
	 * @throws Exception
	 */
	void test(String testname, String codeFilename) throws Exception {
		
		String baseFilename = codeFilename + ".";
		String minExt = "min";
		String asmExt = "asm";
		String ansExt = "asm";
		
		String minDir = "src/TestCaseCode/";
		String asmDir = "src/CompilerOutput/";
		String ansDir = "src/CompilerOutput/ans/";

		String minFilename = minDir + baseFilename + minExt;
		String asmFilename = asmDir + baseFilename + asmExt;
		String ansFilename = ansDir + baseFilename + ansExt;
		
		Tokenizer tokenizer = new Tokenizer(minFilename);
		
		Parser parser = new Parser(tokenizer);
		parser.parse();
		
		CodeGenerator generator = CodeGenerator.getCodeGenerator();
		
		AstNode root = parser.getRootNode();
		generator.cgen(root);
		generator.write(asmFilename);
		
		boolean ret = CheckOutput.getDiffString(testname, asmFilename, ansFilename);
		
		if (ret == false) {
			fail("the output asm code doesn't match the answer.");
		}
	}
	
}
