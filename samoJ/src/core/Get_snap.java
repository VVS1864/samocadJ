package core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import samoJ.Coord;
import samoJ.PrimitiveLine;
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
		Coord cursor = new Coord((int)cursor_coords[0], (int)cursor_coords[1]);
		List<Shape> retShapes = new LinkedList<Shape>();
		Integer min_Distance = (int) snap_distance;
		SnapCoord retSC = null;

		// ******* EndPoint **********
		if (snap_keys.contains(SnapType.EndPoint)) {
			for (Shape sh : Shapes) {
				for (PrimitiveLine p : sh.SnapLines) {
					for (Coord c : p.coords) {
						Integer Distance =  Math.max(
								Math.abs(c.x - cursor.x),
								Math.abs(c.y - cursor.y));
						if (Distance < snap_distance) {
							//min_Distance = Distance;
							retShapes.clear();
							retShapes.add(sh);
							retSC = new SnapCoord(SnapType.EndPoint, c);
							////
							Shapes = retShapes; // ���������� ������ �����
							return retSC;
							////
						}
					}
				}
			}
			/*
			if (min_Distance < snap_distance) {
				System.out.println("end snap");
				Shapes = retShapes; // ���������� ������ �����
				return retSC;
				
			}*/
		}
		
		if (snap_keys.contains(SnapType.MidPoint)) {
			for (Shape sh : Shapes) {
				for (Coord c : sh.SnapPoints) {
					Integer Distance =  Math.max(
							Math.abs(c.x - cursor.x),
							Math.abs(c.y - cursor.y));
					if (Distance < min_Distance) {
						min_Distance = Distance;
						retShapes.clear();
						retShapes.add(sh);
						retSC = new SnapCoord(SnapType.MidPoint, c);
					}
				}
			}
			if (min_Distance < snap_distance) {
				Shapes = retShapes; // ���������� ������ �����
				return retSC;
			}
		}
		
		
		if(snap_keys.contains(SnapType.Intersection)){
			
		}

		return retSC;
	}
}
