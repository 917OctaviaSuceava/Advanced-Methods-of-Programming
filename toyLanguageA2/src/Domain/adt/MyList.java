package Domain.adt;

import Exceptions.ADTException;

import java.util.ArrayList;

public class MyList<T> implements IList<T> {
    ArrayList<T> list;

    public MyList()
    {
        list = new ArrayList<T>();
    }
    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public boolean contains(T elem)
    {
        return list.contains(elem);
    }

    @Override
    public void add(T elem) throws ADTException {
        try {
            list.add(elem);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public void remove(int index) throws ADTException {
        try {
            list.remove(index);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public void set(int index, T elem) throws ADTException {
        try{
            list.set(index, elem);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public void setList(ArrayList<T> new_list) {
        list = new_list;
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
