package org.example;

public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    int cnt = 0;
    for (int i=0; i< grid.length; i++) {
      for (int j=0; j< grid[0].length; j++) {
        if (grid[i][j] == '1') {
          cnt++;
          travserseAnIsland(i, j, grid);
          System.out.println("traverse done for i = " + i + " j = " + j + " with cnt = " + cnt);
        }
      }
    }

    return cnt;

  }

  public void travserseAnIsland(int i, int j, char[][] grid) {

    System.out.println("i = " + i);
    System.out.println("j = " + j);
    System.out.println("grid = ");
    for (int k = 0; k < grid.length; k++) {
      for (int l = 0; l < grid[0].length; l++) {
        System.out.print(grid[k][l] + ",");
      }
      System.out.println();
    }


    if (i <= grid.length-1 && j <= grid[0].length - 1) {
      if (grid[i][j] == '1') {
        grid[i][j] = '0';
        travserseAnIsland(i, j+1, grid);
        travserseAnIsland(i+1, j, grid);
      }
    }
  }

  public static void main(String[] args) {

    char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
    NumberOfIslands numberOfIslands  = new NumberOfIslands();
    System.out.println(numberOfIslands.numIslands(grid));

  }
}
