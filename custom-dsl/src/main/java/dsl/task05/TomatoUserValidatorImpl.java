package dsl.task05;

import java.util.function.Consumer;

class TomatoUserValidatorImpl implements TomatoUserValidator {

    @Override
    public Consumer<Users> checkType() {
        return users -> {
            if (users.type() == Users.Type.ANONYMOUS) {
                throw new IllegalArgumentException("Invalid Authority!");
            }
        };
    }

    @Override
    public Consumer<Users> checkUsernameLength() {
        return users -> {
            if (users.username().length() > 5) {
                throw new IllegalArgumentException("Invalid username length!");
            }
        };
    }

    @Override
    public Consumer<Users> checkUsername() {
        return users -> {
            if (!users.username().equalsIgnoreCase("DANCER")) {
                throw new IllegalArgumentException("Invalid username! 'DANCER' is only for tomatoes!");
            }
        };
    }
}