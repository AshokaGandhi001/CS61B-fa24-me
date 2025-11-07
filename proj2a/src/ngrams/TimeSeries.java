package ngrams;

import edu.princeton.cs.algs4.In;
import org.eclipse.jetty.websocket.client.masks.ZeroMasker;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        this.putAll(subMap(startYear, true, endYear, true));
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        return new ArrayList<>(keySet());
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        return new ArrayList<>(values());
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        List<Integer> year1 = years();
        List<Integer> year2 = ts.years();
        List<Double> value1 = data();
        List<Double> value2 = ts.data();

        int i = 0;
        int j = 0;

        TimeSeries result = new TimeSeries();
        if (year1.isEmpty() && year2.isEmpty()) {
            return result;
        }
        int limit = Math.max(year1.size(), year2.size());
        while (i < year1.size() && j < year2.size()) {
            if (year1.get(i) < year2.get(j)) {
                result.put(year1.get(i), value1.get(i));
                i++;
            } else if (Objects.equals(year1.get(i), year2.get(j))) {
                result.put(year1.get(i), value1.get(i) + value2.get(j));
                i++;
                j++;
            } else {
                result.put(year2.get(j), value2.get(j));
                j++;
            }
        }

        while (i < year1.size()) {
            result.put(year1.get(i), value1.get(i));
            i++;
        }

        while ((j < year2.size())) {
            result.put(year2.get(j), value2.get(j));
            j++;
        }

        return result;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();

        for (Integer year : this.years()) {
            if (!ts.years().contains(year)) {
                throw new IllegalArgumentException();
            }

            Double numerator  = this.get(year);
            Double denumerator = ts.get(year);

//            if (denumerator == 0.0) {
//                throw new IllegalArgumentException("Division by zero.");
//            }
            result.put(year, numerator/denumerator);
        }
        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
