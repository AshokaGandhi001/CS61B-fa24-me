import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> List = new HashMap<>();
        for (char letter = 'a';letter <= 'z'; letter ++  ){
            List.put(letter, letter - 'a' + 1);
        }
        return List;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> result = new HashMap<>();
        for (int current_number : nums){
            result.put(current_number, current_number*current_number);
        }
        return result;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> word_occurance = new HashMap<>();
        for (String current_word : words){
            if (word_occurance.keySet().contains(current_word)){
                continue;
            }else {
                int occurance = 0;
                for (String word : words){
                    if(word.equals(current_word)){
                        occurance += 1;
                    }
                }
                word_occurance.put(current_word, occurance);
            }
        }
        return word_occurance;
    }
}
