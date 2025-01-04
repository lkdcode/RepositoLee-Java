package dsl.task04;

class TomatoValidatorImpl implements TomatoValidator {

    @Override
    public void checkTomatoUsage(Tomato tomato) {
        if (tomato.usage() != Tomato.Usage.DANCER) {
            throw new IllegalArgumentException("Invalid tomato usage");
        }
    }
}