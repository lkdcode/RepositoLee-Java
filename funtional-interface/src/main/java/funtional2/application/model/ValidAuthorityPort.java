package funtional2.application.model;

@FunctionalInterface
public interface ValidAuthorityPort {
    void valid() throws IllegalArgumentException;
}