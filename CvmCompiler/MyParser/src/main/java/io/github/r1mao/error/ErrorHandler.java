package io.github.r1mao.error;

import java.util.ArrayList;

public class ErrorHandler
{
    private ArrayList<ErrorRecord> errors=new ArrayList<>();
    public void addError(ErrorRecord record)
    {
        this.errors.add(record);
    }
    public ArrayList<ErrorRecord> getErrors()
    {
        return this.errors;
    }
}
