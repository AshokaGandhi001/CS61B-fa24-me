import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B {
    T[] item;
    int nextFirst;
    int nextLast;
    int size;

    public ArrayDeque61B() {
        item = (T[]) new Object[8];
        size = 0;
        nextFirst = 2;
        nextLast = nextFirst + 1;
    }
    public T[] resizingup(T[] item) {
        T[] newItem = (T[]) new Object[2*item.length];
        for (int i = 0; i < item.length; i++) {
            newItem[i] = (T) get(i);
        }
        nextFirst = newItem.length -1;
        nextLast = size();
        return newItem;
    }
    @Override
    public void addFirst(Object x) {
        if (size() == item.length) {
            item = resizingup(item);
        }
        item[nextFirst] = (T) x;
        nextFirst = (getNextleft(nextFirst));
        if (x != null) {
            size++;
        }
    }
    public int getNextleft(int index){
        int nextFirstIndex = index;
        if (index - 1 < 0) {
            nextFirstIndex = item.length - ((index +1)% item.length);
        } else {
            nextFirstIndex--;
        }
        return nextFirstIndex;
    }

    @Override
    public void addLast(Object x) {
        if (size() == item.length) {
            item = resizingup(item);
        }
        item[nextLast] = (T) x;
        nextLast = getNextRight(nextLast);
        if (x != null) {
            size++;
        }
    }
    public int getNextRight(int index) {
        int nextLastIndex = index;
        if (index + 1 == item.length) {
            nextLastIndex = (nextLastIndex+1)% item.length;
        } else {
            nextLastIndex++;
        }
        return nextLastIndex;
    }

    @Override
    public List toList() {
        List<T> returnlist = new ArrayList<>();
        for (int index = 0; index < size(); index ++) {
//            if (get(index) == null){
//                break;
//            }
            returnlist.add((T) get(index));
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object removeFirst() {
        T item = (T) get(0);
        size--;
        nextFirst = getNextRight(nextFirst);//nextFirst now points to the romoved space.
        addFirst(null);//set the current space to null, this will cause nextFirst move left.
        nextFirst = getNextRight(nextFirst);// make it move left.
        return item;
    }

    @Override
    public Object removeLast() {
        T item = (T) get(size() - 1);
        size--;
        nextLast = getNextleft(nextLast);
        addLast(null);
        nextLast = getNextleft(nextLast);
        return item;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= item.length){
            return null;
        }
        return item[(nextFirst + index + 1) % item.length];
    }

    @Override
    public Object getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
