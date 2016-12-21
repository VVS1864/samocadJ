package modules.standard_objects.line;

import modules.base_draw_entities.base_draw;
import modules.base_draw_entities.base_draw_class;

public  class draw_line_2 extends base_draw_class implements base_draw{
	
	@Override
	public void run() {
		base_draw next_class = new draw_line_3();
		super.draw_1("Line - next point:","Escape - stop", next_class);
	}
	
}