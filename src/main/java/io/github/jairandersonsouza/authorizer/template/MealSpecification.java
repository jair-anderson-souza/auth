package io.github.jairandersonsouza.authorizer.template;

import org.springframework.stereotype.Service;

@Service
public class MealSpecification<T> implements Specification<T> {

    private Specification<T> left;
    private Specification<T> right;

//    public MealSpecification(final Specification<T> left, final Specification<T> right) {
//        this.left = left;
//        this.right = right;
//    }

    @Override
    public boolean isValidBy(final T t) {
        return left.isValidBy(t) && right.isValidBy(t);
    }

    public Specification<T> and(final Specification<T> other) {
        return null;
//        return new MealSpecification<>(this, other);
    }
}
