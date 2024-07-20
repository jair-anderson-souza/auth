package io.github.jairandersonsouza.authorizer.template;

public interface Specification<T> {

    boolean isValidBy(T entity);

}
