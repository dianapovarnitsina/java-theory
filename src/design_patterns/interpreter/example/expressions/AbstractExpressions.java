package design_patterns.interpreter.example.expressions;

public abstract class AbstractExpressions {
    public abstract boolean interpret(Context context) throws Exception;
}
