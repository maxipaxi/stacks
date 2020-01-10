interface Visitor<T, G> {
    G apply(EmptyStack<T> s);

    <R extends Stack<T>> G apply(NonEmptyStack<T, R> s);
}

