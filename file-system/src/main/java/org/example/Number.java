package org.example;
import java.io.*;

class Number {

  public static void main(String[] args) throws IOException {

    InputStreamReader read = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(read);

    int n, m, b;
    double perb, perg;

    System.out.println("Enter number of girls and boys");

    n = Integer.parseInt(in.readLine());
    m = Integer.parseInt(in.readLine());

    b = n-m;

    perb = (double) b/n * 100;
    perg = (double) m/n * 100;

    System.out.println("Percentage of boys = " + perb);
    System.out.println("Percentage of girls = " + perg);

  }

}
