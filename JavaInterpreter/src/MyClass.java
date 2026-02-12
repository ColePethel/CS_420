// A silly class used for testing
// Feel free to play with it all you like.

public class MyClass {
	private Integer myInt;
	private String myMessage;
	
	public MyClass(){
		myInt = 1;
		myMessage = "I was created with a default constructor!";
	}
	
	public MyClass(Integer num, String message){
		myInt = num;
		myMessage = message;
	}
	
	public String toString(){
		return myInt+" : "+myMessage;
	}
	
	public String getMessage(){
		return myMessage;
	}

	public Integer getNumber(){
		return myInt;
	}
	
	public void print(){
		System.out.println(toString());
	}
	
	public String makeMessages(Integer num){
		String answer = "";
		for(int i = 1; i<= num; i++){
			answer += myMessage;
		}
		return answer;
	}
}
