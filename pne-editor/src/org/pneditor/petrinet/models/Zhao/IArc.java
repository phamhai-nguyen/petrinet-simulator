package org.pneditor.petrinet.models.Zhao;

/**
 * Interface for the class Arc, EmptyArc, ZeroArc
 * @author Yuyan Zhao
 */
public interface IArc {
	/**
	 * Get the id of the arc/empty arc/zero arc.
	 * 
	 * @return id: id of the arc/empty arc/zero arc
	 */
	public String getId();

	/**
	 * Get the id of the source object.
	 * 
	 * @return id: id of the source object.
	 */
	public String getSourceId();

	/**
	 * Get the id of the target object.
	 * 
	 * @return id: id of the target object
	 */
	public String getTargetId();

	/**
	 * Get the place connected.
	 * 
	 * @return place
	 */
	public Place getPlace();

	/**
	 * Get the transition connected.
	 * 
	 * @return transition
	 */
	public Transition getTransition();

	/**
	 * Get the weight.
	 * For EmptyArc, weight = nTokens of the connected place; for ZeroArc, weight = 0.
	 * 
	 * @return weight
	 */
	public int getWeight();

	/**
	 * Set the weight.
	 * For EmptyArc and ZeroArc, do nothing.
	 * 
	 * @param w: weight
	 */
	public void setWeight(int w);

	/**
	 * Get the direction.
	 * For EmptyArc and ZeroArc, direction is p2t.
	 * 
	 * @return direction
	 */
	public String getDirection();

	/**
	 * Check if it's firable.
	 * 
	 * @return true or false
	 */
	public boolean isFirable();
}
