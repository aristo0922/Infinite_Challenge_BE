package subway.domain;

import java.util.LinkedList;

public class Line {
    private String name;
    private LinkedList<Station> stations = new LinkedList<>();
    private Station upperStation;
    private Station bottomStation;

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public StringBuilder stationList(){
        StringBuilder sb = new StringBuilder();
        for(Station station:stations){
            sb.append("[INFO] "+station.getName()).append("\n");
        }
        return sb;
    }

    public void setStations(Station upper, Station bottom) {
        this.upperStation = upper;
        this.bottomStation = bottom;
        stations.addFirst(upper);
        stations.addLast(bottom);
    }

    public void addStation(Station station, int index) {
        stations.add(index, station);
    }

    public boolean deleteStation(String station) {
        int index = 0;
        for (index = 0; index < stations.size(); index++) {
            Station s = stations.get(index);
            if (s.getName().equals(station)) {
                stations.remove(index);
                return true;
            }
        }
        return false;
    }

    // 추가 기능 구현
    public Station getStationByName(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }
}
