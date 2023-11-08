package kut.compiler.cgen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import kut.compiler.parser.ast.AstNode;


/**
 * @author hnishino
 *
 */
public abstract class CodeGenerator 
{
	protected StringBuffer sb;
	
	
	/**
	 * @return
	 */
	public static boolean isMac() {
		return System.getProperty("os.name").toLowerCase().startsWith("mac");
	}
	
	/**
	 * @return
	 */
	public static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}

	
	/**
	 * @return
	 */
	public static CodeGenerator getCodeGenerator()
	{
		if (isMac()) {
			return new MacCodeGenerator();
		}
		else if (isWindows()) {
			return new WinCodeGenerator();
		}
		
		throw new RuntimeException("This OS is not supported.");
	}


	
	/**
	 * 
	 */
	protected CodeGenerator() {
		this.sb = new StringBuffer();
	}
	
	/**
	 * @return
	 */
	public abstract String getEntryPointLabelName();
	
	/**
	 * @return
	 */
	public abstract String getExitSysCallNum();
	
	/**
	 * @param funcname
	 * @return
	 */
	public abstract String getExternalFunctionName(String funcname);
	
	/**
	 * @return
	 */
	public String getExitSysCallLabel()
	{
		return "exit_program#";
	}
	
	/**
	 * @param code
	 */
	public void appendCode(String code) 
	{
		this.appendCode(code, 1);
	}
	
	/**
	 * @param code
	 * @param indent
	 */
	public void appendCode(String code, int indent)
	{
		for (int i = 0; i < indent; i++) {
			sb.append("\t");			
		}
		sb.append(code);
		sb.append("\n");
	}
	
	/**
	 * 
	 */
	public void appendCode()
	{
		sb.append("\n");
	}
	
	/**
	 * @param label
	 */
	public void appendLabel(String label)
	{
		sb.append(label);
		sb.append(":\n");
	}
	
	
	/**
	 * @param fname
	 * @throws IOException
	 */
	public void write(String fname) throws IOException
	{
		File f = new File(fname);
		FileOutputStream fos = new FileOutputStream(f);
		PrintWriter	pw = new PrintWriter(fos);
		
		pw.write(sb.toString());
		
		pw.close();
		
	}
	
	/**
	 * 
	 */
	public void print() {
		System.out.print(sb);
	}
	
	/**
	 * @param program
	 */
	public void cgen(AstNode program)
	{
		this.cgenPrologue();
		program.cgen(this);
		this.cgenEpilogue();
		return;
	}
	
	/**
	 * 
	 */
	public void cgenPrologue()
	{
		//--------------------------------------
		//extern 
		this.appendCode		("; 64 bit code.", 0);
		this.appendCode		("bits 64", 0);
		
		//--------------------------------------
		//extern 
		this.appendCode		("; to use the printf() function.");

		this.appendCode		("extern " + this.getExternalFunctionName("printf"), 0);
		this.appendCode		();
		
		//--------------------------------------
		//data section
		this.appendCode		("; data section.", 0);
		this.appendCode		("section .data", 0);
		
		this.appendCode		(	"fmt:    db \"exit code:%d\", 10, 0 ; the format string for the exit message.");

		this.appendCode		();

		//--------------------------------------
		//text section
		this.appendCode		("; text section", 0);
		this.appendCode		("section .text", 0);
		this.appendCode		(	"global " + this.getEntryPointLabelName() + " ; the entry point.");
		this.appendCode		();
		
		
		//the exit_program subroutine.		
		this.appendCode		("; the subroutine for sys-exit. rax will be the exit code.", 0);
		this.appendLabel	(this.getExitSysCallLabel());				// where we exit the program.
		
		this.appendCode		("and rsp, 0xFFFFFFFFFFFFFFF0 ; stack must be 16 bytes aligned to call a C function.");
		this.appendCode		("push rax ; we need to preserve rax here.");
		this.appendCode		("push rax ; pushing twice for 16 byte alignment. We'll discard this later. ");
		this.appendCode		();
		this.appendCode		("; call printf to print out the exit code.");
		this.appendCode		("lea rdi, [rel fmt] ; the format string");
		this.appendCode		("mov rsi, rax		; the exit code ");
		this.appendCode		("mov rax, 0			; no xmm register is used.");
		this.appendCode		("call " + this.getExternalFunctionName("printf"));
		this.appendCode		();
		this.appendCode		("pop rax ; this value will be discared (as we did 'push rax' twice for 16 bytes alignment.");
		this.appendCode		();
		this.appendCode		("mov rax, "+ this.getExitSysCallNum() + "; specify the exit sys call.");
		this.appendCode		("pop rdi ; this is the rax value we pushed at the entry of this sub routine");
		this.appendCode		("syscall ; exit!");
		this.appendCode		();
		
		//main function
		this.appendLabel	(this.getEntryPointLabelName());
		this.appendCode		("mov rax, 0 ; initialize the accumulator register.");
	}
	
	
	/**
	 * 
	 */
	public void cgenEpilogue()
	{
		this.appendCode		();
		this.appendCode		(	"jmp " + this.getExitSysCallLabel() + " ; exit the program, rax should hold the exit code.");		
	}
	
	
}
