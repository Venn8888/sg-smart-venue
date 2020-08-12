package com.sg.sso.server.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2Mapper implements JsonMapper {
    private ObjectMapper mapper;

    public Jackson2Mapper() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public String write(Object input) throws Exception {
        return this.mapper.writeValueAsString(input);
    }

    @Override
    public <T> T read(String input, Class<T> type) throws Exception {
        return this.mapper.readValue(input, type);
    }
}
