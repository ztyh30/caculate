package caculate;

public class caculRule {


    //获取字符串数组的长度
    public int getLength(String[] stringArray){
        int length = 0;
        for(int i = 0;i < stringArray.length;i++){
            if(stringArray[i] != null) length++;
        }
        return length;
    }

    //将字符串数组转变为字符串
    public String stringArrayToString(String[] stringArray){
        String string = "";
        for(int i = 0;i < getLength(stringArray);i++){
            string += stringArray[i];
        }
        return string;
    }
    //加法
    public String add(String leftOperatorNum,String rightOperatorNum){
        Integer leftSon = Integer.parseInt(getFractionSon(leftOperatorNum));
        Integer leftMom = Integer.parseInt(getFractionMom(leftOperatorNum));
        Integer rightSon = Integer.parseInt(getFractionSon(rightOperatorNum));
        Integer rightMom = Integer.parseInt(getFractionMom(rightOperatorNum));
        return calulate(1,leftMom,leftSon,rightMom,rightSon);
    }
    //减法
    public String sub(String rightOperatorNum,String leftOperatorNum){
        Integer leftSon = Integer.parseInt(getFractionSon(leftOperatorNum));
        Integer leftMom = Integer.parseInt(getFractionMom(leftOperatorNum));
        Integer rightSon = Integer.parseInt(getFractionSon(rightOperatorNum));
        Integer rightMom = Integer.parseInt(getFractionMom(rightOperatorNum));
        return calulate(2,leftMom,leftSon,rightMom,rightSon);
    }
    //乘法
    public String multiply(String leftOperatorNum,String rightOperatorNum){
        Integer leftSon = Integer.parseInt(getFractionSon(leftOperatorNum));
        Integer leftMom = Integer.parseInt(getFractionMom(leftOperatorNum));
        Integer rightSon = Integer.parseInt(getFractionSon(rightOperatorNum));
        Integer rightMom = Integer.parseInt(getFractionMom(rightOperatorNum));
        return calulate(3,leftMom,leftSon,rightMom,rightSon);
    }
    //除法
    public String divide(String rightOperatorNum,String leftOperatorNum){
        Integer leftSon = Integer.parseInt(getFractionSon(leftOperatorNum));
        Integer leftMom = Integer.parseInt(getFractionMom(leftOperatorNum));
        Integer rightSon = Integer.parseInt(getFractionSon(rightOperatorNum));
        Integer rightMom = Integer.parseInt(getFractionMom(rightOperatorNum));
        return calulate(4,leftMom,leftSon,rightMom,rightSon);
    }

    //子表达式计算
    public String calulate(int tag,Integer leftMom,Integer leftSon,Integer rightMom,Integer rightSon){
        Integer Lcm = getLcm(leftMom,rightMom); //获取左操作数分母和右操作数分母最小公倍数
        Integer newSon = 0; //结果分子
        if(tag == 1){
            //加法
            newSon = leftSon*(Lcm/leftMom) + rightSon*(Lcm/rightMom);
            return newSon.toString()+"/"+Lcm.toString();
        }else if(tag == 2){
            //减法
            newSon = leftSon*(Lcm/leftMom) - rightSon*(Lcm/rightMom);
            if(newSon < 0) return null; //计算过程产生负数
            return newSon.toString()+"/"+Lcm.toString();
        }else if(tag == 3){
            //乘法
            Integer multiSon = leftSon*rightSon;
            Integer multiMom = leftMom*rightMom;
            return multiSon.toString()+"/"+multiMom.toString();
        }else if(tag == 4){
            //除法
            if(rightSon == 0) return null;
            Integer diviSon = leftSon*rightMom;
            Integer diviMom = leftMom*rightSon;
            if(diviSon > diviMom) return null; //假分数
            return diviSon.toString()+"/"+diviMom.toString();
        }else{
            return null;
        }
    }
    //获取假分数整数部分
    public String getFractionInt(String string){
        return string.substring(0,string.indexOf('\''));
    }
    //获取分数分母
    public String getFractionMom(String string){
        return string.substring(string.indexOf('/')+1);
    }
    //获取分数分子
    public String getFractionSon(String string){
        return string.substring(string.indexOf('\'')+1,string.indexOf('/'));
    }

    //获取最小公倍数
    public Integer getLcm(Integer leftMom,Integer rightMom){
        if(leftMom == rightMom) return leftMom;
        int temp = Math.max(leftMom,rightMom);
        int Lcm = temp;
        while(true){
            if( (Lcm%leftMom)==0 && (Lcm%rightMom)==0) break;
            Lcm += temp;
        }
        return Lcm;
    }

    //获得符合的答案格式
    public String getAnswer(Integer newSon,Integer Lcm){
        Integer integer = null;
        String answer = "";
        if(newSon % Lcm == 0){ //整数情况
             integer = newSon/Lcm;
            return integer.toString();
        }
        if(newSon/Lcm != 0) {
            integer = newSon / Lcm; //整数部分
            newSon = newSon - integer * Lcm; //新分子
        }
        int HCF = 1;// 最大公因数
        int min = newSon > Lcm ? Lcm : newSon;
        //获取最大公因数
        for(int i = 2; i <= min; i++){
            if(newSon%i == 0 && Lcm%i == 0) HCF = i;
        }
        newSon = newSon/HCF;
        Lcm = Lcm/HCF;
        if(integer != null){ //整数部分不为空
            answer = answer + integer.toString() + "'";
        }
        answer = answer + newSon.toString() + "/" + Lcm.toString();
        return answer;
    }

    //变成假分数
    public String becomeFalsefraction(String operatorNum){
        if(operatorNum.contains("'")){
            Integer intPart = Integer.parseInt(getFractionInt(operatorNum)); //假分数整数部分
            Integer mom = Integer.parseInt(getFractionMom(operatorNum)); //假分数分母
            Integer son = Integer.parseInt(getFractionSon(operatorNum)); //假分数分子
            son = intPart*mom + son;
            String falseFractionNum = son.toString()+"/"+mom.toString();
            return falseFractionNum;
        }
        return operatorNum;
    }


}
