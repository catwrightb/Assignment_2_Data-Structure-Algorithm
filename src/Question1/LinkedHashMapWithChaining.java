package Question1;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapWithChaining<K,V> implements Map<K,V> {

    private Node<K,V>[] hashTable = null;
    private int capacity = 10;
    private int size;

    Node<K,V> head;
    Node<K,V> tail;

    //inner node class
    static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> after;
        Node<K,V> before;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        public Node() {
            //empty constructor
        }
    }

    public LinkedHashMapWithChaining(){
        this.hashTable = new Node[capacity];
        this.size =0;
    }

    /*
    * returns the amount of key-value pairs in the maps
     * */
    @Override
    public int size() {
        return size;
    }

    /*
    *  returns true if no key-values are stored in the map
     * */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /*
    * used to check whether the map has an entry for that key
     * */
    @Override
    public boolean containsKey(Object key) {
        int index = hashCode(key);

        if (hashTable[index] == null) {
            return false;
        } else {
            Node<K, V> node = hashTable[index];

            while (node != null) {
                if (node.key.equals(key)) {
                    return true;
                }

                node = node.next;
            }
            return false;   //returns null if key is not found.
        }
    }

    /*
    * used to check whether the map holds that value
     * */
    @Override
    public boolean containsValue(Object value) {
        Node<K,V> currentNode = head;

        // If current node's element is not equal, move to next node
        while (currentNode != null)
        {
            if (currentNode.value.equals(value))
            {
                return true;
            }

            currentNode = currentNode.after;
        }
        // Not found
        return false;
    }

    /*
    * used to return the value mapped to the key parameter
     * */
    @Override
    public V get(Object key) {
        Node<K,V> target = find(key);
        if (target ==  null){
            return null;
        }

        return target.value;
    }

    private Node<K,V> find(Object key){
        int position = hashCode(key);
        Node<K,V> targetNode = hashTable[position];

        if (targetNode == null){
            return null;
        }
        else {
            while (targetNode != null && !targetNode.key.equals(key)){
                targetNode = targetNode.next;
            }
        }

        return targetNode;
    }

    /*
    * stores the key and value parameters in the map
     * */
    @Override
    public V put(K key, V value) {
        int position = hashCode(key);
        Node<K,V> curr = hashTable[position];

        if (size >= hashTable.length * 0.75)
        {
            expandCapacity();
        }

        if (curr ==  null){
            hashTable[position] = createHashNode(key,value);
            size++;
            return null;
        }
        else {
            while (curr.next != null && !curr.key.equals(key)){
                curr = curr.next;
            }
            if (curr.key.equals(key)){
                curr.value = value;
            }
            else {
                curr.next = createHashNode(key, value);
                size++;
            }
        }
        return null;
    }

    public Node<K,V> createHashNode(K key, V value){
        Node<K,V> newNode = new Node(key,value);
        afterNodeInsert(newNode);
        return newNode;
    }

    private void afterNodeInsert(Node<K,V> curr){
        if (head == null){
            head = tail = curr;
        }
        else {
            tail.after = curr;
            curr.before = tail;
            tail = curr;
        }

    }

    /*
    * uses the parameter key to remove the key-value pair from the map
     * */
    @Override
    public V remove(Object key) {
        int position = hashCode(key);
        Node<K,V> targetNode = hashTable[position];
        Node<K,V> prev = null;
        Node<K,V> curr = targetNode;

        while (curr != null && !curr.key.equals(key)){
            prev = curr;
            curr = curr.next;
        }
        if (curr== null){
            return null;
        }
        if (prev == null){
            hashTable[position] = curr.next;
        }
        else {
            prev.next = curr.next;
        }
        curr.next = null;
        afterRemoveNode(curr);
        size--;
        return curr.value;

    }

    public void afterRemoveNode(Node<K,V> node){
        Node<K,V> curr = node;

        Node<K,V> prevNode = curr.before;
        Node<K,V> nextNode = curr.after;

        if (curr == head){
            head = head.after;
            head.before = null;
        }
        else if (curr == tail){
            prevNode.after = null;
            tail =prevNode;
        }
        else {
            prevNode.after = nextNode;
            nextNode.before = prevNode;
        }
        curr.after = curr.before = null;

    }

    /*
    * adds all key-values from parameter map to “this” map
     * */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    /*
    * clears the map removing all key-value pairs
     * */
    @Override
    public void clear() {
        size = 0;
        this.hashTable = new Node[capacity];
    }

    /*
    * used to get a set of all the keys used in map
     * */
    @Override
    public Set<K> keySet() {
        Node<K,V> node = head;
        Set<K> list = new LinkedHashSet<>();
        while(node != null){
            list.add(node.key);
            node = node.after;
        }

        return list;
    }

    /*
    * returns a collection of values held inside map
    * */
    @Override
    public Collection<V> values() {
        Node<K,V> node = head;
        Collection<V> list = new LinkedHashSet<V>();
        while(node !=null){
            list.add(node.value);
            node = node.after;
        }

        return list;
    }

    /*
    *  used to return a set all key-value pairs
     * */
    @Override
    public Set<Entry<K,V>> entrySet() {
        Set<K> set = keySet();

        Entry<K,V> node = (Entry<K, V>) head;

        while(node !=null){
           // set.add((Entry<K, V>) node.value);
            set.add((K) node);
        }

        return null;
    }


    private int hashCode(Object key){
        return Math.abs(key.hashCode()) % capacity;
    }

    /*
    * expands capacity of hashMap
    * */
    public void expandCapacity(){
        Node<K,V>[] oldTable = hashTable;

        hashTable = new Node[hashTable.length * 2];
        size = 0;
        for (Node<K,V> currentNode : oldTable)
        {
            while(currentNode != null)
            {
                put(currentNode.key, currentNode.value);
                currentNode = currentNode.after;
            }
        }
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        Node<K,V> curr = head;
        stringBuffer.append("{");

        while (curr != null){
            stringBuffer.append(curr.key).append("=").append(curr.value);
            stringBuffer.append(",");
            curr = curr.after;
        }

        if (stringBuffer.length()>1){
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
