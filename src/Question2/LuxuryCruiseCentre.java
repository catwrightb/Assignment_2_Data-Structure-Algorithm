package Question2;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LuxuryCruiseCentre {

    private Map<String, Set<CruiseShip>> portMap;

    public LuxuryCruiseCentre(){

    }

    /**
     * add method is used to add a unique CruiseShip to this map
     * (care taken when the departure port already exists in the portMap)
     * */
    public boolean add(CruiseShip ship){

        return false;
    }

    /**
     *  getPossibleJourneys method is used to return list of all
     *  the uniquely possible routes from the start port and date
     *  to the end port. it does this by calling the findPaths method
     *
     * */
    public List<CruiseShipJourney> getPossibleJourney(String startPoint
    Calendar startDate, String endPoint){

        return null;
    }

    /**
     *  recursive method findPaths which uses a depth first search
     *  technique to build up all the possible journeys that can be
     *  undertaken between the start and end port (if any).
     *
     *  It does this by using the currentJourney parameter and when the
     *  target port is found it will “clone” the current journey and add
     *  it to the List of CruiseJourney values found.
     * */
    private void findPaths(String departPort, Calendar departDate,
                           String endPoint, CruiseJourney currentJourney
                           List<CruiseJourney>journeyList){

    }

}
