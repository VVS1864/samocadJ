package core.dynamic_entities;

import samoJ.SnapCoord;

public class Snap_sign extends Dynamic_entity {

	public void init(SnapCoord snap, float real_snap_distance) {
		vertices = calc(snap, real_snap_distance);
		color = core.values.snap_color;
		width = core.values.dynamic_width;
	}

	private float[] calc(SnapCoord snap, float s) {

		float x = snap.getX();
		float y = snap.getY();
		// float z = snap[3] 3D snap is not available now;
		float[] snap_sing = {};

		switch (snap.snap) {
			case EndPoint :
				snap_sing = new float[]{x - s, y - s, 0, x - s, y + s, 0, x - s,
						y - s, 0, x + s, y - s, 0, x + s, y - s, 0, x + s,
						y + s, 0, x + s, y + s, 0, x - s, y + s, 0};
				break;

			case MidPoint :
				snap_sing = new float[]{x - s, y - s, 0, x + s, y - s, 0, x - s,
						y - s, 0, x, y + s, 0, x, y + s, 0, x + s, y - s, 0};
				break;

			case Intersection :
				snap_sing = new float[]{x - s, y - s, 0, x + s, y + s, 0, x + s,
						y - s, 0, x - s, y + s, 0};
				break;

			case NearPoint :
				snap_sing = new float[]{x - s, y - s, 0, x + s, y + s, 0, x + s,
						y - s, 0, x - s, y + s, 0, x - s, y - s, 0, x - s,
						y + s, 0, x + s, y - s, 0, x + s, y + s, 0};
				break;

		}
		return snap_sing;
	}
}
