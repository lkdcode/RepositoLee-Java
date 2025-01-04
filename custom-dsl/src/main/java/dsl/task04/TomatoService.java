package dsl.task04;

import java.util.List;
import java.util.Map;

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
            .validTomato(tomatoValidator::checkTomatoUsage)
            .save(tomatoRepository::save);
    }

    public Map<Users, List<Tomato>> findAll() {
        return tomatoRepository.findAll();
    }
}