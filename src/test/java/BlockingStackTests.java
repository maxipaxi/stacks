import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlockingStackTests {

    static class a_new_stack {
        @Test
        public void is_empty() {
            var s = new BlockingStack<Integer>();
            Assertions.assertEquals(0, s.getDepth());
        }
    }

    static class an_empty_stack {
        @Test
        public void blocks_when_queried_for_its_top() {
            var thread = new Thread(() -> {
                var s = new BlockingStack<Integer>();
                s.peek();
            });
            thread.start();
            while (thread.getState() == Thread.State.RUNNABLE) {
                Thread.yield();
            }
            Assertions.assertTrue(Thread.State.WAITING == thread.getState() || Thread.State.BLOCKED == thread.getState());
        }

        @Test
        public void blocks_when_popped() {
            var thread = new Thread(() -> {
                var s = new BlockingStack<Integer>();
                s.pop();
            });
            thread.start();
            while (thread.getState() == Thread.State.RUNNABLE) {
                Thread.yield();
            }
            Assertions.assertTrue(Thread.State.WAITING == thread.getState() || Thread.State.BLOCKED == thread.getState());
        }

        @Test
        public void acquires_depth_by_retaining_a_pushed_item_as_its_top() {
            var s = new BlockingStack<Integer>();
            s.push(3);
            Assertions.assertEquals(1, s.getDepth());
            Assertions.assertEquals(3, s.peek());
        }
    }

    static class a_non_empty_stack {
        @Test
        public void becomes_deeper_by_retaining_a_pushed_item_as_its_top() {
            var s = new BlockingStack<Integer>();
            s.push(3);
            s.push(2);
            Assertions.assertEquals(2, s.getDepth());
            Assertions.assertEquals(2, s.peek());
        }

        @Test
        public void on_popping_reveals_tops_in_reverse_order_of_pushing() {
            var s = new BlockingStack<Integer>();
            s.push(3);
            s.push(2);
            s.push(1);
            Assertions.assertEquals(1, s.peek());
            s.pop();
            Assertions.assertEquals(2, s.peek());
            s.pop();
            Assertions.assertEquals(3, s.peek());
        }
    }

}
