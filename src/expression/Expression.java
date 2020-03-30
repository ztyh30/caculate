package expression;

/*
    算术表达式定义
 */

public class Expression {
    private String operatorNum[];//操作数
    private String operator[];//操作符
    private String answer; //答案
    private String flagExprssion[];//用于查重.
    private int flag = 0; //用于标识该表达式是否重复.

    //构造器
    public Expression(){
        operatorNum = new String[5]; //为方便计算将所有数组初始化为空
        for(int i = 0;i<operatorNum.length;i++){
            operatorNum[i] = null;
        }
        operator = new String[6]; //为方便计算将所有数组初始化为空
        for(int i = 0;i<operator.length;i++){
            operator[i] = null;
        }
        flagExprssion = new String[7];
        for(int i = 0;i<flagExprssion.length;i++){
            flagExprssion[i] = null;
        }
    }

    public String[] getOperatorNum() {
        return operatorNum;
    }

    public String[] getOperator() {
        return operator;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getStringExpression(){
        String [] exp = new String[9];
        int i,j,k;
        i = j = k = 0;
        if(operator[i] != null) exp[j++] = operator[i];
        i++;
        for(;operatorNum[k] != null;){
            exp[j++] = operatorNum[k++];
            if(operator[i] != null) {
                if(operator[i].length() > 1){
                    exp[j++] = operator[i].substring(0, 1);
                    exp[j++] = operator[i].substring(1, 2);
                }else{
                    exp[j++] = operator[i];
                }
                i++;
            }else{
                break;
            }
        }
        if(operatorNum[k]!=null)  	exp[j++] = operatorNum[k];
        if(operator[i]!=null) exp[j++] =operator[i];
        return exp;
    }

    public String getExpression(){
        String expression  = "";
        int i = 0,k = 0;
        if(operator[k]!=null) expression += operator[k];
        k++;
        for(;operatorNum[i]!=null;){
            expression += operatorNum[i++];
            if(operator[k] != null) {
                expression += operator[k];
                k++;
            }else{
                break;
            }
        }
        if(operatorNum[i] != null) expression += operatorNum[i];
        if(operator[k] != null) expression += operator[k];
        return expression;
    }
}
