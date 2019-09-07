package model;

import java.util.Objects;

public class Block {

    // start from 0 to (number of block in maze - 1)
    private int idNum;

    // side identity number : up = 0, right = 1, down = 2, left = 3
    // side value : -1 = the edge of maze, 0 = closed, 1 = opened
    private int up;          //0
    private int right;       //1
    private int down;        //2
    private int left;        //3

    public Block (int idNum) {

        this.idNum = idNum;

        up = -1;
        right = -1;
        down = -1;
        left = -1;
    }


    // return false if closed, true if opened
    // can make exception handling for default case
    public boolean isSideIfOpen (int sideIDNum) {
        switch (sideIDNum) {
            case 0:
                if (up == 1) {
                    return true;
                }
                return false;
            case 1:
                if (right == 1) {
                    return true;
                }
                return false;
            case 2:
                if (down == 1) {
                    return true;
                }
                return false;
            case 3:
                if (left == 1) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    // set the side value of the given side ID number to 1
    public void openSide (int sideIDNum) {
        switch (sideIDNum) {
            case 0:
                up = 1;
                break;
            case 1:
                right = 1;
                break;
            case 2:
                down = 1;
                break;
            case 3:
                left = 1;
                break;
            default:
                break;
        }
    }

    // set the side value of the given side ID number to 0
    public void closeSide (int sideIDNum) {

        switch (sideIDNum) {
            case 0:
                up = 0;
                break;
            case 1:
                right = 0;
                break;
            case 2:
                down = 0;
                break;
            case 3:
                left = 0;
                break;
            default:
                break;
        }

    }

    // return the block's ID Number
    public int getIdNum () {

        return this.idNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return idNum == block.idNum;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idNum);
    }
}
