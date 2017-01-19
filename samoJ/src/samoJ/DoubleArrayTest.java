package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;

public class DoubleArrayTest {
	static void print(Object x){
		System.out.println(x);
	}
	
	public DoubleArrayTest() {
		// TODO Auto-generated constructor stub
		
	}
//******************* Testing
	public static void main(String[] args) {
		
		double[] da = {2.0, 3.0, 4.0};
		Double d1 = 11.0;
		Double d2 = 12.0;
		Double d3 = 13.0;
		Double d2_2 = 12.0;
		DoubleArrayList dal= new DoubleArrayList(da);
		print("dal="+dal);
		dal.add(d1);
		dal.add(d2);
		dal.add(d2);
		print("dal="+dal);
		print("dal.contain(d1)="+dal.contains(d1));
		print("dal.contain(d2)="+dal.contains(d2));
		print("dal.contain(d3)="+dal.contains(d3));
		print("dal.contain(d2_2)="+dal.contains(d2_2));
		// TODO Auto-generated method stub

	}

}


