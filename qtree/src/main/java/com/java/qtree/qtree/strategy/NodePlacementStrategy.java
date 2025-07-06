package com.java.qtree.qtree.strategy;

public interface NodePlacementStrategy {
    enum ChildType {
        MUCH_SMALLER, SMALLER, BIGGER, MUCH_BIGGER
    }

    ChildType determineChildType(int parentValue, int childValue);
}