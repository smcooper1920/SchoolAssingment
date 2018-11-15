/**
 * 
 */
package hw9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import hw5.Edge;
import hw8.CampusMapPaths;
import hw8.Point;

/**
 * Produces a map that shows the shortest path from one building to another
 * 
 * @author Stephen Cooper
 *
 */

public class CampusPathsMain{
	
	
	public static void main(String[] args) {
		CampusMapPaths cmPaths = new CampusMapPaths();
		cmPaths.buildGraph();
		// create frame and give it a name; set it to automatically
	    // terminate the application when the window is closed
		JFrame frame = new JFrame("Campus Paths");
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	    // Add the face and a label to the window.
	    // setPreferredSize ensures the face will have the size we
	    // want when the window is packed
	    BackGround campusMap = new BackGround();
	    campusMap.setPreferredSize(new Dimension(1024,768));
	    //Makes sure the Map fills the screen that doesn't include the userInputSpace
	    frame.add(campusMap, BorderLayout.CENTER);
	    //Make panel that encompasses the entire right side of the screen
	    JPanel userInputSpace = new JPanel(new GridLayout(2,1));
	    JPanel topSpace = new JPanel(new GridLayout(10,1));
	    JPanel bottomSpace = new JPanel(new GridLayout(1,1));
	    
	    //Make the labels and set their sizes
	    JLabel originLabel = new JLabel("Origin Building", SwingConstants.CENTER);
	    originLabel.setFont(new Font(originLabel.getName(), Font.BOLD, 30));
	    JLabel destinationLabel = new JLabel("Destination Building", SwingConstants.CENTER);
	    destinationLabel.setFont(new Font(destinationLabel.getName(), Font.BOLD, 30));
	    
	    //Set up input for origin building
	    //When the location is changed we send an update to the map to draw the changes.
	    JComboBox<String> originPoint = new JComboBox<>(cmPaths.getBuildingNames());
	    
	    originPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				campusMap.setStartingBuildingLocation(cmPaths.getPointfromShortName(cmPaths.getShortNameFromBox(originPoint.getSelectedItem())));
				frame.repaint();
			}
		});
	    
	    //Set up input for destination building
	    //When the location is changed we send an update to the map to draw the changes.
	    JComboBox<String> destinationPoint = new JComboBox<>(cmPaths.getBuildingNames());
	    
	    destinationPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				campusMap.setEndingBuildingLocation(cmPaths.getPointfromShortName(cmPaths.getShortNameFromBox(destinationPoint.getSelectedItem())));
				frame.repaint();
			}
		});
	    
	    //Add button to calculate to the shortest path from two points.
	    JButton pathCalculator = new JButton("Path Calculator");
	    pathCalculator.setFont(new Font(pathCalculator.getName(), Font.BOLD, 30));
	    
	    pathCalculator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String startString = cmPaths.getShortNameFromBox(originPoint.getSelectedItem());
				String endString = cmPaths.getShortNameFromBox(destinationPoint.getSelectedItem());
				List<Edge<Double, Point>> path = cmPaths.Dijkstras2(startString, endString);
				campusMap.setPath(path);
				frame.repaint();
			}
	    });
	    
	    //Button that clears the current contents of the board
	    JButton ResetButton = new JButton("Reset");
	    ResetButton.setFont(new Font(ResetButton.getName(), Font.BOLD, 30));
	    
	    ResetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				campusMap.setStartingBuildingLocation(null);
				campusMap.setEndingBuildingLocation(null);
				campusMap.setPath(null);
				frame.repaint();
			}
	    });
	    
	    //Add all of the pieces to the board
	    userInputSpace.add(topSpace);
	    userInputSpace.add(bottomSpace);
	    topSpace.add(originLabel);
	    topSpace.add(originPoint);
	    topSpace.add(destinationLabel);
	    topSpace.add(destinationPoint);
	    topSpace.add(pathCalculator);
	    topSpace.add(ResetButton);
	    
	    
	    frame.add(userInputSpace, BorderLayout.EAST);

	    // pack layout to natural sizes of components, then display
	    frame.pack();
	    frame.setVisible(true);
	}
	
	


}
