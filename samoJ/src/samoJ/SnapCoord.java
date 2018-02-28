package samoJ;

public class SnapCoord extends Coord {

	public SnapType snap;

	public SnapCoord(SnapType snap, float x, float y) {
		this(snap, x, y, 0);
	}

	public SnapCoord(SnapType snap, float x, float y, float z) {
		super(x, y, z);
		this.snap = snap;
	}

	public SnapCoord(SnapType snap, Coord c) {
		this(snap, c.getX(), c.getY(), c.getZ());
	}
	
}
