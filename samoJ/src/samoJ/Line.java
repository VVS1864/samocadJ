package samoJ;

import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.lang3.ArrayUtils;

import core.Values;

public class Line extends Shape {
	
	public Line(int x1, int y1, int z1, int x2, int y2, int z2){
		super();
		super.add(new DashedLine(x1, y1, z1, x2, y2, z2, Values.stipple_factor, Values.stipple));
		
		//thePointData = new int[]{x1, y1, z1, x2, y2, z2};
		//thePointData = new LinkedList<>(Arrays.asList(f));
		//core.GL_base.list1 = ArrayUtils.addAll(core.GL_base.list1, thePointData);
		//core.GL_base.list1.addAll(thePointData);
		/*
		int[] array1and2 = new int[core.GL_base.list1.length + thePointData.length];
		
		System.arraycopy(core.GL_base.list1, 0, array1and2, 0, core.GL_base.list1.length);
		System.arraycopy(thePointData, 0, array1and2, core.GL_base.list1.length, thePointData.length);
		core.GL_base.list1 = array1and2;
		*/
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Line l1 = new Line(1.0, 2.0, 3.0, 5.0, 7.0, 9.0);
		//System.out.println(l1.toList());
	}
}
