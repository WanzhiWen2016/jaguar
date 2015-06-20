package br.usp.each.saeg.jaguar.plugin;


public class JacocoAgentJar {

	private static final char BLANK = ' ';
	private static final char QUOTE = '"';
	private static final char SLASH = '\\';
	
	public String getVmArguments(String includes) {
		return String.format("-javaagent:%s=output=%s", ProjectUtils.getLibJarLocation("jacocoagent.jar"), "tcpserver");
	}
	
	public String getQuotedVmArguments(String includes){
		return quote(getVmArguments(includes));
	}
	
	protected String quote(String arg) {
		if (arg.indexOf(' ') == -1) {
			return arg;
		} else {
			return '\"' + arg + '\"';
		}
	}
	  
	/**
	 * Quotes a single command line argument if necessary.
	 * 
	 * @param arg
	 *            command line argument
	 * @return quoted argument
	 */
	static String oldQuote(final String arg) {
		final StringBuilder escaped = new StringBuilder();
		for (final char c : arg.toCharArray()) {
			if (c == QUOTE || c == SLASH) {
				escaped.append(SLASH);
			}
			escaped.append(c);
		}
		if (arg.indexOf(BLANK) != -1 || arg.indexOf(QUOTE) != -1) {
			escaped.insert(0, QUOTE).append(QUOTE);
		}
		return escaped.toString();
	}
}