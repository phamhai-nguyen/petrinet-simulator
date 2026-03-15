package org.pneditor.petrinet.models.Zhao;

/**
 * Zero arc for Petri Net.
 * @author Yuyan Zhao
 */
public class ZeroArc implements IArc {
	private String id;
	private Place place;
	private Transition transition;

	/* constructor */
	public ZeroArc(String s, Place p, Transition t) {
		id = s;
		place = p;
		transition = t;
	}

	/* methods of IArc */
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Place getPlace() {
		return place;
	}

	@Override
	public Transition getTransition() {
		return transition;
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public void setWeight(int w) {
		return;
	}

	@Override
	public String getDirection() {
		return "p2t";
	}

	@Override
	public String getSourceId() {
		return place.getId();
	}

	@Override
	public String getTargetId() {
		return transition.getId();
	}

	@Override
	public boolean isFirable() {
		boolean res = false;
		// when the source place has no tokens, it's firable
		if (place.getNTokens() == 0) {
			res = true;
		}
		return res;
	}

	@Override
	public String toString() {
		return "(" + place.getId() + "->" + transition.getId() + " ZeroArc)";
	}
}
