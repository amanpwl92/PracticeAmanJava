package org.example;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {

  public boolean exist(char[][] board, String word) {
    Set<String> set = new HashSet<>();
    for(int i=0; i< board.length; i++) {
      for(int j=0; j< board[0].length; j++) {
        if(board[i][j] == word.charAt(0)) {
          if (possible(board, word, i, j, 0, set)) {
            return true;
          }
        }
      }
    }

    return false;

  }

  public boolean possible(char[][] board, String word, int i, int j, int level, Set<String> set) {

    set.add(i+""+j);

    if(level == word.length() - 1) {
      return true;
    }

    level++;

    boolean res=false, res1=false, res2=false, res3=false;

    if((i+1) <= board.length-1 && !set.contains((i+1) + "" + j) && board[i+1][j] == word.charAt((level))) {
      res = possible(board, word, i+1, j, level, set);
    }

    if((j+1) <= board[0].length-1 && !set.contains(i + "" + (j+1)) && board[i][j+1] == word.charAt((level))) {
      res1 =  possible(board, word, i, j+1, level, set);
    }

    if((i-1) >=0 && !set.contains((i-1) + "" + j) && board[i-1][j] == word.charAt((level))) {
      res2 =  possible(board, word, i-1, j, level, set);
    }

    if((j-1) >=0 && !set.contains(i + "" + (j-1)) && board[i][j-1] == word.charAt((level))) {
      res3 =  possible(board, word, i, j-1, level, set);
    }


    set.remove(i+""+j);

    return (res || res1 || res2 || res3);

  }

  public static void main(String[] args) {

    char[][] grid = {{'C','A','A'},{'A', 'A', 'A'},{'B', 'C', 'D'}};
    String word = "AAB";
    WordSearch wordSearch = new WordSearch();
    System.out.println(wordSearch.exist(grid, word));

  }
}
