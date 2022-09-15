package com.miniproject.usermanagement.service.Utility.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miniproject.usermanagement.service.Utility.JsonExporterService;

import java.util.List;

public class JsonExporterServiceImpl<T> implements JsonExporterService<T> {
    @Override
    public String export(List<T> ts) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy").create();

        String listInJson = gson.toJson(ts);
        return listInJson;
    }
}
