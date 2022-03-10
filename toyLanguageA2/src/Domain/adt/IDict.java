package Domain.adt;

import Exceptions.ADTException;

import java.util.HashMap;

public interface IDict<TKey, TElem> {
    void remove(TKey k);
    void add(TKey k, TElem e) throws ADTException;
    void update(TKey k, TElem new_elem) throws ADTException;
    boolean isDefined(TKey k);
    TElem get(TKey k) throws ADTException;
    String toString();
    HashMap<TKey, TElem> getDict();
    public IDict<TKey, TElem> create_copy();
}
