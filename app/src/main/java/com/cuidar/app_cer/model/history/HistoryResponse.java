package com.cuidar.app_cer.model.history;

import java.util.HashMap;

public class HistoryResponse {

    private HashMap<String, EntryResponse> result;

    public HistoryResponse(HashMap<String, EntryResponse> result) {
        this.result = result;
    }

    public HashMap<String, EntryResponse> getResult() {
        return result;
    }
}
