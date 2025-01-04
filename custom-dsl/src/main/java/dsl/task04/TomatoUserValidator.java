package dsl.task04;

import java.util.function.Consumer;

interface TomatoUserValidator {
    Consumer<Users> checkType();

    Consumer<Users> checkUsernameLength();

    Consumer<Users> checkUsername();
}