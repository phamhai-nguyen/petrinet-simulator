package logic;

import java.awt.*;
import javax.swing.*;
import java.util.Map;

/**
 * Figure for the PetriNet
 * @author Yuyan Zhao
 */
public class PetriNetFigure {
	private int FRAME_WIDTH = 400;
	private int FRAME_HEIGHT = 400;
	private int SQUARE_SIZE = 30;
	private int CIRCLE_SIZE = 50;
	private int MARGIN = 10;
	private PetriNet petrinet;

	class PetriPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			super.paint(g);

			for (Map.Entry<String, Transition> entry : petrinet.getTransitions().entrySet()) {
				Transition t = entry.getValue();
				drawTransition(t, g);
			}
		}
	}

	public PetriNetFigure(PetriNet pn) {
		petrinet = pn;
	}

	public void drawPetriNet(String title) {
		JFrame frame = new JFrame(title);
		PetriPanel petripanel = new PetriPanel();

		frame.add(petripanel);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // set frame visible
	}

	private void drawTransition(Transition transition, Graphics g) {
		// draw square
		int d = CIRCLE_SIZE * 3 / 2 + SQUARE_SIZE / 2;
		int tx = MARGIN + d + CIRCLE_SIZE / 2;
		int ty = MARGIN + d + CIRCLE_SIZE / 2;
		int nPlaces = transition.getInArcs().size() + transition.getOutArcs().size();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.GRAY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawRect(tx - SQUARE_SIZE / 2, ty - SQUARE_SIZE / 2, SQUARE_SIZE, SQUARE_SIZE);
		g2d.drawString(transition.getId(), tx - SQUARE_SIZE / 2 + MARGIN, ty - SQUARE_SIZE / 2 + SQUARE_SIZE + MARGIN);

		double i = 0.75;
		for (IArc arc : transition.getInArcs()) {
			int px = tx + (int) (d * Math.cos(i * Math.PI)) - CIRCLE_SIZE / 2;
			int py = ty - (int) (d * Math.sin(i * Math.PI)) - CIRCLE_SIZE / 2;
			// draw place
			drawPlace(arc.getPlace(), g, px, py);
			drawArc(arc, g, tx, ty, px, py, i);
			i += 2 / (double) nPlaces;
		}

		for (IArc arc : transition.getOutArcs()) {
			int px = tx + (int) (d * Math.cos(i * Math.PI)) - CIRCLE_SIZE / 2;
			int py = ty - (int) (d * Math.sin(i * Math.PI)) - CIRCLE_SIZE / 2;
			// draw place
			drawPlace(arc.getPlace(), g, px, py);
			drawArc(arc, g, tx, ty, px, py, i);
			i += 2 / (double) nPlaces;
		}
	}

	private void drawPlace(Place place, Graphics g, int x, int y) {
		// draw place
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.BLUE);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
		g2d.drawString(place.getId(), x + CIRCLE_SIZE / 2 - 5, y + CIRCLE_SIZE + 10);
		g2d.drawString(String.valueOf(place.getNTokens()), x + CIRCLE_SIZE / 2 - 5, y + CIRCLE_SIZE / 2 + 5);
	}

	private void drawArc(IArc arc, Graphics g, int tx, int ty, int px, int py, double i) {
		int ax1 = tx + (int) ((SQUARE_SIZE * 3 / 4) * Math.cos(i * Math.PI));
		int ay1 = ty - (int) ((SQUARE_SIZE * 3 / 4) * Math.sin(i * Math.PI));
		int ax2 = px + CIRCLE_SIZE / 2 - (int) ((CIRCLE_SIZE / 2) * Math.cos(i * Math.PI));
		int ay2 = py + CIRCLE_SIZE / 2 + (int) ((CIRCLE_SIZE / 2) * Math.sin(i * Math.PI));
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawLine(ax1, ay1, ax2, ay2);
		double ratio = 0.15;
		// draw arrow
		if (arc.getDirection().equals("t2p")) {
			g2d.drawLine(ax2, ay2, ax2 - (int) (10 * Math.cos((i + ratio) * Math.PI)),
					ay2 + (int) (10 * Math.sin((i + ratio) * Math.PI)));
			g2d.drawLine(ax2, ay2, ax2 - (int) (10 * Math.cos((i - ratio) * Math.PI)),
					ay2 + (int) (10 * Math.sin((i - ratio) * Math.PI)));
		} else {
			g2d.drawLine(ax1, ay1, ax1 + (int) (10 * Math.cos((i + ratio) * Math.PI)),
					ay1 - (int) (10 * Math.sin((i + ratio) * Math.PI)));
			g2d.drawLine(ax1, ay1, ax1 + (int) (10 * Math.cos((i - ratio) * Math.PI)),
					ay1 - (int) (10 * Math.sin((i - ratio) * Math.PI)));
		}

		if (arc.getClass().getSimpleName().equals("Arc")) {
			g2d.drawString(String.valueOf(arc.getWeight()), (ax1 + ax2) / 2, (ay1 + ay2) / 2);
		} else {
			g2d.drawString(arc.getClass().getSimpleName().replace("Arc", ""), (ax1 + ax2) / 2, (ay1 + ay2) / 2);
		}
	}
}
