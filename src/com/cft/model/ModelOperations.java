package com.cft.model;

import java.util.List;
import java.util.Stack;

/**
 * The class implements mathematical model of the app.
 */
public final class ModelOperations {

    /**
     * The function calculates expression in a postfix form.
     * @param input The list with elements of expression
     *              in a postfix form.
     * @return Value of expression.
     */
    public static double calculate(List<String> input){

        Stack<String> operands = new Stack<>();
        Operator way = Operator.UNDEFINDED;

        while (!input.isEmpty()){
            String operand = input.get(0);
            input.remove(0);
            if(operand.equals("?") && way.equals(Operator.TRUE)) return Double.valueOf(operands.pop());
            if(operand.equals(":") && way.equals(Operator.FALSE)) return Double.valueOf(operands.pop());

            if(operand.equals("-") && operands.size() == 1){ //check -x cases

                toInvertOperand(operands);
                continue;
            }
            if(operand.equals("-") && input.size() > 0 // check x+|-(-x) cases
                    && (input.get(0).equals("-") || input.get(0).equals("+"))){

                toInvertOperand(operands);
                continue;
            }
            if(operand.equals("-") && input.size() == 1   //check x-|+|*|/(-x) cases
                   && operands.size() == 2 && Operator.isMathOperator(input.get(0))){

                toSwapMinus(input);
                continue;
            }
            if(operand.equals("-") && input.size() > 1      //check -/+|-/* cases
                    && Double.valueOf(operands.peek()) > 0  //if last operand < 0 then then do not swap a minus
                    && Operator.isMathOperator(input.get(0))
                    && Operator.isMathOperator(input.get(1))){

                toSwapMinus(input);
                continue;
            }
            if(Operator.isMathOperator(operand)){

                operands.push(getMAthOperation(operands, Operator.getOperator(operand)));
                continue;
            }
            if(Operator.isComparisonOperator(operand)){

                way = compare(operands, Operator.getOperator(operand));
                continue;
            }
            operands.push(operand);
        }
        return Double.valueOf(operands.pop());
    }

    private static void toInvertOperand(Stack<String> operands){ // x = -x

        double result = Double.parseDouble(operands.pop());
        result = 0 - result;
        operands.push(Double.toString(result));
    }

    private static void toSwapMinus(List<String> input){

        String operand = input.get(0);
        input.remove(0);
        input.add(0,"-");
        input.add(0,operand);
    }

    /**
     *
     * The function calculates a mathematical operations.
     *
     * @param operands Stack with mathematical operands.
     * @param operator Mathematical operator
     * @return Value of the mathematical operation
     */
    private static String getMAthOperation(Stack<String> operands, Operator operator){

        String second = operands.pop();
        String first = operands.pop();
        double result = 0.0;

        switch (operator){
            case SUM:
                result = (Double.valueOf(first) + Double.valueOf(second));
                break;
            case MINUS:
                result = (Double.valueOf(first) - Double.valueOf(second));
                break;
            case MULTIPLICATION:
                result = (Double.valueOf(first) * Double.valueOf(second));
                break;
            case DIVISION:
                result = (Double.valueOf(first) / Double.valueOf(second));
                break;
        }
        return Double.toString(result);
    }

    /**
     *
     * The function calculates comparison operations.
     *
     * @param operands Stack with mathematical operands.
     * @param operator Comparison operator
     * @return Value of the comparison operation
     */
    private static Operator compare(Stack<String> operands, Operator operator){

        String second = operands.pop();
        String first = operands.pop();
        Operator result = Operator.FALSE;

        switch (operator){
            case BIGGER:
                if(Double.valueOf(first) > Double.valueOf(second)) result = Operator.TRUE;
                else result = Operator.FALSE;
                break;
            case SMALLER:
                if(Double.valueOf(first) < Double.valueOf(second)) result = Operator.TRUE;
                else result = Operator.FALSE;
                break;
        }
        return result;
    }
}