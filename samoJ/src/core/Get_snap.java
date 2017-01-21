package core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import samoJ.Coord;
import samoJ.Shape;
import samoJ.SnapCoord;
import samoJ.SnapType;

/**
 * Method get_snap for find snap point and snap type. There are 4 types of snap:
 * 
 * 1 - end point, 2 - middle point, 3 - intersection point, 4 - near point, 0 -
 * NO SNAP.
 */
public class Get_snap {

	/**
	 * Method get_snap for find snap point and snap type. There are 4 types of
	 * snap:
	 * 
	 * 1 - end point, 2 - middle point, 3 - intersection point, 4 - near point,
	 * 0 - NO SNAP.
	 * 
	 * @param cursor_coords
	 * @param Shapes
	 *            - List of Shapes for find snap_point among them
	 * @param snap_keys
	 *            from Global_var - {end_point, midpoint, intersection,
	 *            near_point}, 1 - snap on, 0 - snap off
	 * @return - {number of snap_type, x, y, z} xyz of snap point
	 */
	public static SnapCoord get_snap(double[] cursor_coords,
			double snap_distance, List<Shape> Shapes,
			HashSet<SnapType> snap_keys) {
		//
		// System.out.println("get_snap, snap_distance="+ snap_distance);
		// System.out.println("cursor_coords = "+cursor_coords[0]+
		// " "+cursor_coords[1]);
		// Coord cursor = new Coord((int)cursor_coords[0],
		// (int)cursor_coords[1]);
		List<Shape> retShapes = new LinkedList<Shape>();

		Double min_Distance = snap_distance;
		SnapCoord retSC = null;

		for (SnapType st : snap_keys) {
			for (Shape sh : Shapes) {
				for (Coord c : sh.getSnapPoints(st)) {
					Double Distance = Math.max(
							Math.abs(c.getX() - cursor_coords[0]),
							Math.abs(c.getY() - cursor_coords[1]));
					if (Distance < min_Distance) {
						min_Distance = Distance;
						retShapes.clear();
						retShapes.add(sh);
						retSC = new SnapCoord(st, c);
					}
				}
			}
			if (min_Distance < snap_distance) {
				Shapes = retShapes; // ����������
									// ������ �����
				return retSC;
			}
		}
		return retSC;
	}
}
