/*
 * Shannon Duvall
 * This object does the work of looking through an inputted line and pulling
 * out the pieces we actually need, like the object's name and parameters and so forth.
 * A ParseResults object is returned with all the right slots filled in.
 */

import java.util.StringTokenizer;

public class Parser {
	public ParseResults parse(String line){
		// This is cheesy - if it has the word "new" it's a 
		// "create object" line.  Otherwise it's a method call.
		if (line.contains("new")){
			return makeObject(line);
		}
		else{
			return makeMethod(line);
		}
	}
	
	/*
	 * Pull out the important pieces in a line that has "new" in it.
	 * The important pieces are:
	 * What type of object to make
	 * The name of the new object
	 * Any parameters I need for the Constructor (may be empty)
	 */
	private ParseResults makeObject(String line){
		//System.out.println("I'm making a new object.");
		StringTokenizer lineTokens = new StringTokenizer(line);
		String className = lineTokens.nextToken().trim();
		String objectName = lineTokens.nextToken().trim();
		String argString = line.substring(line.indexOf("(")+1, line.lastIndexOf(")")).trim();
		String[] args = setArguments(argString);
		return new CreateResults(className, objectName, args);
	}
	
	/*
	 * Pull out the important pieces in a line that calls a method.
	 * The important pieces are:
	 * The type of thing the answer is, in case I have to make a new one.
	 * The name of the answer object, assuming it's not a void method.
	 * The name of the object doing the method
	 * The name of the method, and
	 * The arguments I need to run the method.
	 */
	private ParseResults makeMethod(String line){
		//System.out.println("I'm making a method object.");
		//StringTokenizer lineTokens = new StringTokenizer(line);	
		String answerName, answerType;
		// This part takes care of the stuff before the equals mark
		int indexEquals = line.indexOf("=");
		// It's a void method
		if (indexEquals == -1){
			answerName = "";
			answerType = "";
		}
		else{
			String beforeEqualMark = line.substring(0,indexEquals);
			StringTokenizer beforeTokens = new StringTokenizer(beforeEqualMark);
			// answer variable is known.
			if (beforeTokens.countTokens()==1){
				answerName = beforeTokens.nextToken().trim();
				answerType = "";
			}
			// answer variable is new.
			else{
				answerType = beforeTokens.nextToken().trim();
				answerName = beforeTokens.nextToken().trim();
			}
			
		}
		
		// Now for the stuff after the equals mark
		String afterEqualMark= line.substring(indexEquals+1);
		String objectName = afterEqualMark.substring(0,afterEqualMark.indexOf(".")).trim();
		String methodName = afterEqualMark.substring(afterEqualMark.indexOf(".")+1,
				afterEqualMark.indexOf("(")).trim();
		String argString = afterEqualMark.substring(afterEqualMark.indexOf("(")+1, afterEqualMark.lastIndexOf(")")).trim();
		String[] arguments = setArguments(argString);
		return new MethodCallResults(answerType, answerName, objectName, methodName, arguments);
	}
	
	/*
	 * This method takes as input the substring of the line that comes in between the parentheses.
	 * It separates based on commas, and returns the array of Strings it finds.
	 */
	private String[] setArguments(String argString){
		//System.out.println("ArgString is: "+argString);
		argString = argString.trim();
		if(argString.equals("")) {
			return new String[0];
		}
		
		// Count how many parameters there are by how many commas there are.
		int parameterCount = 1;
		for(int i = 0; i< argString.length(); i++){
			char letter = argString.charAt(i);
			if(letter == ','){
				parameterCount++;
			}
		}
		//System.out.println(parameterCount +" parameters.");
		String[] arguments = new String[parameterCount];
		
		int paramIndex = 0;
		int stringIndex = 0;
		while(stringIndex<argString.length()){
			//System.out.println("StringIndex is "+stringIndex);
			// Find the comma
			int comma = argString.indexOf(',',stringIndex);
			// If I don't find one, this is the last argument.
			if(comma == -1){
				arguments[paramIndex] = argString.substring(stringIndex).trim();
				//System.out.println("Adding: "+argString.substring(stringIndex).trim());
				stringIndex = argString.length();
			}
			// Comma found - read to the comma
			else{
				arguments[paramIndex] = argString.substring(stringIndex,comma).trim();
				//System.out.println("Adding: "+ argString.substring(stringIndex,comma).trim());
				stringIndex = comma+1;
			}
			paramIndex++;
		}
		//System.out.println("setArguments");
		//print(arguments);
		return arguments;
	}
	
	/*
	 * Just for debugging my argument parser.
	 */
	public void print(String[] words){
		for(String word: words){
			System.out.print(word+",");
		}
		System.out.println();
	}
}
