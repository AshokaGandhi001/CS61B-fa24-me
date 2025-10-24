import java.util.Comparator;
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comp;

    public MaxArrayDeque61B(Comparator<T> comp) {
        super();
        this.comp = comp;
    }
    public T max() {
        return max(comp);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) return null;
        T maxItem = this.get(0);
        for (T item : this) {
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

}
