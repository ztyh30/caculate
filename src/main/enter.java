package main;

import caculate.caculExp;
import expression.Expression;
import random.randExpression;

import java.util.Scanner;

public class enter {
    public static void main(String[] args) {
        randExpression randexpression = new randExpression(); //生成表达式
        caculExp caculexp = new caculExp();
        String[] expressionArray = null;
        String[] answerArray = null;
        Expression[] expressions = null;
        int num,range;
        num = range = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入生成表达式个数：");
        num = scanner.nextInt();
        System.out.println("请输入操作数范围：");
        range = scanner.nextInt();
        expressionArray = new String[num];
        answerArray = new String[num];
        expressions = new Expression[num];
        for (int i = 0; i < num; i++){
            Expression e = randexpression.getExpression(range);
            expressions[i] = e;
            caculexp.getExpValue(e);
            if(e.getAnswer() == null){
                i--;
            }else{
                String exp = e.getExpression();
                expressionArray[i] = exp;
                answerArray[i] = e.getAnswer();
                }
        }
        for (int i = 0; i < num; i++){
            System.out.println("第"+(i+1)+"题：");
            System.out.println(expressionArray[i]+" = "+answerArray[i]);
        }

    }
}
