package modules.standard_objects.line;

import core.Core;
import core.GL_base;

import modules.base_draw_entities.Interface_base_draw;
import open_dxf_lib.dash_type;
import samoJ.ShapeLine;
import samoJ.ObjectMode;
import modules.base_draw_entities.Class_base_draw;
import core.Values;

public  class draw_line_3 extends Class_base_draw implements Interface_base_draw{
	
	
	public draw_line_3() {
		super();
	}
	
	@Override
	public void run() {
		
		super.draw_2("Line - next point:","Escape - stop", this);
		//Global_var.theShapes.add(new Line(x1, y1, 0, x2, y2, 0));
		create(ObjectMode.New_object);
		
		
		core.glRender.redraw_buffer();
		
	}
	@Override
	public void mouse_move_event() {
		super.dynamic_draw();
		create(ObjectMode.Preview_object);
	}
	
	public void create(ObjectMode mode){
		new ShapeLine(core, mode, x1, y1, 0, x2, y2, 0, core.values.stipple_factor, core.values.current_dash, core.values.color, core.values.width);
	}
	
}