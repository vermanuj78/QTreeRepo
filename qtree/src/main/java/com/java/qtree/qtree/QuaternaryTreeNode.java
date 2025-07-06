package com.java.qtree.qtree;

public class QuaternaryTreeNode {
    int value;
    QuaternaryTreeNode muchSmaller;
    QuaternaryTreeNode smaller;
    QuaternaryTreeNode bigger;
    QuaternaryTreeNode muchBigger;

    public QuaternaryTreeNode(int value) {
        this.value = value;
        this.muchSmaller = null;
        this.smaller = null;
        this.bigger = null;
        this.muchBigger = null;
    }
    @Override
    public String toString() {
        return "   QuaternaryTreeNode >>> " +
                "value=" + value +
                 (muchSmaller != null ? ", muchSmaller=" + muchSmaller.value : "") +
                 (smaller != null ? ", smaller=" +smaller.value : "") +
                 (bigger != null ? ", bigger=" +bigger.value : "") +
                (muchBigger != null ? ", muchBigger=" + muchBigger.value : "") +
                '}';
    }
}   
 

 