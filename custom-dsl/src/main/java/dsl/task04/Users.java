package dsl.task04;

import static dsl.task04.Users.Type.*;

record Users(
    String username,
    Type type
) {

    public static Users asAdmin(String username) {
        return new Users(username, ADMIN);
    }

    public static Users asMember(String username) {
        return new Users(username, MEMBER);
    }

    public static Users asAnonymous(String username) {
        return new Users(username, ANONYMOUS);
    }

    public enum Type {
        ADMIN,
        MEMBER,
        ANONYMOUS,
        ;
    }
}