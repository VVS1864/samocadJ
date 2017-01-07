package core;

import samoJ.SnapCoord;
import samoJ.SnapType;

public class Draw_snap_sign{
	/**
	 * Method for draw snap sign 
	 * @param snap - array int[4], first position is snap type (see {@link Get_snap}, 
	 * other positions - coordinates of snap point
	 * @param s - snap distance (sign size) in gl units
	 * @return array of coordinates of snap_sign lines, ready to gl draw array
	 */
	public static int[] draw(SnapCoord snap, int s){
		
		int x = snap.x;
		int y = snap.y;
		//int z = snap[3] 3D snap is not available now;
		int[] snap_sing = {};
		
		// Snap type - end_point
		if (snap.snap == SnapType.EndPoint){
			snap_sing = new int[]{
				x-s, y-s, x-s, y+s,
				x-s, y-s, x+s, y-s,
				x+s, y-s, x+s, y+s,
				x+s, y+s, x-s, y+s,
			};
		}
		
		// Snap type - midpoint
		if (snap.snap == SnapType.MidPoint){
			snap_sing = new int[]{
					x-s, y-s, x+s, y-s,
                    x-s, y-s, x,   y+s,
                    x,   y+s, x+s, y-s,  
			};
		}
		
		// Snap type - intersection point
		if (snap.snap == SnapType.Intersection){
			snap_sing = new int[]{
					x-s, y-s, x+s, y+s,
                    x+s, y-s, x-s, y+s, 
			};
		}
		
		// Snap type - near point
		if (snap.snap == SnapType.NearPoint){
			snap_sing = new int[]{
					x-s, y-s, x+s, y+s,
                    x+s, y-s, x-s, y+s,
                    x-s, y-s, x-s, y+s,
                    x+s, y-s, x+s, y+s, 
			};
		}
		return snap_sing;
	}
}