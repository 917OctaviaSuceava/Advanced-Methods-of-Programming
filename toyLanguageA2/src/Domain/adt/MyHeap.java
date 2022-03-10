package Domain.adt;

import Domain.values.IValue;
import Exceptions.ADTException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements IHeap {
    private HashMap<Integer, IValue> heap;
    private Integer free;

    public MyHeap()
    {
        heap = new HashMap<>();
        free = 1;
    }

    @Override
    public int getFreeAddr()
    {
        return free;
    }

    @Override
    public IValue get(int k) throws ADTException
    {
        try {
            return heap.get(k);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public void add(IValue e) throws ADTException {
        try {
            heap.put(free, e);
            free++;
        }
        catch (Exception ex)
        {
            throw new ADTException(ex.getMessage());
        }
    }

    @Override
    public boolean isDefined(int addr) {
        return heap.containsKey(addr);
    }

    @Override
    public void update(int k, IValue new_elem) throws ADTException {
        try {
            heap.replace(k, new_elem);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public HashMap<Integer, IValue> get() {
        return heap;
    }

    @Override
    public void setHeap(HashMap<Integer, IValue> heap) {
        this.heap = heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> heap1) {
        heap.clear();

        for(Map.Entry<Integer, IValue> entry: heap1.entrySet())
            heap.put((Integer)entry.getKey(), (IValue) entry.getValue());
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
