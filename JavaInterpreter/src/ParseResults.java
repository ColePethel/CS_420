/*
 * Shannon Duvall
 * The object that holds the important pieces of a parsed input line.  
 * This abstract class has two subclasses, CreateResults and MethodCallResults, which correspond to the two 
 * types of lines the Interpreter handles.
 */
public abstract class ParseResults {
	// Only 2 types of lines accepted, method calls or object creation.  This tells which is which.
	public boolean isMethodCall;
	
	// For method calls only. If this is not empty, you need to make a new object to hold the output.
	public String answerType;
	// For method calls only. This is the variable that should hold the result of the method call.  If this is 
	// empty, it's a void method.
	public String answerName;
	// For method calls only.  This is the name of the method.
	public String methodName;
	
	// For object creation only.  This is the type of thing to make.
	public String className;
	
	// For method calls, this is the object before the dot.  For making new objects, this is the 
	// new variable name.
	public String objectName;
	// For either method calls or object creation, this holds the parameters or arguments.
	public String[] arguments;
	
	public String toString(){
		String answer = "isMethodCall = "+isMethodCall+"\n" +
		"answerType = "+answerType+"\n" +
		"answerName = "+answerName+"\n" +
		"methodName = "+methodName+"\n" +
		"className = "+className+"\n" +
		"objectName = "+objectName+"\n" +
		"arguments = \n";
		for(String arg: arguments){
			answer+=arg+" ";
		}
		return answer;
		
	}
}
