package controller.interfaces;

@FunctionalInterface
public interface VoidAction<TRequest> {
    void execute(TRequest request) throws Exception;
}
