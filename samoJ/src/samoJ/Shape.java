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
	LinkedList<DashedLine> theDashedLines;
	//LinkedList<Integer> thePointData;
	//Integer[]  thePointData;
	
	//void makePointData() {
	//	thePointData  = this.toListPred();
	//}

	public Shape() {
		theDashedLines = new LinkedList<DashedLine>();
	}

	public LinkedList<Integer> toList() {
		LinkedList<Integer> ret = new LinkedList<Integer>();
		for (DashedLine p : theDashedLines) {
			ret.addAll(p.toList());
		}
		return ret;
	}
	
	//public LinkedList<Integer> toList() {
	//return thePointData;
	//}
	//public Integer[] toList() {
		//return thePointData;
		//}
	public void add(DashedLine theP) {
		theDashedLines.add(theP);
	}

	// EXAMPLE
	public static void main(String[] args) {

		//Shape theShape = new Shape();
		//theShape.add(new PrimitiveLine(1.0 , 2.0, 3.0, 5.0, 7.0, 9.0));
		//System.out.println(theShape.toList());
	}
}
