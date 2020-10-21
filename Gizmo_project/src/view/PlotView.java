package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Activity;
import model.DataFetcher;
import model.TrackPoint;

public class PlotView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private List<TrackPoint> trackpoints;
	private int width;
	private int height;
	private DataFetcher fetcher;
	private double totalElapsedTime; // related to width
	private double minDataValue; // related to height
	private double maxDataValue; // related to height
	private int[] xPixels;
	private int[] yPixels;

	public PlotView(String title, Activity activity, DataFetcher fetcher) {
		this.fetcher = fetcher;
		this.trackpoints = activity.getActivity();
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(title));
	}

// Denna metod hittar maximalt och minimalt datavärde samt den totala
// tiden som aktiviteten pågått (sista punktens elapsed time)
	private void findLimitsInData() {
		if (trackpoints != null && trackpoints.size() > 1) {
			TrackPoint firstTp = trackpoints.get(0);
			TrackPoint lastTp = trackpoints.get(trackpoints.size()-1);
			minDataValue = maxDataValue = fetcher.fetch(firstTp);
			totalElapsedTime = lastTp.getElapsedTime();
			for (TrackPoint tp : trackpoints) {
				double value = fetcher.fetch(tp);
				if (value > maxDataValue)
					maxDataValue = value;
				else if (value < minDataValue)
					minDataValue = value;
			}
		}
	}

// Denna metod skapar två arrayer (heltal) som ska fyllas med
// respektive datavärde och tidsvärde, fast omräknat till det
// antal pixlar som kan visas i x-led och det antal pixlar som
// kan visas i y-led. Sammanfattat: anpassa mätvärdena till
// panelens höjd och bredd och lägg de nya värdena i en x-array
// och en y-array.
	private void createArrays() {
		if (trackpoints != null && trackpoints.size() > 0) {
			findLimitsInData();
			width = getWidth();
			height = getHeight();
			yPixels = new int[width];
			xPixels = new int[width];
			double timeStep = totalElapsedTime / width;
			double yVariation = maxDataValue - minDataValue;
			double yScale = height / yVariation;
			Iterator<TrackPoint> tpit = trackpoints.iterator();
			if (tpit.hasNext()) {
				TrackPoint tp = tpit.next();
				for (int x = 0; x < width; x++) {
					double time = x * timeStep;
					while (tpit.hasNext() && tp.getElapsedTime() < time)
						tp = tpit.next();
					double value = fetcher.fetch(tp);
					value = value - minDataValue;
					yPixels[x] = height - (int) (0.5 + yScale * value);
					xPixels[x] = x;
				}
			}
		}
	}

// Det här är metoden som ritar ut grafiken. Se hur enkelt det blir
// med hjälp av arrayerna och metoden drawPolyline.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		createArrays();
		g.setColor(Color.BLUE);
		g.drawPolyline(xPixels, yPixels, width);
	}
}
