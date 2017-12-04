package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import java.util.ArrayList;


/**
 * It is an point, non-graphical, with 3 coordinates x,y,z Currently Integer is
 * the type of coordinates
 * 
 * @author E.Askadullin
 * 
 */

public class Coord {
	DoubleArrayList c;

	public Coord(double x, double y) {
		this(x, y, 0);
	}

	Coord(double x, double y, double z) {
		c = DoubleArrayList.wrap(new double[] {x, y, z});

	}

	/**
	 * 
	 * @return lists for GL rendering
	 */
	
	public ArrayList<Double> toList() {
		return new ArrayList<Double>(c);

	}

	public DoubleArrayList toListDouble(){
		return c;
	}
	
	/**
	 * double only coordinates
	 * @return
	 */
	public double getX(){
		return c.getDouble(0);
	}
	
	public double getY(){
		return c.getDouble(1);
	}
	
	public double getZ(){
		return c.getDouble(2);
	}
	
	public double[] getXYZ(){
		 double[] XYZ = {getX(), getY(), getZ()};
		return XYZ;
		
	}
	
	public String toString(){
		
		String ret="[";
		for (double d:c)
		{
			ret += d+" ";
		}
		return ret+"]";}
	
	// ***************Simple testing
	public static void main(String[] args) {

		Coord c = new Coord(1, 2, 3);
		System.out.println(c.toList());
	}
	


}
