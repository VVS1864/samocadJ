import java.util.LinkedList;

/**
 * General class for all shapes
 * 
 * @author E.Askadullin
 *
 */
public class Shape {
	// Displayed Lines
	LinkedList<PrimitiveLine> thePrimLines;

	public Shape()
	{
		thePrimLines = new LinkedList<PrimitiveLine>();
	}

	public LinkedList<Float> toList() {
		LinkedList<Float> ret = new LinkedList<Float>();
		for (PrimitiveLine p : thePrimLines) {
			ret.addAll(p.toList());
		}
		return ret;
	}

	public void add(PrimitiveLine theP) {
		thePrimLines.add(theP);
	}

	
	// EXAMPLE
	public static void main(String[] args) {

		Shape theShape = new Shape();
		theShape.add(new PrimitiveLine(1.0f, 2.0f, 3.0f, 5.0f, 7.0f, 9.0f));
		System.out.println(theShape.toList());
	}
}
