package com.transfer.account.demo;

import java.util.Objects;

/**
 * @author LiuQi
 * @date 2021/12/13 14:24
 */
@FunctionalInterface
public interface Consumer<T> {


    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
