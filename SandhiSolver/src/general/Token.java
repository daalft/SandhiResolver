package general;

/**
 * Represents a simple token with a type and content
 * @author David
 *
 */
public class Token {

	/////////////////////////////////////////////////
	// Variables
	/////////////////////////////////////////////////
	
	public enum Type {
		
		/////////////////////////////////////////////////
		// Enum types
		/////////////////////////////////////////////////
		
		DELIMITER(0),
		CONTENT(1),
		KEYWORD(2);
		
		/////////////////////////////////////////////////
		// Variables
		/////////////////////////////////////////////////
		
		private final int inttype;
		
		/////////////////////////////////////////////////
		// Constructors
		/////////////////////////////////////////////////
		
		Type(int type) {
			inttype = type;
		}
		
		/////////////////////////////////////////////////
		// Methods
		/////////////////////////////////////////////////
		
		public int getInternalType () {
			return inttype;
		}
	}
	
	private Type type;
	private String content;
	
	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////
	
	public Token() {
		
	}
	
	public Token(String s) {
		content = s;
	}
	
	public Token(String s, Type i) {
		this(s);
		type = i;
	}
	
	/////////////////////////////////////////////////
	// Methods
	/////////////////////////////////////////////////
	
	public void setType(Type i) {
		type = i;
	}
	
	public void setContent(String s) {
		content = s;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return content + "[" + type + "]";
	}
}
