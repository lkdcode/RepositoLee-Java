package dsl.task05;

class TomatoService {
    private final TomatoUserValidator tomatoUserValidator;
    private final TomatoValidator tomatoValidator;
    private final TomatoRepository tomatoRepository;

    public TomatoService(TomatoUserValidator tomatoUserValidator, TomatoValidator tomatoValidator, TomatoRepository tomatoRepository) {
        this.tomatoUserValidator = tomatoUserValidator;
        this.tomatoValidator = tomatoValidator;
        this.tomatoRepository = tomatoRepository;
    }

    public void persistDsl(Tomato tomato, Users users) {
        TomatoCustomDsl.action(tomato, users)
            .validUsers(tomatoUserValidator.checkType())
            .validUsers(tomatoUserValidator.checkUsername())
            .validUsers(tomatoUserValidator.checkUsernameLength())
            .validUsers(target -> {
                System.out.println("Hello, DSL!");
            })
            .validTomato(tomatoValidator::checkTomatoUsage)
            .save(tomatoRepository::save);
    }
}