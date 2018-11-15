package hw9;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import hw5.Edge;
import hw8.Point;

@SuppressWarnings("serial")
public class BackGround extends JPanel {
	
	private BufferedImage backgroundImage;
	
	private List<Edge<Double, Point>> path;
	
	private int imageOriginalWidth;
	
	private int imageOriginalHeight;
	
	private Point startingBuildingLocation;
	
	private Point endingBuildingLocation;
	


	@Override public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
    	Graphics2D g2 = (Graphics2D) g;
    	
		try {
			backgroundImage = ImageIO.read(new File("src\\hw8\\data\\campus_map.jpg"));
			imageOriginalWidth = backgroundImage.getWidth();
			imageOriginalHeight = backgroundImage.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Draw the map
        g2.drawImage(backgroundImage.getScaledInstance((int) getWidth(), (int) getHeight(), Image.SCALE_DEFAULT), 0, 0, null);

        double widthScaleFactor = getWidth() / (imageOriginalWidth * 1.0);
        double heightScaleFactor = getHeight() / (imageOriginalHeight * 1.0);
        
        //Mark the starting building
        if(startingBuildingLocation != null) {
        	g2.setColor(Color.red);
        	g2.setStroke(new BasicStroke(3));
        	int radius = 20;
        	g2.drawOval((int) (startingBuildingLocation.getX() * widthScaleFactor) - radius/2, (int) (startingBuildingLocation.getY() * heightScaleFactor) - radius/2, radius, radius);
        }
        
        //Mark the ending building
        if(endingBuildingLocation != null) {
        	g2.setColor(Color.red);
        	g2.setStroke(new BasicStroke(3));
        	int radius = 20;
        	g2.drawOval( (int) (endingBuildingLocation.getX() * widthScaleFactor) - radius/2, (int) (endingBuildingLocation.getY() * heightScaleFactor) - radius/2, radius, radius);
        }
        
        //Draw Path
        if(path != null) {
        	g2.setColor(Color.red);
        	g2.setStroke(new BasicStroke(3));
        	for(int i = 0; i < path.size() - 1; i++) {
        		int x1 = (int) (path.get(i).getEndNode().getName().getX() * widthScaleFactor);
        		int y1 = (int) (path.get(i).getEndNode().getName().getY() * heightScaleFactor);
        		int x2 =(int) (path.get(i+1).getEndNode().getName().getX() * widthScaleFactor);
        		int y2 = (int) (path.get(i+1).getEndNode().getName().getY() * heightScaleFactor);
        		g2.drawLine(x1, y1, x2, y2);
        	}
        }
    }

	public void setPath(List<Edge<Double, Point>> path) {
		this.path = path;
	}

	public void setStartingBuildingLocation(Point startingBuildingLocation) {
		this.startingBuildingLocation = startingBuildingLocation;
	}

	public void setEndingBuildingLocation(Point endingBuildingLocation) {
		this.endingBuildingLocation = endingBuildingLocation;
	}
}
