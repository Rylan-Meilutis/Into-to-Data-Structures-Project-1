// File: DoubleArraySeq.java

// This is an assignment for students to complete after reading Chapter 3 of
// "Data Structures and Other Objects Using Java" by Michael Main.


/******************************************************************************
 * A DoubleArraySeq is a collection of double numbers.
 * The sequence can have a special "current element," which is specified and
 * accessed through four methods that are not available in the bag class
 * (start, getCurrent, advance and isCurrent).
 *
 * @note
 *   (1) The capacity of one a sequence can change after it's created, but
 *   the maximum capacity is limited by the amount of free memory on the
 *   machine. The constructor, addAfter,
 *   addBefore, clone,
 *   and concatenation will result in an
 *   OutOfMemoryError when free memory is exhausted.
 *   <p>
 *   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
 *   (Integer.MAX_VALUE). Any attempt to create a larger capacity
 *   results in a failure due to an arithmetic overflow.
 *
 * @note
 *   This file contains only blank implementations ("stubs")
 *   because this is a Programming Project for my students.
 *
 * @see
 *   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
 *   Java Source Code for this class
 *   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
 *   </A>
 *
 * @author Rylan Meilutis
 * @author Vassily Dudkin
 *
 * @version
 *   March 5, 2002
 ******************************************************************************/
public class DoubleArraySeq implements Cloneable {
    // Invariant of the DoubleArraySeq class:
    //   1. The number of elements in the sequences is in the instance variable
    //      manyItems.
    //   2. For an empty sequence (with no elements), we do not care what is
    //      stored in any of data; for a non-empty sequence, the elements of the
    //      sequence are stored in data[0] through data[manyItems-1], and we
    //      don�t care what�s in the rest of data.
    //   3. If there is a current element, then it lies in data[currentIndex];
    //      if there is no current element, then currentIndex equals manyItems.
    private double[] data;
    private int manyItems;
    private int currentIndex;

    /**
     * Initialize an empty sequence with an initial capacity of 10.  Note that the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     *
     * @param - none
     * <dt><b>PreCondition: </b>This sequence is empty and has an initial capacity of 10.</dt>
     * @throws OutOfMemoryError Indicates insufficient memory for: new double[10].
     **/
    public DoubleArraySeq() {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new double[INITIAL_CAPACITY];
    }


    /**
     * Initialize an empty sequence with a specified initial capacity. Note that the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     *
     * @param initialCapacity the initial capacity of this sequence
     * <dt><b>Precondition: </b> InitialCapacity is non-negative.</dt>
     * </dt></b>postcondition This sequence is empty and has the given initial capacity.</dt>
     * @throws IllegalArgumentException Indicates that initialCapacity is negative.
     * @throws OutOfMemoryError Indicates insufficient memory for: new double[initialCapacity].
     **/
    public DoubleArraySeq(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException
                    ("The initialCapacity is negative: " + initialCapacity);
        }
        data = new double[initialCapacity];
        manyItems = 0;
    }


    /**
     * Add a new element to this sequence, after the current element. If the new element would take this sequence beyond
     * its current capacity, then the capacity is increased before adding the new element.
     *
     * @param element the new element that is being added
     * <dt><b>PostCondition: </b>A new copy of the element has been added to this sequence. If there was a current
     * element, then the new element is placed after the current element. If there was no current element, then the new
     * element is placed at the end of the sequence. In all cases, the new element becomes the new current element of
     * this sequence.</dt>
     * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause the sequence to fail with an
     * arithmetic overflow.
     * @throws OutOfMemoryError Indicates insufficient memory for increasing the sequence's capacity.
     **/
    public void addAfter(double element) {
        if (manyItems + 1 == data.length) {
            ensureCapacity((manyItems + 1) * 2);
        }
        if (currentIndex != 0) {
            double tmp = data[currentIndex];
            data[++currentIndex] = element;
            data[currentIndex + 1] = tmp;
            manyItems++;
        }
        else {
            data[manyItems] = element;
            currentIndex = manyItems++;
        }

    }


    /**
     * Add a new element to this sequence, before the current element. If the new element would take this sequence
     * beyond its current capacity, then the capacity is increased before adding the new element.
     *
     * @param element the new element that is being added
     * <dt><b>postcondition</b> A new copy of the element has been added to this sequence. If there was a current
     * element, then the new element is placed before the current element. If there was no current element, then the new
     * element is placed at the start of the sequence. In all cases, the new element becomes the new current element of
     * this sequence.</dt>
     *
     * @throws OutOfMemoryError Indicates insufficient memory for increasing the sequence's capacity.
     * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause the sequence to fail with an
     * arithmetic overflow.
     **/
    public void addBefore(double element) {
        if (manyItems + 1 == data.length) {
            ensureCapacity((manyItems + 1) * 2);
        }
        for (int i = manyItems; i > currentIndex; i--) {
            if (i == 0) {
                break;
            }
            data[i] = data[i - 1];
        }
        data[currentIndex] = element;


        manyItems++;
    }


    /**
     * Method that returns true if sequence is the same length and order and data. (The current element could be
     * different) Use the proper format for the equals method shown in the book, we are overriding the equals method
     * from the Object class.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoubleArraySeq other) {
            if (currentIndex != other.currentIndex) {
                return false;
            }

            if (manyItems != other.manyItems) {
                return false;
            }
            for (int i = 0; i < manyItems; i++) {
                if (data[i] != other.data[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * A method to add a new element at the front of the sequence and make it the current element.
     *
     * @param element the new element that is being added
     */
    public void addFront(double element) {
        if (manyItems == data.length) {
            ensureCapacity((manyItems + 1) * 2);
        }
        for (int i = manyItems; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = element;
        manyItems++;
        currentIndex = 0;
    }

    /**
     * A method to remove the element at the front of the sequence. If there is only one element, remove it and make the
     * current index zero (equal to manyItems, which means no current element), otherwise make the current element the
     * new front element.
     *
     * @throws IllegalStateException if the sequence is empty when the method is called.
     */
    void removeFront() {
        if (manyItems == 0) {
            throw new IllegalStateException("The sequence is empty");
        }
        for (int i = 0; i < manyItems - 1; i++) {
            data[i] = data[i + 1];
        }
        manyItems--;
        if (manyItems == 0) {
            currentIndex = manyItems;
        }
    }

    /**
     * A method that returns the nth element of the sequence. Make current element this nth element. NOTE: n is not the
     * index value.  (if the value of n that is passed in is 4, that means index 3 in the array)
     *
     * @throws IllegalStateException if the sequence is empty.
     * @throws IllegalArgumentException if n is greater than the sequence size, or if n is zero or negative.
     */
    double getElement(int n) {
        if (manyItems == 0) {
            throw new IllegalStateException("The sequence is empty");
        }
        if (n <= 0 || n > manyItems) {
            throw new IllegalArgumentException("n is greater than the sequence size, or if n is zero or negative");
        }
        currentIndex = n - 1;
        return data[currentIndex];
    }


    /**
     * Place the contents of another sequence at the end of this sequence.
     *
     * @param addend a sequence whose contents will be placed at the end of this sequence
     *
     * <dt><b>PreCondition: </b> The parameter, addend, is not null. </dt>
     * <dt><b>PostCondition: </b>> The elements from addend have been placed at the end of this sequence. The current
     * element of this</dt> sequence remains where it was, and the addend is also unchanged.
     *
     * @throws NullPointerException Indicates that addend is null.
     * @throws OutOfMemoryError Indicates insufficient memory to increase the size of this sequence.
     * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause an arithmetic overflow that will
     * cause the sequence to fail.
     **/
    public void addAll(DoubleArraySeq addend) {
        if (addend.size() + manyItems > data.length) {
            ensureCapacity(manyItems + addend.manyItems);
        }

        for (int i = 0; i < addend.size(); i++) {
            addAfter(addend.data[i]);
        }
    }


    /**
     * Move forward, so that the current element is now the next element in this sequence.
     *
     * @param - none
     *
     * <dt><b>PreCondition: </b>isCurrent() returns true.</dt>
     * <dt><b>PostCondition </b>If the current element was already the end element of this sequence (with nothing after
     * it), then there is no longer any current element. Otherwise, the new element is the element immediately after the
     * original current element.</dt>
     *
     * @throws IllegalStateException Indicates that there is no current element, so advance may not be called.
     **/
    public void advance() {
        if (isCurrent()) {
            currentIndex++;
        }
        else {
            throw new IllegalStateException("There is no current element, so advance may not be called.");
        }
    }


    /**
     * Generate a copy of this sequence.
     *
     * @param - none
     *
     * @return The return value is a copy of this sequence. Subsequent changes to the copy will not affect the original,
     * nor vice versa.
     *
     * @throws OutOfMemoryError Indicates insufficient memory for creating the clone.
     **/
    public DoubleArraySeq clone() {  // Clone a DoubleArraySeq object.
        DoubleArraySeq answer;

        try {
            answer = (DoubleArraySeq) super.clone();
        }
        catch (CloneNotSupportedException e) {  // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException
                    ("This class does not implement Cloneable");
        }

        answer.currentIndex = currentIndex;
        answer.manyItems = manyItems;
        answer.data = data.clone();

        return answer;
    }


    /**
     * Create a new sequence that contains all the elements from one sequence followed by another.
     *
     * @param s1 the first of two sequences
     * @param s2 the second of two sequences
     *
     * @return a new sequence that has the elements of s1 followed by the elements of s2 (with no current element)
     * <dt><b>precondition</b>  Neither s1 nor s2 is null.</dt>
     *
     * @throws NullPointerException Indicates that one of the arguments is null.
     * @throws OutOfMemoryError Indicates insufficient memory for the new sequence.
     * @note An attempt to create a sequence with a capacity beyond Integer.MAX_VALUE will cause an arithmetic overflow
     * that will cause the sequence to fail.
     **/
    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) {
        DoubleArraySeq newSequence = new DoubleArraySeq(s1.size() + s2.size());
        for (int i = 0; i < s1.size(); i++) {
            newSequence.addAfter(s1.data[i]);
        }
        for (int i = 0; i < s2.size(); i++) {
            newSequence.addAfter(s2.data[i]);
        }
        return newSequence;
    }


    /**
     * Change the current capacity of this sequence.
     *
     * @param minimumCapacity the new capacity for this sequence
     * <dt><b>postcondition</b>  This sequence's capacity has been changed to at least minimumCapacity. If the capacity
     * was already at or greater than minimumCapacity, then the capacity is left unchanged.</dt>
     *
     * @throws OutOfMemoryError Indicates insufficient memory for: new int[minimumCapacity].
     **/
    public void ensureCapacity(int minimumCapacity) {
        double[] biggerArray;

        if (data.length < minimumCapacity) {
            biggerArray = new double[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }


    /**
     * Accessor method to get the current capacity of this sequence. The add method works efficiently (without needing
     * more memory) until this capacity is reached.
     *
     * @param - none
     *
     * @return the current capacity of this sequence
     **/
    public int getCapacity() {
        return data.length;
    }


    /**
     * Accessor method to get the current element of this sequence.
     *
     * @param - none
     *
     * @return the current element of this sequence
     * <dt><b>precondition</b>  isCurrent() returns true.</dt>
     *
     * @throws IllegalStateException Indicates that there is no current element, so getCurrent may not be called.
     **/
    public double getCurrent() {
        if (isCurrent()) {
            return data[currentIndex];
        }
        else {
            throw new IllegalStateException("There is no current element, so getCurrent may not be called.");
        }
    }


    /**
     * Accessor method to determine whether this sequence has a specified current element that can be retrieved with the
     * getCurrent method.
     *
     * @param - none
     *
     * @return true (there is a current element) or false (there is no current element at the moment)
     **/
    public boolean isCurrent() {
        return currentIndex != manyItems;
    }

    /**
     * Remove the current element from this sequence.
     *
     * @param - none
     *
     * <dt><b>precondition</b>  isCurrent() returns true.</dt>
     * <dt><b>postcondition</b>  The current element has been removed from this sequence, and the following element (if
     * there is one) is now the new current element. If there was no following element, then there is now no current
     * element.</dt>
     *
     * @throws IllegalStateException Indicates that there is no current element, so removeCurrent may not be called.
     **/
    public void removeCurrent() {
        if (isCurrent()) {
            for (int i = currentIndex; i < manyItems - 1; i++) {
                data[i] = data[i + 1];
            }
            manyItems--;
        }
        else {
            throw new IllegalStateException("There is no current element, so removeCurrent may not be called.");
        }
    }


    /**
     * Determine the number of elements in this sequence.
     *
     * @param - none
     *
     * @return the number of elements in this sequence
     **/
    public int size() {
        return manyItems;
    }


    /**
     * Set the current element at the front of this sequence.
     *
     * @param - none
     *
     * <dt><b>postcondition</b>  The front element of this sequence is now the current element (but if this sequence
     * has no elements at all, then there is no current element).</dt>
     **/
    public void start() {
        currentIndex = 0;
    }


    /**
     * A method that makes the nth element become the current element
     *
     * @param n the location of the element to be made the current element
     *
     * @throws IllegalStateException if the sequence is empty.
     * @throws IllegalArgumentException if n does not represent a valid location in the array
     */
    void setCurrent(int n) {
        if (n <= 0 || n > manyItems) {
            throw new IllegalArgumentException("n does not represent a valid location in the array");
        }
        currentIndex = n - 1;
    }


    /**
     * returns the elements of the sequence in a string
     *
     * @return the elements of the sequence in a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int current = currentIndex;
        start();
        while (currentIndex < manyItems) {
            sb.append(getCurrent());
            advance();
        }
        currentIndex = current;
        return "[ " + sb + " ]";
    }

    /**
     * Reduce the current capacity of this sequence to its actual size (i.e., the number of elements it contains).
     *
     * @param - none
     * <dt><b>postcondition</b>  This sequence's capacity has been changed to its current size.</dt>
     *
     * @throws OutOfMemoryError Indicates insufficient memory for altering the capacity.
     **/
    public void trimToSize() {
        double[] trimmedArray;

        if (data.length != manyItems) {
            trimmedArray = new double[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
}










