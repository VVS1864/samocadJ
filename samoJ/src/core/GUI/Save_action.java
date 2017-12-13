package core.GUI;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import open_dxf_lib.DXF_file;
import open_dxf_lib.Mode;
import samoJ.ShapeLine;
import samoJ.Shape;
import core.Global_var;
import core.Values;

public class Save_action {

	public Save_action(){
		
		Save_dialog dialog = new Save_dialog("Save new drawing", JFileChooser.SAVE_DIALOG, 
				Values.current_working_dir, Values.current_file_name);
		
		if(dialog.userSelection == JFileChooser.APPROVE_OPTION){
			
		//new current fime name and dir
		Path p = Paths.get(dialog.selected_file);
		Values.current_file_name = p.getFileName().toString();
		Values.current_working_dir = p.getParent().toString();
		
		DXF_file f = new DXF_file(Mode.New_file, dialog.selected_file);
		shapes_to_DXF(f);
		f.save_file();
		
		}
	}
	public void shapes_to_DXF(DXF_file f) {
		for(Shape sh: Global_var.theShapes.values()){
			if(sh instanceof ShapeLine){
				sh.save_to_DXF(f);
			}
		}
	}
}
