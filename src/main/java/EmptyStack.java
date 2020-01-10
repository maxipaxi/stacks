class EmptyStack<T> implements Stack<T> {
    static final int depth = 0;

    @Override
    public NonEmptyStack<T, EmptyStack<T>> push(T elem) {
        return new NonEmptyStack<>(elem, this);
    }

    @Override
    public <G> G apply(Visitor<T, G> v) {
        return v.apply(this);
    }

    @Override
    public int getDepth() {
        return EmptyStack.depth;
    }
}
