import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {

    // Helper class to store a passenger's active check-in details
    private static class CheckInInfo {
        String stationName;
        int time;

        CheckInInfo(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    // Helper class to store total duration and trip count for a specific route
    private static class RouteInfo {
        double totalTime;
        int count;

        RouteInfo(double totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }

        void addTrip(int travelTime) {
            this.totalTime += travelTime;
            this.count++;
        }

        double getAverage() {
            return totalTime / count;
        }
    }

    // id -> CheckInInfo
    private Map<Integer, CheckInInfo> checkInMap;
    // "startStation->endStation" -> RouteInfo
    private Map<String, RouteInfo> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        // Retrieve and remove check-in record for this passenger
        CheckInInfo checkIn = checkInMap.remove(id);

        String routeKey = checkIn.stationName + "->" + stationName;
        int travelTime = t - checkIn.time;

        // Update the route aggregate stats
        routeMap.computeIfAbsent(routeKey, k -> new RouteInfo(0, 0))
                .addTrip(travelTime);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        return routeMap.get(routeKey).getAverage();
    }
}