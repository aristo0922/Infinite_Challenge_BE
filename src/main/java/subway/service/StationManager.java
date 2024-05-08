package subway.service;

import subway.domain.Station;
import subway.domain.StationRepository;

import java.util.List;

// 역 관련 기능하는 서비스
public class StationManager extends Managerbale{
    private static StationRepository stations = new StationRepository();
    @Override // 삽입
    public boolean register(String name) {
//        해당 역이 존재하는 지 확인
//        삽입
        if(stations.getStationByName(name)!=null){
            // 이미 존재하는 역이라고 전달하기
            return false; // 문자열 반환해서 상태 메시지 출력에 활용
        }
        stations.addStation(new Station(name));
        return true;
    }

    @Override
    public boolean delete(String name) {
        return stationRepo.deleteStation(name);
    }

    @Override
    public StringBuilder read() {
//        역 조회
        StringBuilder sb = new StringBuilder();
        for(Station station : stations.stations()){
            sb.append("[INFO] " + station.getName()+"\n");
        }
        return sb;
    }

    public List<Station> readStation(){
        return stationRepo.stations();
    }
}
