//By Ryan Chan
//AP Computer Science 2015
import java.util.*;
import java.io.*;
public class MadLibs {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);	
		System.out.println("Welcome to Mad Libs!");
		String madLibString = readTemplate("madlib.txt");	//Obtains the contents of madlib.txt as a String
		while (madLibString.indexOf("]")!=-1){	//Loops until it has progressed through the entire string
			madLibString=lib(madLibString);	
		}
		System.out.println("Your final Mad Lib: ");
		System.out.println(madLibString);
		sc.close();
	}
	public static String lib(String madLibString) throws FileNotFoundException{
		String input = "";
		Scanner sc = new Scanner(System.in);
		int startPos = madLibString.indexOf("[");
		int endPos = madLibString.indexOf("]");
		String prompt = madLibString.substring(startPos+1,endPos);
		System.out.print("Please enter a "+prompt+ ": ");
		input=sc.nextLine();		
		madLibString = madLibString.substring(0,startPos)+input+madLibString.substring(endPos+1);
		return madLibString;
	}
	public static String readTemplate(String filename) throws FileNotFoundException{
		Scanner input = new Scanner(new File(filename));
		String madLibString = "";
		while(input.hasNextLine()){
			madLibString+=input.nextLine()+"\n";
		}
		input.close();
		return madLibString;
	}
}
