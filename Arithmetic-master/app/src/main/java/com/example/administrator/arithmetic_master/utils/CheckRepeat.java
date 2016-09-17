package com.example.administrator.arithmetic_master.utils;

import com.example.administrator.arithmetic_master.base.BaseGenerator;

import java.util.ArrayList;
import java.util.List;

public class CheckRepeat {

    /**
     *@author GuoQingYun
     *@param list 以生成的题目组成的集合，集合呢各题均不重复
     *@param exp 新生成的待检测的表达式
     *@param BaseGenerator的对象
     *@return  true-新生成的表达式与之前的重复   false-新生成的表达式与之前的不重复
     * */
    public static boolean check(List list,String exp,BaseGenerator b){
        String  latterVal=b.calculate(Convert.infix2postfix(exp));
        List formorOperator=new ArrayList();
        List latterOperator=new ArrayList();
        List formorNumber=new ArrayList();
        List latterNumber=new ArrayList();
        for(int i=0;i<list.size();i++){
            //判断两个表达式的计算结果是否相等
            if(b.calculate(Convert.infix2postfix(list.get(i).toString())).equals(latterVal)){
                formorOperator=extractOperator(list.get(i).toString());
                latterOperator=extractOperator(exp);
                //判断两个表达式的运算符合是否完全一一匹配
                if(compare(formorOperator, latterOperator)){
                    formorNumber=extractNumber(list.get(i).toString());
                    latterNumber=extractNumber(exp);
                    //判断两个表达式中包含的整数项是否完全一一匹配
                    if(compare(formorNumber, latterNumber)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @author GuoQingYun
     * @param exp -四则运算表达式
     * @return List  -从表达式exp中分解出来的所有运算符的集合
     * */
    public static List extractOperator(String exp){
        List operator=new ArrayList();
        String c="";
        exp=exp.replace("(", "");//剔除掉表达中包含的括号
        exp=exp.replace(")", "");
        for (int i = 0; i < exp.length(); i++) {
            c=exp.substring(i,i+1);//逐个扫描表达式中的字符
            if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")){
                operator.add(c);
            }
        }
        return operator;
    }

    /**
     * @author GuoQingYun
     * @param exp -四则运算表达式
     * @return List  -从表达式exp中分解出来的所有整数的集合
     * */
    public static List extractNumber(String exp){
        List number=new ArrayList();
        String c="";
        exp=exp.replace("(", "");//剔除掉表达式中包含的括号
        exp=exp.replace(")", "");
        for (int i = 0; i < exp.length(); i++) {
            c=exp.substring(i,i+1);
            for(int j=0;j<5;j++){//如读到数字，则继续向后读取，直到读到运算符为止
                String c_temp="";
                if((i+j+2)>exp.length()){//判断是否到达输入的字符串的结尾
                    break;
                }
                c_temp=exp.substring(i+j+1,i+j+2);
                try {
                    Integer.parseInt(c_temp);//判断独到的字符是否为数字
                    c+=c_temp;
                    continue;
                } catch (Exception e) {
                    break;
                }
            }
            i+=(c.length()-1);
            number.add("c");
        }
        return number;
    }

    /**
     * @author GuoQingYun
     * @param formor -以存在的与其他表达式不重复的表达式中包含的运算符或整数的集合
     * @param latter -新生成的表达式中包含的运算符或整数的集合
     * @return boolean  -true:formor与latter元素种类个元素个数完全匹配
     * 					-false：formor与latter元素种类个元素个数不完全匹配
     * */
    public static boolean compare(List formor,List latter){
        if(formor.size()!=latter.size()){
            //两个表达式运算符或整数项不是一一匹配
            return false;
        }else{
            for(int i=0;i<formor.size();i++){
                if(formor.contains(latter.get(i))){
                    formor.remove(latter.get(i));//剔除掉已经比较过的项
                    latter.remove(i);
                    continue;
                }else{
                    //两个表达式的运算符或整数项不是一一匹配
                    return false;
                }
            }
            //两个表达式的运算符或整数项一一匹配
            return true;
        }
    }
}
