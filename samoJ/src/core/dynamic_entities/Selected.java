package core.dynamic_entities;

import java.util.LinkedList;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import open_dxf_lib.Color_rgb;
import samoJ.Shape;


/**
 * Entity for 1 and more selected Shapes.
 * @author vlad
 *
 */
public class Selected extends Dynamic_entity{
	protected LinkedList<Shape> collection;
	public void new_collection(LinkedList<Shape> shapes) {
		collection = shapes;
		FloatArrayList list1 = new FloatArrayList(0);
		for(Shape sh: collection) {
			list1.addAll(sh.toListFloat());
		}
		vertices = list1.elements();
		
		set_color_width();
		
	}
	
	protected void set_color_width() {
		color = core.values.selected_shape_color;
		width = core.values.dynamic_width;
	}
	
	

}
