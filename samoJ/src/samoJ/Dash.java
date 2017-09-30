package samoJ;

import core.Global_var;
import open_dxf_lib.dash_type;

public class Dash extends Dash_base{
	
	public Dash(dash_type type) {
		this.dash = type;
		this.mask = Global_var.dashes.dash_map.get(type);
		
	}
}
