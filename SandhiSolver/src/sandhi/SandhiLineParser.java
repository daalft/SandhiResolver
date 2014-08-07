package sandhi;

import general.Token;
import general.Token.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a single sandhi-rule line
 * @author David
 *
 */
public class SandhiLineParser {
	
	/////////////////////////////////////////////////
	// Variables
	/////////////////////////////////////////////////
	/**
	 * Rule pattern<br>
	 * Content enclosed in double quotes, followed by a comma,
	 * followed by content enclosed in double quotes,
	 * possibly followed by an inline comment
	 */
	private static Pattern p = Pattern.compile("\"(.+?)\"(,)\\s?\"(.+?)\"(\\s?#.+)?");
	
	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////
	
	/////////////////////////////////////////////////
	// Methods
	/////////////////////////////////////////////////
	/**
	 * Parses a single sandhi rule line 
	 * @param line sandhi rule line
	 * @return token list on success
	 * @throws Exception Malformed rule exception if line cannot be parsed into token list according to pattern
	 */
	public List<Token> parse(String line) throws Exception {
		// output list
		List<Token> out = new ArrayList<Token>();
		// matcher
		Matcher m = p.matcher(line);
		// if match can be found
		if (m.find()) {
			// if match doesn't contain 3 elements
			if (m.groupCount() < 3) throw new Exception("Malformed rule exception!");
			// else build tokens
			// replace double backslashes in first group by simple backslash
			Token t1 = new Token(m.group(1).replace("\\\\", "\\"), Type.CONTENT);
			// get delimiter
			Token d = new Token(m.group(2), Type.DELIMITER);
			// replace double backslashes in second group by dollar sign
			Token t2 = new Token(m.group(3).replace("\\\\", "$"), Type.CONTENT);
			// add tokens to list
			out.add(t1);
			out.add(d);
			out.add(t2);
		} else { // if no match could be found
			throw new Exception("Malformed rule exception!");
		}
		// return output
		return out;
	}
}
