/*
 * Shannon Duvall
 * The result of parsing a line that has "new" in it.
 */
public class CreateResults extends ParseResults {
	public CreateResults(String classString, String objString, String[] argStrings){
		this.isMethodCall = false;
		this.className = classString;
		this.objectName = objString;
		this.arguments = argStrings;
	}
}
