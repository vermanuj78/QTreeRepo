package com.java.qtree.qtree;

import org.springframework.stereotype.Component;

import com.java.qtree.qtree.factory.QTreeFactory;

@Component
public class QTreeBuilder {
        int[] numbers = {42, 5, 33, 8, 35, 15, 30, 0, 40, 10,55,75,2,-30,-15};
        public QTreeBuilder(){
         Tree tree = QTreeFactory.createQTree(QTreeFactory.StrategyType.SIZE_BASED);
         try{
                tree.populateAndPrintTree(numbers);
         }catch(Throwable e){
                e.printStackTrace();
         }
        }

}
