package kut.compiler.tokenizer;

public class Token
{
	public static final int	EOF	 	= -1;
	public static final int	ERROR 	= -2;
	public static final int	INT_LITERAL	= -3;
	
	private int 	klazz;	// トークン・クラス
	private String	lexeme; // 語彙素
	
	/**
	 * @param klazz
	 * @param lexeme
	 */
	public Token(int klazz, String lexeme)
	{
		this.klazz 	= klazz;
		this.lexeme = lexeme;
	}
	

	/**
	 * @return トークン・クラスを返す。
	 */
	public int getKlazz() {
		return this.klazz;
	}
	
	/**
	 * @return 語彙素を返す。
	 */
	public String getLexeme() {
		return this.lexeme;
	}
	
	/**
	 *
	 */
	public String toString() {
		return this.getTokenClassString() + ":" + this.lexeme;
	}
	
	/**
	 * @return
	 */
	private String getTokenClassString() {
		
		String s;
		switch(this.klazz) {
		case EOF:
			s = "EOF";
			break;
			
		case ERROR:
			s = "ERROR";
			break;
			
		case INT_LITERAL:
			s = "INT_LITERAL";
			break;
		
		default:
			s = "" + (char)this.klazz;
			break;
		}
		
		return s;
	}
}
