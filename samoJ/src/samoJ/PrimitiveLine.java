package samoJ;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;

import java.util.ArrayList;
import java.util.LinkedList;

import core.Global_var;

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
	//line width
	private int width;
	
	private PrimitiveLine(Coord c1, Coord c2, Color_rgb color, int width) {
		
		this.color_1 = this.color_2 = color;
		this.width = width;
		coords = new LinkedList<Coord>();
		coords.add(c1);
		coords.add(c2);
		
	}

	public PrimitiveLine(float x1, float y1, float z1, float x2, float y2,
			float z2, Color_rgb color, int width) {
		this(new Coord(x1, y1, z1), new Coord(x2, y2, z2), color, width);
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
	
	public FloatArrayList toListFloatColor() {
		FloatArrayList ret = new FloatArrayList(6);
		FloatArrayList c1 = FloatArrayList.wrap(new float[] {color_1.get_r(), color_1.get_g(), color_1.get_b()});
		FloatArrayList c2 = FloatArrayList.wrap(new float[] {color_2.get_r(), color_2.get_g(), color_2.get_b()});
		ret.addAll(c1);
		ret.addAll(c2);
		return ret;

	}
	
	public FloatArrayList toListFloatData() {
		FloatArrayList ret = new FloatArrayList();
		ret.addAll(coords.get(0).toListFloat());
		ret.addAll(FloatArrayList.wrap(new float[] {color_1.get_r(), color_1.get_g(), color_1.get_b()}));
		ret.add(width);
		ret.addAll(coords.get(1).toListFloat());
		ret.addAll(FloatArrayList.wrap(new float[] {color_2.get_r(), color_2.get_g(), color_2.get_b()}));
		ret.add(width);
		return ret;
	}
	public FloatArrayList toListFloatWidth() {
		FloatArrayList ret = new FloatArrayList(2);
		ret.add(width);
		ret.add(width);
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
	
	public int getWidth() {
		return this.width;
	}

	@Override
	public String toString() {
		return coords.toString();
	}
}
