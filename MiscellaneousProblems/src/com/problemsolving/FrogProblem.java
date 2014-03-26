package com.problemsolving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * 
 * @author aniket
 *
 *
 *Take the size from user, 
 *   initially assuming 3*3
 *   allowed steps would be  :
 *      x[] ={0,0,d,-d}
 *      y[] ={d,-d,0,0}
 *      
 *   isallowed(); ->Check for each step , if yes then return true is else
 *                 Maintain a global counter for each starting point 
 *                 
 *                 MaxCounter
 *
 *EachSquare on the Matrix is a starting point.
 */


public class FrogProblem {

	
	//Create a n*n board
	
	int[][] board;
	int []x = {0,0,j,-j};
	int []y ={j,-j,0,0};
	int counterMax = 0;	
	
	static int p;
	static int q;
	static int j;
	
	
	
	
	
	
	
	
	public static int squareCount(int[] input1)
	{
	p =input1[0];
	q =input1[1];
	j= input1[2];

	List<Integer> results = new ArrayList<Integer>();

	FrogProblem frogProblem = new FrogProblem();

	int[][] board = frogProblem.reIntialiazeBoard();

	
	results = frogProblem.operate(board);
	Collections.sort(results);

	System.out.println("print the list");
	for(Integer value:results) {

	System.out.println(value);
	}

	int counter =1;
	for(int i=results.size()-1;i>0;i--) {
	if(results.get(results.size()-1)==results.get(i-1)) {
	counter++;
	}
	}
	return counter;
	}


	
	
	
	
	
	
	
	//Initialing the board with -1 , which denotes each box is unvisited.
	FrogProblem() {
     
		board = new int[p][q];

	}
	
	
	/**
	 * 
	 * 
	 */
	int[][] reIntialiazeBoard() {
		for(int i=0;i<p;i++) {
			for(int j=0;j<q;j++) {
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
	
	//take any position of the matrix and check if its valid position
	boolean isValidPosition(int x, int y,int[][] board) {
		
		
		//System.out.println("The values of initial x:"+x+"the value of initial y:"+y);
	    //System.out.println("BOARD VALUES ARE"+board[0][2]);
		
		if(x<p && x>=0 && y<q && y>=0 && board[x][y]==-1) {
			
			//System.out.println("valid position for x:"+x+"valid position for y:"+y);
			return true;
		}
		else return false;
	}
	
	
	/**
	 * 
	 * 
	 * @param startingX
	 * @param startingY
	 */
	
	
	int traverse(int startingX, int startingY,int[][]board) {
		
		board[startingX][startingY]  = 0;
		boolean result =false;
		List<ValidPostions> validPositions = new ArrayList<>();
		int trueCount = 1;
		FrogProblem frogProblem = new FrogProblem();
		//System.out.println("board[startingX][startingY]::"+board[startingX][startingY]);
		for(int i=0;i<4;i++) {
			
			int newX = startingX+x[i];
			int newY = startingY+y[i];
			
			//System.out.println("newX :"+newX +"newY: "+newY);
			result = frogProblem.isValidPosition(newX, newY,board);
			
			
			
			if(result==true) {
				
				ValidPostions obj = new ValidPostions();
				obj.setX(newX);
				obj.setY(newY);
				validPositions.add(obj);
				System.out.println("The valid path is X : "+newX+"The valid path is Y : "+newY);
				
				
				
			} else
			{
				System.out.println("The INVALID path is X: "+newX+"The invalid path of Y: "+newY);
				System.out.println();
				continue;
			}
		}
		for(ValidPostions obj:validPositions) {
			
			 int newPosX = obj.getX();
			 int newPosY = obj.getY();
			 //board[newPosX][newPosY] = 0;

			 return trueCount+ traverse(newPosX,newPosY,board);//This counts the number of valid distances that were travelled;

		}
		
		
		
		return 0;
		
		
		
	}
	
	
	
	/**
	 * 
	 * 
	 */
	
	List<Integer> operate(int[][] board) {
		
		
		int resultOfEachTraversal =0;
		int startingX =0;
		int startingY =0;
		FrogProblem frogProblem = new FrogProblem();
		List<Integer> results = new ArrayList<>();
		
				for(int i =0;i<p;i++) {
			 for(int j=0;j<q;j++) {
		 
				 startingX = i;
				 startingY =j;
				 
		   System.out.println("+++++++++++++++The new co-ordiantes passed+++++++++++++++++++ X :"+startingX+"  Starting Y: "+startingY);
		   resultOfEachTraversal = frogProblem.traverse(startingX, startingY,board);
		   System.out.println("The value retunred is++"+resultOfEachTraversal);
		board =   frogProblem.reIntialiazeBoard();
		   results.add((resultOfEachTraversal));   
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
	
		
}


class ValidPostions{
	int x;
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
	int y;
	
}
