package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;

import java.util.ArrayList;
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

	public ArrayList<Double> toList() {
		ArrayList<Double> ret = new ArrayList<Double>();

		for (Coord c : coords)
			ret.addAll(c.toList());
		return ret;
	}
	
	public DoubleArrayList toListDouble(){
		DoubleArrayList ret = new DoubleArrayList();
		for (Coord coord : coords)
			ret.addAll(coord.toListDouble());
		return ret;
		
	}

	public Coord getC1() {
		return coords.getFirst();
	}

	public Coord getC2() {
		return coords.getLast();
	}
	
	// Get middle point (Coord)
	public SnapCoord getMiddle() {
		return new SnapCoord(SnapType.MidPoint,(getC1().getX() + getC2().getX()) / 2,
				(getC1().getY() + getC2().getY()) / 2, (getC1().getZ()+getC2().getZ())/2);
	}
	
	@Override
	public String toString(){
		return coords.toString();
	}
}
