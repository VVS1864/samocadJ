package samoJ.PrimitiveLine;

import java.util.ArrayList;
import java.util.LinkedList;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import samoJ.Coord;
import samoJ.SnapCoord;
import samoJ.SnapType;
/**
 * Entity for basic Line snap and data. Is not drawable line. 
 * @author vlad
 *
 */
public class Line {
	public LinkedList<Coord> coords;
	
	public Line(Coord c1, Coord c2) {
		coords = new LinkedList<Coord>();
		coords.add(c1);
		coords.add(c2);
		
	}

	public Line(float x1, float y1, float z1, float x2, float y2,
			float z2) {
		this(new Coord(x1, y1, z1), new Coord(x2, y2, z2));
	}

	public ArrayList<Float> toList() {
		ArrayList<Float> ret = new ArrayList<Float>();

		for (Coord c : coords)
			ret.addAll(c.toList());
		return ret;
	}

	public float[] getX1Y1X2Y2() {
		float[] r = { coords.getFirst().getX(), coords.getFirst().getY(),
				coords.getLast().getX(), coords.getLast().getY() };
		return r;
	}

	public FloatArrayList toListFloat() {
		FloatArrayList ret = new FloatArrayList(6);
		for (Coord coord : coords)
			ret.addAll(coord.toListFloat());
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
		return new SnapCoord(SnapType.MidPoint, (getC1().getX() + getC2()
				.getX()) / 2, (getC1().getY() + getC2().getY()) / 2, (getC1()
				.getZ() + getC2().getZ()) / 2);
	}
	
	@Override
	public String toString() {
		return coords.toString();
	}
}
