package project.mylist;

/**
 *  
 * @author Jan de Boer
 *	
 *	The "MyListSlot" represents a slot in a "MyList".
 *	It can hold an object of type T and point on the previous and next slot.
 *	
 * @param <T>
 */
public class MyListSlot<T extends Comparable<T>> {
	private T object;
	private MyListSlot<T> previous;
	private MyListSlot<T> next;
	
	/**
	 * Initializer for a MyListSlot giving the value of the object.
	 * The object can not be changed later.
	 * @param value
	 */
	public MyListSlot(T value) {
		this.object = value;
	}
	
	/**
	 * Returns the object stored in the slot.
	 * @return
	 */
	public T getObject() {
		return this.object;
	}
	
	/**
	 * Get the previous slot.
	 * @return
	 */
	public MyListSlot<T> getPrevious() {
		return this.previous;
	}
	
	/**
	 * Get the next slot.
	 * @return
	 */
	public MyListSlot<T> getNext() {
		return this.next;
	}
	
	/**
	 * Public method to attach a newSlot to this slot (to "next").
	 * @param newNextSlot
	 */
	public void attach(MyListSlot<T> newNextSlot) {
		if(newNextSlot == this) return; //Check if it's not itself
		if(newNextSlot == null) return; //Check if argument isn't null
		if(next != null) {
			next.previous = null;
		}
		if(newNextSlot.previous != null) {
			newNextSlot.previous.next = null;
		}
		this.next = newNextSlot;
		newNextSlot.previous = this;
	}
	
	/**
	 * Public method to safely disconnect a slots from it's neighbours.
	 */
	public void disconnectSlot() {
		if(previous != null) {
			previous.next = null;
			previous = null;
		}
		if(next != null) {
			next.previous = null;
			next = null;
		}
	}
	
	/**
	 * toString-method to make slots printable.
	 * @return printable String, representing the object stored on the slot.
	 */
	public String toString() {
		if(object != null) {
			return object.toString();
		} else {
			return "null";
		}
	}
}
