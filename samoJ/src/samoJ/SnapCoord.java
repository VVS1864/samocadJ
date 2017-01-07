package samoJ;

public class SnapCoord extends Coord {

	public SnapType snap;

	public SnapCoord(SnapType snap, int x, int y) {
		this(snap, x, y, 0);
	}

	public SnapCoord(SnapType snap, int x, int y, int z) {
		super(x, y, z);
		this.snap = snap;
	}

	public SnapCoord(SnapType snap, Coord c) {
		this(snap, c.x, c.y);
	}
}
