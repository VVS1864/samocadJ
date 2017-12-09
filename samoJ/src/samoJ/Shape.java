package samoJ;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;
import open_dxf_lib.DXF_file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import core.Global_var;
import core.Values;


/**
 * General class for all shapes
 * 
 * @author E.Askadullin
 * 
 */

public class Shape {
	// Displayed Lines
	LinkedList<PrimitiveLine> PrimLines;
	// Snap lines
	LinkedList<PrimitiveLine> SnapLines;
	// Snap points
	LinkedList<SnapCoord> SnapPoints;
	// Properties
	protected float factor;
	protected Dash dash;
	/**
	 * One color of all lines of Shape. TODO - Every primitive line of Shape must have own color, 
	 * then this value is null.
	 */
	protected Color_rgb color;
	// Unique object ID
	public int ID;
	
	public Shape(ObjectMode mode) {
		PrimLines = new LinkedList<PrimitiveLine>();
		SnapLines = new LinkedList<PrimitiveLine>();
		SnapPoints = new LinkedList<SnapCoord>();
		if(mode == ObjectMode.New_object){
			Global_var.theShapes.put(Global_var.current_ID, this);
			this.ID = Global_var.current_ID;
			Global_var.current_ID++;
		}
		// System.out.println("The constructor Shape()");
	}

	protected ArrayList<Float> toList() {
		ArrayList<Float> ret = new ArrayList<Float>();
		for (PrimitiveLine p : PrimLines)
			ret.addAll(p.toList());
		return ret;
	}

	public FloatArrayList toListFloat() {
		FloatArrayList ret = new FloatArrayList(PrimLines.size()*6);
		for (PrimitiveLine p : PrimLines) {
			ret.addAll(p.toListFloat());
			
		}
		return ret;
	}
	
	public FloatArrayList toListFloatColor() {
		FloatArrayList ret = new FloatArrayList(PrimLines.size()*6);
		for (PrimitiveLine p : PrimLines)
			ret.addAll(p.toListFloatColor());
		return ret;
	}
	
	public FloatArrayList toListFloatWidth() {
		FloatArrayList ret = new FloatArrayList(PrimLines.size()*2);
		for (PrimitiveLine p : PrimLines)
			ret.addAll(p.toListFloatWidth());
		return ret;
	}
	
	public FloatArrayList toListFloatData() {
		FloatArrayList ret = new FloatArrayList();
		for (PrimitiveLine p : PrimLines)
			ret.addAll(p.toListFloatData());
		return ret;
	}

	protected void add(PrimitiveLine theP) {
		PrimLines.add(theP);
	}

	/**
	 * Method for add new snap line for this Shape. Middle point of line will be
	 * calculated automatically and will be add to SnapPoints
	 * 
	 * @param new_snap_line
	 */
	protected void add_snap_line(PrimitiveLine new_snap_line) {
		SnapLines.add(new_snap_line);
		/*
		 * for (Coord c : new_snap_line.coords) SnapPoints.add(new
		 * SnapCoord(SnapType.EndPoint, c));
		 */
		SnapPoints.add(new_snap_line.getMiddle());
	}

	public List<PrimitiveLine> getSnapLines() {
		return Collections.unmodifiableList(SnapLines);
	}

	public List<SnapCoord> getSnapPoints(SnapType st) {
		List<SnapCoord> ll = new LinkedList<SnapCoord>();
		switch (st) {
		case MidPoint:
			return SnapPoints;

		case EndPoint: {

			for (PrimitiveLine p : SnapLines) {
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
	
	public void save_to_DXF(DXF_file f){
		
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
