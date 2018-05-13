package core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import samoJ.Coord;
import samoJ.Shape;
import samoJ.SnapCoord;
import samoJ.SnapType;
import samoJ.PrimitiveLine.Line;

public class Clip_algorithm {

	/**
	 * Method return all Shapes in rectangular area. The Shape may be in
	 * rectangle completely or partially. (Now LinearShapes only!!!)
	 * 
	 * @param xBL
	 *            - Bottom Left x of rect
	 * @param yBL
	 *            - Bottom Left y of rect
	 * @param xTR
	 *            - Top Right x of rect
	 * @param yTR
	 *            - Top Right y of rect
	 * @param theShapes
	 *            - All Shapes or Shapes in active sector (with optimization)
	 * @return - Shapes in rectangle
	 */
	public static LinkedList<Shape> simple_clip(float xBL, float yBL, float xTR,
			float yTR, Map<Integer, Shape> theShapes) {

		LinkedList<Shape> ReturnableShapes = new LinkedList<Shape>();
		for (Shape shape : theShapes.values()) {
			List<SnapCoord> midPoints = shape.getSnapPoints(SnapType.MidPoint);
			if (midPoints.size() > 0) {
				for (SnapCoord snap_point : midPoints) {//Edited by Vlad, find more then 1 midpoint
					//SnapCoord snap_point = midPoints.get(0);
					float x = snap_point.getX();
					float y = snap_point.getY();
					if ((xBL < x && x < xTR) && (yBL < y && y < yTR)) {
						ReturnableShapes.add(shape);
						continue;
					}
				}
			}

			for (Line snap_line : shape.getSnapLines()) {
				Coord c1 = snap_line.getC1();
				float x1 = c1.getX();
				float y1 = c1.getY();
				Coord c2 = snap_line.getC2();
				float x2 = c2.getX();
				float y2 = c2.getY();

				if ((x1 > xTR) && (x2 > xTR)) continue; // RIGHT
				if ((x1 < xBL) && (x2 < xBL)) continue; // LEFT
				if ((y1 > yTR) && (y2 > yTR)) continue; // UP
				if ((y1 < yBL) && (y2 < yBL)) continue; // DOWN

				// if first or second end of line in {BL-TR}
				if (((xBL < x1 && x1 < xTR) && (yBL < y1 && y1 < yTR))
						|| ((xBL < x2 && x2 < xTR) && (yBL < y2 && y2 < yTR))) {

					ReturnableShapes.add(shape);
					continue;// break;? only one shape ??? AER 24/01/2017
				}

				float c = x2 * y1 - x1 * y2;
				float b = x1 - x2;
				float a = y2 - y1;

				float r1 = a * xBL + b * yTR + c;
				if (r1 == 0) {
					ReturnableShapes.add(shape);
					continue;// break; ? only one shape ??? AER 24/01/2017
				}

				float r2 = a * xBL + b * yBL + c;
				if (r2 == 0) {
					ReturnableShapes.add(shape);
					continue;// break; ? only one shape ??? AER 24/01/2017
				}

				float r3 = a * xTR + b * yTR + c;
				if (r3 == 0) {
					ReturnableShapes.add(shape);
					continue;// break; ? only one shape ??? AER 24/01/2017
				}

				float r4 = a * xTR + b * yBL + c;
				if (r4 == 0) {
					ReturnableShapes.add(shape);
					continue;// break; ? only one shape ??? AER 24/01/2017
				}

				if ((r1 < 0 && r2 < 0 && r3 < 0 && r4 < 0)
						|| (r1 > 0 && r2 > 0 && r3 > 0 && r4 > 0)) {
					continue;
				}
				else {
					ReturnableShapes.add(shape);
					continue;// break; ? only one shape ??? AER 24/01/2017
				}

			}
		}

		return ReturnableShapes;
	}
	/**
	 * Clip algorithm for get lines fully enclosed in selecting area
	 * @param xBL
	 * @param yBL
	 * @param xTR
	 * @param yTR
	 * @param theShapes
	 * @return
	 */
	public static LinkedList<Shape> enclose_clip(float xBL, float yBL,
			float xTR, float yTR, Map<Integer, Shape> theShapes) {
		LinkedList<Shape> ReturnableShapes = new LinkedList<Shape>();
		for (Shape shape : theShapes.values()) {
			for (Line snap_line : shape.getSnapLines()) {
				Coord c1 = snap_line.getC1();
				float x1 = c1.getX();
				float y1 = c1.getY();
				Coord c2 = snap_line.getC2();
				float x2 = c2.getX();
				float y2 = c2.getY();

				// if first or second end of line in {BL-TR}
				if (((xBL < x1 && x1 < xTR) && (yBL < y1 && y1 < yTR))
						&& ((xBL < x2 && x2 < xTR) && (yBL < y2 && y2 < yTR))) {

					ReturnableShapes.add(shape);
					continue;
				}
			}
		}
		return ReturnableShapes;
	}
}