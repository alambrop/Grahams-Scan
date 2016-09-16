/************************************************************************
 * This project implements Graham's Scan to solve the Convex Hull problem.
 * 
 *************************************************************************/

import java.util.*;
import java.io.*;

public class Main {
	
	public static void main (String [] args) throws IOException {
		
		String fileName = "points.txt";
		Scanner inputFile = new Scanner(new File(fileName));
		Scanner inputFile2 = new Scanner(new File(fileName));
			
		inputFile.useDelimiter(",");
		inputFile2.useDelimiter(",");
		
		Point leftMost;
		Point temp;
		Point temp2;
				
		int transFormX = 0;
		int transFormY = 0;
		int locationOfCurrMin = 0;
		int amount = 0;
		int count = 0;
		int choice = 0;
		int choice2;
		int x;
		int y;
		
		/*********************************************
		 * Reads the File to see how many lines it has
		 ********************************************/
		while (inputFile.hasNextLine()) {
			inputFile.nextLine();
			
			amount++;
		}
		inputFile.close();
		
		//Creates an array of points
		Point [] points = new Point[amount];
		Point ref;
		
		
		/************************************************************************
		 * Reads each line, Tokenizes and adds each point into the array "Points"
		 ***********************************************************************/
		while (inputFile2.hasNextLine()) {
			
			String line = inputFile2.nextLine();
			StringTokenizer stok = new StringTokenizer(line, ", ");
				
			while (stok.hasMoreTokens()) {
				x = Integer.parseInt(stok.nextToken());
				y = Integer.parseInt(stok.nextToken());
				ref = new Point(x,y);
				points[count] = ref;
				count++;	
			}
		}
		inputFile2.close();
		
		
		//Prints out the array
		for (int i = 0; i < points.length; i++ ) {
			System.out.println(points[i].toString());
		}
				
		
		/************************************
		 * Finds left_Most Point sequentially
		 ***********************************/
		//System.out.println(points.length);
		leftMost = points[0];
		for (int b = points.length - 1; b > 0; b--) {
			
			for (int j = 1; j <= b; j++) {
				if (points[j].getX() < leftMost.getX()) {
					leftMost = points[j];
					locationOfCurrMin = j;
				}
			}	
		}	
		
		leftMost = points[locationOfCurrMin];	
		transFormX = leftMost.getX();
		transFormY = leftMost.getY();
		
		/****************************************
		 * Puts the left most on top of the array
		 ***************************************/
		temp2 = points[0];
		points[0] = leftMost;
		points[locationOfCurrMin] = temp2;
		
		
		/***************************************************
		 * Transforms each point and prints out the new ones
		 ***************************************************/
		System.out.println("\nAfter Transformation");
		for (int a = 0; a < points.length; a++) {
			points[a] = points[a].transform(points[a], transFormX, transFormY);
			System.out.println(points[a].toString());
		}
		
		
		/****************************
		 * Sorts based on orientation
		 ***************************/
		System.out.println("\nSorted by Orientation");
		for (int a = 0; a < points.length; a++) {
			for (int b = a + 1; b < points.length; b++) {
				choice = points[a].compareTo(points[b]);
				if (choice == 1) {
					temp = points[b];
					points[b] = points[a];
					points[a] = temp;
				}	
			}
			System.out.println(points[a].toString());
		}	
		
		Graham convexHull = new Graham(points[0]);
		convexHull.push(points[1]);
		convexHull.push(points[2]);
		
		
		/*********************************************************************************
		 * Adds the correct points into the convex hull and takes the incorrect points out
		 *********************************************************************************/
		for (int i = 3; i < points.length; i++) {
			choice2 = convexHull.orientation(convexHull.getPrevPoint(), convexHull.getTopPoint(), points[i]);
			if (choice2 == 1) {
				convexHull.pop();
				convexHull.push(points[i]);
			}
			else {
				convexHull.push(points[i]);
			}
			
		}
		
		//Prints out the convex hull
		System.out.println("\n\n Convex Hull:");
		System.out.println(points[0]);
		while (convexHull != null) {
			System.out.println(convexHull.pop());
		}
	
		
	}	
}
