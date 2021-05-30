package Question2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LuxuryCruiseCentre {

    private Map<String,Set<CruiseShip>> portMap;

    public LuxuryCruiseCentre(){
        portMap = new ConcurrentHashMap<>();

    }

    /**
     * add method is used to add a unique CruiseShip to this map
     * (care taken when the departure port already exists in the portMap)
     * */
    public boolean add(CruiseShip ship){
        if (portMap.containsKey(ship.getDepartPort())){
            return portMap.get(ship.getDepartPort()).add(ship);
        }
        else {
            Set set = new HashSet();
            set.add(ship);
            portMap.put(ship.getDepartPort(),set);
            return true;
        }

    }

    //"Auckland", new GregorianCalendar(2020, Calendar.JUNE,1), "Vanuatu"
    /**
     *  getPossibleJourneys method is used to return list of all
     *  the uniquely possible routes from the start port and date
     *  to the end port. it does this by calling the findPaths method
     *
     * */
    public List<CruiseJourney> getPossibleJourneys(String startPoint, GregorianCalendar startDate, String endPoint){

        //list of cruises
        List<CruiseJourney> cruiseJourneyList = new ArrayList<>();
        //cruise object to pass to findPaths
        CruiseJourney cruiseJourney = new CruiseJourney();

        if (!portMap.containsKey(startPoint) ){
            System.out.println("Starting Port does not exist");
            return cruiseJourneyList;
        }
        else if (!portMap.containsKey(endPoint)){
            System.out.println("Ending Port does not exist");
            return cruiseJourneyList;
        }

        //recursive call to find paths
        findPaths(startPoint, startDate, endPoint, cruiseJourney, cruiseJourneyList);

        return cruiseJourneyList;
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
    private void findPaths(String departPort, Calendar departDate, String endPoint, CruiseJourney currentJourney,
                           List<CruiseJourney>journeyList){
        try {
            if (departPort.equals(endPoint)){
                CruiseJourney clone = currentJourney.cloneJourney();
                journeyList.add(clone);

            }
            else {
                Set<CruiseShip> set = portMap.get(departPort);
                CruiseShip next;

                for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
                    next = (CruiseShip) iterator.next();

                    if (!endPoint.equalsIgnoreCase("Antarctica")) {
                        if (!next.getArrivalPort().equalsIgnoreCase("Antarctica")) {
                            if (next.getDepartDate().after(departDate) || next.getDepartDate().equals(departDate)) {
                                if (currentJourney.addCruise(next))
                                {
                                    findPaths(next.getArrivalPort(), next.getArrivalDate(), endPoint, currentJourney, journeyList);
                                    currentJourney.removeLastTrip();

                                }
                            }
                        }
                    }
                    else { //if endport is Antarctica
                        if (next.getDepartDate().after(departDate) || next.getDepartDate().equals(departDate)) {
                            if (currentJourney.addCruise(next))
                            {
                                findPaths(next.getArrivalPort(), next.getArrivalDate(), endPoint, currentJourney, journeyList);
                                currentJourney.removeLastTrip();

                            }

                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("No return cruises from your destination");
        }



        }

}
