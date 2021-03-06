package samoJ;

import java.util.ArrayList;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import samoJ.PrimitiveLine.DrawableLine;

public class VboTest {
	ArrayList<DrawableLine> primLines;
	FloatArrayList vertFloat;

	public VboTest() {
		// TODO Auto-generated constructor stub
		primLines = new ArrayList<DrawableLine>();
		vertFloat = new FloatArrayList();

	}

	public boolean add(DrawableLine pp) {
		if (primLines.contains(pp))
			return false;
		else {
			// Here need transaction TO DO
			
			vertFloat.addAll(pp.toListFloat());
			primLines.add(pp);
			// End transaction; TO DO
			return true;
		}
	}

	public boolean remove(DrawableLine pp) {
		int ind = primLines.indexOf(pp);
		if (ind >= 0) {
			// Here need transaction TO DO
			vertFloat.removeElements(ind*6, (ind+1)* 6);
			primLines.remove(ind);
			// End transaction; TO DO
			return true;
		} else
			return false;
	}

	public boolean contains(DrawableLine pp) {
		return primLines.contains(pp);
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < primLines.size(); i++) {
			int ii = i * 6;
			ret += String.valueOf(i) + ":" +vertFloat.subList(ii, ii+6).toString()+ "\n";
		}
		return ret;
	}
/*
	public static void main(String[] args) {
		PrimitiveLine p1 = new PrimitiveLine(1, 2, 3, 4, 5, 6);
		PrimitiveLine p2 = new PrimitiveLine(11, 12, 13, 14, 15, 16);
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p1.toListDouble());
		System.out.println(p2.toListDouble());
		VboTest vbo1 = new VboTest();
		vbo1.add(p2);
		vbo1.add(p1);
		System.out.println(vbo1);
		vbo1.add(new PrimitiveLine(123, 125, 127, 130, 132, 134));
		System.out.println(vbo1);
		vbo1.remove(p1);
		System.out.println(vbo1);
		System.out.println("vbo1.contains(p2)="+vbo1.contains(p2));
		System.out.println("vbo1.contains(p1)="+vbo1.contains(p1));
		
		
		vbo1.vertDouble.set(0, 10000);
		System.out.println(vbo1);
		System.out.println(p2.toString());
		
	}
*/
}
