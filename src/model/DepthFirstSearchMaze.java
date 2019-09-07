package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearchMaze extends Maze {

    private Set<Block> visited = new HashSet<Block>();

    public DepthFirstSearchMaze(int sideLength) {

        super(sideLength);
        depthFirstSearch();
    }

    private void depthFirstSearch() {


        for (Block b : blocks) {

            if (!visited.contains(b)) {

                depthFirstSearchRecursion(b);
            }
        }

        answerKeyArrayList = new ArrayList(answerKey);

    }

    private void depthFirstSearchRecursion(Block currentBlock) {

        visited.add(currentBlock);

        if (!visited.contains(end)) {
            answerKey.push(currentBlock);
        }

        // give random sequence of 0 to 3 to randomly access adjacent blocks
        ArrayList<Integer> randomSequence = new ArrayList<>();
        randomSequenceGenerator(randomSequence);

        // 4 = the max number of possible adjacent blocks
        for (Integer i : randomSequence) {

            Block randomAdjacentBlock = adjacencyList.get(currentBlock).get(i);

            if (randomAdjacentBlock != null && !visited.contains(randomAdjacentBlock)) {

                // open the side between current block and randomly chosen adjacent block
                currentBlock.openSide(i);
                randomAdjacentBlock.openSide((i + 2) % 4);

                //recursion call
                depthFirstSearchRecursion(randomAdjacentBlock);

                if (!visited.contains(end)) {
                    answerKey.pop();
                }
            }
        }
    }

    // give randomly ordered sequence of 0 to 3
    private void randomSequenceGenerator(ArrayList<Integer> randomSequence) {

        // random method gives random double between [0, 4) where 4 is not included
        // floor method gives closest integer that is equal or less than given double
        int randomInteger = (int) Math.floor(Math.random() * 4);

        randomSequence.add(randomInteger);
        randomSequence.add((randomInteger + 1) % 4);
        randomSequence.add((randomInteger + 2) % 4);
        randomSequence.add((randomInteger + 3) % 4);

    }


}
