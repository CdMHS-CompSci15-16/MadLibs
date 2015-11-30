//By Ryan Chan
//AP Computer Science 2015
import java.util.*;
import java.io.*;
public class MadLibs {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);	
		System.out.println("Welcome to Mad Libs!");
		String madLibString = readTemplate("madlib.txt");	//Obtains the contents of madlib.txt as a String
		while (madLibString.indexOf("]")!=-1){	//Loops until the program has progressed through the entire string
			madLibString=lib(madLibString);	
		}
		System.out.println("Your final Mad Lib: ");
		System.out.println(madLibString);	//Outputs finished Mad Lib to console
		sc.close();
	}
	public static String lib(String madLibString) throws FileNotFoundException{	//Finds the first [ ] in the string, prompts user input using, and replaces the bracketed area with the users input.
		String input = "";
		Scanner sc = new Scanner(System.in);
		int startPos = madLibString.indexOf("[");	//Finds the locations of the first set of brackets
		int endPos = madLibString.indexOf("]");
		String prompt = madLibString.substring(startPos+1,endPos);	//Stores the words inside of the brackets
		System.out.print("Please enter a(n) "+prompt+ ": ");	//Prompts user input
		input=sc.nextLine();		
		madLibString = madLibString.substring(0,startPos)+input+madLibString.substring(endPos+1);	//Recreates the string, replacing the brackets with user input
		return madLibString;	//Returns the new string
	}
	public static String readTemplate(String filename) throws FileNotFoundException{	//Takes the contents of a specified file and returns the contents as a String
		Scanner input = new Scanner(new File(filename));
		String madLibString = "";
		while(input.hasNextLine()){	//Adds the entire contents of the text file to a string
			madLibString+=input.nextLine()+"\n";	//\n is to preserve line breaks in the text
		}
		input.close();
		return madLibString;	//Returns the String of raw text
	}
}