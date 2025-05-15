package org.example;

import java.util.Stack;

public class DecodeString {

  public String decodeString(String s) {

    Stack<String> stack =new Stack<>();

    int ch;
    String str;
    StringBuilder tmp;
    int freq;

    for (int i=0; i< s.length(); i++) {

      ch = s.charAt(i);
      if((char)ch == '[' || isCharacter(ch) || isDigit(ch) ) {
        stack.push(String.valueOf((char)ch));
      } else {
        str = getStringToRepeat(stack);
        freq = getFreqToRepeat(stack);

        tmp = new StringBuilder();
        while(freq != 0) {
          tmp.append(str);
          freq--;
        }

        stack.push(tmp.toString());
      }

    }

    tmp = new StringBuilder();

    while(stack.size()!=0) {
      tmp.insert(0, stack.pop());
    }

    return tmp.toString();

  }

  public static boolean isCharacter(int ch) {
    return ch >= 97 && ch <= 122;
  }

  public static boolean isDigit(int ch) {
    return ch >= 48 && ch <= 57;
  }

  public static boolean stringIsNumeric(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch(NumberFormatException ex) {
      return false;
    }
  }

  public static String getStringToRepeat(Stack<String> stack) {
    StringBuilder res = new StringBuilder();


    while(!stack.peek().equals("[")) {
      res.insert(0, stack.pop());
    }

    // remove open bracket
    stack.pop();

    return res.toString();
  }

  public static int getFreqToRepeat(Stack<String> stack) {
    StringBuilder freq = new StringBuilder();

    while(stack.size()!=0 && stringIsNumeric(stack.peek())) {
      freq.insert(0, stack.pop());
    }

    return Integer.parseInt(freq.toString());
  }

  public static void main(String[] args) {
    String input = "2[abc]3[cd]ef";

    DecodeString decodeString = new DecodeString();
    System.out.append(decodeString.decodeString(input));
  }

}
