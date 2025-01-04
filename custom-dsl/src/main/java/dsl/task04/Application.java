package dsl.task04;

public class Application {
    private static final TomatoRepository TOMATO_REPOSITORY = new TomatoRepository();
    private static final TomatoValidator TOMATO_VALIDATOR = new TomatoValidatorImpl();
    private static final TomatoUserValidator TOMATO_USER_VALIDATOR = new TomatoUserValidatorImpl();
    private static final TomatoService TOMATO_SERVICE = new TomatoService(TOMATO_USER_VALIDATOR, TOMATO_VALIDATOR, TOMATO_REPOSITORY);
    private static final TomatoController TOMATO_CONTROLLER = new TomatoController(TOMATO_SERVICE);

    public static void main(String[] args) {
        TOMATO_CONTROLLER.save();
    }
}