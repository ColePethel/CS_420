/*
 * Shannon Duvall
 * Tester.java
 * Tests the methods you wrote
 * using junit testing.
 * 
 * Do not change anything in this file.
 */
import junit.framework.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester extends TestCase{
	//private ReflectionUtilities myMethods;
	
	public Tester(){
		super();
		//myMethods = new ReflectionUtilities();
	}
	
	public void testTypesMatch(){
		Integer i = 3;
		String s = "Hey";
		MyClass m = new MyClass();
		// You can get classes in 2 ways - either is fine.
		Class<?> sClass = s.getClass();
		Class<?> mClass;
		try {
			mClass = Class.forName("MyClass");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mClass = null;
		}
		
		Object[] first = {s,m};
		Object[] second = {i,s,m};
		Object[] third = {};
		Object[] fourth = {s};
		Object[] fifth = {m,s};
		Object[] sixth = {s,i};
		Object[][] objectLists = {first,second,third,fourth,fifth, sixth};
		Class<?>[] firstClass = {sClass, mClass};
		Class<?>[] secondClass = {int.class, sClass, mClass};
		Class<?>[] thirdClass = {};
		Class<?>[] fourthClass = {sClass};
		Class<?>[] fifthClass = {mClass,sClass};
		Class<?>[] sixthClass = {sClass,int.class};
		Class<?>[][] classLists = {firstClass,secondClass,thirdClass,fourthClass,fifthClass, sixthClass};
		
		for(int k = 0; k< objectLists.length; k++){
			for(int j = 0; j<classLists.length; j++){
				if(k==j){
					assertTrue(ReflectionUtilities.typesMatch(classLists[j], objectLists[k]));
				}
				else{
					assertFalse(ReflectionUtilities.typesMatch(classLists[j], objectLists[k]));
				}
			}
		}
		
		assertFalse(ReflectionUtilities.typesMatch(fourthClass, firstClass));
		assertFalse(ReflectionUtilities.typesMatch(fourthClass,fourthClass));
		assertFalse(ReflectionUtilities.typesMatch(firstClass,fourthClass));	
	}
	
	public void testCreateInstance(){
		Object[] one = {};
		Object[] two = {2, "Two"};
		Object[] three = {3, "Three"};
		Object[] four = {"Four", 4};
		MyClass objOne = (MyClass)ReflectionUtilities.createInstance("MyClass",one);
		MyClass objTwo = (MyClass)ReflectionUtilities.createInstance("MyClass",two);
		MyClass objThree = (MyClass)ReflectionUtilities.createInstance("MyClass",three);
		MyClass objFour = (MyClass)ReflectionUtilities.createInstance("MyClass",four);
		assertTrue(objOne.getMessage().equals("I was created with a default constructor!"));
		assertTrue(objOne.getNumber()==1);
		assertTrue(objTwo.getMessage().equals("Two"));
		assertTrue(objTwo.getNumber()==2);
		assertTrue(objThree.getMessage().equals("Three"));
		assertTrue(objThree.getNumber()==3);
		assertTrue(objFour == null);		
	}
	
	public void testCallMethod(){
		MyClass mine = new MyClass(4,"Hey");
		Integer answer1 = (Integer)ReflectionUtilities.callMethod(mine, "getNumber", new Object[]{});
		assertTrue(answer1==4);
		String answer2 = (String)ReflectionUtilities.callMethod(mine, "getMessage", new Object[]{});
		assertTrue(answer2.equals("Hey"));
		String answer3 = (String)ReflectionUtilities.callMethod(mine, "makeMessages", new Object[]{3});
		assertTrue(answer3.equals("HeyHeyHey"));
		Object answer4 = ReflectionUtilities.callMethod(mine, "print", new Object[]{});
		assertTrue(answer4 == null);
	}

	private static TestSuite suite() {
		TestSuite suite = new TestSuite("Test for ReflectionUtilities.java");
		suite.addTestSuite(Tester.class);
		return suite;
	}
}
