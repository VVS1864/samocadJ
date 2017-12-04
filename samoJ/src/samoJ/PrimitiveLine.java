package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import open_dxf_lib.Color_rgb;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * the PrimitiveLine is a base for Shape
 * 
 * @author E.Askadullin
 * 
 */
public class PrimitiveLine {
	public LinkedList<Coord> coords;
	//2 colors for 2 coords
	private Color_rgb color_1;
	private Color_rgb color_2;
	private PrimitiveLine(Coord c1, Coord c2, Color_rgb color) {
		this.color_1 = this.color_2 = color;
		coords = new LinkedList<Coord>();
		coords.add(c1);
		coords.add(c2);
		
	}

	public PrimitiveLine(double x1, double y1, double z1, double x2, double y2,
			double z2, Color_rgb color) {
		this(new Coord(x1, y1, z1), new Coord(x2, y2, z2), color);
	}

	public ArrayList<Double> toList() {
		ArrayList<Double> ret = new ArrayList<Double>();

		for (Coord c : coords)
			ret.addAll(c.toList());
		return ret;
	}

	public double[] getX1Y1X2Y2() {
		double[] r = { coords.getFirst().getX(), coords.getFirst().getY(),
				coords.getLast().getX(), coords.getLast().getY() };
		return r;
	}

	public DoubleArrayList toListDouble() {
		DoubleArrayList ret = new DoubleArrayList();
		for (Coord coord : coords)
			ret.addAll(coord.toListDouble());
		return ret;

	}
	
	public DoubleArrayList toListDoubleColor() {
		DoubleArrayList ret = new DoubleArrayList();
		ret.addAll(color_1.toListDoubleColor());
		ret.addAll(color_2.toListDoubleColor());
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
