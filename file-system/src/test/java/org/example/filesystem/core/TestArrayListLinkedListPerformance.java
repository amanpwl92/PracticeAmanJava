package org.example.filesystem.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayListLinkedListPerformance {


  private static final int size = 10000000;
  private static final int inc = 10000;

  @Before
  public void initialize() {
    System.out.println("Total memory available = " + Runtime.getRuntime().totalMemory()/(1024 * 1024));
  }

  @Test
  public void testInsertAndDeleteArrayList() {

    long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

    List<Integer> a = new ArrayList<>();

    for(int i=0; i< size; i++) {
      a.add(i);
    }

    long startTime = System.nanoTime();

    for (int i=0;i< size; i=i+inc) {
      a.remove(i);
    }

    long endTime = System.nanoTime();
    long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    System.out.println("Time in arraylist (secs) = " + (endTime - startTime)/1000000000);
    System.out.println("memory used in arraylist (mb) = " + (afterUsedMem-beforeUsedMem)/(1024*1024));

    // Time in arraylist (secs) = 9
    // memory used in arraylist (mb) = 324
  }

  @Test
  public void testInsertAndDeleteLinkedList() {

    long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

    List<Integer> a = new LinkedList<>();

    for(int i=0; i< size; i++) {
      a.add(i);
    }

    long startTime = System.nanoTime();

    for (int i=0;i< size; i=i+inc) {
      a.remove(i);
    }

    long endTime = System.nanoTime();
    long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    System.out.println("Time in linkedlist (secs) = " + (endTime - startTime)/1000000000);
    System.out.println("memory used in linkedlist (mb) = " + (afterUsedMem-beforeUsedMem)/(1024*1024));

    // Time in linkedlist (secs) = 20
    // memory used in linkedlist (mb) = 409
  }

}
