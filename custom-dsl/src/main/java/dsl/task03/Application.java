package dsl.task03;

public class Application {
    private static final TomatoRepository TOMATO_REPOSITORY = new TomatoRepository();
    private static final TomatoService TOMATO_SERVICE = new TomatoService(TOMATO_REPOSITORY);
    private static final TomatoController TOMATO_CONTROLLER = new TomatoController(TOMATO_SERVICE);

    public static void main(String[] args) {
        TOMATO_CONTROLLER.save();
    }
}