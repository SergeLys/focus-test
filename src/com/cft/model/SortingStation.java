package com.cft.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Class implements the Dijkstra's sorting station algorithm.
 */
public final class SortingStation {

    /**
     * The function splits a string into operators and numbers.
     *
     * @param input This is string with input math expression.
     * @return List with operators and numbers.
     */

    private static List<String> getOperandsArray(String input){
        List<String> elements = new ArrayList<>();

        for(String value : input.split("(?=[+|\\-|*|/|^|(|)|<|>|:|?])|(?<=[+|\\-|*|/|^|(|)|<|>|:|?])")){
            if(!value.equals(" ")){
                elements.add(value);
            }
        }
        return elements;
    }

    /**
     * The function implements the Dijkstra's sorting station algorithm.
     *
     * @param input This is string with input math expression.
     * @return Stack of strings with numbers and operators in a postfix form
     */
    public static Stack<String> toPostfixForm(String input){

        List<String> elements = SortingStation.getOperandsArray(input);
        Stack<String> postfixForm = new Stack<>();
        Stack<String> operators = new Stack<>();

            while (!elements.isEmpty()){
                String element = elements.get(0);
                elements.remove(0);
                if(!Operator.isOperator(element)){
                    postfixForm.push(element);
                    continue;
                }
                if(element.equals("(")){
                    operators.push(element);
                    continue;
                }
                if(element.equals(")")){
                    popBrackets(operators,postfixForm);
                    continue;
                }
                if(operators.empty()){
                    operators.push(element);
                    continue;
                }
                if(getPriority(element) < getPriority(operators.peek())){
                    while (getPriority(element) < getPriority(operators.peek())){
                        postfixForm.push(operators.pop());
                        if(operators.empty()) break;
                    }
                }
                if(!operators.empty()){
                    if(getPriority(element) == getPriority(operators.peek())){
                        postfixForm.push(operators.pop());
                        operators.push(element);
                    } else operators.push(element);

                } else operators.push(element);
            }
            popAllElements(operators,postfixForm);

        return postfixForm;
    }

    /**
     * The function calculates a priority of the operator.
     *
     * @param input String with operator.
     * @return Priority of the operator.
     */
    private static int getPriority(String input){

        if(input.equals("(")) return 1;
        if(input.equals(")")) return 2;
        if(input.equals("+")) return 3;
        if(input.equals("-")) return 3;
        if(input.equals("*")) return 4;
        if(input.equals("/")) return 4;
        if(input.equals("<")) return 5;
        if(input.equals(">")) return 5;
        if(input.equals(":")) return 5;
        if(input.equals("?")) return 5;
        return 0;
    }

    /**
     * The function returns all elements of stack.
     *
     * @param outStack The stack source.
     * @param inStack The stack with result.
     */
    private static void popAllElements(Stack<String> outStack, Stack<String> inStack){
        while(true){
            if(outStack.empty()) break;
            inStack.push(outStack.pop());
        }
    }

    /**
     * The function returns all elements in brackets.
     *
     * @param outStack The stack source.
     * @param inStack The stack with result.
     */
    private static void popBrackets(Stack<String> outStack, Stack<String> inStack) {
        while (true){
            if(outStack.peek().equals("(")){
                outStack.pop();
                break;
            }
            inStack.add(outStack.pop());
        }
    }
}
