import java.util.LinkedList;

/**
 * It is an point, non-graphical, with 3 coordinates x,y,z Currently float is
 * the type of coordinates
 * 
 * @author E.Askadullin
 *
 */

public class Coord {
	float x, y, z;

	Coord(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	public LinkedList<Float> toList() {
		LinkedList<Float> ret = new LinkedList<Float>();
		ret.add(x);
		ret.add(y);
		ret.add(z);
		return ret;
	}

	public static void main(String[] args) {

		Coord c = new Coord(1.0f, 2.0f, 3.0f);
		System.out.println(c.toList());
	}

}
