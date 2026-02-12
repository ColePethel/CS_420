/*
 * Shannon Duvall
 * The result of parsing a method call line.
 */
public class MethodCallResults extends ParseResults {
	public MethodCallResults(String ansTypeString, String ansNameString, String objNameString, String methNameString, String[] argStrings){
		this.isMethodCall = true;
		this.answerType = ansTypeString;
		this.answerName = ansNameString;
		this.objectName = objNameString;
		this.methodName = methNameString;
		this.arguments = argStrings;
	}
}
