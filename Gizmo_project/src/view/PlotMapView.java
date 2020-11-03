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
	private int[] xPixels;
	private int[] yPixels;

	public PlotMapView(Activity activity) {
	
		this.trackpoints = activity.getActivity();
		xPixels = new int [trackpoints.size()];
		yPixels = new int [trackpoints.size()];
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
			xPixels[i] = (int) (((trackpoints.get(i).getLong() - minLong) / (maxLong - minLong)) * getWidth());
			yPixels[i] = (int) (((trackpoints.get(i).getLat()  - minLat) /  (maxLat - minLat)  ) * getHeight());
			yPixels[i] = getHeight() - yPixels[i];
		}
		
		g.setColor(Color.GREEN);
		g.drawPolyline(xPixels, yPixels, xPixels.length);
				
	}
}

