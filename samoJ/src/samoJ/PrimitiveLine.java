package samoJ;

import java.util.LinkedList;

/**
 * the PrimitiveLine is a base for Shape
 * 
 * @author E.Askadullin
 *
 */
public class PrimitiveLine {
	public Coord c1;
	public Coord c2;

	private PrimitiveLine(Coord c1, Coord c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	PrimitiveLine(int x1, int y1, int z1, int x2, int y2, int z2) {
		this(new Coord(x1, y1, z1), new Coord(x2, y2, z2));
	}

	public LinkedList<Integer> toList() {
		LinkedList<Integer> ret = c1.toList();
		ret.addAll(c2.toList());
		return ret;
	}

}
