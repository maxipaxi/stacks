import java.util.EmptyStackException;

class UnsafeStack<T> {

    private Stack<T> stack;

    UnsafeStack() {
        stack = new EmptyStack<>();
    }

    void push(T elem) {
        stack = stack.push(elem);
    }

    void pop() {
        stack = stack.apply(new Visitor<>() {
            @Override
            public Stack<T> apply(EmptyStack<T> s) {
                throw new EmptyStackException();
            }

            @Override
            public <R extends Stack<T>> Stack<T> apply(NonEmptyStack<T, R> s) {
                return s.pop;
            }
        });
    }

    T peek() {
        return stack.apply(new Visitor<>() {
            @Override
            public T apply(EmptyStack<T> s) {
                throw new EmptyStackException();
            }

            @Override
            public <R extends Stack<T>> T apply(NonEmptyStack<T, R> s) {
                return s.peek;
            }
        });
    }

    int getDepth() {
        return stack.getDepth();
    }

}
