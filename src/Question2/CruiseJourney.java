package Question2;

import java.util.Calendar;
import java.util.List;

/* CruiseJourney  represents a journey
    comprised of one or more CruiseShip
    objects between multiple ports*/

public class CruiseJourney {
    private List<CruiseShip> shipList;

   /**
    * default constructor creating a journey with no initial trips
    */
    public CruiseJourney(){

    }

    /**
     * constructor that adds an existing list of CruiseShip
     * objects to the underlying data structure
     */
    public CruiseJourney(List<CruiseShip> list){

    }

    /**
     * addCruise method should add a CruiseShip to the journey,
     * returning true and only adding the trip
     * to the current journey if it meets the following criteria:
     *
     * - The journey’s end port is equal to the newly added CruiseShip
     * parameter departure port (so the new cruise departs from the same
     * port as journey’s current end port).
     *
     * - The journey’s end date is earlier in time to the newly added
     * CruiseShip parameter’s departure date (so the new cruise cannot
     * be added if its departure date is earlier than the journey’s
     * current end date) – You may assume that all departures are in the
     * afternoon, and all arrivals in the morning (so dates that are the
     * same are valid).
     *
     * - The journey so far does not already contain the parameters
     * arrival port somewhere in its shipList (meaning the same port
     * is never visited twice – no closed paths).
     *
     */
    public boolean addCruise(CruiseShip ship){

        return false;
    }


    /**
     * removeLastTrip method removes the lastly added CruiseShip
     * from the current journey (if any)
     */
    public boolean removeLastTrip(){

        return false;
    }

    /**
     * containsPort method should return true
     * if the given port is in the journey.
     *
     */
    public boolean containsPort(String port){

        return false;
    }

    /**
     * gets the start port
     * (start port and date are the first CruiseShip’s departure values)
     */
    public String getStartPort(){

        return null;
    }

    /**
     * gets the end port
     * (end port and date are the last CruiseShip’s arrival values)
     */
    public String getEndPort(){

        return null;
    }

    /**
     * gets the start date
     * (start port and date are the first CruiseShip’s departure values)
     */
    public Calendar getStartDate(){

        return null;
    }

    /**
     * gets the end port
     * (end port and date are the last CruiseShip’s arrival values)
     */
    public Calendar getEndDate(){

        return null;
    }

    /**
     * cloneJourney method returns a new CruiseJourney object, passing
     * its shipList to the new instance by calling the second constructor
     */
    public CruiseJourney cloneJourney(){

        return null;
    }

    /**
     * getNumberOfTrips
     * returns the number of CruiseShips which comprise the journey
     */
    public int getNumberOfTrips(){

        return 0;
    }

    /**
     * getTotalCost returns the total cost of all the CruiseShip trips
     * in this journey
     */
    public double getTotalCost(){

        return 0;
    }


    /**
     * The toString method should print out
     * a string representation of all trips in this journey
     * and the total cost in a nicely formatted way
     * */
    @Override
    public String toString() {
        return "CruiseJourney{" +
                "shipList=" + shipList +
                '}';
    }
}
