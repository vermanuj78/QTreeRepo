package com.java.qtree.qtree;

import java.util.Objects;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.java.qtree.qtree.strategy.NodePlacementStrategy;
import com.java.qtree.qtree.strategy.NodePlacementStrategy.ChildType;

@Service
public class QuaternaryTree {
     
    private QuaternaryTreeNode root;
    private NodePlacementStrategy strategy;
    private String separator = "|";

    public QuaternaryTree() {
        
    }
    public QuaternaryTree(NodePlacementStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
        this.root = null;
    }

    // Iterative insertion
    public void insert(int value) {
        if (root == null) {
            root = new QuaternaryTreeNode(value);
            System.out.println("Inserted root: " + value);
            return;
        }

        QuaternaryTreeNode current = root;
        System.out.println("Current " + current.value + " Value " + value);
        while (true) {
            System.out.println(" inside while Current " + current.value + " Value " + value);
            ChildType type = this.strategy.determineChildType(current.value, value);
            
            if (null == type) {
                // Value already exists (duplicate), do nothing
                return;
            } 
            else switch (type) {
                case MUCH_SMALLER -> {
                    if (current.muchSmaller == null) {
                        current.muchSmaller = new QuaternaryTreeNode(value);
                        return;
                    }   System.out.println("Current " + current.value + " Value " + value);
                    current = current.muchSmaller;
                    System.out.println("Current MUCH SMALLER " + current.value + " Value " + value);
                }
                case SMALLER -> {
                    if (current.smaller == null) {
                        current.smaller = new QuaternaryTreeNode(value);
                        return;
                    }   current = current.smaller;
                }
                case MUCH_BIGGER -> {
                    if (current.muchBigger == null) {
                        current.muchBigger = new QuaternaryTreeNode(value);
                        return;
                    }   current = current.muchBigger;
                }
                case BIGGER -> {
                    if (current.bigger == null) {
                        current.bigger = new QuaternaryTreeNode(value);
                        return;
                    }   current = current.bigger;
                }
                default -> {
                    // Value already exists (duplicate), do nothing
                    return;
                }
            }
        }
    }

    // Iterative tree printing using a stack
    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        Stack<NodeWithDepth> stack = new Stack<>();
        stack.push(new NodeWithDepth(root, 0));

        while (!stack.isEmpty()) {
            NodeWithDepth current = stack.pop();
            QuaternaryTreeNode node = current.node;
            int depth = current.depth;

            // Print indentation
            for (int i = 0; i < depth; i++) {
                for(int j=depth;j==0;j--)
                 separator = separator + "   |   ";
                
                System.out.print(separator + depth);
                //System.out.print("Depth " + depth + " ");
            }
            System.out.println(node.toString());

            // Push children in reverse order (so they're processed in correct order)
            if (node.muchBigger != null) {
                stack.push(new NodeWithDepth(node.muchBigger, depth + 1));
            }
            if (node.bigger != null) {
                stack.push(new NodeWithDepth(node.bigger, depth + 1));
            }
            if (node.smaller != null) {
                stack.push(new NodeWithDepth(node.smaller, depth + 1));
            }
            if (node.muchSmaller != null) {
                stack.push(new NodeWithDepth(node.muchSmaller, depth + 1));
            }
        }
    }

    // Helper class for tracking nodes with their depth
    private static class NodeWithDepth {
        QuaternaryTreeNode node;
        int depth;

        public NodeWithDepth(QuaternaryTreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
 
    public static void buildAndPrintQuaternaryTree(int[] array, NodePlacementStrategy strategy) {
       
        if (array == null || array.length == 0) {
            System.out.println("Empty array - no tree to build.");
            return;
        }
        
        QuaternaryTree tree = new QuaternaryTree(strategy);
        for (int num : array) {
            System.out.println("Inserting: " + num);
            tree.insert(num);
        }

        System.out.println("Quaternary Tree Structure:");
        tree.printTree();
    }
}
