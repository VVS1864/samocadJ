package core;

import java.util.List;
import samoJ.Shape;
/**
 * Method get_snap for find snap point and snap type. There are 4 types of snap: 
 * 
 * 1 - end point, 
 * 2 - middle point, 
 * 3 - intersection point, 
 * 4 - near point, 
 * 0 - NO SNAP.
 */
public class Get_snap {
	/**
	 * Method get_snap for find snap point and snap type. There are 4 types of snap: 
	 * 
	 * 1 - end point, 
	 * 2 - middle point, 
	 * 3 - intersection point, 
	 * 4 - near point, 
	 * 0 - NO SNAP.
	 * 
	 * @param cursor_coords
	 * @param Shapes - List of Shapes for find snap_point among them
	 * @param snap_keys from Global_var - {end_point, midpoint, intersection, near_point}, 1 - snap on, 0 - snap off
	 * @return - {number of snap_type, x, y, z} xyz of snap point
	 */
	public static int[] get_snap(double [] cursor_coords, List<Shape> Shapes, int[] snap_keys){
		// 
		
		//Positions in array: 0 - snap type, 1 -x, 2 - y, 3 - z. Coordinates of snap point.
		//If snap_type == 0, no snap, coordinates are not importance.
		int [] rezult = new int[]{0, 0, 0, 0};
		
		return rezult;
		
	}
}