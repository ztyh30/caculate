package caculate;

import expression.Expression;
import expression.RPN;

import java.util.Stack;

public class caculExp {

    caculRule caculrule = new caculRule();
    RPN r = new RPN();

    public void getExpValue(Expression expression){
        String[] rpn = r.getRPN(expression); //获得逆波兰表达式
        String ansewr = caculateRPN(rpn);
        expression.setAnswer(ansewr);
    }

    public String caculateRPN(String[] rpn){
        Stack<String> stack = new Stack<>();
        //获得逆波兰表达式字符串数组的长度
        int length = caculrule.getLength(rpn);
        String temp = null;
        for(int i = 0; i < length; i++){
            if(rpn[i].length() > 1){ //长度大于1，操作数入栈
                stack.push(rpn[i]);
            }else{
                String leftOp = caculrule.becomeFalsefraction(stack.pop());
                String rightOp = caculrule.becomeFalsefraction(stack.pop());
                String result = caculateExp(rpn[i],leftOp,rightOp);
                if(result == null) return null;
                stack.push(result);
            }
        }
        String answer = stack.pop();
        Integer newSon = Integer.parseInt(caculrule.getFractionSon(answer));
        Integer Lcm = Integer.parseInt(caculrule.getFractionMom(answer));

        return caculrule.getAnswer(newSon,Lcm);
    }

    //计算逆波兰表达式的每个子式
    public String caculateExp(String operator,String leftOperatorNum,String rightOperatorNum){
        switch (operator){
            case "+": return caculrule.add(leftOperatorNum,rightOperatorNum);
            case "-": return caculrule.sub(leftOperatorNum,rightOperatorNum);
            case "*": return caculrule.multiply(leftOperatorNum, rightOperatorNum);
            case "÷": return caculrule.divide(leftOperatorNum, rightOperatorNum);
            default: return null;
        }
    }
}
