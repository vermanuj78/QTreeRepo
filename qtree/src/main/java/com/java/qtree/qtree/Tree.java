package com.java.qtree.qtree;

public interface Tree {
    public void insert(int value) throws IllegalArgumentException;
    public void printTree();
    public void populateAndPrintTree(int[] numbers) throws Throwable;
}
