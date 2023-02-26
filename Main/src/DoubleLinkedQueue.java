public class DoubleLinkedQueue<T> implements Queue<T>, Benchmark{

    // Properties
    Node head;
    Node tail;


    @Override
    public void add(T item){
        if(head != null){  // If list isn't empty, attach to tail
            this.tail = new Node(tail, item);
        }
        else{  // Set head and tail to first element
            head = new Node(item);
            tail = head;
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
