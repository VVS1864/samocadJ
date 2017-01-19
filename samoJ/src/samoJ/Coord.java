package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;


import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;

/**
 * It is an point, non-graphical, with 3 coordinates x,y,z Currently Integer is
 * the type of coordinates
 * 
 * @author E.Askadullin
 * 
 */

public class Coord {
	//public double x, y, z;
	
	DoubleArrayList c;
	// Integer c[];

	public Coord(double x, double y) {
		this(x, y, 0);
	}

	Coord(double x, double y, double z) {
		c = DoubleArrayList.wrap(new double[] {x, y, z});
		/*this.x = x;
		this.y = y;
		this.z = z;*/
		// c = new Integer[] {x,y,z};

	}

	/**
	 * 
	 * @return list for GL rendering
	 */
	
	public ArrayList<Double> toList() {
		/*LinkedList<Double> ret = new LinkedList<Double>();
		
		ret.add(x);
		ret.add(y);
		ret.add(z);
		return ret;*/
		return new ArrayList<Double>(c);
		
		// for (Integer theC:c) ret.add(theC);
		// return (LinkedList)Arrays.asList(c);
	}

	public DoubleArrayList toListDouble(){
		return c;
	}
	
	public double getX(){
		return c.getDouble(0);
	}
	
	public double getY(){
		return c.getDouble(1);
	}
	
	public double getZ(){
		return c.getDouble(2);
	}
	
	public String toString(){
		String ret="[";
		for (double d:c)
		{
			ret += d+" ";
		}
		return ret+"]";
	}
	// ***************Simple testing
	public static void main(String[] args) {

		Coord c = new Coord(1, 2, 3);
		System.out.println(c.toList());
	}
	


}
