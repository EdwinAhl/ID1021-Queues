import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T>{

    // Properties
    public Node root;


    // Constructor
    public BinaryTree(){
        root = null;
    }


    // Adds a new node recursively
    public void add(int key, T item){
        root = addRecursive(root, key, item);
    }
    private Node addRecursive(Node current, int key, T value){
        if(current == null){  // Root is null or reached point of leaf, create new
            return new Node(key, value);
        }
        if(current.key > key){ // Current key is larger than given key, move to the left and repeat
            current.left = addRecursive(current.left, key, value);
            return current;
        }
        if (current.key < key){  // Current key is smaller than given key, move to the right and repeat
            current.right = addRecursive(current.right, key, value);
            return current;
        }
        else{  // Key already present, update value
            current.item = value;
            return current;
        }
    }


    // Finds and returns the value associated to the key. If key is not found it returns null.
    public T lookup(Integer key){
        Node<T> current = root;  // Start at root
        current = lookupRecursive(current, key);  // Recursion
        if (current!=null){  // If there exists a node with the key, return its value
            return current.item;
        }
        else {
            return null;
        }
    }
    private Node lookupRecursive(Node current, Integer key){
        if(current == null){  // Root is null or reached point of leaf => no node with key, return null
            return null;
        }
        else if(current.key > key){ // Current key is larger than given key, move to the left and repeat
            current.left = lookupRecursive(current.left, key);
            return current;
        }
        else if (current.key < key){  // Current key is smaller than given key, move to the right and repeat
            current.right = lookupRecursive(current.right, key);
            return current;
        }
        return null;
    }



    // Creates an iterator
    public Iterator<T> iterator() {
        return new TreeIterator();
    }


    // Defines an iterator
    public class TreeIterator<T> implements Iterator<T> {

        // Properties
        private Node next;
        private Queue<Node> queue;


        // Constructor
        public TreeIterator() {

            // Init
            next = BinaryTree.this.root;  // Start at root
            queue = new SingleLinkedQueue();  // Create queue
            queue.add(next);

            if(next == null) {return;}  // No nodes
        }


        // If there exists a value
        @Override
        public boolean hasNext() {
            next = queue.remove();
            return next != null;
        }


        // Selects the next value
        @Override
        public T next(){
            // Add left node if it exists
            if(next.left != null){
                queue.add(next.left);
            }
            // Add right node if it exists
            if(next.right != null){
                queue.add(next.right);
            }
            return (T)next.item;
        }


        @Override
        public void remove() {throw new UnsupportedOperationException();}
    }
}