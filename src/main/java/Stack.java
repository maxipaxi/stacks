interface Stack<T> {
    <G> G apply(Visitor<T, G> v);

    int getDepth();

    Stack<T> push(T elem);
}
