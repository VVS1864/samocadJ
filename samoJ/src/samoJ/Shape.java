package samoJ;

import java.util.LinkedList;

/**
 * General class for all shapes
 * 
 * @author E.Askadullin
 * 
 */

public class Shape {
	// Displayed Lines
	LinkedList<PrimitiveLine> PrimLines;
	double factor;
	int[] mask;

	public Shape() {
		PrimLines = new LinkedList<PrimitiveLine>();
		System.out.println("The constructor Shape()");
	}

	public LinkedList<Integer> toList() {
		LinkedList<Integer> ret = new LinkedList<Integer>();
		for (PrimitiveLine p : PrimLines) {
			ret.addAll(p.toList());
		}
		return ret;
	}
	

	public void add(PrimitiveLine theP) {
		PrimLines.add(theP);
	}

	// EXAMPLE
	public static void main(String[] args) {

		Shape theShape = new Shape();
		theShape.add(new PrimitiveLine(1, 2, 3, 5, 7, 9));
		System.out.println(theShape.toList());
	}
}
