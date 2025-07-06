package com.java.qtree.qtree.strategy;

public class ParityBasedNodePlacementStrategy implements NodePlacementStrategy {
    @Override
    public ChildType determineChildType(int parentValue, int childValue) {
       return ChildType.BIGGER;
    }
}
