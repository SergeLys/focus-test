package com.cft.model;

import java.util.List;
import java.util.Stack;

public final class ModelOperations {

    public static double calculate(List<String> input){

        Stack<String> operands = new Stack<>();
        Double result;

        while (!input.isEmpty()){
            String operand = input.get(0);
            if(operand.equals("-") && operands.size() == 1){

                result = Double.valueOf(operands.pop());
                result = 0 - result;
                operands.push(result.toString());
            } else if(Operator.isOperator(operand)){

                        Operator operator = Operator.getOperator(operand);
                        String firstOperand, secondOperand;
                        secondOperand = operands.pop();
                        firstOperand = operands.pop();
                        operands.push(getExpression(firstOperand,secondOperand,operator));
                    } else{
                        operands.push(operand);
                    }
            input.remove(0);
        }
        return Double.valueOf(operands.pop());
    }

    private static String getExpression(String first, String second, Operator operator){

        Double result = 0.0;

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
        return result.toString();
    }

}




