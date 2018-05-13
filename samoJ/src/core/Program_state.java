package core;


/**
 * Class for manage of program states
 * 
 * @author vlad
 *
 */
public class Program_state {	
	/**class for set default state, kill all processes
	 * 
	 */
	private Core core = Core.c;
	public void set_default(){
		core.global.current_function = null;	
		
		clear_dynamic_entities();
		
		core.global.draw_new_object = false;
		
		core.gui.info.setText("Comand:");
		core.gui.info2.setText(" ");
		core.gui.cmd.setText("");
		
	}
	
	private void clear_dynamic_entities() {
		core.global.selective_rect.clear();
		core.global.collection.clear();
		core.global.preview_shape.clear();
	}
}