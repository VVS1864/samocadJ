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
		core.global.preview_object_vertices = null;
		core.global.fast_dynamic_vertices = null;
		core.global.draw_new_object = false;
		core.global.selective_rect.clear();
		core.gui.info.setText("Comand:");
		core.gui.info2.setText(" ");
		core.gui.cmd.setText("");
		core.global.temp_move = false;
	}
}