# Petri Net Simulator
A Petri net is made up of places which contain tokens, transitions and arcs that carry a weight linking places to transitions or transitions to places.

## Source code
The main classes and interfaces for implementing the Petri net are shown as below.
The visibility of the class PetriNet is `public`, which provides interfaces for users. While the visibility of class Place, Transition, Arc, EmptyArc, ZeroArc is `default`, which can prevent users from directly modifying objects of these classes. For example, it can prevent users from directly calling the constructors of these classes to generate objects that are not added to Petri Net.

### Interface IPetriNet, class PetriNet
PetriNet class contains the main functions for Petri Net simulation and implemented the interface IPerinet.  
+ **public PetriNet()**  
The constructor for creating a PetriNet.  
It initializes a HashMap of places, a HashMap of transitions and a HashMap of arcs. There are also index for place, transition, and arc with a view to naming their id.  
+ **public void addPlace(int n)**  
Add a place to the Petri Net with an initial number of tokens. If n is negative, an error message is printed.  
Parameters:  
n: an integer representing the initial number of tokens in the place.  
+ **public void removePlace(String id)**  
Remove a place from the Petri Net by its unique identifier.  
Parameters:  
id: a string representing the unique identifier of the place to be removed.  
+ **public void addPlaceTokens(String id, int n)**  
Add tokens to a selected place. If n is negative or the selected place is null, an error message is printed.  
Parameters:  
id: the unique identifier of the place to add tokens to.  
n: an integer representing the number of tokens to add.  
+ **public void removePlaceTokens(String id, int n)**  
Remove tokens from a selected place. If n is negative or the selected place is null, an error message is printed.  
Parameters:  
id: the unique identifier of the place to remove tokens from.  
n: an integer representing the number of tokens to remove.  
+ **public void setPlaceNTokens(String id, int n)**  
Set the number of tokens in a selected place. If n is negative or the selected place is null, an error message is printed.  
Parameters:  
id: the unique identifier of the place.  
n: an integer representing the new number of tokens.  
+ **public int getNTokens(String id)**  
Get the number of tokens in a selected place. If the selected place is null, an error message is printed.  
Parameters:  
id: the unique identifier of the place.  
Returns:  
An integer representing the number of tokens in the selected place.  
+ **public void addTransition()**  
Add a transition to the Petri Net.  
+ **public void removeTransition(String id)**  
Remove a transition from the Petri Net by its unique identifier.  
Parameters:  
id: a string representing the unique identifier of the transition to be removed.  
+ **public void fireTransition(String id)**  
Fire a selected transition. If the selected transition is null, an error message is printed.  
Parameters:  
id: the unique identifier of the transition to fire.  
+ **public void addArc(String sourceId, String targetId, int weight)**  
Add a regular arc by specifying the source and target objects and the arc's weight. If the weight is not positive, an error message is printed. If the source or target object doesn't exist or if an arc in the same direction already exists, an error message is printed.  
Parameters:  
sourceId: the unique identifier of the source object.  
targetId: the unique identifier of the target object.  
weight: an integer representing the weight of the arc.  
+ **public void setArcWeight(String id, int weight)**  
Set the weight of a selected regular arc. If the weight is not positive or if the selected object is not an instance of the Arc class, an error message is printed.  
Parameters:  
id: the unique identifier of the arc.  
weight: an integer representing the new weight to set for the arc.  
+ **public void addEmptyArc(String sourceId, String targetId)**  
Add an empty arc connecting a source and target object. If the source or target object doesn't exist or if an arc in the same direction already exists, an error message is printed.  
Parameters:  
sourceId: the unique identifier of the source object.  
targetId: the unique identifier of the target object.  
+ **public void addZeroArc(String sourceId, String targetId)**  
Add a zero arc connecting a source and target object. If the source or target object doesn't exist or if an arc in the same direction already exists, an error message is printed.  
Parameters:  
sourceId: the unique identifier of the source object.  
targetId: the unique identifier of the target object.  
+ **public void removeIArc(String id)**  
Remove a selected arc, empty arc, or zero arc by its unique identifier. If the selected arc is null, an error message is printed.  
Parameters:  
id: the unique identifier of the arc to be removed.  
+ **public String toString()**  
Print the Petri Net.
Return:
The string to describe the Petri Net.  
+ **public void draw()**  
Draw the figure of the Petri Net using Swing\'s JFrame.  

### class PetriNetFigure
It contains functions to draw arcs, places, transitions and simulate 2D Petri Net on JFrame.  

### class Place
It includes the nTokens attribute, as well as methods for obtaining, setting, adding and decreasing the number of tokens in the place.  
+ **public Place(String id, int nTokens)**  
Constructor for creating a new place.  
Parameters:  
id: a string representing the unique identifier for the place.  
nTokens: an integer representing the initial number of tokens in the place. Must be non-negative. If provided with a negative value, it will be set to 0.  
+ **public int getNTokens()**  
Get the number of tokens currently in the place.  
Returns:  
An integer representing the number of tokens in the place.  
+ **public void setNTokens(int n)**  
Set the number of tokens in the place.  
Parameters:  
n: an integer representing the number of tokens. Must be non-negative. If provided with a negative value, an error message will be printed, and the number of tokens will not be updated.  
+ **public void removeTokens(int n)**  
Remove a specified number of tokens from the place.  
Parameters:  
n: an integer representing the number of tokens to be removed. Must be non-negative. If provided with a negative value, an error message will be printed, and the number of tokens will not be updated.  
+ **public void addTokens(int n)**  
Add a specified number of tokens to the place.  
Parameters:  
n: an integer representing the number of tokens to be added. Must be non-negative. If provided with a negative value, an error message will be printed, and the number of tokens will not be updated.  

### Interface IArc, class Arc, class ZeroArc, class EmptyArc
IArc interface provides abstract methods to be implemented in Arc, ZeroArc and EmptyArc classes.  
The Arc class includes weight, direction, place and transition as attributes.  
But in the ZeroArc and EmptyArc classes, they only have a fixed direction from place to transition and have a fixed weight.
+ **public int getWeight()**  
Get the weight of the arc. For EmptyArc, weight = nTokens of the connected place; for ZeroArc, weight = 0.  
Returns:  
An integer representing the weight of the arc.  
+ **public void setWeight(int w)**  
Set the weight of the arc. For EmptyArc and ZeroArc, do nothing.  
Parameters:  
w: an integer representing the new weight to be set for the arc.  
+ **public String getDirection()**  
Get the direction of the arc. For EmptyArc and ZeroArc, direction is p2t.  
Returns:  
A string representing the direction of the arc.  
+ **public boolean isFirable()**  
Check if the arc is firable.  
For Arc, it's firable when the number of tokens in the source place is equal or greater than the weight of the arc entering transition;  
For EmptyArc, it's firable when the number of tokens in the source place is equal or greater than 1.  
For ZeroArc, it's firable when the number of tokens in the source place is 0;  
Returns:  
true if the arc is firable; otherwise, false.  

### class Transition
It includes inArcs and outArcs attributes of type List \< IArc \> as well as the fire() method for firing the transition. 
+ **public Transition(String id)**  
Constructor for creating a new transition.  
Parameters:  
id: a string representing the unique identifier for the transition.  
+ public void addInArc(IArc a)  
Add an incoming arc (IArc) to the transition.  
Parameters:  
a: an incoming arc that connects a place to the transition.  
+ **public void addOutArc(IArc a)**  
Add an outgoing arc (IArc) to the transition.  
Parameters:  
a: an outgoing arc that connects the transition to a place.  
+ **public void removeArc(IArc a)**  
Remove an arc (IArc) from the transition's list of incoming and outgoing arcs.  
Parameters:  
a: the arc to be removed.  
+ **public boolean fire()**
Checks if the Petri net can be fired. If it can be fired, remove tokens from source places and add tokens to destination
places.  
Returns: true if all the incoming arcs are firable, and the transition is fired; otherwise, false.

## Test
There are 2 JUnit test classes for testing the Petri Net project: PetriNetCreationTest in the section 7.2 of test plan, PetriNetActivationTest for the section 7.3 of test plan.  
The coverage in EclEmma reached 99.1%. The uncovered codes are in the constructer of the class Place and class Arc to check the validity of parameters, because the parameters have been checked in the Petri Net. The other is in the setWeight() function of the class EmptyArc and class Zero Arc, because their weight don't need to be setted.

## Example usage
1. Add places and a transition  
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/1.png)

2. Add arcs  
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/2.png)
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/simulation1.png)

4. Fire the transition, the first time can fire, the second time can't  
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/3.png)
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/simulation2.png)

6. Modify the Petrinet, and fire the transition  
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/4.png)
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/simulation3.png)
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/simulation4.png)

8. Remove the place (and the related arcs), remove the transition (and the related arcs)  
![image](https://github.com/yuyan-z/fil-rouge/blob/main/example/5.png)














