package com.example.demo.Domain.adt;

import com.example.demo.Exceptions.ADTException;

import java.util.HashMap;

public class MyDict<TKey, TElem> implements IDict<TKey, TElem> {
    private HashMap<TKey, TElem> dict;

    public MyDict()
    {
        dict = new HashMap<TKey, TElem>();
    }

    @Override
    public void remove(TKey k) {
        dict.remove(k);
    }

    @Override
    public HashMap<TKey, TElem> getDict() {
        return dict;
    }

    @Override
    public void add(TKey k, TElem e) throws ADTException {
        try {
            dict.put(k, e);
        }
        catch (Exception ex)
        {
            throw new ADTException(ex.getMessage());
        }
    }

    @Override
    public void update(TKey k, TElem new_elem) throws ADTException {
        try {
            dict.replace(k, new_elem);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public boolean isDefined(TKey k) {
        return dict.get(k) != null;
    }

    @Override
    public TElem get(TKey k) throws ADTException {
        try {
            return dict.get(k);
        }
        catch (Exception e)
        {
            throw new ADTException(e.getMessage());
        }
    }

    @Override
    public MyDict<TKey, TElem> create_copy() {
        MyDict<TKey, TElem> new_dict = new MyDict<>();
        for (var key: dict.keySet())
        {
            new_dict.dict.put(key, dict.get(key));
        }
        return new_dict;
    }

    @Override
    public String toString() {
        return dict.toString();
    }

}
