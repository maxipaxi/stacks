class NonEmptyStack<T, R extends Stack<T, R>> implements Stack<T, NonEmptyStack<T, R>> {
    final int depth;
    final T peek;
    final R pop;

    NonEmptyStack(T elem, R rest) {
        depth = rest.getDepth() + 1;
        peek = elem;
        pop = rest;
    }

    @Override
    public NonEmptyStack<T, NonEmptyStack<T, R>> push(T elem) {
        return new NonEmptyStack<>(elem, this);
    }

    @Override
    public <G> G apply(Visitor<T, G> v) {
        return v.apply(this);
    }

    @Override
    public int getDepth() {
        return depth;
    }

}
