package funtional2.application.model;

import java.util.Map;

@FunctionalInterface
public interface QueryPort {
    Map<String, Object> read();
}
