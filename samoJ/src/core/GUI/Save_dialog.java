package core.GUI;

import java.io.File;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Save_dialog extends JFileChooser{
	
	public String selected_file;
	public int userSelection;
	
	public Save_dialog(JFrame jframe, String title, int mode, String dir, String file_name) {
		super(dir);
		setSelectedFile(new File(file_name));
		setDialogTitle(title);   
		Action details = getActionMap().get("viewTypeDetails"); 
		details.actionPerformed(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DXF 2000 files", "dxf", "dxf files");
		setFileFilter(filter);
		
		String string_mode = "";
		if(mode == JFileChooser.SAVE_DIALOG){
			userSelection = showSaveDialog(jframe);
			string_mode = "saving";
		}
		else{
			userSelection = showOpenDialog(jframe);
			string_mode = "opening";
		}
		
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			selected_file = getSelectedFile().getAbsolutePath();
		}
		else if(userSelection == JFileChooser.CANCEL_OPTION) {
		    // file chooser closed  
			
			System.out.println("Cancel " + string_mode);
			
		}
	}
	
	@Override
    public void approveSelection(){
        File f = getSelectedFile();
        if(f.exists() && getDialogType() == SAVE_DIALOG){
            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                    return;
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.CLOSED_OPTION:
                    return;
                case JOptionPane.CANCEL_OPTION:
                    cancelSelection();
                    return;
            }
        }
        super.approveSelection();
    }        
}
	
