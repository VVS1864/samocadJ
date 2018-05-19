package core.functions;

import java.util.LinkedList;

import core.Core;
import samoJ.Shape;

public class Delete {
	private Core core = Core.c;
	public void delete(LinkedList<Shape> Shapes) {
		
		for(Shape sh: Shapes) {
			sh.delShape();
		}
		
		core.program_state.set_default();
		core.glRender.redraw_buffer();
	}

}
