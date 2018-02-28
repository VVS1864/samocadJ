package modules.standard_objects.circle;

import modules.base_draw_entities.Class_base_draw;

public  class draw_circle_2 extends Class_base_draw{

	@Override
	public void run() {
		Class_base_draw next_class = new draw_circle_3();
		//dynamic rendering = true
		next_class.display_dynamic = true;
		get_point_1("Circle - radius:","Escape - stop", next_class);
	}
		
}