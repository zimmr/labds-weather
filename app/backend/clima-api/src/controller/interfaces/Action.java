package controller.interfaces;

@FunctionalInterface
public interface Action<TRequest, TResponse> {
    TResponse execute(TRequest request) throws Exception;
}