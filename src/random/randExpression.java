package random;

import expression.Expression;

public class randExpression {


    numAndoperator nao = new numAndoperator();
    randRule randrule = new randRule();

    public Expression getExpression(int range){
        int operatorNum = (int)(Math.random()*3); //操作符数目
        if(operatorNum == 0){
            return get1OperatorExpression(range,0,1);
        }else if(operatorNum == 1){
            //sign：0:两个操作符不带括号 1:两个操作符带左括号 2:两个操作符带右括号
            int sign = (int)(Math.random()*3);
            if(sign == 0) return get2OperatorExpression(range,0,1);
            else if(sign == 1) return get2OperatorExpressionWithLeftBrackets(range,0,1);
            else return get2OperatorExpressionWithRightBrackets(range,0,1);
        }else{
            //sign：0:三个操作符不带括号 1:三个操作符带左括号 2:三个操作符带右括号
            int sign = (int)(Math.random()*3);
            if(sign == 0) return get3OperatorExpression(range,0,1);
            else if(sign == 1) return get3OperatorExpressionWithLeftBrackets(range,0,1);
            else return get3OperatorExpressionWithRightBrackets(range,0,1);
        }
    }
    /*
        生成单操作符表达式
        numBeginPosion：操作数在表达式字符串数组中的位置
        operatorPosition：运算符在表达式字符串数组中的位置
     */

    public Expression get1OperatorExpression(int range,int numBeginPosion,int operatorPosition){
        Expression expression = new Expression();
        String operator = nao.getOperatorChar();
        String leftOperatorNumber = nao.getOperatorNum(range);
        String rightOperatorNumber  = randrule.getRightOperatorNum(operator,nao.getOperatorNum(range), range);
        expression.getOperatorNum()[numBeginPosion] = leftOperatorNumber;
        expression.getOperatorNum()[numBeginPosion+1] = rightOperatorNumber;
        expression.getOperator()[operatorPosition] = operator;
        return expression;
    }
    /*
        生成一个带括号的单操作符表达式
        numBeginPosion：操作数在表达式字符串数组中的位置
        operatorPosition：运算符在表达式字符串数组中的位置
     */
    public Expression get1OperatorExpressionWithBrackets(int range,int numBeginPosion,int operatorPosition){
        Expression expression = get1OperatorExpression(range,numBeginPosion,operatorPosition);
        expression.getOperator()[operatorPosition-1] = "(";
        expression.getOperator()[operatorPosition+1] = ")";
        return expression;
    }
    /*
       生成两个操作符表达式
       1+2*4
     */


    public Expression get2OperatorExpression(int range,int numBeginPosion,int operatorPosition){
        Expression expression = get1OperatorExpression(range,numBeginPosion,operatorPosition);
        String operator = nao.getOperatorChar();//生成一个随机的操作符
        String thridOperatorNumber  = randrule.getRightOperatorNum(operator, nao.getOperatorNum(range), range);//生成一个符合操作要求的随机的操作数
        expression.getOperatorNum()[numBeginPosion+2] = thridOperatorNumber;
        expression.getOperator()[operatorPosition+1] = operator;
        return expression;
    }
    /*
       生成带左括号的两个操作符表达式
     */

    public Expression get2OperatorExpressionWithLeftBrackets(int range,int numBeginPosion,int operatorPosition){
        Expression expression = get1OperatorExpressionWithBrackets(range,numBeginPosion,operatorPosition);
        String operator = nao.getOperatorChar();
        expression.getOperator()[operatorPosition+1] += operator;
        String thirdNum  = randrule.getRightOperatorNum(operator, nao.getOperatorNum(range), range);
        expression.getOperatorNum()[numBeginPosion+2] = thirdNum;
        return expression;
    }

    public Expression get2OperatorExpressionWithRightBrackets(int range,int numBeginPosion,int operatorPosition){
        Expression expression = get1OperatorExpressionWithBrackets(range,numBeginPosion+1,operatorPosition+1);
        String operator = nao.getOperatorChar();
        expression.getOperator()[operatorPosition] = (operator += expression.getOperator()[operatorPosition]);
        String thridNumb  = randrule.getRightOperatorNum(operator, nao.getOperatorNum(range), range);
        expression.getOperatorNum()[numBeginPosion] = thridNumb;
        return expression;
    }
    /*
      (1+2+3)
     */

    public Expression getTwoOperatorExpressionWithMidBrackets(int range,int numBeginPosion,int operatorPosition){
        Expression expression = get2OperatorExpression(range,numBeginPosion,operatorPosition);
        expression.getOperator()[operatorPosition-1] = "(";
        expression.getOperator()[operatorPosition+2] = ")";
        return expression;
    }
    /*
       生成带三个操作符表达式
       1+2-8*4
     */

    public Expression get3OperatorExpression(int range,int numBeginPosion,int operatorPosition){
        Expression expression = get2OperatorExpression(range,numBeginPosion,operatorPosition);
        String operator = nao.getOperatorChar();
        String thridOperatorNumber  = randrule.getRightOperatorNum(operator, nao.getOperatorNum(range), range);
        expression.getOperatorNum()[numBeginPosion+3] = thridOperatorNumber;
        expression.getOperator()[operatorPosition+2] = operator;
        return expression;
    }
    /*
       生成带左括号的三个操作符表达式
       (1+2-3)+4
     */

    public Expression get3OperatorExpressionWithLeftBrackets(int range, int numBeginPosion, int operatorPosition) {
        Expression expression = getTwoOperatorExpressionWithMidBrackets(range,numBeginPosion,operatorPosition);
        String operator = nao.getOperatorChar();
        expression.getOperator()[operatorPosition+2] += operator;
        String fourthNum  = randrule.getRightOperatorNum(operator, nao.getOperatorNum(range), range);
        expression.getOperatorNum()[numBeginPosion+3] = fourthNum;
        return expression;
    }
    /*
    生成带右括号的三个操作符表达式
       6*(1+2-3)
     */

    public Expression get3OperatorExpressionWithRightBrackets(int range, int numBeginPosion, int operatorPosition) {
        Expression expression = getTwoOperatorExpressionWithMidBrackets(range,numBeginPosion+1,operatorPosition+1);
        String operator = nao.getOperatorChar();
        expression.getOperator()[operatorPosition] = (operator += expression.getOperator()[operatorPosition]);
        String thridOperatorNumber  = randrule.getRightOperatorNum(operator, nao.getOperatorNum(range), range);
        expression.getOperatorNum()[numBeginPosion] = thridOperatorNumber;
        return expression;
    }



}
