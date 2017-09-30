package samoJ;

import java.util.HashMap;
import java.util.Map;

import open_dxf_lib.dash_type;

public class Dash_base {
	public int[] mask;
	public dash_type dash;
	public Map<dash_type, int[]> dash_map = new HashMap<dash_type, int[]>();
	
	public Dash_base() {
		int[] CENTER = {4,1,1,1};
		int[] DASHED = {1,1};
		int[] PHANTOM = {4,1,1,1,1,1};
		dash_map.put(dash_type.Continuous, null);
		dash_map.put(dash_type.CENTER, CENTER);
		dash_map.put(dash_type.DASHED, DASHED);
		dash_map.put(dash_type.PHANTOM, PHANTOM);
		
	}
	
	

}
