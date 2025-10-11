import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int total_sum = 0;
        for (int number : L){
            total_sum += number;
        }
        return total_sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> even_numbers = new ArrayList<>();
        for (int number : L){
            if (number % 2 == 0){
                even_numbers.add(number);
            }
        }
        return even_numbers;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> common_item = new ArrayList<>();
        for (int number : L1){
            if (L2.contains(number)){
                common_item.add(number);
            }
        }
        return common_item;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        for (String current_word : words){
            for (int i = 0; i < current_word.length(); i ++){
                if(c == current_word.charAt(i)){
                    count += 1;
                }
            }
        }
        return count;
    }
}
