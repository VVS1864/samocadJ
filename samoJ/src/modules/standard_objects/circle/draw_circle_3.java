package modules.standard_objects.circle;

import modules.base_draw_entities.Class_base_draw;
import samoJ.ShapeLine;
import samoJ.ShapeCircle;
import samoJ.ObjectMode;

public  class draw_circle_3 extends Class_base_draw{
	
	@Override
	public void run() {
		create(ObjectMode.New_object);	
		core.glRender.redraw_buffer();
		Class_base_draw draw_action = new draw_circle_1();
		draw_action.run();
	}
	
	@Override
	public void create(ObjectMode mode){
		new ShapeCircle(mode, x1, y1, 0.0f, x2, y2, 0.0f, core.values.color, core.values.width);
	}
	
}