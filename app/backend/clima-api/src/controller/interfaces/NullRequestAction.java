package controller.interfaces;

@FunctionalInterface
public interface NullRequestAction<TResponse> {
    TResponse execute() throws Exception;
}