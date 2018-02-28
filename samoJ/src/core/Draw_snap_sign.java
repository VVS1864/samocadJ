package core;

import samoJ.SnapCoord;

public class Draw_snap_sign {
	/**
	 * Method for draw snap sign
	 * 
	 * @param snap
	 *            - array float[4], first position is snap type (see
	 *            {@link Get_snap}, other positions - coordinates of snap point
	 * @param s
	 *            - snap distance (sign size) in gl units
	 * @return array of coordinates of snap_sign lines, ready to gl draw array
	 */
	public static float[] draw(SnapCoord snap, float s) {

		float x = snap.getX();
		float y = snap.getY();
		// float z = snap[3] 3D snap is not available now;
		float[] snap_sing = {};

		switch (snap.snap) {
		case EndPoint:
			snap_sing = new float[] { 
					x - s, y - s,  0,
					x - s, y + s,  0,
					x - s, y - s,  0,
					x + s, y - s,  0,
					x + s, y - s,  0,
					x + s, y + s,  0,
					x + s, y + s,  0,
					x - s, y + s,  0};
			break;

		case MidPoint:
			snap_sing = new float[] { 
					x - s, y - s,  0,
					x + s, y - s,  0,
					x - s, y - s,  0,
					x, y + s,  0,
					x, y + s,  0,
					x + s, y - s, 0 };
			break;

		case Intersection:
			snap_sing = new float[] { 
					x - s, y - s,  0,
					x + s, y + s,  0,
					x + s, y - s,  0,
					x - s, y + s,  0};
			break;

		case NearPoint:
			snap_sing = new float[] { 
					x - s, y - s,  0,
					x + s, y + s,  0,
					x + s, y - s,  0,
					x - s, y + s,  0,
					x - s, y - s,  0,
					x - s, y + s,  0,
					x + s, y - s,  0,
					x + s, y + s,  0};
			break;

		}
		return snap_sing;
	}
}