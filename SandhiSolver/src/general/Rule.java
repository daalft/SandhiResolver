package general;

import java.util.List;

/**
 * Represents a replacement rule with a left hand side indicating what to replace (regex)
 * and the right hand side indicating with what to replace (regex with back reference)
 * @author David
 *
 */
public class Rule {
	
	/////////////////////////////////////////////////
	// Variables
	/////////////////////////////////////////////////
	
	private String left, right;
	
	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////
	
	public Rule() {}
	
	public Rule(String l, String r) {
		left = l;
		right = r;
	}
	
	public Rule(List<Token> list) {
		this(list.get(0).getContent(), list.get(2).getContent());
	}
	
	/////////////////////////////////////////////////
	// Methods
	/////////////////////////////////////////////////
	
	/**
	 * Applies the rule at the first possible location
	 * and returns the result
	 * @param s string
	 * @return string with this rule applied (if applicable)
	 * @see #isApplicable(String)
	 */
	public String applyFirst(String s) {
		return s.replaceFirst(left, right);
	}
	
	/**
	 * Applies the rule at every possible location
	 * and returns the result
	 * @param s string
	 * @return string with this rule applied globally (if applicable)
	 * @see #isApplicable(String)
	 */
	public String applyAll(String s) {
		return s.replaceAll(left, right);
	}
	
	/**
	 * Checks whether this rule can be applied to the given string
	 * @param s string
	 * @return <b>true</b> if this rule can be applied to given string, <b>false</b> otherwise
	 */
	public boolean isApplicable(String s) {
		return s.matches(left);
	}
	
	@Override
	public String toString() {
		return left + " -> " + right;
	}
}
