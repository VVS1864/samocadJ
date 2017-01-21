package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import core.Global_var;

public class GroupShape extends Shape {
	
	List<Shape> shapes;
	
	public GroupShape(List<Shape> shapes) {
		this.shapes = shapes;
		for (Shape sh:shapes)
		{
			Global_var.theShapes.remove(sh.ID);
		}
	}

		
	
	@Override
	public List<PrimitiveLine> getSnapLines() {
		List<PrimitiveLine> retList = new LinkedList<PrimitiveLine>();
		for (Shape sh:shapes)
		{
			retList.addAll(sh.getSnapLines());
		}
		return Collections.unmodifiableList(retList);
	}
	
	@Override
	public List<SnapCoord> getSnapPoints(SnapType st){
		List<SnapCoord> retList = new LinkedList<SnapCoord>();
		for (Shape sh:shapes)
		{
			retList.addAll(sh.getSnapPoints(st));
		}
		return Collections.unmodifiableList(retList);
	}
	
	@Override
	public DoubleArrayList toListDouble()
	{
		DoubleArrayList ret = new DoubleArrayList();
		for (Shape sh:shapes)
			ret.addAll(sh.toListDouble());
		return ret;
		
	}
	
	public static void main(String[] args) {
	
		
	}
	
}


