package core;

public class Draw_snap_sign{
	/**
	 * Method for draw snap sign 
	 * @param snap - array int[4], first position is snap type (see {@link Get_snap}, 
	 * other positions - coordinates of snap point
	 * @param s - snap distance (sign size) in gl units
	 * @return array of coordinates of snap_sign lines, ready to gl draw array
	 */
	public static int[] draw(int[] snap, int s){
		
		int x = snap[1];
		int y = snap[2];
		//int z = snap[3] 3D snap is not available now;
		int[] snap_sing = {};
		
		// Snap type - end_point
		if (snap[0] == 1){
			snap_sing = new int[]{
				x-s, y-s, x-s, y+s,
				x-s, y-s, x+s, y-s,
				x+s, y-s, x+s, y+s,
				x+s, y+s, x-s, y+s,
			};
		}
		
		// Snap type - midpoint
		if (snap[0] == 2){
			snap_sing = new int[]{
					x-s, y-s, x+s, y-s,
                    x-s, y-s, x,   y+s,
                    x,   y+s, x+s, y-s,  
			};
		}
		
		// Snap type - intersection point
		if (snap[0] == 3){
			snap_sing = new int[]{
					x-s, y-s, x+s, y+s,
                    x+s, y-s, x-s, y+s, 
			};
		}
		
		// Snap type - near point
		if (snap[0] == 4){
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