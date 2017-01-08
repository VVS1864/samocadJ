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
	public double x, y, z;

	// Integer c[];

	public Coord(double x, double y) {
		this(x, y, 0);
	}

	Coord(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		// c = new Integer[] {x,y,z};

	}

	/**
	 * 
	 * @return list for GL rendering
	 */
	public LinkedList<Double> toList() {
		LinkedList<Double> ret = new LinkedList<Double>();
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
