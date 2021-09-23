package com.cuidar.app_cer.model.history;

import com.cuidar.app_cer.model.activity.Activity;

import java.util.HashMap;

public class EntryResponse {

    private HashMap<String, HashMap<String, Integer>> result;

    public EntryResponse(HashMap<String, HashMap<String, Integer>> result) {
        this.result = result;
    }

    public HashMap<String, HashMap<String, Integer>> getResult() {
        return result;
    }
}
