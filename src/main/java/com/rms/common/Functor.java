package com.rms.common;

@FunctionalInterface
public interface Functor<A, R> {
    public R apply(A a);
}
