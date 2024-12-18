/* ----------------------------------------------------------------------------
 * BSTInterface.java
 *
 * Tree objects of classes that implement this interface are unbounded and allow
 * duplicate elements, but do not allow null elements.
 *
 * In-order, pre-order and post-order traversals are supported.
 *
 * ---------------------------------------------------------------------------*/

package interfaces;

public interface BSTInterface<T extends Comparable<T>> {

    void add(T element);

    boolean remove(T element);
    // remove the first item found in the tree such that item.compareTo(element) == 0
    // if no such item exists, return false

    boolean isEmpty();

    int size();

    boolean contains(T element);
    // return true if there is an item in the tree such that item.compareTo(element) == 0
    // otherwise return false

    T get(T element);
    // return the first item in the tree such that item.compareTo(element) == 0
    // if no such item exists, return null


}