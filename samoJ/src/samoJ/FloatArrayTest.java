package samoJ;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatList;

public class FloatArrayTest {
	static void print(Object x){
		System.out.println(x);
	}
	
	public FloatArrayTest() {
		// TODO Auto-generated constructor stub
		
	}
//******************* Testing
	public static void main(String[] args) {
		
		float[] da = {2, 3, 4};
		float d1 = 11;
		float d2 = 12;
		float d3 = 13;
		float d2_2 = 12;
		FloatArrayList dal1= new FloatArrayList(da);
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
		
		FloatList dal2 =  dal1.subList(1, 5);
		FloatList dal3 =  dal1.subList(2, 6);
		print("dal1="+dal1);
		print("dal2="+dal2);
		print("dal3="+dal3);
		
		dal1.removeFloat(dal1.size()-1);
		
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


