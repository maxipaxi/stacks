import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackCalculator {

    private <R extends Stack<Integer, R>> NonEmptyStack<Integer, R> add(NonEmptyStack<Integer, NonEmptyStack<Integer, R>> input) {
        var a = input.peek;
        var b = input.pop.peek;
        var rest = input.pop.pop;
        return rest.push(a + b);
    }

    @Test
    public void smoke_test() {
        /* If only Java had let-bindings
        let st = new EmptyStack<Integer>() in
        let st = st.push(3) in
        let st = st.push(2) in
        let st = st.push(1) in
        let st = add(st) in
        let st = add(st) in
         */
        var st = new EmptyStack<Integer>()
                .push(3)
                .push(2)
                .push(1);
        var st2 = add(st);
        var st3 = add(st2);
        Assertions.assertEquals(6, st3.peek);
    }

}
