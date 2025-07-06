package com.java.qtree.qtree;

import java.util.Objects;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.java.qtree.qtree.strategy.NodePlacementStrategy;
import com.java.qtree.qtree.strategy.NodePlacementStrategy.ChildType;

@Service
public class QuaternaryTree implements Tree {
     
    private QuaternaryTreeNode root;
    private NodePlacementStrategy strategy;
    private String separator = "|";

    public void setRoot(QuaternaryTreeNode root) {
        this.root = root;
    }
    public void setStrategy(NodePlacementStrategy strategy) {
        this.strategy = strategy;
    }
    public void setSeparator(String separator) {
        this.separator = separator;
    }
    public QuaternaryTree() {
        
    }
    public QuaternaryTreeNode getRoot() {
        return root;
    }
    public NodePlacementStrategy getStrategy() {
        return strategy;
    }
    public String getSeparator() {
        return separator;
    }
    public QuaternaryTree(NodePlacementStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
        this.root = null;
    }

    // Iterative insertion
    /**
     * @param value
     * @throws IllegalArgumentException
     */
    @Override
    public void insert(int value) throws IllegalArgumentException {
        if (root == null) {
            root = new QuaternaryTreeNode(value);
            return;
        }

        QuaternaryTreeNode current = root;
        
        while (true) {
            
            ChildType type = this.strategy.determineChildType(current.value, value);
            
            if (null == type) {
                throw new IllegalArgumentException("Duplication Error");
            }else switch (type) {
                case MUCH_SMALLER -> {
                    if (current.muchSmaller == null) {
                        current.muchSmaller = new QuaternaryTreeNode(value);
                        return;
                    } 
                    current = current.muchSmaller;
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
                    throw new IllegalArgumentException("Duplication Error");
                }
            }
        }
    }

    // Iterative tree printing using a stack
    @Override
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
 
    @Override
    public void populateAndPrintTree(int[] array) throws Throwable{
       
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        
        for (int num : array) {
            insert(num);
        }

        System.out.println("Quaternary Tree Structure:");
        printTree();
    }
}
