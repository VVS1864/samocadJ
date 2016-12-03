import java.util.LinkedList;

/**
 * the PrimitiveLine is a base for Shape
 * 
 * @author E.Askadullin
 *
 */
public class PrimitiveLine {
	Coord c1;
	Coord c2;

	private PrimitiveLine(Coord c1, Coord c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	PrimitiveLine(float x1, float y1, float z1, float x2, float y2, float z2) {
		this(new Coord(x1, y1, z1), new Coord(x2, y2, z2));
	}

	public LinkedList<Float> toList() {
		LinkedList<Float> ret = new LinkedList<Float>();
		ret.addAll(c1.toList());
		ret.addAll(c2.toList());
		return ret;
	}

}
