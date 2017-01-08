package core;

import samoJ.SnapCoord;
import samoJ.SnapType;

public class Draw_snap_sign{
	/**
	 * Method for draw snap sign 
	 * @param snap - array double[4], first position is snap type (see {@link Get_snap}, 
	 * other positions - coordinates of snap point
	 * @param s - snap distance (sign size) in gl units
	 * @return array of coordinates of snap_sign lines, ready to gl draw array
	 */
	public static double[] draw(SnapCoord snap, double s){
		
		double x = snap.x;
		double y = snap.y;
		//double z = snap[3] 3D snap is not available now;
		double[] snap_sing = {};
		
		// Snap type - end_point
		if (snap.snap == SnapType.EndPoint){
			snap_sing = new double[]{
				x-s, y-s, x-s, y+s,
				x-s, y-s, x+s, y-s,
				x+s, y-s, x+s, y+s,
				x+s, y+s, x-s, y+s,
			};
		}
		
		// Snap type - midpoint
		if (snap.snap == SnapType.MidPoint){
			snap_sing = new double[]{
					x-s, y-s, x+s, y-s,
                    x-s, y-s, x,   y+s,
                    x,   y+s, x+s, y-s,  
			};
		}
		
		// Snap type - intersection point
		if (snap.snap == SnapType.Intersection){
			snap_sing = new double[]{
					x-s, y-s, x+s, y+s,
                    x+s, y-s, x-s, y+s, 
			};
		}
		
		// Snap type - near point
		if (snap.snap == SnapType.NearPoint){
			snap_sing = new double[]{
					x-s, y-s, x+s, y+s,
                    x+s, y-s, x-s, y+s,
                    x-s, y-s, x-s, y+s,
                    x+s, y-s, x+s, y+s, 
			};
		}
		return snap_sing;
	}
}