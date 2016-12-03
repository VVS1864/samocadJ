package samoJ;

public class Line extends Shape {
	
	public Line(float x1, float y1, float z1, float x2, float y2, float z2){
		super();
		super.add(new PrimitiveLine(x1, y1, z1, x2, y2, z2));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Line l1 = new Line(1.0f, 2.0f, 3.0f, 5.0f, 7.0f, 9.0f);
		System.out.println(l1.toList());
	}
}
