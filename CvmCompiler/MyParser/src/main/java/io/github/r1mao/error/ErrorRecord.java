package io.github.r1mao.error;

public class ErrorRecord
{
    String text;
    ErrorType type;
    private ErrorRecord(ErrorType type,String text)
    {
        this.type=type;
        this.text=text;
    }
    public ErrorType getType()
    {
        return this.type;
    }
    public String getText()
    {
        return this.text;
    }
    public static ErrorRecord UndefineError(String var)
    {
        ErrorRecord record=new ErrorRecord(ErrorType.SYMBOL_UNDEFINE,"undefine symbol "+var);
        return record;
    }
    public static ErrorRecord RedefineError(String var)
    {
        ErrorRecord record=new ErrorRecord(ErrorType.SYMBOL_REDEFINE,"redefine symbol "+var);
        return record;
    }
    public static ErrorRecord SymbolTypeError(String var)
    {
        ErrorRecord record=new ErrorRecord(ErrorType.SYMBOL_TYPE_ERROR,"type error: "+var);
        return record;
    }
    public static ErrorRecord ReturnTypeError(String info)
    {
        ErrorRecord record=new ErrorRecord(ErrorType.RETURN_TYPE_ERROR,"return type error: "+info);
        return record;
    }
}
