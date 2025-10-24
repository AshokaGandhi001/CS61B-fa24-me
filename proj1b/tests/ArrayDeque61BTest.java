import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
    void testGet() {
         Deque61B<Integer> test1= new ArrayDeque61B<>();
         assertThat(test1.get(-1)).isEqualTo(null);
         assertThat(test1.get(9)).isEqualTo(null);

         Deque61B<Integer> test2= new ArrayDeque61B<>();
         test2.addFirst(1);
         test2.addFirst(2);
         test2.addLast(13);
         test2.addFirst(4);
         test2.addFirst(5);
         test2.addFirst(1);
         assertThat(test2.get(2)).isEqualTo(4);
         assertThat(test2.get(5)).isEqualTo(13);
     }

     @Test
    void testSize() {
         Deque61B<Integer> test1= new ArrayDeque61B<>();
         assertThat(test1.size()).isEqualTo(0);

         Deque61B<Integer> test2= new ArrayDeque61B<>();
         test2.addFirst(1);
         test2.addFirst(2);
         test2.addLast(13);
         test2.addFirst(4);
         test2.addFirst(5);
         test2.addFirst(1);
         assertThat(test2.size()).isEqualTo(6);
     }
     @Test
    void testIsEmpty() {
         Deque61B<Integer> test1= new ArrayDeque61B<>();
         assertThat(test1.isEmpty()).isTrue();

         Deque61B<Integer> test2= new ArrayDeque61B<>();
         test2.addFirst(1);
         test2.addFirst(2);
         test2.addLast(13);
         test2.addFirst(4);
         test2.addFirst(5);
         test2.addFirst(1);
         assertThat(test2.isEmpty()).isFalse();
     }

     @Test
    void testToList() {
         Deque61B<Integer> test1= new ArrayDeque61B<>();
         assertThat(test1.toList()).containsExactly();

         Deque61B<Integer> test2= new ArrayDeque61B<>();
         test2.addFirst(1);
         test2.addFirst(2);
         test2.addLast(3);
         test2.addFirst(4);
         test2.addFirst(5);
         test2.addFirst(6);
         test2.addFirst(7);
         test2.addFirst(8);
         test2.addFirst(9);
         test2.addFirst(10);
         assertThat(test2.toList()).containsExactly(10, 9, 8, 7, 6, 5, 4, 2, 1, 3);

         Deque61B<Integer> test3= new ArrayDeque61B<>();
         test3.addLast(1);
         test3.addLast(2);
         test3.addLast(3);
         test3.addLast(4);
         test3.addLast(5);
         test3.addLast(6);
         test3.addLast(7);
         test3.addLast(8);
         test3.addLast(9);
         test3.addLast(10);
         assertThat(test3.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


     }

    @Test
    void testRemoveFirstAndRemoveLast() {
         Deque61B<Integer> test1 = new ArrayDeque61B<>();
         test1.addFirst(1);
         test1.addFirst(2);
         test1.addFirst(3);
         test1.addFirst(4);
         test1.addFirst(5);
         test1.addFirst(6);
         test1.addFirst(7);
         assertThat(test1.removeFirst()).isEqualTo(7);
         assertThat(test1.toList()).containsExactly(6, 5, 4, 3, 2, 1);

        Deque61B<Integer> test2 = new ArrayDeque61B<>();
        test2.addFirst(1);
        test2.addFirst(2);
        test2.addFirst(3);
        test2.addFirst(4);
        test2.addFirst(5);
        test2.addFirst(6);
        test2.addFirst(7);
        assertThat(test2.removeLast()).isEqualTo(1);
        assertThat(test2.toList()).containsExactly(7, 6, 5, 4, 3, 2);

        Deque61B<Integer> test3 = new ArrayDeque61B<>();
        for (int i = 1; i <= 33; i++){
            test3.addLast(i);
        }

        for (int i = 0; i < 19; i++) {
            test3.removeFirst();
        }
        assertThat(test3.get(0)).isEqualTo(20);
    }
    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1).containsExactly("front", "middle", "back");
    }

}
