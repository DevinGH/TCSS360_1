/**
 * TCSS 360 A
 * Group Project 1: Minesweeper
 * Devin Hanson
 * 10-05-2022
 */

import java.util.Scanner;

/**
 * Steps (for development purposes):
 * - Create int[][]
 * - Read line in from input file
 * - Get data/location of mine from current line
 * - Using int[][] add one to each safe space (0) around the location of mine (9)
 * - Print out each line of int[][], while filling in locations of mines (9) with *
 */
public class MineSweeper {
    private int numRows;
    private int numCols;
    private int[][] hintGrid;
    private static int fieldNum = 0;

    public static void main(String[] args) {
        boolean repeat = true;
        Scanner inputFile = new Scanner(System.in);

        String line = inputFile.nextLine();
        String[] nums = line.split(" ");

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

    private MineSweeper(final int rows,final int cols){
        this.numRows = rows;
        this.numCols = cols;
        this.hintGrid = new int[numRows][numCols];
    }

    private void setGrid(){
        for(int i = 0; i < hintGrid.length; i++){
            for(int j = 0; j < hintGrid[i].length; j++){
                hintGrid[i][j] = 0;
            }
        }
    }

    private void findMines(final String[] currentRow, final int mineY){
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

    private boolean inBoundsCheck(final int row, final int col){
        if(row >= 0 && row <= numRows - 1 && col >= 0 && col <= numCols - 1){
            return true;
        }else{
            return false;
        }
    }

    private void addHints(final int row, final int col){
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

    private void outputGrids(){
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
    }
}


