/******************************************************
 * This Class defines a poing on an X,Y coordinate grid. 
 *******************************************************/
import java.util.*;
import java.io.*;

public class Point {
	
	private int xCord;
	private int yCord;
	
	public Point()
	{
		xCord = 0;
		yCord = 0;
	}
	
	public Point(int x, int y) {
		xCord = x;
		yCord = y;
	}
	
	public int getX() {
		return xCord;
	}
	
	public int getY() {
		return yCord;
	}

	public Point transform(Point inPoint, int transX, int transY) {
		inPoint.xCord = inPoint.xCord - transX;
		inPoint.yCord = inPoint.yCord - transY;
		return inPoint;
	}
	
	public String toString(){
		String line = (xCord + "," + yCord);
		return line;
	}
	
	public int compareTo(Point otherPoint) {
		int choice = 0;
		if ((this.getY() * otherPoint.getX()) - (this.getX() * otherPoint.getY()) > 0) {
			choice = 1; 
		}
		else if ((this.getY() * otherPoint.getX()) - (this.getX() * otherPoint.getY()) < 0) {
			choice = -1; 
		}
		else 
			choice = 0;
		return choice;
	}
	
}
