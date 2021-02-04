import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
    
public class Stack_ITest {
        
    private static Stack<String> stack = new Stack_I();

    /**
     * push an empty String to the stack
     * @result the stak should recive it and place it on last place in stack
     */
    @Test
    public void pushEmptyString() {
        String temp = "";
        stack.push(temp);
        assertEquals(temp, stack.pop());
    }

    /**
     * use peek on empty stack
     * @result the peek of an empty stack should return a null
     */
    @Test
    public void peekEmptyStack() {
        String peek = stack.peek();
        assertNull(peek);
    }

    /**
     * do pop on empty stack 
     * @result the pop should return a null
     */
    @Test
    public void popEmptyStack(){
        String pop = stack.pop();
        assertNull(pop);
    }

    /**
     * use empty method on an empty stack
     * @result it should return true
     */
    @Test
    public void EmptyOnEmptyStack(){
        boolean empty = stack.empty();
        assertEquals(true, empty);
    }

    /**
     * use size method on empty stack
     * @result it should return 0
     */
    @Test
    public void sizeOnEmptyStack(){
        int size = stack.size();
        assertEquals(0, size);
    }
}
    