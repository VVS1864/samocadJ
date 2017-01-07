package core;

import java.util.LinkedList;
import java.util.Map;

import samoJ.Coord;
import samoJ.PrimitiveLine;
import samoJ.Shape;

public class Clip_algorithm{
	
	/**
	 * Method return all Shapes in rectangular area. The Shape may be in rectangle 
	 * completely or partially. (Now LinearShapes only!!!)
	 * 
	 * @param xBL - Bottom Left x of rect 
	 * @param yBL - Bottom Left y of rect
	 * @param xTR - Top Right x of rect
	 * @param yTR - Top Right y of rect
	 * @param theShapes - All Shapes or Shapes in active sector (with optimization)
	 * @return - Shapes in rectangle
	 */
	public static LinkedList<Shape> simple_clip(int xBL, int yBL, int xTR, int yTR, Map<Integer, Shape> theShapes){
		
		LinkedList<Shape> ReturnableShapes = new LinkedList<Shape>();
		
		for (Shape shape : theShapes.values()){
			for (PrimitiveLine snap_line : shape.SnapLines){
				Coord c1 = snap_line.getC1();
				int x1 = c1.x;
				int y1 = c1.y;
				Coord c2 = snap_line.getC2();
				int x2 = c2.x;
				int y2 = c2.y;
				
				if (x1 > xTR && x2 > xTR) continue;
				if (x1 < xBL && x2 < xBL) continue;
				if (y1 > yTR && y2 > yTR) continue;
				if (y1 < yBL && y2 < yBL) continue;			
				
				if ((xBL < x1 && x1 < xTR) && (xBL < x2 && x2 < xTR) && 
					(yBL < y1 && y1 < yTR) && (yBL < y2 && y2 < yTR)){
					
					ReturnableShapes.add(shape);
					break;
				}
				int c = x2*y1-x1*y2;
				int b = x1-x2;
				int a = y2-y1;
				
				int r1 = a*xBL + b*yTR + c;
				if (r1 == 0){
					ReturnableShapes.add(shape);
					break;
				}
				
				int r2 = a*xBL + b*yBL + c;
				if (r2 == 0){
					ReturnableShapes.add(shape);
					break;
				}
				
				double r3 = a * xTR + b * yTR + c;
				if (r3 == 0){
					ReturnableShapes.add(shape);
					break;
				}
				
				double r4 = a * xTR + b * yBL + c;
				if (r4 == 0){
					ReturnableShapes.add(shape);
					break;
				}
				
				if ((r1<0 && r2<0 && r3<0 && r4<0) || (r1>0 && r2>0 && r3>0 && r4>0)){
					continue;
				}
				else {
					ReturnableShapes.add(shape);
					break;
				}
				
			}
		}
		
		return ReturnableShapes;
	}
}