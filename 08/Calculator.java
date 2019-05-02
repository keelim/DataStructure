import java.util.Arrays;

public class Calculator {

    private static final int MAX_EXPRESSION_LENGTH = 100;

    private Stack<Character> _operatorStack;
    private String _infixExpression;
    private String _postfixExpression;
    private PostfixCalculator _postfixCalculator;

    public Stack<Character> operatorStack() {
        return _operatorStack;
    }

    public void setOperatorStack(Stack<Character> _operatorStack) {
        this._operatorStack = _operatorStack;
    }

    public String infixExpression() {
        return _infixExpression;
    }

    public void setInfixExpression(String _infixExpression) {
        this._infixExpression = _infixExpression;
    }

    public String postfixExpression() {
        return _postfixExpression;
    }

    public void setPostfixExpression(String _postfixExpression) {
        this._postfixExpression = _postfixExpression;
    }

    public PostfixCalculator postfixCalculator() {
        return _postfixCalculator;
    }

    public void setPostfixCalculator(PostfixCalculator _postfixCalculator) {
        this._postfixCalculator = _postfixCalculator;
    }

    public Calculator() {
        this.setOperatorStack(new ArrayList<Character>(Calculator.MAX_EXPRESSION_LENGTH));
        this.setPostfixCalculator(new PostfixCalculator(Calculator.MAX_EXPRESSION_LENGTH));
    }

    private void showTokenPostfixExpression(char aToken, char[] aPostfixExpressionArray) {
        AppView.outputDebugMessage(aToken + " :(Postfix) ");
        AppView.outputLineDebugMessage(new String(aPostfixExpressionArray));
    }

    private int inComingPrecedence(Character aToken) {
        switch (aToken.charValue()) {
            case '(':
                return 20;
            case ')':
                return 19;
            case '^':
                return 17;
            case '*':
                return 13;
            case '/':
                return 13;
            case '%':
                return 13;
            case '+':
                return 12;
            case '-':
                return 12;
            default:
                return -1;
        }
    }

    private int inStackPrecedence(Character aToken) {
        switch (aToken.charValue()) {
            case '(':
                return 0;
            case ')':
                return 19;
            case '^':
                return 16;
            case '*':
                return 13;
            case '/':
                return 13;
            case '%':
                return 13;
            case '+':
                return 12;
            case '-':
                return 12;
            default:
                return -1;
        }

    }

    private CalculatorError infixToPostfix() {
        char[] postfixExpressionArray = new char[this.infixExpression().length()];
        Arrays.fill(postfixExpressionArray, ' '); //�迭�� ä���ִ� �Լ��� �ֳ�?

        Character currentToken, poppedToken, topToken;
        this.operatorStack().clear();
        int p = 0;
        for (int i = 0; i < this.infixExpression().length(); i++) {
            currentToken = this.infixExpression().charAt(i);
            if (Character.isDigit(currentToken.charValue())) {
                postfixExpressionArray[p++] = currentToken;
                this.showTokenPostfixExpression(currentToken, postfixExpressionArray);
            } else {
                if (currentToken == ')') {
                    this.showTokenAndMessage(currentToken, "���� ��ȣ�� ��Ÿ�� ���� ���ÿ��� ������ ���");
                    poppedToken = this.operatorStack().pop();
                    while (poppedToken != null && poppedToken.charValue() != '(') {
                        postfixExpressionArray[p++] = poppedToken.charValue();
                        this.showOperatorStack("Popped");
                        this.showTokenPostfixExpression(poppedToken, postfixExpressionArray);
                        poppedToken = this.operatorStack().pop();
                    }
                    if (poppedToken == null) {
                        return CalculatorError.InfixError_MissingLeftParen;
                    }
                    this.showOperatorStack("Popped");
                } else {
                    int inComingPrecedence = this.inComingPrecedence(currentToken.charValue());
                    if (inComingPrecedence < 0) {
                        AppView.outputLineDebugMessage(currentToken + " : (Unknown Operator)");
                        return CalculatorError.InfixError_UnknownOperator;
                    }
                    this.showTokenAndMessage(
                            currentToken, "�Է� �����ں��� ������ ���� ���� �����ڸ� ���ÿ��� ������ ���");
                    topToken = this.operatorStack().peek();
                    while (topToken != null && this.inStackPrecedence(topToken) >= inComingPrecedence) {
                        poppedToken = this.operatorStack().pop();
                        postfixExpressionArray[p++] = poppedToken;
                        this.showOperatorStack("Popped");
                        this.showTokenPostfixExpression(poppedToken, postfixExpressionArray);
                        topToken = this.operatorStack().peek();
                    }
                    if (this.operatorStack().isFull()) {
                        this.showOperatorStack("Fulled");
                        return CalculatorError.InfixError_TooLongExpression;
                    }
                    this.operatorStack().push(currentToken);
                    this.showOperatorStack("Pushed");
                }
            }

        }
        AppView.outputLineDebugMessage("(End of infix expression: ���ÿ��� ��� �����ڸ� ������ ���");

        while (!this.operatorStack().isEmpty()) {
            poppedToken = this.operatorStack().pop();
            this.showOperatorStack("Popped");
            if (poppedToken == '(') {
                return CalculatorError.InfixError_MissingRightParen;
            }
            postfixExpressionArray[p++] = poppedToken;
            this.showTokenPostfixExpression(poppedToken, postfixExpressionArray);
        }
        this.setPostfixExpression(new String(postfixExpressionArray).trim());
        return CalculatorError.InfixError_None;
    }

    private void showOperatorStack(String popped) {
    }

    private void showTokenAndMessage(Character currentToken, String s) {
    }

    public int evaluate(String anInfixExpression) throws CalculatorException {
        this.setInfixExpression(anInfixExpression);
        AppView.outputLineDebugMessage("[Infix to Postfix] " + anInfixExpression);
        if (this.infixExpression() == null || this.infixExpression().length() == 0) {
            throw new CalculatorException(CalculatorError.InfixError_NoExpression);
        }

        CalculatorError infixError = this.infixToPostfix();
        if (infixError == CalculatorError.InfixError_None) {
            AppView.outputDebugMessage("[Evaluate Postfix] " + this.postfixExpression());
            return this.postfixCalculator().evaluate(this.postfixExpression());
        } else {
            throw new CalculatorException((infixError));
        }

    }
}
