package random;

public class numAndoperator {
    public int randomNum; //随机数
    public char operatorChar; //操作符

    public String[] opetator = {"+","-","*","÷"}; //加减乘除
/*
      range:随机数范围
 */

    public String getOperatorNum(int range){
        if(range <= 0) return null; //range范围有误
        if(range == 1) return getIntegerOperatorNum(range);
        int sign1 = (int)(Math.random()*2);
        //生成整数
        if(sign1 == 1){
            return getIntegerOperatorNum(range);
        }else{
            int sign2 = (int)(Math.random()*2);
            //生成真分数
            if(sign2 == 1){
                return getTruefraction(range);
            }else{
                //生成假分数
                return getFalsefraction(range);
            }
        }
    }

    public String getIntegerOperatorNum(int range){
        Integer num = (Integer)getRandomNum(range);
        return num.toString();
    }

    //获得随机数
    public int getRandomNum(int range){
        return (int)(Math.random()*range);
    }
    //获得运算符号
    public String getOperatorChar(){
        int temp = (int)(Math.random()*4);
        return opetator[temp];
    }
    //生成真分数
    public String getTruefraction(int range){
        String trueFraction = "";
        //生成分母
        Integer mother = getRandomNum(range);
        while(true){
            if(mother <= 1){
                mother = getRandomNum(range);
            }else{
                break;
            }
        }
        //生成分子
        Integer son = getRandomNum(mother);
        while(true){
            if(son == 0){
                son = getRandomNum(mother);
            }else{
                break;
            }
        }
        //拼接
        trueFraction += son.toString();
        trueFraction += "/";
        trueFraction += mother.toString();
        return trueFraction;
    }
    //生成假分数
    public String getFalsefraction(int range){
        String falseFraction = ""; //假分数
        int temp = getRandomNum(range);
        String part; //假分数整数部分
        while(true){
            if(temp == 0){
                temp = getRandomNum(range);
            }else{
                break;
            }
        }
        //生成真分数部分
        part = getTruefraction(range);
        falseFraction += ((Integer)temp).toString();
        falseFraction += "'";
        falseFraction += part;
        return falseFraction;
    }


}
