package modules.standard_objects.line;

import modules.base_draw_entities.base_draw;
import modules.base_draw_entities.base_draw_class;

/**
 * Custom realization of base_draw_class for initial method of Line-creating process
 * @author vlad
 *
 */
public class draw_line extends base_draw_class {
	
	public draw_line(){
		base_draw next_class = new draw_line_2();
		super.init_draw("Line - First point:","Escape - stop", next_class);
	}
	
}
	
	