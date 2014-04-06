/**
 * 
 */
package com.problemsolving;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Good Problem to start Dynamic Programming . This problem can be solved using
 * recursion as well,but rather than calculating the values again and again, we use an array to store the 
 * previous calculated value to check the length of the sequence at any given index and if its greater , 
 * than update the with the new value.
 * 
 * @author aniket
 * 
 */
public class LongestIncSubSeqDP {

	
	static List<Integer> resultList = new ArrayList<Integer>();
    static  int highest =1;
	static int[] arrayInput = {0,5,11,21,7,8};
 
	//Change according to the length of the array.
	static int[] lis= new int[6];
		

	/**
	 * 
	 * 0,5,11,21,7,8 
	 * ,15,14
	 * 
	 * 
	 * 
	 */

	public static List<Integer> longestSeq() {

		
		int size = arrayInput.length;
		for (int i = 0; i < size; i++) {
			lis[i] = 1;
		}

		/*
		 * Assume the ith position is the longest sequence.Basic algo is if ith
		 * position is the longest sequence , then compare with the next
		 * position "i" and check if the length is larger. If the length is more
		 * , then the next position is the longest increasing sequence.
		 */
		List<List> mainList = new ArrayList<>();
		for (int i = 1; i < size; i++) {
     
			List<Integer> seqList = new ArrayList<>();
			//System.out.println("***********values of i "+i);
			
			for (int j = i-1; j >= 0; j--) {

				/*
				 * arrayInput[i]>arrayInput[j] ==> that say we take 10,5,12 so
				 * for i=1 condition fails since array[1]=5!<array[0]=10 when
				 * i=2 , array[2] =12 > array[0]=10 AND lis[2]<lis[0]+1 so set
				 * lis[2] = 2(becuz , lis[0]==lis[2] =1(initially set in the
				 * loop)
				 * 
				 * 0,5,11,7
				 * 
				 */
				if (arrayInput[i] > arrayInput[j] && lis[i] < lis[j] + 1) {
	
					lis[i] = lis[j] + 1;
						seqList.add(arrayInput[j]);
					}
			}mainList.add(seqList);

		}
		
		
         
		// Now find the lagest value of lis and find the value of i
		for (int i = 0; i < size - 1; i++) {
			if (highest <= lis[i + 1]) {
				highest = lis[i + 1];
			}
		}
		
		//System.out.println("the length of longest seq is"+highest);

		for (int i = 0; i < size; i++) {
			if (lis[i] == highest) {
				resultList.add(i);
			}
		}

		
		for(List<Integer> list:mainList) {
			
			if(list.size()==highest) {
		    for(Integer values:list) {
			System.out.print(values);
				
			}
		}
		}
		return resultList;
	}

	/**
	 * @param args
	 * 
	 * 0,5,11,7
	 * 
	 */
	public static void main(String[] args) {
    
    printPath();
	

	}
	
	
	/**
	 * 
	 * Print the path
	 * 
	 * 
	 */
	
	static void printPath() {

		List<List<Integer>> finalseq = new ArrayList<>();

		longestSeq();

		for (Integer indexes : resultList) {

			List<Integer> listSeq = new ArrayList<Integer>();
			int k = 0;
			for (int i = indexes; i > 0 && k < highest; i--, k++) {

				for (int j = i - 1; j >= 0; j--) {

					if (arrayInput[i] > arrayInput[j]) {

						listSeq.add(arrayInput[i]);
						i = j;

					}
				}

			}
			listSeq.add(0);

			finalseq.add(listSeq);
			
		}
		System.out.println("Maximum Length of the increasing sequence is: "+highest);
		for (List<Integer> sequences : finalseq) {
			System.out.print("\n Sequence:   "+"\n");
			for (int numbers : sequences) {
				System.out.print(numbers);
				System.out.print(",");
			}

		}

	}
}
