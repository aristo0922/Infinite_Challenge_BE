package subway.controller;

import subway.view.AskView;
import subway.view.ResponseView;
import subway.view.util.MakeString;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static subway.controller.StationController.subwayException;

public abstract class ManageController implements Controller, Constants {
    static final AskView ask = new AskView();
    static final ResponseView response = new ResponseView();
    static final MakeString makeString = new MakeString();

    protected static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void work(final Controller controller, final String target) {
        ask.WhatToManage(target);
        ask.Function();
        try {
            String command = br.readLine();
            sendRequest(controller, command);
        } catch (Exception e) {
            subwayException.unexpected();
        }
    }

    // 등록하기
    public void sendRequest(final Controller controller, final String command) {
        if (command.equals(REGISTER_COMMAND)) {
            controller.register();
            return;
        }
        if (command.equals(DELETE_COMMAND)) {
            controller.delete();
            return;
        }
        if (command.equals(READ_COMMAND)) {
            controller.read();
            return;
        }
        if (command.equals(BACK_COMMAND)) {
            return;
        }
        subwayException.notValidCommand();
    }
}
