class List {
 
    private class Node {
        // Fields
 
        int data;
        Node next;
        Node prev;
 
        // Constructor
        Node(int data) {
            this.data = data;
            prev = next = null;
        }
 
        public String toString() {
            return String.valueOf(data);
        }
    }
    // Nodes
    private Node front;
    private Node back;
    private Node current;
    private int length;
 
    // Constructor
    List() {
        //Creates a new empty List.
        front = back = null;
        length = 0;
        current = null;
    }
 
    // Access Functions 
    int getLength() {
        // getLength(): returns length of this List
        int length = 0;
        if (front != null) {
            Node temp = front;
            while (temp != null) {
                length++;
                temp = temp.next;
            }
        }
        return length;
    }
 
    boolean isEmpty() {
        // isEmpty(): returns true if this is an empty list, false otherwise
 
        return front == null; 
    }
 
    boolean offEnd() {
        // offend(): Returns true if current is undefined.
        if (current == null) {
            return true;
        } else {
            return false;
        }
    }
 
    boolean equals(List L) {
        // Returns true if this List and L are the same integer
        // sequence. Ignores the current element in both Lists.
 
        Node check = L.front;
 
        for (moveTo(0); !offEnd(); moveNext()) {
            if (current.data != check.data) {
                return false;
            }
            check = check.next;
        }
        if (L.getLength() != this.getLength()) {
            return false;
        }
        return true;
 
    }
 
    int getIndex() {
        // If current element is defined, returns its position in this List,                    
        // ranging from 0 to getLength()-1 inclusive.
        // If current element is undefined, returns -1.
        int position = 0;
        if (current == null) {
            return -1;
        }
        //for (Node N = front; N != null; N = N.next) {
        for (Node M = front; M != current; M = M.next) {
            position++;
        }
 
        return position;
    }
 
    int getFront() {
        // Returns front element. Pre: !isEmpty().
        if (isEmpty()) {
            throw new RuntimeException("List Error: getFront() called on empty List");
        }
        return front.data;
    }
 
    int getBack() {
        // Returns back element. Pre: !isEmpty().
        if (isEmpty()) {
            throw new RuntimeException("List Error: getBack() called on empty List");
        }
        return back.data;
    }
 
    int getCurrent() {
        // Returns current element. Pre: !isEmpty(), !offEnd().
 
        if (isEmpty()) {
            throw new RuntimeException("List Error: getCurrent() called on empty List");
        }
        if (offEnd()) {
            throw new RuntimeException("List Error: getCurrent() called on undefined current");
        }
        return current.data;
    }
// Manipulation Procedures
 
    void makeEmpty() {
        // Sets this List to the empty state. Post: isEmpty().
        front.next = null;
        back.prev = null;
        front.next = back;
        back.prev = front;
 
        front = null;
        current = null;
 
    }
 
    void moveTo(int i) {
        // If 0 <= i <= getLength()-1, moves current element
        // marker to position i in this List. Otherwise current
        // element becomes undefined.
        // Moves current one step toward front element. // Pre: !isEmpty(), !offEnd().
        current = front;
        if (0 <= i) {
            if (i <= getLength() - 1) {
 
                for (int j = 0; j < i; j++) {
                    moveNext();
                }
            }
        } else {
            current = null;
        }
    }
 
    void movePrev() {
        // Moves current one step toward front element. 
        // Pre: !isEmpty(), !offEnd(). 
        if (isEmpty()) {
            throw new RuntimeException("List Error: movePrev() called on empty list");
        }
        current = current.prev;
    }
 
    void moveNext() {
        // Moves current one step toward back element.
        // Pre: !isEmpty(), !offEnd().
        if (isEmpty() || offEnd()) {
            throw new RuntimeException("List Error: moveNext() called on empty list");
        }
        current = current.next;
    }
 
    void insertFront(int data) {
        // Inserts new element before front element.
        // Post: !isEmpty().
        Node newNode = new Node(data);
        if (front == null) {
            front = newNode;
            back = newNode;
        } else {
            front.prev = newNode;
            newNode.next = front;
            front = newNode;
 
        }
    }
 
    void insertBack(int data) {
        // Inserts new element after back element.
        // Post: !isEmpty().
        Node newNode = new Node(data);
        //if (back == null) {
        if (isEmpty()) {
            back = newNode;
            front = newNode;
        } else {
            back.next = newNode;
            newNode.prev = back;
            back = newNode;
        }
    }
 
    void insertBeforeCurrent(int data) {
        // Inserts new element before current element.
        // Pre: !isEmpty(), !offEnd().
        Node node = new Node(data);
        if (isEmpty()) {
            throw new RuntimeException("List Error: insertBeforeCurrent() is empty.");
        }
        if (offEnd()) {
            throw new RuntimeException("List Error: insertBeforeCurrent() is undefined.");
        } else if (current == front) {
            node.prev = null;
            front = node;
        } else {
            node.prev = current.prev;
            current.prev.next = node;
        }
        node.next = current;
        current.prev = node;
        length++;
    }
 
    void insertAfterCurrent(int data) {
        // Inserts new element after current element.
        // Pre: !isEmpty(), !offEnd().
        if (isEmpty()) {
            throw new RuntimeException("List Error: insertBeforeCurrent() is empty.");
        } else if (offEnd()) {
            throw new RuntimeException("List Error: insertBeforeCurrent() is undefined.");
        }
        Node node = new Node(data);
        node.prev = current;
        node.next = current.next;
        if (current == back) {
            back = node;
        } else {
            current.next.prev = node;
        }
        current.next = node;
 
    }
 
    void deleteFront() {
        // Deletes front element. Pre: !isEmpty().
        if (isEmpty()) {
            throw new RuntimeException("List Error: deleteFront() is empty");
        } else {
            front = front.next;
            front.prev = null;
        }
    }
 
    void deleteBack() {
        // Deletes back element. Pre: !isEmpty().
        if (isEmpty()) {
            throw new RuntimeException("List Error: deleteBack() is empty");
        } else {
            back = back.prev;
            back.next = null;
 
        }
    }
 
    void deleteCurrent() {
        // Deletes current element.
        // Pre: !isEmpty(), !offEnd(); Post: offEnd()
        // Other methods
        if (isEmpty()) {
            throw new RuntimeException("List Error: deleteCurrent() is empty");
        } else if (offEnd()) {
            throw new RuntimeException("List Error: deleteCurrent() is undefined");
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            //current.prev=current.next;
            //current.next=current.prev;
            current = current.next;
        }
    }
 
    List copy() {
        // Returns a new list which is identical to this list. The current
        // element in the new list is undefined, regardless of the state of
        // the current element in this List. The state of this List is
        // unchanged.
        List C = new List();
        Node temp = front;
        for (int i = 0; i < getLength(); i++) {
            C.insertBack(temp.data);
            temp = temp.next;
 
        }
        return C;
    }
 
    public String toString() {
        // Overrides Object's toString method. Returns a string
        // representation of this List consisting of a space
        // separated sequence of integers, with no trailing space.
 
        String display = "";
        for (Node temp = front; temp != null; temp = temp.next) {
            display += temp.toString() + " ";
        }
        return display;
    }
}