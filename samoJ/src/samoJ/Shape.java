package samoJ;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;
import open_dxf_lib.DXF_file;
import samoJ.PrimitiveLine.DrawableLine;
import samoJ.PrimitiveLine.Line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import core.Core;
import core.Global_var;


/**
 * General class for all shapes
 * 
 * @author E.Askadullin
 * 
 */

public class Shape {
	private Core core = Core.c;
	// Displayed Lines
	LinkedList<DrawableLine> PrimLines;
	// Snap lines
	LinkedList<Line> SnapLines;
	// Snap points
	LinkedList<SnapCoord> SnapPoints;
	// Properties
	protected int width;
	protected float factor;
	protected Dash dash;
	/**
	 * One color of all lines of Shape. TODO - Every primitive line of Shape must have own color, 
	 * then this value is null.
	 */
	protected Color_rgb color;
	// Unique object ID
	public int ID;
	protected ObjectMode mode;
	
	public Shape(ObjectMode mode) {
		PrimLines = new LinkedList<DrawableLine>();
		SnapLines = new LinkedList<Line>();
		SnapPoints = new LinkedList<SnapCoord>();
		this.mode = mode;
		if(mode == ObjectMode.New_object){
			
			core.global.theShapes.put(core.global.current_ID, this);
			this.ID = core.global.current_ID;
			core.global.current_ID++;
		}
		// System.out.println("The constructor Shape()");
	}

	protected ArrayList<Float> toList() {
		ArrayList<Float> ret = new ArrayList<Float>();
		for (DrawableLine p : PrimLines)
			ret.addAll(p.toList());
		return ret;
	}

	public FloatArrayList toListFloat() {
		FloatArrayList ret = new FloatArrayList(PrimLines.size()*6);
		for (DrawableLine p : PrimLines) {
			ret.addAll(p.toListFloat());
			
		}
		return ret;
	}
	
	public FloatArrayList toListFloatColor() {
		FloatArrayList ret = new FloatArrayList(PrimLines.size()*6);
		for (DrawableLine p : PrimLines)
			ret.addAll(p.toListFloatColor());
		return ret;
	}
	
	public FloatArrayList toListFloatWidth() {
		FloatArrayList ret = new FloatArrayList(PrimLines.size()*2);
		for (DrawableLine p : PrimLines)
			ret.addAll(p.toListFloatWidth());
		return ret;
	}
	
	protected void add(DrawableLine theP) {
		PrimLines.add(theP);
	}

	/**
	 * Method for add new snap line for this Shape. Middle point of line will be
	 * calculated automatically and will be add to SnapPoints
	 * 
	 * @param new_snap_line
	 */
	protected void add_snap_line(Line new_snap_line) {
		SnapLines.add(new_snap_line);
		/*
		 * for (Coord c : new_snap_line.coords) SnapPoints.add(new
		 * SnapCoord(SnapType.EndPoint, c));
		 */
		SnapPoints.add(new_snap_line.getMiddle());
	}
	
	protected void add_snap_point(SnapCoord new_snap_point) {
		SnapPoints.add(new_snap_point);
	}

	public List<Line> getSnapLines() {
		return Collections.unmodifiableList(SnapLines);
	}

	public List<SnapCoord> getSnapPoints(SnapType st) {
		List<SnapCoord> ll = new LinkedList<SnapCoord>();
		switch (st) {
		case MidPoint:
			return SnapPoints;

		case EndPoint: {

			for (Line p : SnapLines) {
				for (Coord c : p.coords) {
					ll.add(new SnapCoord(st, c));
				}
			}
			return Collections.unmodifiableList(ll);
		}

		default:
			return ll;

		}

	}
	public float[] getPreviewData(){
		FloatArrayList listFloat = new FloatArrayList(PrimLines.size()*6);
		listFloat.addAll(toListFloat());
		return listFloat.elements();
	}
	
	public int get_width(){
		return width;
	}
	
	public void save_to_DXF(DXF_file f){
	}
	
	public void delShape(){
		for (DrawableLine p : PrimLines) {
			p.delDrawableLine();
		}
			
	}

	// EXAMPLE
	/*
	public static void main(String[] args) {

		Shape theShape = new Shape();
		theShape.add(new PrimitiveLine(1, 2, 3, 5, 7, 9));
		theShape.add_snap_line(new PrimitiveLine(1, 2, 3, 5, 7, 9));
		System.out.println(theShape.toList());
		System.out.println(theShape.getSnapPoints(SnapType.MidPoint));
		System.out.println(theShape.getSnapPoints(SnapType.EndPoint));

	}*/
}
