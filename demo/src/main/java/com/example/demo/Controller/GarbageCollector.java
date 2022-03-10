package com.example.demo.Controller;

import com.example.demo.Domain.adt.IList;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.RefValue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollector
{
    public static Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->symTableAddr
                        .contains(e.getKey()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Set<Integer> getAddrFromSymTable(Collection<IValue> symTableValues,  Collection<IValue> heap)
    {
        /*return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> { RefValue v1 = (RefValue)v;
                    return v1.getAddress(); }
                )
                .collect(Collectors.toList());*/
        return Stream.concat(symTableValues.stream(),heap.stream()).filter(
                value->value instanceof RefValue
        ).map(value->{
            RefValue value1 = (RefValue)value;
            return value1.getAddress();
        }).collect(Collectors.toSet());
    }

    public static void conservativeGarbageCollector(List<PrgState> prgStates){
        var heap= Objects.requireNonNull(prgStates.stream().map(
                prg->getAddrFromSymTable(prg.getSymTable().getDict().values(),
                        prg.getHeap().get().values())
        ).map(Collection::stream).reduce(Stream::concat).orElse(null).collect(
                Collectors.toList()
        ));

        prgStates.forEach(prg-> prg.getHeap().setHeap(
                (HashMap<Integer, IValue>) unsafeGarbageCollector(heap,prgStates.get(0).getHeap().get())
        ));
    }
}
