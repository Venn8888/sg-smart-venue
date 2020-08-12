package com.sg.sso.server.json;

public class JacksonMapper implements JsonMapper {
    private org.codehaus.jackson.map.ObjectMapper mapper;

    public JacksonMapper() {
        this.mapper = new org.codehaus.jackson.map.ObjectMapper();
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
