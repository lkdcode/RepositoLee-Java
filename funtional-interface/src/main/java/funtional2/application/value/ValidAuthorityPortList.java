package funtional2.application.value;


import funtional2.application.model.ValidAuthorityPort;

import java.util.ArrayList;
import java.util.List;

public final class ValidAuthorityPortList {
    private final List<ValidAuthorityPort> list;

    private ValidAuthorityPortList() {
        this.list = new ArrayList<>();
    }

    public static ValidAuthorityPortList init() {
        return new ValidAuthorityPortList();
    }

    public void add(final ValidAuthorityPort port) {
        this.list.add(port);
    }

    public void valid() throws IllegalArgumentException {
        this.list.forEach(ValidAuthorityPort::valid);
    }
}