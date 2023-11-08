package kut.compiler.parser.ast;


/**
 * @author hnishino
 *
 */
public class AstNode 
{

	/**
	 * 
	 */
	public AstNode() {
		//do nothing here.
	}
	
	
	
	/**
	 * @param indent 
	 * @return
	 */
	public String getTreeString(int indent){
		throw new RuntimeException("not implemented yet! : " + this.getClass().getCanonicalName());
	}
	
	
	/**
	 * @param indent
	 * @param s
	 * @return
	 */
	public String indent(int indent, String s) {
		
		for (int i = 0; i < indent; i++) {
			s = "  " + s;
		}
				
		s += "\n";
		return s;
	}

}
