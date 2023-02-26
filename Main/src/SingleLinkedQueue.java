public class SingleLinkedQueue<T> implements Queue<T>, Benchmark{

    Node head;  // Property


    @Override
    public void add(T item) {
        Node node = new Node(item);  // Node to be added
        if (head!=null){  // Not empty, append node to bottom
            Node nxt = head;
            while(nxt.right != null){
                nxt = nxt.right;
            }
            nxt.right = node;
        }
        else{  // Empty, create new head
            head = node;
        }
    }


    @Override
    public T remove() {
        if(head!=null){  // If there is something to remove
            Node<T> node = head;
            head = node.right;
            return node.item;
        }
        return null;  // Else returns nothing
    }

    @Override
    public double bench() {
        return 0;
    }
}
