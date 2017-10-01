package modules.standard_objects.line;

import modules.base_draw_entities.Interface_base_draw;
import core.Global_var;
import modules.base_draw_entities.Class_base_draw;

public  class draw_line_2 extends Class_base_draw implements Interface_base_draw{
	
	@Override
	public void run() {
		Interface_base_draw next_class = new draw_line_3();
		super.draw_1("Line - next point:","Escape - stop", next_class);
	}
	
	@Override
	public void mouse_move_event() {
		
		
	}
	
}