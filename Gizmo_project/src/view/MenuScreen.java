package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.FileChooser;

public class MenuScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	public MenuScreen() {
		
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Clock");
		JMenu activity = new JMenu("Activities");
		JMenuItem openFile = new JMenuItem("Read CSV file...");
		
		
		openFile.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			
			chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = chooser.showOpenDialog(this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				FileChooser.getInstance().selectFile(chooser.getSelectedFile().toPath().toString());
			}
		});
		
		file.add(openFile);
		
		bar.add(file);
		bar.add(activity);
		return bar;
	}
	
	 public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	try {
				Image img = ImageIO.read(new File("Assets/Background.jpg"));
				g.drawImage(img, 0,0, getWidth(),getHeight(),this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
