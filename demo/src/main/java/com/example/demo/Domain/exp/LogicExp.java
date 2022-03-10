package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.BoolType;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.IntType;
import com.example.demo.Domain.values.BoolValue;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.IntValue;
import com.example.demo.Exceptions.*;

import java.util.Objects;

public class LogicExp implements Exp {
    Exp exp1;
    Exp exp2;
    String operation; // 1-and, 2-or

    public LogicExp(String op, Exp e1, Exp e2)
    {
        exp1 = e1;
        exp2 = e2;
        operation = op;
    }
//
//    @Override
//    public String toString() {
//        return "LogicExp{" +
//                "exp1=" + exp1 +
//                ", exp2=" + exp2 +
//                ", operation=" + operation +
//                '}';
//    }

    @Override
    public String toString() {
        return "(" + exp1.toString() + operation + exp2.toString() + ")";
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        IType typ1, typ2;
        typ1 = exp1.typeCheck(typeEnv);
        typ2 = exp2.typeCheck(typeEnv);
        if(typ1.equals(new BoolType()))
        {
            if(typ2.equals(new BoolType()))
            {
                return new BoolType();
            }
            else throw new Exception("second operand is not a boolean!");
        }
        else throw new Exception("first operand is not a boolean!");
    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) throws ADTException, DivisionByZero, ProgramException, TypeException, InvalidOperand
    {

        IValue v1, v2;
        v1 = exp1.eval(table, heap);
        if(v1.getType().equals(new BoolType()))
        {
            v2 = exp2.eval(table, heap);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(Objects.equals(operation, "and")) return new BoolValue(n1 && n2);
                else if(Objects.equals(operation, "or")) return new BoolValue(n1 || n2);
                else throw new InvalidOperand("That operand is not valid!");
            }

        }
        return new BoolValue(false);
    }

    /*
    @Override
    public Exp deepCopy() {
        return new LogicExp(operation, exp1.deepCopy(), exp2.deepCopy());
    }
     */
}
