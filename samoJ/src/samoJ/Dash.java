package samoJ;

import open_dxf_lib.dash_type;

public class Dash extends Dash_base{
	
	public Dash(dash_type type) {
		this.dash = type;
		this.mask = dash_map.get(type);
		
	}
}
