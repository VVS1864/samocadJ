package samoJ;

import java.util.LinkedList;

/**
 * the PrimitiveLine is a base for Shape
 * 
 * @author E.Askadullin
 * 
 */
public class PrimitiveLine {
	// public Coord c1;
	// public Coord c2;
	public LinkedList<Coord> coords;

	private PrimitiveLine(Coord c1, Coord c2) {
		// this.c1 = c1;
		// this.c2 = c2;
		coords = new LinkedList<Coord>();
		coords.add(c1);
		coords.add(c2);

	}

	PrimitiveLine(double x1, double y1, double z1, double x2, double y2, double z2) {
		this(new Coord(x1, y1, z1), new Coord(x2, y2, z2));
	}

	public LinkedList<Double> toList() {
		LinkedList<Double> ret = new LinkedList<Double>();
		// = c1.toList();
		// ret.addAll(c2.toList());
		for (Coord c : coords)
			ret.addAll(c.toList());
		return ret;
	}

	public Coord getC1() {
		return coords.getFirst();
	}

	public Coord getC2() {
		return coords.getLast();
	}
	
	// Get middle point (Coord)
	public Coord getMiddle() {
		return new Coord((getC1().x + getC2().x) / 2,
				(getC1().y + getC2().y) / 2, (getC1().z+getC2().z)/2);
	}

}
