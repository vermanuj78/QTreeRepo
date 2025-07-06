package com.java.qtree.qtree.factory;

import com.java.qtree.qtree.QuaternaryTree;
import com.java.qtree.qtree.Tree;
import com.java.qtree.qtree.strategy.ParityBasedNodePlacementStrategy;
import com.java.qtree.qtree.strategy.SizeBasedNodePlacementStrategy;

public class QTreeFactory {
    public enum StrategyType {
        SIZE_BASED, PARITY_BASED
    }
    

    public static Tree createQTree(StrategyType type) {
        switch (type) {
            case SIZE_BASED -> {
                return new QuaternaryTree(new SizeBasedNodePlacementStrategy());
            }
            case PARITY_BASED -> {
                // Placeholder for future strategy implementation
                return new QuaternaryTree(new ParityBasedNodePlacementStrategy());
            }
            default -> throw new IllegalArgumentException("Unknown strategy");
        }
    }
}
