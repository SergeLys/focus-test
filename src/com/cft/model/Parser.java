package com.cft.model;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Parser {

    public static List<String> getOperandsArray(String input){
        List<String> elements = new ArrayList<String>();

        for(String value : input.split("(?=[+|\\-|*|/|^|(|)])|(?<=[+|\\-|*|/|^|(|)])")){
            if(!value.equals("")){
                elements.add(value);
            }
        }
        return elements;
    }

    public static List<String> toPostfixForm(String input){

        List<String> elements = Parser.getOperandsArray(input);
        List<String> postfixForm = new ArrayList<String>();
        Stack<String> operators = new Stack<String>();

            while (!elements.isEmpty()){
                String element = elements.get(0);
                if(!Operator.isOperator(element)){
                    postfixForm.add(element);
                }else {
                    if(element.equals("(")){
                        operators.push(element);
                    }
                    else if(element.equals(")")){
                        popBrackets(operators,postfixForm);
                    }
                    else if(operators.empty()){
                        operators.push(element);
                    } else {
                        if(getPriority(element) < getPriority(operators.peek())){
                            while (getPriority(element) < getPriority(operators.peek())){
                                postfixForm.add(operators.pop());
                                if(operators.empty()) break;
                            }
                        }
                        if(!operators.empty()){
                            if(getPriority(element) == getPriority(operators.peek())){
                                postfixForm.add(operators.pop());
                                operators.push(element);
                            } else operators.push(element);

                        } else operators.push(element);
                    }
                }
                elements.remove(0);
            }
            popAllElements(operators,postfixForm);
        return postfixForm;
    }

    public static int getPriority(String input){

        if(input.equals("(")) return 1;
        if(input.equals(")")) return 2;
        if(input.equals("+")) return 3;
        if(input.equals("-")) return 3;
        if(input.equals("*")) return 4;
        if(input.equals("/")) return 4;
        return 0;
    }

    private static void popAllElements(Stack<String> stack, List<String> list){
        while(true){
            if(stack.empty()) break;
            list.add(stack.pop());
        }
    }

    private static void popBrackets(Stack<String> stack, List<String> list) {
        while (true){
            if(stack.peek().equals("(")){
                stack.pop();
                break;
            }
            list.add(stack.pop());
        }
    }

}
