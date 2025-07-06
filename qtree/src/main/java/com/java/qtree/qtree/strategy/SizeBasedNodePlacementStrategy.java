package com.java.qtree.qtree.strategy;

public class SizeBasedNodePlacementStrategy implements NodePlacementStrategy {
    @Override
    public ChildType determineChildType(int parentValue, int childValue) {
        if (childValue < parentValue - 10) {
            return ChildType.MUCH_SMALLER;
        } else if (childValue < parentValue) {
            return ChildType.SMALLER;
        } else if (childValue > parentValue + 10) {
            return ChildType.MUCH_BIGGER;
        } else if (childValue > parentValue) {
            return ChildType.BIGGER;
        }
        throw new IllegalArgumentException("Duplicate value: " + childValue);
    }
}
