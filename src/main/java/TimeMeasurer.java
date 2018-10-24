/**
 * <h1>Simple class for time measurements</h1>
 *
 * Uses {@link System#currentTimeMillis()} <p>
 * Does not have a <tt>stop</tt> method so <i>allows to get
 * multiple 'laps'</i>.
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public class TimeMeasurer {
    private long start;

    /**
     * Sets the start time to the current time
     */
    public void setStartNow(){
        start = System.currentTimeMillis();
    }

    /**
     * Returns the time difference between the current time and the start time
     *
     * @return elapsed time since start in milliseconds
     */
    public long getElapsedMS(){
        long now = System.currentTimeMillis();
        return now-start;
    }
}
