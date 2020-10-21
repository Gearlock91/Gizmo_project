package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.FileChooser;

public class MenuScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	JFrame frame;
	
	public MenuScreen(JFrame frame) {
		this.frame = frame;
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Clock");
		JMenu activity = new JMenu("Activities");
		JMenuItem openFile = new JMenuItem("Read CSV file...");
		
		
		openFile.addActionListener(e -> {
			
			JPanel mainFileChooser = new JPanel(new BorderLayout());
			
			JPanel fieldBoxOfDoom = new JPanel(new GridLayout(2,1));
			
	
			JTextField activityName = new JTextField("Choose activity name");
			
			JTextField selectFile = 	new JTextField("Press enter...");
			
			
			selectFile.addActionListener(e2 -> {
				JFileChooser chooser = new JFileChooser();
				
				chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = chooser.showOpenDialog(this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					selectFile.setText((chooser.getSelectedFile().toPath().toString()));
				}
			});
			
			
			fieldBoxOfDoom.add(activityName);
			fieldBoxOfDoom.add(selectFile);
			mainFileChooser.add(fieldBoxOfDoom);
			
			
			int valueR = JOptionPane.showConfirmDialog(frame,mainFileChooser, "Import...", JOptionPane.OK_CANCEL_OPTION , 2);
			if(valueR == JOptionPane.OK_OPTION) {
				FileChooser.getInstance().selectFile(selectFile.getText());
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
