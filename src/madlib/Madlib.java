package madlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * {@link madlib.Madlib} by liach. So much fun.
 * I used google java style. {@link https://google.github.io/styleguide/javaguide.html}
 * 
 * @author liach
 *
 */
public final class Madlib {
  
  Scanner scan;
  Scanner fileStream;
  File lib;
  String all;
  ArrayList<String> fields;
  ArrayList<String> reps;
  
  void run() {
    scan = new Scanner(System.in);
    System.out.println("give me a file below:");
    String fileName = scan.nextLine();
    lib = new File(fileName);
    try {
      fileStream = new Scanner(lib);
      fileStream.useDelimiter("\\Z");
    } catch (FileNotFoundException e) {
      System.err.println("bad file!");
      return;
    }
    all = new String();
    scanFrom(fileStream);
    
    fields = new ArrayList<>(100);
    
    boolean inBracket = false;
    int bracketStart = 0;
    for (int i = 0; i < all.length(); i++) {
      char c = all.charAt(i);
      if (c == '[') {
        if (inBracket) {
          System.err.println("bad format in file l!" + i);
          return;
        }
        inBracket = true;
        bracketStart = i;
      }
      
      if (c == ']') {
        if (!inBracket) {
          System.err.println("bad format in file r!" + i);
          return;
        }
        inBracket = false;
        String part = all.substring(bracketStart + 1, i);
        fields.add(part);
      }
    }
    
    reps = new ArrayList<>(fields.size());
    
    for (String part : fields) {
      System.out.print("Fill in for \"" + part + "\": ");
      String rep = scan.nextLine();
      reps.add(rep);
    }
    
    int pointer = 0;
    inBracket = false;
    for (int i = 0; i < all.length(); i++) {
      char c = all.charAt(i);
      if (c == '[') {
        if (inBracket) {
          System.err.println("left bracket error");
          return;
        }
        inBracket = true;
        System.out.print(reps.get(pointer));
        pointer ++;
      }
      
      if (!inBracket) {
        System.out.print(c);
      }
      
      if (c == ']') {
        if (!inBracket) {
          System.err.println("right bracket error");
          return;
        }
        inBracket = false;
      }
    }
    
  }
  
  void scanFrom(Scanner src) {
    all = src.next();
    System.out.println(all);
  }
  
  /**
   * The hook for running {@link madlib.Madlib}.
   * @param args arguments from the command line
   */
  public static final void main(String[] args) {
    new Madlib().run();
  }
}
