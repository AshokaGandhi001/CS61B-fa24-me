package deque;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{

    private class Node {
        public T item;
        public Node next;
        public  Node prev;

        public Node(T item, Node n, Node p){
            this.item = item;
            this.next = n;
            this.prev = p;
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque61B(){
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    @Override
    public void addFirst(T x) {
        Node oldFirst = sentinel.next;
        Node newFirst = new Node(x, oldFirst, sentinel);
        sentinel.next = newFirst;
        oldFirst.prev = newFirst;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node oldPrev = sentinel.prev;
        Node newPrev = new Node(x, sentinel, oldPrev);
        sentinel.prev = newPrev;
        oldPrev.next = newPrev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node slideNode = new Node(null, sentinel.next, sentinel);
        while (!slideNode.next.equals(sentinel)) {
            returnList.add(slideNode.next.item);
            slideNode.next = slideNode.next.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next.equals(sentinel)) return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node slideNode = new Node(null, sentinel.next, sentinel);
        while (index >= 0) {
            slideNode.item = slideNode.next.item;
            slideNode.next = slideNode.next.next;
            index--;
        }
        return slideNode.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >=size){
            return null;
        }
        return getRecursivehelper(sentinel.next, index);
    }

 private T getRecursivehelper  (Node node, int index){
        if (index == 0) {
            return node.item;
        } else {
            return getRecursivehelper(node.next, index -1);
        }
 }
}
