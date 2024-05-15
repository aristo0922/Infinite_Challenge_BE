package subway.controller;

import subway.config.handler.SubwayException;
import subway.service.InitManager;
import subway.service.SectionManager;
import subway.view.ResponseView;

public class SectionController extends ManageController {
    private static SectionManager sectionManager;
    private static ResponseView response = new ResponseView();
    static SubwayException subwayException;

    public SectionController(final InitManager manager){
        sectionManager = manager.getSectionManager();
        subwayException = manager.getSubwayException();
    }

    @Override
    public void sendRequest(final Controller controller, final String command){
        if (command.equals(REGISTER_COMMAND)) { // 등록
            controller.register();
            return;
        }
        if (command.equals(DELETE_COMMAND)) { // 삭제
            controller.delete();
            return;
        }
        if (command.equals(BACK_COMMAND)) { // 되돌아가기
            return;
        }
        subwayException.notValidCommand();
    }

    @Override
    public void register() { // 노선, 역 이름, 순서 입력 받고 등록
        String line = getLine();
        if(!sectionManager.isValid(line)){
            return;
        }

        String station = getStation();
        int index = getIndex();

        sectionManager.insertSection(station, index-1);
        response.printInfo("구간이 등록되었습니다.");
    }

    public String getLine() { // util 로 뺄까?
        ask.orderWhere(LINE);
        try {
            String line = br.readLine();
            return line;
        } catch (Exception e) {
            e.printStackTrace();
            subwayException.unexpected();
        }
        return null;
    }

    public String getStation() {
        ask.orderWhere("역 이름");
        try {
            return br.readLine();
        } catch (Exception e) {
            subwayException.unexpected();
        }
        return null;
    }

    public int getIndex() { // util?
        ask.orderWhere();
        try {
            String input = br.readLine();
            if(subwayException.isBack(input) == true) return -1;
            int index = Integer.parseInt(input);
            return index;
        } catch (Exception e) {
            subwayException.checkCommand();
        }
        return -1;
    }

    @Override
    public void delete() {
        try{
            ask.orderWhere(DELETE, "구간의 노선");
            String line = br.readLine();

            ask.orderWhere(DELETE, "구간의 역");
            String station = br.readLine();

            if(sectionManager.isValid(line)){
                sectionManager.delete(station);
                response.printInfo("구간이 삭제되었습니다.");
            }
        } catch (Exception e){
            subwayException.unexpected();
        }
    }

    @Override
    public void read() {}
}
