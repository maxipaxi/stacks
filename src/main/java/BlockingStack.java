import java.util.concurrent.Semaphore;

class BlockingStack<T> {
    private static class Impossible extends RuntimeException {
    }

    private Stack<T, ?> stack;
    private Semaphore available;

    BlockingStack() {
        stack = new EmptyStack<>();
        available = new Semaphore(0);
    }

    void push(T elem) {
        stack = stack.push(elem);
        available.release();
    }

    void pop() {
        available.acquireUninterruptibly();
        stack = stack.apply(new Visitor<>() {
            @Override
            public Stack<T, ?> apply(EmptyStack<T> s) {
                throw new Impossible();
            }

            @Override
            public <R extends Stack<T, R>> Stack<T, R> apply(NonEmptyStack<T, R> s) {
                return s.pop;
            }
        });
    }

    T peek() {
        available.acquireUninterruptibly();
        available.release();
        return stack.apply(new Visitor<>() {
            @Override
            public T apply(EmptyStack<T> s) {
                throw new Impossible();
            }

            @Override
            public <R extends Stack<T, R>> T apply(NonEmptyStack<T, R> s) {
                return s.peek;
            }
        });
    }

    int getDepth() {
        return stack.getDepth();
    }

}
