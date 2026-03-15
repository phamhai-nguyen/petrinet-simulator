package org.pneditor.petrinet.adapters.Zhao;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.Zhao.Place;

/**
 * Adapter for the class Place of my project.
 * @author Yuyan Zhao
 */
public class PlaceAdapter extends AbstractPlace {
	private Place place;

	public PlaceAdapter(String label) {
		super(label);
	}
	
	public void setPlace(Place p) {
		place = p;
	}

	@Override
	public void addToken() {
		place.addTokens(1);
	}

	@Override
	public void removeToken() {
		place.removeTokens(1);
	}

	@Override
	public int getTokens() {
		return place.getNTokens();
	}

	@Override
	public void setTokens(int tokens) {
		place.setNTokens(tokens);
	}

}
