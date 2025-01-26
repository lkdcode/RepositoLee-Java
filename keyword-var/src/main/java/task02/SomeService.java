package task02;

class SomeService {
    private final QueryService queryService = new QueryService();
    private final CommandService commandService = new CommandService();

    public Boolean someMethod() {
        final var values = queryService.getValues();
        final var result = commandService.totalSum(values);
        return result % 2 == 0;
    }
}