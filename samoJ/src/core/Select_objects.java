package core;

public class Select_objects{
	public static void draw_selective_rect(){
		Global_var.select_rect_2 = Global_var.cursor_snap_coords.clone();
		double x1 = Global_var.select_rect_1[0];
		double y1 = Global_var.select_rect_1[1];
		double x2 = Global_var.select_rect_2[0];
		double y2 = Global_var.select_rect_2[1];
		Global_var.select_rect_vertices = new double[]{x1, y1, x2, y1,
													x1, y2, x2, y2,
													x2, y1, x2, y2,
													x1, y1, x1, y2};
		if (x1 > x2){
			Global_var.select_rect_color = new int[]{255, 0, 0};
		}
		else
		{
			Global_var.select_rect_color = new int[]{0, 0, 255};
		}
		Global_var.glcanvas.display();
		
	}
	public static void mouse_select(){
		if (Global_var.select_mode == false){
			//TO-DO block if object on cursor!!!
			Global_var.select_rect_1 = Global_var.cursor_snap_coords.clone();
			Global_var.select_mode = true;
		}
		else {
			Global_var.select_rect_2 = Global_var.cursor_snap_coords.clone();
			Global_var.select_mode = false;
			Global_var.glcanvas.display();
		}
		
	}
}