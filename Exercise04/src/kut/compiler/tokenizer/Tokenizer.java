package kut.compiler.tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * @author hnishino
 * 字句解析を行う。
 * 結果はVector<Token>として保持する。
 */
public class Tokenizer {
	
	Vector<Character>	inputProgram;	//解析するプログラム
	int					ridx		;	//現在読んでいる文字の位置
	
	Vector<Token> 		tokens		;	//トークン列（内部保持）
	int					tokenIndex	;	//次に読みだすトークンの位置
	
	
	/**
	 * 
	 */
	private Tokenizer() 
	{
		inputProgram = new Vector<Character>();
		ridx = 0;
		
		tokens = new Vector<Token>();
		tokenIndex = 0;
		
		return;
	}
	
	/**
	 * @param fliename
	 * @throws IOException
	 */
	public Tokenizer(String fliename) throws IOException
	{
		this(new File(fliename));
	}
	/**
	 * 
	 */
	public Tokenizer(File file) throws IOException
	{
		this();
		//check if the file exists
		if (file.exists() == false) {
			throw new FileNotFoundException("input file can't be found. filename:" + file.getAbsolutePath() );
		}
		
		FileReader fis = new FileReader(file);
		
		while(true) {
			int c = fis.read();
			if (c == -1) {
				break;
			}
			inputProgram.add((char)c);
		}
		
		fis.close();
		
		return;
	}
	
	
	/**
	 * 
	 */
	public void tokenize()
	{
		//字句解析のループ
		while(true) {
			
			//現在の文字を読む
			int c = this.peekChar();
			
			//空白文字だったらスキップ
			if (Character.isSpaceChar(c)) {
				this.consumeChar(); //現在の文字を消費
				continue;
			}
			
			//入力文字がもうなかったらbreakしておしまい
			if (c == -1) {
				break;
			}

			//もし現在の文字が数字だったら、整数トークンとして処理
			if (Character.isDigit(c)) {
				//整数トークンを作る。
				Token t = tokenizeIntLiteral();
				tokens.add(t);
				
				//ループ先頭に戻る
				continue;
			}
			
			if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%') {
				Token t = new Token(c, "" + (char)c);
				tokens.add(t);
				this.consumeChar();
				continue;
			}

			//ここにきてたらErrorトークン。
			Token t = new Token(Token.ERROR, "" + (char)c);
			tokens.add(t);
			this.consumeChar(); //現在の文字を消費
		}
		
		return;
	}
	

	/**
	 * 整数トークンを作る
	 * @return　整数トークン
	 */
	public Token tokenizeIntLiteral() {
	
		StringBuffer buf = new StringBuffer();
		while(true) {
			//現在の文字を読む
			int c = this.peekChar();
			
			//EOFであれば打ち切り
			if (c == -1) {
				break;
			}
			
			//数字以外であれば打ち切り
			if (Character.isDigit(c) == false){
				break;
			}
			
			//読み込んだ数字をバッファに追加
			buf.append((char)c);
			this.consumeChar(); //現在の文字を消費
		}
		
		return new Token(Token.INT_LITERAL, buf.toString());
	}

	
	/**
	 * @return
	 */
	private int peekChar() {
		if (ridx >= inputProgram.size()) {
			return -1;
		}
		
		int c = inputProgram.elementAt(ridx);
		
		return c;
	}
	
	/**
	 * 
	 */
	private void consumeChar() {
		if (ridx >= inputProgram.size()) {
			return;
		}
		ridx++;
		return;
	}
	
	/**
	 * @return the current token.
	 */
	public Token peekToken()
	{
		//もうトークンがなかったらEOFトークンを返す
		if (tokenIndex >= tokens.size()) {
			return new Token(Token.EOF, null);
		}
		
		//次のトークンを返す
		Token token = tokens.elementAt(tokenIndex);
		return token;
	}
	
	/**
	 * consume token.
	 */
	public void consumeToken()
	{
		if (tokenIndex >= tokens.size()) {
			return;
		}
		tokenIndex++;
		
		return;
	}

}
