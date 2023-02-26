public class Node<T> {

    // Properties
    public T item;  // Stored value
    public Integer key;  // Unique identifier
    public Node left;  // Head
    public Node right;  // Tail


    // Constructor
    public Node(T item, Node tail) {
        this.item = item;
        this.right = tail;
        key=null;
        left = null;
    }
    public Node(Node head, T item){
        this.left = head;
        head.right = this;
        this.item = item;
    }
    public Node(T item) {
        this.item = item;
        this.right = null;
    }
    public Node(Integer key, T item){
        this.key = key;
        this.item = item;
        this.left = null;
        this.right = null;
    }
}
