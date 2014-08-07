package general;

import java.util.ArrayList;
import java.util.List;

import general.Token.Type;

/**
 * Validates a list of tokens against a type structure
 * @author David
 *
 */
public class TokenValidator {
	
	/////////////////////////////////////////////////
	// Variables
	/////////////////////////////////////////////////
	
	/**
	 * Short notation for type list
	 * @author David
	 *
	 */
	public enum ShortType {
		/**
		 * Short notation
		 */
		CDC(Type.CONTENT, Type.DELIMITER, Type.CONTENT);
		
		/**
		 * Type list
		 */
		private List<Type> typelist;
		
		/**
		 * Constructor
		 * @param types types
		 */
		private ShortType(Type...types) {
			typelist = new ArrayList<Type>();
			for (Type t : types) {
				typelist.add(t);
			}
		}
		
		/**
		 * Returns this short notation type list's expanded type list
		 * @return expanded type list
		 */
		public List<Type> getTypelist () {
			return typelist;
		}
	}
	
	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////
	
	/////////////////////////////////////////////////
	// Methods
	/////////////////////////////////////////////////
	
	/**
	 * Checks whether a given list of tokens has a given Type structure
	 * @param st short notation for type list
	 * @param list token list
	 * @return <b>true</b> if token list has given type list structure, <b>false</b> otherwise
	 */
	public static boolean is (ShortType st, List<Token> list) {
		return is(st.getTypelist(), list);
	}
	
	/**
	 * Checks whether a given list of tokens has a given Type structure
	 * @param typelist type list
	 * @param tokenlist token list
	 * @return <b>true</b> if token list has given type list structure, <b>false</b> otherwise
	 */
	public static boolean is (List<Type> typelist, List<Token> tokenlist) {
		if (typelist.size() != tokenlist.size()) return false;
		for (int i = 0; i < tokenlist.size(); i++) {
			if (typelist.get(i) != tokenlist.get(i).getType()) return false;
		}
		return true;
	}
}
