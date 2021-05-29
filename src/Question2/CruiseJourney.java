package Question2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* CruiseJourney  represents a journey
    comprised of one or more CruiseShip
    objects between multiple ports*/

public class CruiseJourney {
    private List<CruiseShip> shipList;
    private final SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");

   /**
    * default constructor creating a journey with no initial trips
    */
    public CruiseJourney(){
        shipList = new LinkedList<>();
    }

    /**
     * constructor that adds an existing list of CruiseShip
     * objects to the underlying data structure
     */
    public CruiseJourney(List<CruiseShip> list){
        this();
        for (Iterator<CruiseShip> iterator = list.iterator(); iterator.hasNext();){
            CruiseShip next = iterator.next();
            this.addCruise(next);
        }
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
        //empty
        if (getEndPort() == null){
            shipList.add(ship);
            return true;
        }
        else if((getEndPort().equalsIgnoreCase(ship.getDepartPort())) && // Check if the arrival port of last ship is the same as the passed-in ship's departure port.
                (getEndDate().after(ship.getDepartDate()) || getEndDate().equals(ship.getDepartDate())) && // Check if last ship arrival date is before/on same day as passed-in ship depart date.
                (!containsPort(ship.getArrivalPort()))) { // If the parameters arrival port is not within the journey (Prevents closed paths)...
            shipList.add(ship); // Adds the passed-in ship to shiplist.
            return true;
        } else {
            return false;
        }
    }


    /**
     * removeLastTrip method removes the lastly added CruiseShip
     * from the current journey (if any)
     */
    public boolean removeLastTrip(){
        if (!shipList.isEmpty()){
            int last = shipList.size()-1;
            shipList.remove(last);
            return true;
        }

        return false;
    }

    /**
     * containsPort method should return true
     * if the given port is in the journey.
     *
     */
    public boolean containsPort(String port){
        if (!shipList.isEmpty()){
            for (Iterator<CruiseShip> iterator = shipList.iterator(); iterator.hasNext();){
                CruiseShip next = iterator.next();

                if (port.equals(next.getDepartPort())){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * gets the start port
     * (start port and date are the first CruiseShip’s departure values)
     */
    public String getStartPort(){
        if (!shipList.isEmpty()){
            return shipList.get(0).getDepartPort();
        }

        return null;
    }

    /**
     * gets the end port
     * (end port and date are the last CruiseShip’s arrival values)
     */
    public String getEndPort(){
        if (!shipList.isEmpty()){
            int end = shipList.size()-1;
            return shipList.get(end).getArrivalPort();
        }

        return null;
    }

    /**
     * gets the start date
     * (start port and date are the first CruiseShip’s departure values)
     */
    public Calendar getStartDate(){
        if (!shipList.isEmpty()){
            return shipList.get(0).getDepartDate();
        }

        return null;
    }

    /**
     * gets the end port
     * (end port and date are the last CruiseShip’s arrival values)
     */
    public Calendar getEndDate(){
        if (!shipList.isEmpty()){
            int end = shipList.size()-1;
            return shipList.get(end).getArrivalDate();
        }

        return null;
    }

    /**
     * cloneJourney method returns a new CruiseJourney object, passing
     * its shipList to the new instance by calling the second constructor
     */
    public CruiseJourney cloneJourney(){

        return new CruiseJourney(shipList);
    }

    /**
     * getNumberOfTrips
     * returns the number of CruiseShips which comprise the journey
     */
    public int getNumberOfTrips(){
        int trips=0;

        if (!shipList.isEmpty()){

            for (Iterator<CruiseShip> iterator = shipList.iterator(); iterator.hasNext();){
                CruiseShip next = iterator.next();
                trips++;
            }
            return trips;
        }

        return trips;
    }

    /**
     * getTotalCost returns the total cost of all the CruiseShip trips
     * in this journey
     */
    public double getTotalCost(){
        double cost = 0.0;

        if (!shipList.isEmpty()){

            for (Iterator<CruiseShip> iterator = shipList.iterator(); iterator.hasNext();){
                CruiseShip next = iterator.next();
                cost =+ next.getCost();
            }
            return cost;
        }

        return cost;
    }


    /**
     * The toString method should print out
     * a string representation of all trips in this journey
     * and the total cost in a nicely formatted way
     * */
    @Override
    public String toString() {
        String s = "Total Cost: $" + getTotalCost() +"!!!";

        for (int i = 0; i < shipList.size(); i++) {
            fmt.setCalendar(shipList.get(i).getDepartDate());
            String departFormatted = fmt.format(shipList.get(i).getDepartDate().getTime());
            fmt.setCalendar(shipList.get(i).getArrivalDate() );
            String arrivalFormatted = fmt.format(shipList.get(i).getArrivalDate().getTime());
            s += "\n>>>>> BOAT :"+shipList.get(i).getBoatName()+" COST : $"+shipList.get(i).getCost()+" <<<<<< "+
                    "\nLEAVING "+shipList.get(i).getDepartPort()+" AT "+departFormatted+
                    "\nARRIVING :"+shipList.get(i).getArrivalPort() +" AT "+arrivalFormatted;
        }


//        for (Iterator<CruiseShip> iterator = shipList.iterator(); iterator.hasNext();){
//            CruiseShip next = iterator.next();
//            stringBuilder += "\n"+ next.getBoatName() + ": "+
//                    "LEAVING " + next.getDepartPort() + " " + next.getDepartDate() +
//                    " and ARRIVING " + next.getArrivalPort() + " " + next.getArrivalDate() +
//                    " $" + next.getCost();
//        }

        return s;
    }
}
