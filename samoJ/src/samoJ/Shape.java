package samoJ;

import java.util.LinkedList;

import core.Global_var;

/**
 * General class for all shapes
 * 
 * @author E.Askadullin
 * 
 */

public class Shape {
	// Displayed Lines
	public LinkedList<PrimitiveLine> PrimLines;
	public LinkedList<PrimitiveLine> SnapLines;
	public LinkedList<Coord> SnapPoints;
	double factor;
	int[] mask;

	public Shape() {
		PrimLines = new LinkedList<PrimitiveLine>();
		SnapLines = new LinkedList<PrimitiveLine>();
		SnapPoints= new LinkedList<Coord>();
		Global_var.theShapes.put(Global_var.current_ID, this);
		Global_var.current_ID++;
		//System.out.println("The constructor Shape()");
	}

	public LinkedList<Double> toList() {
		LinkedList<Double> ret = new LinkedList<Double>();
		for (PrimitiveLine p : PrimLines) {
			ret.addAll(p.toList());
		}
		return ret;
	}
	

	public void add(PrimitiveLine theP) {
		PrimLines.add(theP);
	}
	
	/**
	 * Method for add new snap line for this Shape. Middle point of line will be 
	 * calculated automatically and will be add to SnapPoints
	 * @param new_snap_line
	 */
	public void add_snap_line(PrimitiveLine new_snap_line) {
		SnapLines.add(new_snap_line);
		
		//Coord of line middle point
		
		double xc = (new_snap_line.getC1().x + new_snap_line.getC2().x)/2;
		double yc = (new_snap_line.getC1().y + new_snap_line.getC2().y)/2;
		double zc = (new_snap_line.getC1().z + new_snap_line.getC2().z)/2;
		SnapPoints.add(new Coord(xc, yc, zc));
	}

	// EXAMPLE
	public static void main(String[] args) {

		Shape theShape = new Shape();
		theShape.add(new PrimitiveLine(1, 2, 3, 5, 7, 9));
		System.out.println(theShape.toList());
	}
}
