package samoJ;

public class SnapCoord extends Coord {

	public SnapType snap;

	public SnapCoord(SnapType snap, double x, double y) {
		this(snap, x, y, 0);
	}

	public SnapCoord(SnapType snap, double x, double y, double z) {
		super(x, y, z);
		this.snap = snap;
	}

	public SnapCoord(SnapType snap, Coord c) {
		this(snap, c.x, c.y);
	}
}
