package org.pneditor.petrinet.adapters.Zhao;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.Zhao.Arc;
import org.pneditor.petrinet.models.Zhao.EmptyArc;
import org.pneditor.petrinet.models.Zhao.IArc;
import org.pneditor.petrinet.models.Zhao.ZeroArc;

public class ArcAdapter extends AbstractArc {
	private IArc arc;
	private String label;
	private AbstractNode sourceNode;
	private AbstractNode targetNode;
	
	public ArcAdapter(String s, AbstractNode source, AbstractNode destination) {
		label = s;
		sourceNode = source;
		targetNode = destination;	
	}
	
	public void setArc(IArc a) {
		arc = a;
	}
	
	public String getLabel() {
		return label;
	}

	@Override
	public AbstractNode getSource() {
		return sourceNode;
	}

	@Override
	public AbstractNode getDestination() {
		return targetNode;
	}

	@Override
	public boolean isReset() {
		return arc instanceof EmptyArc;
	}

	@Override
	public boolean isRegular() {
		return arc instanceof Arc;
	}

	@Override
	public boolean isInhibitory() {
		return arc instanceof ZeroArc;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return arc.getWeight();
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		arc.setWeight(multiplicity);
	}

}
