import java.io.PrintWriter;

/**
 * Class that calculated cache hits & misses,
 * disk reads & writes, and time.
 * 
 * @author William Lummus (williamnl)
 * @version 2022.19.2022
 */

// !Question! how do i save the file?
public class StatsOutput {

    private String name;
    private int hits;
    private int misses;
    private int reads;
    private int writes;
    private long time;

    /**
     * Constructor
     */
    public StatsOutput() {
        name = "";
        hits = 0;
        misses = 0;
        reads = 0;
        writes = 0;
        time = 0;
    }


    /**
     * Sets the name of the file
     * 
     * @param fileName
     *            name of file
     */
    public void setFileName(String fileName) {
        name = fileName;
    }


    /**
     * Increment hits
     */
    public void incHits() {
        hits++;
    }


    /**
     * Increment misses
     */
    public void incMisses() {
        misses++;
    }


    /**
     * Increment reads
     */
    public void incReads() {
        reads++;
    }


    /**
     * Increment writes
     */
    public void incWrites() {
        writes++;
    }


    /**
     * Sets the time taken
     * 
     * @param timeTaken
     *            time program runs
     */
    public void setTime(long timeTaken) {
        time = timeTaken;
    }


    /**
     * Gets file name
     * 
     * @return name
     */
    public String name() {
        return name;
    }


    /**
     * Gets hits
     * 
     * @return hits
     */
    public int hits() {
        return hits;
    }


    /**
     * Gets misses
     * 
     * @return misses
     */
    public int misses() {
        return misses;
    }


    /**
     * Gets reads
     * 
     * @return reads
     */
    public int reads() {
        return reads;
    }


    /**
     * Gets writes
     * 
     * @return writes
     */
    public int writes() {
        return writes;
    }


    /**
     * Gets time
     * 
     * @return time
     */
    public long time() {
        return time;
    }


    /**
     * Forms the OutputStats file.
     * 
     * @return String of output stats.
     */
    public String output() {
        String output = "";
        output += "------  STATS ------\n";
        output += "File name: " + name + "\n";
        output += "Cache Hits: " + hits + "\n";
        output += "Cache Misses: " + misses + "\n";
        output += "Disk Reads: " + reads + "\n";
        output += "Disk Writes: " + writes + "\n";
        output += "Time to Sort: " + time;
        

        return output;
    }

}