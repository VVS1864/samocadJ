package samoJ.PrimitiveLine;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;
import samoJ.Coord;
import samoJ.ObjectMode;
import core.Core;

/**
 * the DrawableLine is a base for Shape, is visible Line for OpenGL rendering. 
 * Global_var.N_DrawableLines - is very important variable, it is number of all DrawableLines in all Shapes,
 * each line must increase/decrease this value for stable GL rendering!
 * 
 * @author E.Askadullin
 * @author vlad
 * 
 */

public class DrawableLine extends Line{
	private Core core = Core.c;
	//2 colors for 2 coords
	private Color_rgb color_1;
	private Color_rgb color_2;
	/**
	 * line width
	 */
	private int width;
	
	private DrawableLine(ObjectMode mode, Coord c1, Coord c2, Color_rgb color, int width) {
		super(c1, c2);
		this.color_1 = this.color_2 = color;
		this.width = width;	
		if(mode == ObjectMode.New_object)	core.global.N_DrawableLines++;
	}

	public DrawableLine(ObjectMode mode, float x1, float y1, float z1, float x2, float y2,
			float z2, Color_rgb color, int width) {
		this(mode, new Coord(x1, y1, z1), new Coord(x2, y2, z2), color, width);
	}
	
	public FloatArrayList toListFloatColor() {
		FloatArrayList ret = new FloatArrayList(6);
		FloatArrayList c1 = FloatArrayList.wrap(color_1.get_float_rgb());
		FloatArrayList c2 = FloatArrayList.wrap(color_2.get_float_rgb());
		ret.addAll(c1);
		ret.addAll(c2);
		return ret;
	}
	
	public FloatArrayList toListFloatWidth() {
		FloatArrayList ret = new FloatArrayList(2);
		ret.add(width);
		ret.add(width);
		return ret;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void delDrawableLine() {
		core.global.N_DrawableLines--;
	}
}
