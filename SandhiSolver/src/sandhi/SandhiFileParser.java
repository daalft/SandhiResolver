package sandhi;

import general.Rule;
import general.Token;
import general.TokenValidator;
import general.TokenValidator.ShortType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Parses a sandhi rule file line by line using the SandhiLineParser
 * @author David
 *
 */
public class SandhiFileParser {

	/////////////////////////////////////////////////
	// Variables
	/////////////////////////////////////////////////
	
	private SandhiLineParser slp = new SandhiLineParser();
	
	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////
	
	/////////////////////////////////////////////////
	// Methods
	/////////////////////////////////////////////////
	/**
	 * Parses a file containing sandhi rules, one per line
	 * @param file file
	 * @return list of rules
	 * @throws Exception if rule is malformed
	 */
	public List<Rule> parse (String file) throws Exception {
		return parse(new File(file));
	}
	
	/**
	 * Parses a file containing sandhi rules, one per line
	 * @param file file
	 * @return list of rules
	 * @throws Exception if rule is malformed
	 */
	public List<Rule> parse (File file) throws Exception {
		// output list
		Set<Rule> rules = new LinkedHashSet<Rule>();
		// reader
		BufferedReader br = new BufferedReader(new FileReader(file));
		// line variable
		String l = "";
		while((l = br.readLine())!=null) {
			// skip empty lines or whitespace-only lines
			if (l.trim().isEmpty())
				continue;
			// skip comments
			if (l.startsWith("#"))
				continue;
			// parse result into token list
			List<Token> parsedResult = slp.parse(l);
			// if result is valid CDC (content, delimiter, content) structure
			if (TokenValidator.is(ShortType.CDC, parsedResult)) {
				// add to output
				rules.add(new Rule(parsedResult));
			} else { // else
				br.close(); // close buffer to prevent leak
				// throw exception
				throw new Exception("Parser exception! Malformed rule exception!");
			}
		}
		// close buffer
		br.close();
		// return result
		return new ArrayList<Rule>(rules);
	}
}
