package org.example;/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*

Write a function that accepts a string representation of a chemical compound and returns a JSON object containing KV pairs where keys are the element symbols and the values are the element counts.

"H2O" => {"H": 2, "O": 1}
"C6H12" => {"C": 6, "H": 12}
"COOH" => {"C": 1, "O": 2, "H": 1}
"CLO2" => C :1, L: 1, O:2

*/
class ChemicalCompoundBreakup {

  public static boolean isElement(char ch) {
    return (int) ch >=65 && (int) ch <= 90;
  }

  public static int getValency(int index, String s) {
    String valency = "";
    char ch;
    for(int i=index+1; i< s.length(); i++) {
      ch = s.charAt(i);
      if ((int)ch >= 48 && (int)ch <= 57) {
        valency = valency + "" + ch;
      } else {
        break;
      }
    }

    if(valency.equals("")) {
      return 1;
    }
    return Integer.parseInt(valency);
  }
  public void getCompoundBreakUp(String s) {
    char ch;
    int valency;
    Map<Character, Integer> map = new HashMap<>();
    for(int i=0; i< s.length(); i++) {
      ch = s.charAt(i);
      if(isElement(ch)) {
        valency = getValency(i, s);
        map.put(ch, map.getOrDefault(ch, 0) + valency);
      }
    }

    System.out.println("Aman");
  }

  public static void main(String[] args) {

    String input = "COOH2C17O2H4"; // COOH, H2O, C6H12

    ChemicalCompoundBreakup s = new ChemicalCompoundBreakup();
    s.getCompoundBreakUp(input);

  }

}
