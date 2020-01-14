interface Stack<T, THIS extends Stack<T, THIS>> {
    <G> G apply(Visitor<T, G> v);

    int getDepth();

    NonEmptyStack<T, THIS> push(T elem);
}
