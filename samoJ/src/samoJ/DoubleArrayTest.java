package samoJ;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;

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
		DoubleArrayList dal1= new DoubleArrayList(da);
		//print("dal1="+dal1);
		dal1.add(d1);
		dal1.add(d2);
		dal1.add(d2);
		
		//print("dal1="+dal1);
		/*
		print("dal1.contain(d1)="+dal1.contains(d1));
		print("dal1.contain(d2)="+dal1.contains(d2));
		print("dal1.contain(d3)="+dal1.contains(d3));
		print("dal1.contain(d2_2)="+dal1.contains(d2_2));
		*/
		
		DoubleList dal2 =  dal1.subList(1, 5);
		DoubleList dal3 =  dal1.subList(2, 6);
		print("dal1="+dal1);
		print("dal2="+dal2);
		print("dal3="+dal3);
		
		dal1.removeDouble(dal1.size()-1);
		
		//dal2.set(0, 77777);
		print("dal1="+dal1);
		print("dal2="+dal2);
		print("dal3="+dal3);
		
		dal2.add(333);
		print("dal1="+dal1);
		print("dal2="+dal2);
		print("dal3="+dal3);
		
		dal3.remove(0);
		print("dal1="+dal1);
		print("dal2="+dal2);
		print("dal3="+dal3);
		
		
	}

}


