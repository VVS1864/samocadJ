package modules.standard_objects.line;

import core.GL_base;
import core.cad_demo;
import modules.base_draw_entities.base_draw;
import modules.base_draw_entities.base_draw_class;
import samoJ.Line;

public  class draw_line_3 extends base_draw_class implements base_draw{
	@Override
	public void run() {
		
		super.draw_2("Line - next point:","Escape - stop", this);
		cad_demo.theShapes.add(new Line(x1, y1, 0, x2, y2, 0));
		System.out.println("New Line coordidates: " + x1 + " " + y1 + " " + x2 + " " + y2);
		
		GL_base.redraw_buffer();
		
	}
}