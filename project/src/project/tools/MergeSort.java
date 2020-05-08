package project.tools;

import java.util.Comparator;

import project.mylist.MyList;
import project.mylist.MyListSlot;

public class MergeSort<T extends Comparable<T>> {

	/**
	 * Dividing-Part of merge sort.
	 * Splits the list in two halfs: leftList & rightList.
	 * Then sorts them (recursive calls) and merges them.
	 * @param <T>
	 * @param <C>
	 * @param myList
	 * @param myComperator
	 * @return
	 */
	public static <T extends Comparable<T>, C extends Comparator<T>> MyList<T> sort(MyList<T> myList, C myComperator) {
		if(myList.getLength() <= 1) return myList; 
		MyListSlot<T> pointer = myList.getHeadSlot();
		MyList<T> leftList = new MyList<T>();
		MyList<T> rightList = new MyList<T>();
		int i;
		for(i = 0; i < myList.getLength()/2;i++) {
			leftList.addObjectToTail(pointer.getObject());
			pointer = pointer.getNext();
		}
		for(;i < myList.getLength(); i++) {
			rightList.addObjectToTail(pointer.getObject());
			pointer = pointer.getNext();
		}
		leftList = sort(leftList, myComperator);
		rightList = sort(rightList, myComperator);
		MyList<T> myListToReturn = merge(leftList, rightList, myComperator);
		return myListToReturn;
	}
	
	/**
	 * Merching two list.
	 * Adding smaller head of the two lists and then removes it.
	 * @param <T>
	 * @param <C>
	 * @param leftList
	 * @param rightList
	 * @param myComperator
	 * @return
	 */
	private static <T extends Comparable<T>, C extends Comparator<T>> MyList<T> merge(MyList<T> leftList, MyList<T> rightList, C myComperator){
		MyList<T> myListToReturn = new MyList<T>();
		MyListSlot<T> leftHeadSlot = leftList.getHeadSlot();
		MyListSlot<T> rightHeadSlot = rightList.getHeadSlot();
		while(leftHeadSlot != null && rightHeadSlot != null) {
			double compareResult;
			if(myComperator != null) {
				compareResult = myComperator.compare(leftHeadSlot.getObject(), rightHeadSlot.getObject());
			} else {
				compareResult = leftHeadSlot.getObject().compareTo(rightHeadSlot.getObject());
			}
			if(compareResult > 0) {
				myListToReturn.addObjectToTail(leftHeadSlot.getObject());
				leftList.removeAtIndex(0);
				leftHeadSlot = leftList.getHeadSlot();
			} else {
				myListToReturn.addObjectToTail(rightHeadSlot.getObject());
				rightList.removeAtIndex(0);
				rightHeadSlot = rightList.getHeadSlot();
			}
		}
		myListToReturn.addList(leftList);
		myListToReturn.addList(rightList);
		return myListToReturn;
	}
}
