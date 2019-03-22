package com.cft.model;

/**
 * The enum realizes the mathematical and managing operators.
 */
public enum Operator {

    MULTIPLICATION, DIVISION,
    UNDEFINDED, SUM, MINUS,
    TRUE, FALSE, BIGGER,
    SMALLER;

    public static Operator getOperator(String input){
        if(input.equals("*")) return MULTIPLICATION;
        if(input.equals("/")) return DIVISION;
        if(input.equals("+")) return SUM;
        if(input.equals("-")) return MINUS;
        if(input.equals("?")) return TRUE;
        if(input.equals(":")) return FALSE;
        if(input.equals("<")) return SMALLER;
        if(input.equals(">")) return BIGGER;
        return UNDEFINDED;
    }

    public static boolean isOperator(String input){

        return input.equals("+") || input.equals("-") || input.equals("/") ||
                input.equals("*") || input.equals("(") || input.equals(")") ||
                input.equals(">") || input.equals("<") || input.equals(":") ||
                input.equals("?");
    }

    public static boolean isMathOperator(String input){

        return input.equals("+") || input.equals("-") || input.equals("/") ||
                input.equals("*") || input.equals("(") || input.equals(")");
    }

    public static boolean isComparisonOperator(String input){

        return input.equals("<") || input.equals(">");
    }
}
