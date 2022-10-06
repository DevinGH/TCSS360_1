/**
 * TCSS 360 A
 * Group Project 1: Minesweeper
 * Devin Hanson
 * 10-05-2022
 */

import java.util.Scanner;

/**
 * Steps:
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
        Scanner inputFile = new Scanner(System.in);

        String line = inputFile.nextLine();
        String[] nums = line.split(" ");

        int cols = Integer.parseInt(nums[0]);
        int rows = Integer.parseInt(nums[1]);

        if(rows <= 100 && cols <= 100){
            System.out.println("[" + rows + "]" +  ", " + "[" + cols + "]");
            MineSweeper newGrid = new MineSweeper(rows, cols);
            newGrid.setGrid();
            System.out.println("length of grid = " + newGrid.hintGrid.length);
            newGrid.outputGrids(newGrid.hintGrid);

            for(int i = 0; i < rows; i++){
                String[] currentLine = inputFile.nextLine().split("");

                newGrid.findMines(currentLine, i);
            }

            newGrid.outputGrids(newGrid.hintGrid);
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
                System.out.println("[" + mineX + "]" +  ", " + "[" + mineY + "]");
                hintGrid[mineX][mineY] = 9;
                break;
            }else{
                mineX++;
            }
        }

    }

    private void addHints(final int[][] hintGrid){

    }

    private void outputGrids(final int[][] grid){
        StringBuilder row = new StringBuilder();

        for(int i = 0; i < hintGrid.length; i++){
            for(int j = 0; j < hintGrid[i].length; j++){
                row.append(hintGrid[j][i]);
            }
            System.out.println(row.toString());
            row = new StringBuilder();
        }
    }
}
