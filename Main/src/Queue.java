public interface Queue<T>{
    void add(T item);  // Adds an item to the list
    T remove();  // Removes the top item of the list
}
