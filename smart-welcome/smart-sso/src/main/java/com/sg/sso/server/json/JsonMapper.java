package com.sg.sso.server.json;

public interface JsonMapper {

    String write(Object var1) throws Exception;

    <T> T read(String var1, Class<T> var2) throws Exception;

}
