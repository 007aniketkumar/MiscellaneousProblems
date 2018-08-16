package src.com.problemsolving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





class ValidPostions {
	
	int x;
	
	int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	

}








public class FrogProblem {

	// Create a n*n board

	int[][] board;
	int[] x = { 0, 0, j, -j };
	int[] y = { j, -j, 0, 0 };
	int counterMax = 0;

	static int p;
	static int q;
	static int j;

	static int globalvar = 0;
	int globalVarTemp = 0;

	public static int squareCount(int[] input1) {
		p = input1[0];
		q = input1[1];
		j = input1[2];

		List<Integer> results = new ArrayList<Integer>();

		FrogProblem FrogProblem = new FrogProblem();

		int[][] board = FrogProblem.reIntialiazeBoard();

		results = FrogProblem.operate(board);
		Collections.sort(results);

		System.out.println("print the list");
		for (Integer value : results) {

			System.out.println(value);
		}

		int counter = 1;
		for (int i = results.size() - 1; i > 0; i--) {
			if (results.get(results.size() - 1) == results.get(i - 1)) {
				counter++;
			}
		}
		return counter;
	}

	// Initialing the board with -1 , which denotes each box is unvisited.
	FrogProblem() {

		board = new int[p][q];

	}

	/**
  * 
  * 
  */
	int[][] reIntialiazeBoard() {
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < q; j++) {
				board[i][j] = -1;
			}
		}
		return board;
	}

	/**
	 * 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */

	// take any position of the matrix and check if its valid position
	boolean isValidPosition(int x, int y, int[][] board) {

		// System.out.println("The values of initial x:"+x+"the value of initial y:"+y);
		// System.out.println("BOARD VALUES ARE"+board[0][2]);

		if (x < p && x >= 0 && y < q && y >= 0 && board[x][y] == -1) {

			// System.out.println("valid position for x:"+x+"valid position for y:"+y);
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * 
	 * @param startingX
	 * @param startingY
	 */

	void traverse(int startingX, int startingY, int[][] board) {

		System.out.println("the global var is" + globalvar);
		board[startingX][startingY] = 0;
		boolean result = false;
		List<ValidPostions> validPositions = new ArrayList<ValidPostions>();
		System.out
				.println("Location to check is" + startingX + "," + startingY);
		FrogProblem.printBoard(board);
		// trueCount = 1;
		FrogProblem FrogProblem = new FrogProblem();
		// System.out.println("board[startingX][startingY]::"+board[startingX][startingY]);
		for (int i = 0; i < 4; i++) {

			int newX = startingX + x[i];
			int newY = startingY + y[i];

			// System.out.println("newX :"+newX +"newY: "+newY);
			result = FrogProblem.isValidPosition(newX, newY, board);

			if (result == true) {

				ValidPostions obj = new ValidPostions();
				obj.setX(newX);
				obj.setY(newY);
				validPositions.add(obj);
				// System.out.println("The valid path is X : "+newX+"The valid path is Y : "+newY);

			} else {
				// System.out.println("The INVALID path is X: "+newX+"The invalid path of Y: "+newY);
				System.out.println();
				continue;
			}
		}
		for (ValidPostions obj : validPositions) {

			int newPosX = obj.getX();
			int newPosY = obj.getY();
			// board[newPosX][newPosY] = 0;

			// trueCount++;
			/*
			 * if(globalvar<=trueCount){ globalvar =trueCount;
			 * System.out.println
			 * ("global variable inside loop is++++"+globalvar); }
			 */
			// System.out.println("Inside loop of valid positions :"+obj.getX()+"  "+obj.getY()+"The number of valid positions is :"+validPositions.size());
			traverse(newPosX, newPosY, board);// This counts the number of valid
												// distances that were
												// travelled;

		}

		// System.out.println("the  value of true Count is"+globalvar);

	}

	/**
  * 
  * 
  */

	List<Integer> operate(int[][] board) {

		int startingX = 0;
		int startingY = 0;
		List<Integer> results = new ArrayList<Integer>();
		FrogProblem FrogProblem = new FrogProblem();

		for (int i = 0; i < p; i++) {
			for (int j = 0; j < q; j++) {

				startingX = i;
				startingY = j;

				System.out
						.println("+++++++++++++++The new co-ordiantes passed+++++++++++++++++++ X :"
								+ startingX + "  Starting Y: " + startingY);
				
				// globalVarTemp=0;
				// resultOfEachTraversal = FrogProblem.traverse(startingX,
				// startingY,board,trueCount);
				FrogProblem.traverse(startingX, startingY, board);

				int counter = 0;
				for (int s = 0; s < p; s++) {
					for (int t = 0; t < q; t++) {

						if (board[s][t] == 0) {
							System.out.println("the counter is " + counter
									+ "@x: " + s + "@y: " + t);
							counter++;
						}
					}
				}
				System.out.println("the counter value is" + counter);
				results.add((counter));

				// System.out.println("The value retunred is++"+resultOfEachTraversal);
				board = FrogProblem.reIntialiazeBoard();
				// results.add((resultOfEachTraversal));
			}
		}

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		
		int[] input = {3,3,1};
		  int result = squareCount(input);
		  System.out.println("the final steps are"+result);
		 
	}

	public static void printBoard(int[][] board) {
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < q; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
