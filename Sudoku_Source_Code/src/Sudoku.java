import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {

    public static Board Main_Board;
    
    public static int[][] inputBoards(int[][] data) {
    		
    		Cell [][] board = new Cell[Sudoku_window.dimension][Sudoku_window.dimension];
			for (int i = 0; i < Sudoku_window.dimension; i++) {
				for (int j = 0; j < Sudoku_window.dimension; j++) {
					
					board[i][j] = new Cell(i,j,data[i][j]);
				}
			}
			
			Main_Board = new Board(board);
			
			//System.out.println(Main_Board.toString());
			//System.out.println("After Backtrack_fwck:\n\n\n");
			
			BackTrack_FWCK back_fwck = new BackTrack_FWCK(Main_Board);
			back_fwck.Initial_Forward_Checking();
			
			back_fwck.BackTrack();
			
			data = back_fwck.get_Board();
			
			//System.out.println(Main_Board.toString());
			
			//System.out.println(Main_Board.Complete());
			
			return data;
	}
    
}
