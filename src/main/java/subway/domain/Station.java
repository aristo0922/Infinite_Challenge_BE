package subway.domain;

import java.util.List;

public class Station {
    private String name;
    private List<Line>[] line;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
