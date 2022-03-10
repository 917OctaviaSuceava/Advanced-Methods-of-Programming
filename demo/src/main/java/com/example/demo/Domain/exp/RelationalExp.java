package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.BoolType;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.IntType;
import com.example.demo.Domain.values.BoolValue;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.IntValue;
import com.example.demo.Exceptions.DivisionByZero;
import com.example.demo.Exceptions.InvalidOperand;
import com.example.demo.Exceptions.ProgramException;

public class RelationalExp implements Exp {
    private Exp exp1;
    private Exp exp2;
    private String operation; // <, <=, ==, >=, >

    public RelationalExp(Exp e1,Exp e2, String op)
    {
        exp1 = e1;
        exp2 = e2;
        operation = op;
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
    public IValue eval(MyDict<String, IValue> table, IHeap heap) throws DivisionByZero, ProgramException, InvalidOperand {
        IValue value1, value2;
        value1 = exp1.eval(table, heap);
        if(!value1.getType().equals(new IntType()))
            throw new ProgramException("The first value is not an integer!");
        value2 = exp2.eval(table, heap);
        if(!value2.getType().equals(new IntType()))
            throw new ProgramException("The second value is not an integer!");
        IntValue int1 = (IntValue) value1;
        IntValue int2 = (IntValue) value2;
        int n1, n2;
        n1 = int1.getVal();
        n2 = int2.getVal();
        return switch (operation) {
            case "<" -> new BoolValue(n1 < n2);
            case "<=" -> new BoolValue(n1 <= n2);
            case "==" -> new BoolValue(n1 == n2);
            case ">=" -> new BoolValue(n1 >= n2);
            case ">" -> new BoolValue(n1 > n2);
            default -> throw new InvalidOperand("That operation is not valid!");
        };
    }

    @Override
    public String toString()
    {
        return "(" + exp1.toString() + operation + exp2.toString() + ")";
    }

    /*
    @Override
    public Exp deepCopy() {
        return new RelExp(operation, exp1.deepCopy(), exp2.deepCopy());
    }
     */
}
