import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTests {

    static class a_new_stack {
        @Test
        public void is_empty() {
            var s = new EmptyStack<Integer>();
            Assertions.assertEquals(0, s.depth);
        }
    }

    static class an_empty_stack {
        @Test
        public void acquires_depth_by_retaining_a_pushed_item_as_its_top() {
            var s = new EmptyStack<Integer>()
                    .push(3);
            Assertions.assertEquals(3, s.peek);
            Assertions.assertEquals(1, s.depth);
        }
    }

    static class a_non_empty_stack {
        @Test
        public void becomes_deeper_by_retaining_a_pushed_item_as_its_top() {
            var s = new EmptyStack<Integer>()
                    .push(3)
                    .push(2);
            Assertions.assertEquals(2, s.depth);
            Assertions.assertEquals(2, s.peek);
        }

        @Test
        public void on_popping_reveals_tops_in_reverse_order_of_pushing() {
            var s = new EmptyStack<Integer>()
                    .push(3)
                    .push(2)
                    .push(1);
            Assertions.assertEquals(1, s.peek);
            Assertions.assertEquals(2, s.pop.peek);
            Assertions.assertEquals(3, s.pop.pop.peek);
        }
    }

}
