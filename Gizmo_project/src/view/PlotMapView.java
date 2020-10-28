package view;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import java.util.List;

import javax.swing.JPanel;

import model.Activity;

import model.TrackPoint;

public class PlotMapView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private List<TrackPoint> trackpoints;

	private double[] xPixels;
	private double[] yPixels;
	

	public PlotMapView(Activity activity) {
	
		this.trackpoints = activity.getActivity();
		
		xPixels = new double [trackpoints.size()];
		yPixels = new double [trackpoints.size()];
	
		
		setBackground(Color.gray);
	}
	


// Det h�r �r metoden som ritar ut grafiken. Se hur enkelt det blir
// med hj�lp av arrayerna och metoden drawPolyline.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(trackpoints.size() == 0) {
			return;
		}
		
		double minLong = trackpoints.get(0).getLong();
		double maxLong = minLong;
		double minLat  = trackpoints.get(0).getLat();
		double maxLat  = minLat;
		
		for(int i = 0 ; i < trackpoints.size(); i++) {
			TrackPoint tp = trackpoints.get(i);
			
			if(tp.getLat() < minLat)
				minLat = tp.getLat();
			if(tp.getLong() < minLong)
				minLong = tp.getLong();
			if(tp.getLat() > maxLat)
				maxLat = tp.getLat();
			if(tp.getLong() > maxLong)
				maxLong = tp.getLong();
		}
		
		for(int i = 0; i < trackpoints.size(); i++) {
			xPixels[i] = ((trackpoints.get(i).getLong() - minLong) / (maxLong - minLong)) * getWidth();
			yPixels[i] = ((trackpoints.get(i).getLat()  - minLat) /  (maxLat - minLat)  ) * getHeight();
		}
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.green);
		
		
		//g2.translate(0, getHeight());
		AffineTransform toCenterAt = new AffineTransform();
		
		
		
		
//		g2.translate(0, getHeight());
//		g2.rotate(((Math.PI) / 2) * -1);
		
	//	g2.drawRect(0, 0, 100, 100);
//		g2.translate(0, getHeight());
//		g2.rotate(Math.PI/2 * -1);
		
	
		
		for(int i = 0; i < trackpoints.size(); i++) {
			if((i + 1) == trackpoints.size()) {}
			
			else {
				
				g2.draw(new Line2D.Double(xPixels[i] , yPixels[i], xPixels[i+1] , yPixels[i+1]));
				//System.out.println(xPixels[i] +"   " + yPixels[i] );
			}
		
		}		
	}
}

