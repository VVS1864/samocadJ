package core.dynamic_entities;

import core.Core;
import open_dxf_lib.Color_rgb;

public class Dynamic_entity {
	protected Core core = Core.c;
	public Color_rgb color;
	public  float[] vertices;
	public int width = 1;
		
	public void clear() {
		vertices = null;
		color = null;
	}
}
