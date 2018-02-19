package modules.standard_objects.line;

import modules.base_draw_entities.Interface_base_draw;
import modules.base_draw_entities.Class_base_draw;

/**
 * Custom realization of base_draw_class for initial method of Line-creating process
 * @author vlad
 *
 */
public class draw_line extends Class_base_draw {
	
	public draw_line() {
		super();
	}

	@Override
	public void run(){
		Interface_base_draw next_class = new draw_line_2();
		Interface_base_draw init_class = new draw_line();
		core.program_state.set_default(); // Kill another processes if they are.
		super.init_draw("Line - First point:","Escape - stop", next_class, init_class);
	}
	
}
	
	