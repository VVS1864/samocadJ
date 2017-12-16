package core;

import java.util.LinkedList;
import java.util.List;

import samoJ.Coord;
import samoJ.Shape;
import samoJ.SnapCoord;
import samoJ.SnapType;
import samoJ.PrimitiveLine.Line;

/**
 * Method get_snap for find snap point and snap type. There are 4 types of snap:
 * 
 * 1 - end point, 2 - middle point, 3 - intersection point, 4 - near point, 0 -
 * NO SNAP.
 */
public class Get_snap {
	/**
	 * 
	 * @param snapLines
	 *            - two lines fo finding intersection
	 * @return point of intersection
	 */

	public static Coord getLinesIntersection(
			LinkedList<Line> snapLines) {
		if (snapLines.size() != 2)
			return null;
		return getLinesIntersection(snapLines.getFirst(), snapLines.getLast());
	}
	//TODO min scale trouble
	public static Coord getLinesIntersection(Line s1,
			Line s2) {

		float x1 = s1.getC1().getX();
		float y1 = s1.getC1().getY();
		float x2 = s1.getC2().getX();
		float y2 = s1.getC2().getY();

		float x3 = s2.getC1().getX();
		float y3 = s2.getC1().getY();
		float x4 = s2.getC2().getX();
		float y4 = s2.getC2().getY();

		float y43 = (y4 - y3);
		float x21 = (x2 - x1);
		float y21 = (y2 - y1);
		float x43 = (x4 - x3);
		float x13 = (x1 - x3);
		float y13 = (y1 - y3);

		float znam = y43 * x21 - x43 * y21;
		// System.out.println("znam = "+znam);
		if (znam == 0)
			return null;

		float Ua = (x43 * y13 - y43 * x13) / znam;
		// System.out.println("Ua = "+Ua);
		if ((Ua < 0) || (Ua > 1)) // intersection out of line end points of line
									// 1
			return null;

		float Ub = (x21 * y13 - y21 * x13) / znam;
		// System.out.println("Ub = "+Ub);
		if ((Ub > 1) || (Ub < 0)) // intersection out of line end points of line
									// 2
			return null;

		float x = x1 + Ua * x21;
		float y = y1 + Ua * y21;

		return new Coord(x, y);

	}

	/**
	 * Method get_snap for find snap point and snap type. There are 4 types of
	 * snap:
	 * 
	 * 1 - end point, 2 - middle point, 3 - intersection point, 4 - near point,
	 * 0 - NO SNAP.
	 * 
	 * @param cursor_coords
	 * @param shapes
	 *            - List of Shapes for find snap_point among them
	 * @param snap_keys
	 *            from Global_var - {end_point, midpoint, intersection,
	 *            near_point}, 1 - snap on, 0 - snap off
	 * @return - {number of snap_type, x, y, z} xyz of snap point
	 */
	public static SnapCoord get_snap(float[] cursor_coords,
			float snap_distance, List<Shape> shapes,
			LinkedList<SnapType> snap_keys) {

		List<Shape> retShapes = new LinkedList<Shape>();

		Float min_Distance = snap_distance;
		SnapCoord retSnapCoord = null;

		for (SnapType snapType : snap_keys) {

			if (snapType == SnapType.Intersection) {
				//System.out.println("shapes.size()=" + shapes.size());
				if (shapes.size() < 2)
					return null;

				LinkedList<Line> theOtherSnapLines = new LinkedList<Line>();
				for (Shape sh1: shapes.subList(1, shapes.size())) // without last element
				    {
					Shape sh = shapes.get(shapes.indexOf(sh1)-1);
					// get SnapLines from other Shapes
					theOtherSnapLines.addAll(sh.getSnapLines());
					
					// Find Intersection
					for (Line p1 : sh1.getSnapLines())
						// from current shape

						for (Line p2 : theOtherSnapLines) { // List
																		// from
																		// other
																		// shapes
							Coord c = getLinesIntersection(p1, p2);
							
							if (c != null) {
								//System.out.println("cursor_coords=["+cursor_coords[0]+" ,"+cursor_coords[1]+"] �="+�.toString()+" , min_Distance="+min_Distance);
								float Distance = (float)Math.max(
										Math.abs(c.getX()
												- cursor_coords[0]),
										Math.abs(c.getY()
												- cursor_coords[1]));
								//System.out.println(" Distance="+Distance+" , min_Distance="+min_Distance);
								if (Distance < min_Distance) {
									
									min_Distance = Distance;
									retShapes.clear();
									retShapes.add(sh);
									retSnapCoord = new SnapCoord(snapType, c);
								}
							}
						}
				}

			} else
				for (Shape sh : shapes) {
					for (Coord c : sh.getSnapPoints(snapType)) {
						float Distance =  (float)Math.max(
								Math.abs(c.getX() - cursor_coords[0]),
								Math.abs(c.getY() - cursor_coords[1]));
						if (Distance < min_Distance) {
							min_Distance = Distance;
							retShapes.clear();
							retShapes.add(sh);
							retSnapCoord = new SnapCoord(snapType, c);
						}
					}
				}
			
			if (min_Distance < snap_distance) {
				shapes = retShapes; /// ��� ����������..
				return retSnapCoord;
			}
		}
		return retSnapCoord;
	}

	//public static void main(String[] args) {

		//PrimitiveLine p1 = new PrimitiveLine(1, 1, 0, 5, 5, 0);
		//PrimitiveLine p2 = new PrimitiveLine(1, 10, 0, 6, 4, 0);
		//Coord sc = getLinesIntersection(p1, p2);
		//System.out.println(sc);

	//}
}
