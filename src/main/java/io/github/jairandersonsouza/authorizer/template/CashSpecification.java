package io.github.jairandersonsouza.authorizer.template;

import org.springframework.stereotype.Service;

@Service
public class CashSpecification<T> implements Specification<T> {

    private Specification<T> left;
    private Specification<T> right;


//    public CashSpecification(final Specification<T> left, final Specification<T> right) {
//        this.left = left;
//        this.right = right;
//    }

    @Override
    public boolean isValidBy(final T t) {
        return left.isValidBy(t) || right.isValidBy(t);
    }

    public Specification<T> or(final Specification<T> other) {
        return null;
//        return new CashSpecification<>(this, other);
    }
}
