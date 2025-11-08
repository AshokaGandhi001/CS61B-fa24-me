package ngrams;

import edu.princeton.cs.algs4.In;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    //NGramMap is gonna read the files i provide and perform many behavaious.
    Map<String, TimeSeries> wordHistory;
    TimeSeries totalRecord;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        wordHistory = new HashMap<>();
        //create a TS for any word and put that word-TS pair into a map.
        In inWord = new In(wordsFilename);

        while (inWord.hasNextLine()) {
            String nextLine = inWord.readLine();
            String[] splitLine = nextLine.split("\t");
            String word = splitLine[0];
            Integer year = Integer.parseInt(splitLine[1]);
            Double count = Double.parseDouble(splitLine[2]);

            wordHistory.putIfAbsent(word, new TimeSeries());
            wordHistory.get(word).put(year, count);
        }
        //put the total record into a TS
        totalRecord = new TimeSeries();
        In inCount = new In(countsFilename);

        while (inCount.hasNextLine()) {
            String nextLine = inCount.readLine();
            String[] splitLine = nextLine.split(",");
            Integer year = Integer.parseInt(splitLine[0]);
            Double totalCount = Double.parseDouble(splitLine[1]);

            totalRecord.put(year, totalCount);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordHistory.containsKey(word)) {return new TimeSeries();}
        return new TimeSeries(wordHistory.get(word), startYear, endYear);
    }
    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        if (!wordHistory.containsKey(word)) {return new TimeSeries();}
        return new TimeSeries(wordHistory.get(word));
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(totalRecord);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordHistory.containsKey(word)) {return new TimeSeries();}
        TimeSeries totalRecordPerYear = totalCountHistory();
        TimeSeries wordRecordPreYear = countHistory(word, startYear, endYear);

        return wordRecordPreYear.dividedBy(totalRecordPerYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!wordHistory.containsKey(word)) {return new TimeSeries();}
        TimeSeries totalRecordPerYear = totalCountHistory();
        TimeSeries wordRecordPreYear = countHistory(word);

        return wordRecordPreYear.dividedBy(totalRecordPerYear);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries summedWeightHistory = new TimeSeries();
        for (String word : words) {
            summedWeightHistory=summedWeightHistory.plus(weightHistory(word, startYear, endYear));
        }
        return summedWeightHistory;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries summedWeightHistory = new TimeSeries();
        for (String word : words) {
            summedWeightHistory = summedWeightHistory.plus(weightHistory(word));
        }
        return summedWeightHistory;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
