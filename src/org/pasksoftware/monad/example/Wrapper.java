package org.pasksoftware.monad.example;

public class Wrapper<A> {

    final A value;

    Wrapper(A value) {
        this.value = value;
    }
}
