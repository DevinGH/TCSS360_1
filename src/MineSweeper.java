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
                System.out.println("[" + Integer.parseInt(nums[0]) + "]" +  ", " + "[" + Integer.parseInt(nums[1]) + "]");
                MineSweeper newGrid = new MineSweeper(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
                System.out.println("num rows = " + newGrid.hintGrid.length);
                System.out.println("num cols = " + newGrid.hintGrid[0].length);
                newGrid.setGrid();
                newGrid.outputGrids(newGrid.hintGrid);

                for(int i = 0; i < Integer.parseInt(nums[0]); i++){
                    String[] currentLine = inputFile.nextLine().split("");

                    newGrid.findMines(currentLine, i);
                }

                newGrid.outputGrids(newGrid.hintGrid);

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
                System.out.println("Mine Found at: [" + mineX + "]" +  ", " + "[" + mineY + "]");
                hintGrid[mineY][mineX] = 9;
                mineX++;
            }else{
                mineX++;
            }
        }

    }

    private void addHints(final int row, final int col){

    }

    private void outputGrids(final int[][] grid){
        StringBuilder row = new StringBuilder();

        for(int i = 0; i < hintGrid.length; i++){
            for(int j = 0; j < hintGrid[i].length; j++){
                row.append(hintGrid[i][j]);
            }
            System.out.println(row.toString());
            row = new StringBuilder();
        }
    }
}
