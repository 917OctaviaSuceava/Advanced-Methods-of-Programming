package Domain.adt;
import Exceptions.ADTException;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack()
    {
        stack = new Stack<T>();
    }

    public Stack<T> getStack()
    {
        return stack;
    }

    @Override
    public T getFirst()
    {
        return stack.get(0);
    }

    @Override
    public String toString()
    {
        return stack.toString();
    }

    @Override
    public T pop() throws ADTException
    {
        try {
            return stack.pop();
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public void push(T value)
    {
        stack.push(value);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
