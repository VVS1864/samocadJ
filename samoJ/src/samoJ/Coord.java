package samoJ;

//import java.util.Arrays;
import java.util.LinkedList;

/**
 * It is an point, non-graphical, with 3 coordinates x,y,z Currently Integer is
 * the type of coordinates
 * 
 * @author E.Askadullin
 * 
 */

public class Coord {
	int x, y, z;

	// Integer c[];

	Coord(int x, int y) {
		this(x, y, 0);
	}

	Coord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		// c = new Integer[] {x,y,z};

	}

	/**
	 * 
	 * @return list for GL rendering
	 */
	public LinkedList<Integer> toList() {
		LinkedList<Integer> ret = new LinkedList<Integer>();
		ret.add(x);
		ret.add(y);
		ret.add(z);
		return ret;
		
		// for (Integer theC:c) ret.add(theC);
		// return (LinkedList)Arrays.asList(c);
	}

	// Simple testing
	public static void main(String[] args) {

		Coord c = new Coord(1, 2, 3);
		System.out.println(c.toList());
	}

}
