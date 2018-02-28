package samoJ;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import java.util.ArrayList;


/**
 * It is an point, non-graphical, with 3 coordinates x,y,z Currently Integer is
 * the type of coordinates
 * 
 * @author E.Askadullin
 * 
 */

public class Coord {
	FloatArrayList c;

	public Coord(float x, float y) {
		this(x, y, 0);
	}

	public Coord(float x, float y, float z) {
		c = FloatArrayList.wrap(new float[] {x, y, z});

	}

	/**
	 * 
	 * @return lists for GL rendering
	 */
	
	public ArrayList<Float> toList() {
		return new ArrayList<Float>(c);

	}

	public FloatArrayList toListFloat(){
		return c;
	}
	
	/**
	 * float only coordinates
	 * @return
	 */
	public float getX(){
		return c.getFloat(0);
	}
	
	public float getY(){
		return c.getFloat(1);
	}
	
	public float getZ(){
		return c.getFloat(2);
	}
	
	public float[] getXYZ(){
		 float[] XYZ = {getX(), getY(), getZ()};
		return XYZ;
		
	}
	
	public String toString(){
		
		String ret="[";
		for (float d:c)
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
