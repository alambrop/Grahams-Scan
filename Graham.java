/**********************************************
 * This Class implements a Stack Data Structure.
 ************************************************/

import java.util.*;
public class Graham {

	Point currPoint;
	Graham prevPoint;

	public static Graham topOfStack;
	
	
	public Graham(Point inPoint) {
		currPoint = inPoint;
		prevPoint = null;
	}
	
	//pushes a new point into The stack
	public void push (Point inPoint) {
		if (topOfStack == null) 
			topOfStack = new Graham(inPoint);
		else {
			Graham newInPoint  = new Graham(inPoint);
			newInPoint.prevPoint  = topOfStack;
			topOfStack = newInPoint;
		}
			
	}
	
	//Removes a point from the stack 
	public Point pop() {
		if (topOfStack == null)
			throw new RuntimeException ("These points make up the convex Hull!");
		else {
			Point outPoint = topOfStack.currPoint;
			topOfStack = topOfStack.prevPoint;
			return outPoint;
		}
	}
	
	
	public Point getTopPoint() {
		return topOfStack.currPoint;
	}
	
	public Point getPrevPoint() {
		return topOfStack.prevPoint.currPoint;
	}
	
	
	public int orientation(Point inPoint1, Point inPoint2, Point inPoint3) {
		int choice = (inPoint2.getY() - inPoint1.getY()) * (inPoint3.getX() - inPoint2.getX())  -  (inPoint2.getX() - inPoint1.getX()) * (inPoint3.getY() - inPoint2.getY()) ;
		
		if (choice > 0)
			choice = 1;
		
		else if (choice < 0)
			choice = -1;
		
		else 
			choice = 0;
		
		
		return choice;
	}
	
}
