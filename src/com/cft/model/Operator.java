package com.cft.model;

public enum Operator {

    MULTIPLICATION("*"), DIVISION("/"),
    UNDEFINDED(""), SUM("+"), MINUS("-");

    private String symbol;

    Operator(String symbol){

        this.symbol = symbol;
    }

    public static Operator getOperator(String input){
        if(input.equals("*")) return MULTIPLICATION;
        if(input.equals("/")) return DIVISION;
        if(input.equals("+")) return SUM;
        if(input.equals("-")) return MINUS;
        return UNDEFINDED;
    }

    public static boolean isOperator(String input){
        if(input.equals("+") || input.equals("-") || input.equals("/") ||
                input.equals("*") || input.equals("(") || input.equals(")")){
            return true;
        }
        return false;
    }
}
