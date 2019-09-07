package model;


import java.util.*;

public abstract class Maze {

    protected int size;
    protected int sideLength;
    protected Map<Block, List<Block>> adjacencyList;
    protected List<Block> blocks;
    protected Stack<Block> answerKey;
    protected List<Block> answerKeyArrayList;

    //starting block and ending block of the maze
    protected Block start;
    protected Block end;


    public Maze(int sideLength) {

        this.sideLength = sideLength;
        this.size = sideLength * sideLength;
        adjacencyList = new HashMap<>();
        blocks = new ArrayList<>();
        answerKey = new Stack<>();

        mazeGenerator();

    }

    //create graph form of the maze by constructing vertices(blocks) and adjacency list(sides that separating blocks)
    private void mazeGenerator() {

        for (int i = 0; i < size; i++) {

            Block newBlock = new Block(i);
            adjacencyList.put(newBlock, new ArrayList<Block>());
            blocks.add(newBlock);
        }

        start = blocks.get(0);
        end = blocks.get(size - 1);

        for (int j = 0; j < size; j++) {

            int upperBlockIDNum = j - sideLength;
            int rightBlockIDNum = j + 1;
            int lowerBlockIDNum = j + sideLength;
            int leftBlockIDNum = j - 1;


            // check if up side of the block is not edge if so then put the upper block in adjacencyList and set block side value
            if (upperBlockIDNum > 0) {
                adjacencyList.get(new Block(j)).add(blocks.get(upperBlockIDNum));
                blocks.get(j).closeSide(0);
                blocks.get(upperBlockIDNum).closeSide(2);
            }
            else {

                adjacencyList.get(new Block(j)).add(null);
            }

            // check if right side of the block is not edge if so then put the right block in adjacencyList and set block side value
            if (! ((rightBlockIDNum % sideLength) == 0 || rightBlockIDNum >= size)) {
                adjacencyList.get(new Block(j)).add(blocks.get(rightBlockIDNum));
                blocks.get(j).closeSide(1);
                blocks.get(rightBlockIDNum).closeSide(3);
            }
            else {

                adjacencyList.get(new Block(j)).add(null);
            }

            // check if down side of the block is not edge if so then put the lower block in adjacencyList and set block side value
            if (lowerBlockIDNum < size) {
                adjacencyList.get(new Block(j)).add(blocks.get(lowerBlockIDNum));
                blocks.get(j).closeSide(2);
                blocks.get(lowerBlockIDNum).closeSide(0);
            }
            else {

                adjacencyList.get(new Block(j)).add(null);
            }

            // check if left side of the block is not edge if so then put the left block in adjacencyList and set block side value
            if (! ((j % sideLength) == 0 || leftBlockIDNum < 0)) {
                adjacencyList.get(new Block(j)).add(blocks.get(leftBlockIDNum));
                blocks.get(j).closeSide(3);
                blocks.get(leftBlockIDNum).closeSide(1);
            }
            else {

                adjacencyList.get(new Block(j)).add(null);
            }

        }
    }


    public Block getBlockByIdNumInMaze(int idNum) {

        return blocks.get(idNum);
    }

    public Boolean isThisBlockAnswer(int idNum) {

        return answerKeyArrayList.contains(new Block(idNum));
    }





}
