package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.IntType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.IntValue;
import com.example.demo.Exceptions.DivisionByZero;
import com.example.demo.Exceptions.InvalidOperand;
import com.example.demo.Exceptions.ProgramException;

public class ArithExp implements Exp {
    private Exp exp1, exp2;
    private char operation; //+, -, *, /

    public ArithExp(char op, Exp e1, Exp e2)
    {
        exp1 = e1;
        exp2 = e2;
        operation = op;
    }

    @Override
    public String toString() {
        return "(" + exp1 + operation + exp2 + ")";
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        IType typ1, typ2;
        typ1 = exp1.typeCheck(typeEnv);
        typ2 = exp2.typeCheck(typeEnv);
        if(typ1.equals(new IntType()))
        {
            if(typ2.equals(new IntType()))
            {
                return new IntType();
            }
            else throw new Exception("second operand is not an integer");
        }
        else throw new Exception("first operand is not an integer");
    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) throws ProgramException {
        IValue v1, v2;
        v1 = exp1.eval(table, heap);
        if(v1.getType().equals(new IntType()))
        {
            v2 = exp2.eval(table, heap);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(operation == '+') return new IntValue(n1+n2);
                else if(operation == '-') return new IntValue(n1-n2);
                else if(operation == '*') return new IntValue(n1*n2);
                else if(operation == '/')
                {
                    if(n2 == 0)
                        throw new DivisionByZero("Error: Division by zero!");
                    return new IntValue(n1/n2);
                }
                else throw new InvalidOperand("That operand is not valid!");
            }

        }
        return new IntValue(0);
    }

    /*
    @Override
    public Exp deepCopy() {
        return new ArithExp(operation, exp1.deepCopy(), exp2.deepCopy());
    }
     */
}
