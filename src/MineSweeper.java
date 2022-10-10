/**
 * TCSS 360 A
 * Group Project 1: Minesweeper
 * Devin Hanson
 * 10-05-2022
 */
import java.util.Scanner;

public class MineSweeper {
    /**
     * Instance Variables
     */
    public int numRows;
    public int numCols;
    public int[][] hintGrid;
    public static int fieldNum = 0;

    /**
     * Main Driver method
     * @param args
     */
    public static void main(String[] args) {
        boolean repeat = true;
        Scanner inputFile = new Scanner(System.in);

        String line = inputFile.nextLine();     //Reads lines in from input
        String[] nums = line.split(" ");

        //Loop that reads each size of minefield
        while(repeat){
            if(Integer.parseInt(nums[1]) == 0 || Integer.parseInt(nums[0]) == 0){
                repeat = false;
            }
            else if(Integer.parseInt(nums[1]) > 100 && Integer.parseInt(nums[0]) > 100){
                throw new IndexOutOfBoundsException("Please enter a value between 1-100");
            }
            else if(Integer.parseInt(nums[1]) <= 100 && Integer.parseInt(nums[0]) <= 100
                    && Integer.parseInt(nums[1]) > 0 && Integer.parseInt(nums[0]) > 0){
                MineSweeper newGrid = new MineSweeper(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
                fieldNum++;
                newGrid.setGrid();

                for(int i = 0; i < Integer.parseInt(nums[0]); i++){
                    String[] currentLine = inputFile.nextLine().split("");

                    newGrid.findMines(currentLine, i);
                }

                newGrid.outputGrids();

                line = inputFile.nextLine();
                nums = line.split(" ");
            }
        }
    }

    /**
     * Main constructor
     * @param rows
     * @param cols
     */
    public MineSweeper(final int rows,final int cols){
        this.numRows = rows;
        this.numCols = cols;
        this.hintGrid = new int[numRows][numCols];
    }

    /**
     * Sets the every cell of the field to 0
     */
    public void setGrid(){
        //2d array loop from : https://www.codegrepper.com/code-examples/whatever/how+to+fill+a+2d+array+with+0+in+java
        for(int i = 0; i < hintGrid.length; i++){
            for(int j = 0; j < hintGrid[i].length; j++){
                hintGrid[i][j] = 0;
            }
        }
    }

    /**
     * Finds the positions of the mines and sets the hint data
     * @param currentRow
     * @param mineY
     */
    public void findMines(final String[] currentRow, final int mineY){
        int mineX = 0;

        for(String str : currentRow){
            if(str.equals("*")){
                hintGrid[mineY][mineX] = 9;
                addHints(mineY, mineX);
                mineX++;
            }else{
                mineX++;
            }
        }

    }

    /**
     * Checks to see if a position is within the bounds of the field
     * @param row
     * @param col
     * @return
     */
    public boolean inBoundsCheck(final int row, final int col){
        if(row >= 0 && row <= numRows - 1 && col >= 0 && col <= numCols - 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Every time a mine is found adds one to every safe position within bounds
     * @param row
     * @param col
     */
    public void addHints(final int row, final int col){
        if(inBoundsCheck(row + 1, col + 1)
                && hintGrid[row + 1][col + 1] != 9){
            hintGrid[row + 1][col + 1]++;
        }
        if(inBoundsCheck(row, col + 1)
                && hintGrid[row][col + 1] != 9){
            hintGrid[row][col + 1]++;
        }
        if(inBoundsCheck(row + 1, col)
                && hintGrid[row + 1][col] != 9){
            hintGrid[row + 1][col]++;
        }
        if(inBoundsCheck(row - 1, col)
                && hintGrid[row - 1][col] != 9){
            hintGrid[row - 1][col]++;
        }
        if(inBoundsCheck(row, col - 1)
                && hintGrid[row][col - 1] != 9){
            hintGrid[row][col - 1]++;
        }
        if(inBoundsCheck(row - 1, col - 1)
                && hintGrid[row - 1][col - 1] != 9){
            hintGrid[row - 1][col - 1]++;
        }
        if(inBoundsCheck(row - 1, col + 1)
                && hintGrid[row - 1][col + 1] != 9){
            hintGrid[row - 1][col + 1]++;
        }
        if(inBoundsCheck(row + 1, col - 1)
                && hintGrid[row + 1][col - 1] != 9){
            hintGrid[row + 1][col - 1]++;
        }
    }

    /**
     * Prints out the field with hint numbers
     */
    public void outputGrids(){
        StringBuilder row = new StringBuilder();
        System.out.println("Field " + fieldNum + ": ");

        for(int i = 0; i < hintGrid.length; i++){
            for(int j = 0; j < hintGrid[i].length; j++){
                if(hintGrid[i][j] == 9){
                    row.append("*");
                }else{
                    row.append(hintGrid[i][j]);
                }
            }
            System.out.println(row.toString());
            row = new StringBuilder();
        }

        System.out.println();
    }
}

