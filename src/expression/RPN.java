package expression;

/*
    获取逆波兰表达式
 */

import caculate.caculRule;

import java.util.Stack;

public class RPN {


    caculRule caculRule = new caculRule();


    public String[] getRPN(Expression expression){
        Stack<String> stack = new Stack<>();
        String[] RPN = new String[9]; //逆波兰表达式
        //将expression转变为字符串数组
        String[] tempExpression = expression.getStringExpression();
        //获得字符串数组的长度
        int length = caculRule.getLength(tempExpression);
        int index = 0;
        for (int i = 0; i < length; i++) {
            String temp;
            String string = tempExpression[i];
            switch (string) {
                case "(":
                    stack.push(string);
                    break;
                case "+":
                case "-":
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp.equals("(")) {
                            stack.push("(");
                            break;
                        }
                        RPN[index++] = temp;
                    }
                    stack.push(string);
                    break;
                case "*":
                case "÷":
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp.equals("(") || temp.equals("+") || temp.equals("-")) {
                            stack.push(temp);
                            break;
                        } else {
                            RPN[index++] = temp;
                        }
                    }
                    stack.push(string);
                    break;
                case ")":
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp.equals("("))
                            break;
                        else
                            RPN[index++] = temp;
                    }
                    break;
                default:
                    if(!string.contains("'") && !string.contains("/")){
                        string += "/1";
                    }
                    RPN[index++] = string;
            }
        }

        while (stack.size() != 0) {
            String temp = stack.pop();
            RPN[index++] = temp;
        }
        return RPN;
    }

}
