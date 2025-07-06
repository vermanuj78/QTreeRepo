package com.java.qtree.qtree;

import org.springframework.stereotype.Component;
//import com.java.qtree.qtree.QuaternaryTree;
import com.java.qtree.qtree.strategy.*;

@Component
public class QTreeBuilder {
        int[] numbers = {42, 5, 33, 8, 35, 15, 30, 0, 40, 10,55,75,2,-30,-15};
        public QTreeBuilder(){
         NodePlacementStrategy strategy = new SizeBasedNodePlacementStrategy();
         QuaternaryTree.buildAndPrintQuaternaryTree(numbers, strategy);
        }

}
