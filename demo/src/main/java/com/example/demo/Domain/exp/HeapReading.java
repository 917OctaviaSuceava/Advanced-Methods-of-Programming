package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.IntType;
import com.example.demo.Domain.types.RefType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.RefValue;
import com.example.demo.Exceptions.DivisionByZero;
import com.example.demo.Exceptions.InvalidOperand;
import com.example.demo.Exceptions.ProgramException;

public class HeapReading implements Exp {
    Exp exp;

    public HeapReading(Exp e)
    {
        exp = e;
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        IType typ = exp.typeCheck(typeEnv);
        if (typ instanceof RefType)
        {
            RefType reft =(RefType) typ;
            return reft.getInner();
        }
        else throw new Exception("the rH argument is not a Ref Type");

    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) throws DivisionByZero, ProgramException, InvalidOperand {
        IValue res = exp.eval(table, heap);
        if(!(res instanceof RefValue))
            throw new ProgramException("It should be a RefType!");
        int res_addr = ((RefValue) res).getAddress();
        if(!heap.isDefined(res_addr))
            throw new ProgramException("That address does not exist!");
        return heap.get(res_addr);
    }
}
