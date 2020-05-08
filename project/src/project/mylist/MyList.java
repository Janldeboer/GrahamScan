package project.mylist;

import java.lang.reflect.Array;

import project.point.Point;

/**
 * 
 * @author Jan de Boer
 * 
 * "MyList" represents a double-linked list.
 * It points on the headSlot and tailSlot of the list.
 *
 * @param <T>
 */
public class MyList<T extends Comparable<T>> {
	
	private MyListSlot<T> headSlot;
	private MyListSlot<T> tailSlot;
	
	/**
	 * Constructor, only setting the type.
	 * @param type: the type of the MyList.
	 */
	public MyList(){
	}

	/**
	 * Constructor, setting the type and a variable amount of Objects.
	 * @param type: the type of the MyList.
	 * @param startObjects: the Objects to add to the new MyList.
	 */
	public MyList(@SuppressWarnings("unchecked") T... startObjects) {
		addObjectsToTail(startObjects);
	}

	/**
	 * @return the headObject
	 */
	public MyListSlot<T> getHeadSlot() {
		return headSlot;
	}

	/**
	 * @return the tailObject
	 */
	public MyListSlot<T> getTailSlot() {
		return tailSlot;
	}

	/**
	 * @return the length, by going through the list and counting.
	 */
	public int getLength() {
		int counter = 0;
		MyListSlot<T> pointer = headSlot;
		while(pointer != null) {
			counter++;
			pointer = pointer.getNext();
		}
		return counter;
	}
	

	/**
	 * Adds Object to the end of the list.
	 * @param object: the object to add.
	 */
	public void addObjectToTail(T object) {
		MyListSlot<T> newSlot = new MyListSlot<T>(object);
		if(headSlot == null) {
			headSlot = newSlot;
		} else {
			getTailSlot().attach(newSlot);
		}
		tailSlot = newSlot;
	}

	/**
	 *Adds multiple objects to the end of the list.
	 * @param objectsToAdd
	 */
	@SuppressWarnings("unchecked")
	public void addObjectsToTail(T... objectsToAdd) {
		for(int i = 0; i < objectsToAdd.length; i++) {
			this.addObjectToTail(objectsToAdd[i]);
		}
	}
	
	/**
	 * Adds an Object to the MyList in head position.
	 * @param object: the object to add
	 */
	public void addObjectAsHead(T object) {
		if(headSlot == null) {
			addObjectToTail(object);
			return;
		}
		MyListSlot<T> newSlot = new MyListSlot<T>(object);
		newSlot.attach(headSlot);
		headSlot = newSlot;
	}
	
	/**
	 * Removes the slot at the given index.
	 * Returns true if the operation was sucessful.
	 * @param index
	 * @return
	 */
	public boolean removeAtIndex(int index) {
		MyListSlot<T> pointer = headSlot;
		if(pointer == null) return false;
		for(int i = 0; i < index; i++) {
			pointer = pointer.getNext();
			if(pointer == null) return false;
		}
		removeSlot(pointer);
		return true;
	}
	
	/**
	 * Deleting a slot by attaching the previous slot to the next slot, if possible.
	 * Only used for internal computation.
	 * @param slotToRemove
	 * @return
	 */
	private T removeSlot(MyListSlot<T> slotToRemove) {
		if (getLength() == 0) return null;
		MyListSlot<T> previousSlot = slotToRemove.getPrevious();
		MyListSlot<T> nextSlot = slotToRemove.getNext();
		slotToRemove.disconnectSlot();
		if(nextSlot == null) {
			tailSlot = previousSlot;
		}
		if(previousSlot == null) {
			headSlot = nextSlot;
		} else {
			previousSlot.attach(nextSlot);
		}
		T object = slotToRemove.getObject();
		slotToRemove = null; //will be removed by the garbage collector
		return object;
	}
	
	/**
	 * Searches for a slot containing object T to then delete the Slot with the object.	
	 * @param object
	 * @return true if the operation was sucessful
	 */
	public boolean removeObject(T object) {
		MyListSlot<T> slotToRemove = searchObject(object);
		if(slotToRemove != null) {
			removeSlot(slotToRemove);
			return true;
		}
		return false;
	}
	
	/**
	 * Searches an object in the list an return the slot containing it, if it's found.
	 * @param object
	 * @return the slot containg the object
	 */
	private MyListSlot<T> searchObject(T object){
		MyListSlot<T> pointer = headSlot;
		while(pointer != null) {
			if(pointer.getObject() == object) {
				return pointer;
			}
			pointer = pointer.getNext();
		}
		return null;
	}
	
	/**
	 * toString-method so MyList is well printable.
	 * @return representative String to print
	 */
	public String toString(){
		MyListSlot<T> pointer = headSlot;
		String outputString = "";///
		while(pointer != null) {
			outputString += pointer.toString();
			pointer = pointer.getNext();
			if(pointer != null) {
				outputString += "\n";
			}
		}
		//outputString += "}";
		return outputString;
	}
	
	/**
	 * Creates an Array that represents the MyList.
	 * @param type
	 * @return
	 */
	public T[] getArray(Class<T> type) {
		@SuppressWarnings("unchecked")
		T[] returnArray = (T[]) Array.newInstance(type, getLength());
		MyListSlot<T> pointer = headSlot;
		for(int i = 0; i < getLength(); i++) {
			returnArray[i] = pointer.getObject();
			pointer = pointer.getNext();
		}
		return returnArray;
	}
	
	/**
	 * Searches for an object in the list and returns true if it's contained, false if it isn't.
	 * @param object
	 * @return true / false
	 */
	public boolean contains(T object) {
		if(searchObject(object) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Creates new slots for the objects of the list to add and attaches them to this.
	 * @param listToAdd
	 */
	public void addList(MyList<T> listToAdd) {
		MyListSlot<T> pointer = listToAdd.headSlot;
		while(pointer != null) {
			addObjectToTail(pointer.getObject());
			pointer = pointer.getNext();
		}
	}
	
	/**
	 * Finds the Object T with the lowest value in the list.	
	 * @return Object T
	 */
	public T min() {
		if(headSlot == null) return null;
		MyListSlot<T> pointer = headSlot;
		T smallestObject = pointer.getObject();
		pointer = pointer.getNext();
		while(pointer != null) {
			if(pointer.getObject().compareTo(smallestObject) < 0){
				smallestObject = pointer.getObject();
			}
			pointer = pointer.getNext();
		}
		return smallestObject;
	}


	/**
	 * This function clones the objects in all slots and creates a new list with new slots to avoid problems with pointers.
	 * @return
	 */
	public  MyList<T> cloneList(){
		MyList<T> clone = new MyList<T>();
		clone.addList(this);
		return clone;
	}
}