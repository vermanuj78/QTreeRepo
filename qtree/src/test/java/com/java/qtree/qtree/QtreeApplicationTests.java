package com.java.qtree.qtree;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.qtree.qtree.factory.QTreeFactory;
import com.java.qtree.qtree.strategy.NodePlacementStrategy;
class QuaternaryTreeTest {

    private QuaternaryTree tree;
    private NodePlacementStrategy strategy;

    @BeforeEach
    public void setUp() {
        
        tree = (QuaternaryTree) QTreeFactory.createQTree(QTreeFactory.StrategyType.SIZE_BASED);
        strategy = tree.getStrategy();
        
    }

    // Helper method to access root for testing
    private QuaternaryTreeNode getRoot(Tree tree) {
        try {
            java.lang.reflect.Field field = QuaternaryTree.class.getDeclaredField("root");
            field.setAccessible(true);
            return (QuaternaryTreeNode) field.get(tree);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access root field", e);
        }
    }

    @Test
    void testInsertRoot() {
        tree.insert(50);
        QuaternaryTreeNode root = getRoot(tree);
        assertNotNull(root, "Root should not be null after insertion");
        assertEquals(50, root.value, "Root value should match inserted value");
    }

    @Test
    void testInsertMuchSmaller() {
        tree.insert(50);
        tree.insert(30); // 50 - 30 = 20 > 10 → MUCH_SMALLER
        
        QuaternaryTreeNode root = getRoot(tree);
        assertNotNull(root.muchSmaller, "Much smaller child should exist");
        assertEquals(30, root.muchSmaller.value, "Much smaller child value should match");
    }

    @Test
    void testInsertSmaller() {
        tree.insert(50);
        tree.insert(45); // 50 - 45 = 5 ≤ 10 → SMALLER
        
        QuaternaryTreeNode root = getRoot(tree);
        assertNotNull(root.smaller, "Smaller child should exist");
        assertEquals(45, root.smaller.value, "Smaller child value should match");
    }

    @Test
    void testInsertBigger() {
        tree.insert(50);
        tree.insert(55); // 55 - 50 = 5 ≤ 10 → BIGGER
        
        QuaternaryTreeNode root = getRoot(tree);
        assertNotNull(root.bigger, "Bigger child should exist");
        assertEquals(55, root.bigger.value, "Bigger child value should match");
    }

    @Test
    void testInsertMuchBigger() {
        tree.insert(50);
        tree.insert(65); // 65 - 50 = 15 > 10 → MUCH_BIGGER
        
        QuaternaryTreeNode root = getRoot(tree);
        assertNotNull(root.muchBigger, "Much bigger child should exist");
        assertEquals(65, root.muchBigger.value, "Much bigger child value should match");
    }

    @Test
    void testInsertDuplicate() {
        tree.insert(50);
       assertThrows(IllegalArgumentException.class, () -> tree.insert(50),
            "Should throw IllegalArgumentException for duplicate value");
       
    }

    @Test
    void testEmptyTreePrint() {
        QuaternaryTree emptyTree = new QuaternaryTree(strategy);
        assertDoesNotThrow(() -> emptyTree.printTree(), 
            "Printing empty tree should not throw exception");
    }

   @Test
    void testBuildAndPrintQuaternaryTree() {
        int[] array = {50, 30, 70, 20, 40, 60, 80};
        assertDoesNotThrow(() -> tree.populateAndPrintTree(array),
            "buildAndPrintQuaternaryTree should execute without exceptions");
    }
}