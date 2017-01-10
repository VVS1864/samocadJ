package core;

import samoJ.SnapCoord;

public class Draw_snap_sign {
	/**
	 * Method for draw snap sign
	 * 
	 * @param snap
	 *            - array double[4], first position is snap type (see
	 *            {@link Get_snap}, other positions - coordinates of snap point
	 * @param s
	 *            - snap distance (sign size) in gl units
	 * @return array of coordinates of snap_sign lines, ready to gl draw array
	 */
	public static double[] draw(SnapCoord snap, double s) {

		double x = snap.getX();
		double y = snap.getY();
		// double z = snap[3] 3D snap is not available now;
		double[] snap_sing = {};

		switch (snap.snap) {
		case EndPoint:
			snap_sing = new double[] { x - s, y - s, x - s, y + s, x - s,
					y - s, x + s, y - s, x + s, y - s, x + s, y + s, x + s,
					y + s, x - s, y + s, };
			break;

		case MidPoint:
			snap_sing = new double[] { x - s, y - s, x + s, y - s, x - s,
					y - s, x, y + s, x, y + s, x + s, y - s, };
			break;

		case Intersection:
			snap_sing = new double[] { x - s, y - s, x + s, y + s, x + s,
					y - s, x - s, y + s, };
			break;

		case NearPoint:
			snap_sing = new double[] { x - s, y - s, x + s, y + s, x + s,
					y - s, x - s, y + s, x - s, y - s, x - s, y + s, x + s,
					y - s, x + s, y + s, };
			break;

		}
		return snap_sing;
	}
}