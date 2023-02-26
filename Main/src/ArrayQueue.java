import java.lang.reflect.Array;

public class ArrayQueue<T> implements Queue<T>{

    // Properties
    T[] queue;
    int first = 0;  // Points to the first item of the queue
    int last = 0;  // Points to the last item in the queue
    Class<T> cls;  // Saves the class for T


    // Constructor
    public ArrayQueue(Class<T> cls, int arr_size){
        this.cls = cls;
        this.queue = (T[]) Array.newInstance(cls, arr_size);
    }


    // If the queue is empty
    boolean empty(){
        return (first == last) && queue[first] == null;
    }


    // Returns expanded array
    public void expand(T item){

        // Init new array and size
        int size = queue.length;
        T[] arr = (T[]) Array.newInstance(cls, size*2);
        int i = 0;  // New array index

        if(last > first){  // If last is larger than first, normal
            while(i < size){
                arr[i] = queue[i];
                i++;
            }
        }
        else{  // If last is smaller than first, has looped
            int x = first;  // Temporary position in queue
            while(x < size){  // First to end
                arr[i++] = queue[x++];
            }
            x = 0;
            while(x <= last){  // Start to last
                arr[i++] = queue[x++];
            }
        }
        arr[i] = item;  // Add new item
        queue = arr;  // Assign queue as new array
        first = 0;
        last=size;  // Increment last after adding
    }


    @Override public void add(T item) {
        if(last >= first){  // Last is same or after first
            if(empty()){  // Empty
                queue[first] = item;
            }
            else if(last != queue.length-1){  //  Free space to the right
                queue[++last] = item;
            }
            else{  // Last would overflow, wrap around?
                if(first != 0){  // First position is free, wrap around
                    queue[last=0] = item;
                }
                else{  // Array full, expand instead
                    expand(item);
                }
            }
        }
        else{  // Last is before first, has wrapped around
            if((last+1)!=first){  // Won't overwrite first
                queue[++last] = item;
            }
            else{  // Will overwrite first, expand
                expand(item);
            }
        }
    }

    @Override
    public T remove() {
        if(!empty()){  // Not empty
            if(first != queue.length-1){  // Won't wrap around
                T value = queue[first];
                queue[first] = null;
                first++;
                return value;
            }
            else{  // Will wrap around
                T value = queue[queue.length-1];
                queue[queue.length-1] = null;
                first = 0;
                return value;
            }
        }
        else{  // Empty
            return null;
        }
    }
}
