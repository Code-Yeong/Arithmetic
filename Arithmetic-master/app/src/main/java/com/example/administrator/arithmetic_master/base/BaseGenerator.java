package com.example.administrator.arithmetic_master.base;

import com.example.administrator.arithmetic_master.grade.GradeOne;
import com.example.administrator.arithmetic_master.utils.CheckRepeat;
import com.example.administrator.arithmetic_master.utils.Simplify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;


public abstract class BaseGenerator {

    /**
     * @param min 最小数
     * @param max 最大数
     * @return 返回区间[min,max]内的一个随机整式
     * */
	public abstract String zhengshi(int min,int max);//������ʽ

    /**
     * @param deno 分母最大值
     * @return 返回一个随机真分式
     * */
	public abstract String fenshi(int deno);//���������ʽ
    /**
     * @param min 最小数
     * @param max 最大数
     * @return 返回区间[min,max]内的一个随机整数
     * */
	public abstract String zhengshu(int min,int max);//��������

    /**
     * @param deno 分母最大值
     * @return 返回一个随机真分数
     * */
	public abstract String fenshu(int deno);//�����������

    /**
     * @param type 题目类型
     * @param min  整式中出现的整数的最小值
     * @param max  整式中出现的整数的最大值
     * @param deno 分式中分母可能出现的最大值
     * @param count
     * @return List<String> 返回题目集合
     * */
	public List show(String type,int min,int max,int deno,int count){
		String exp="";
		String[] ops={"+","*"};
		Random r=new Random();
		List questions=new ArrayList();
		BaseGenerator b=new GradeOne();
		if(type.equals("1")){//整式运算����
			for (int i = 0; i < count; i++) {//生成count个正式
				exp=b.zhengshi(min, max);
				if(CheckRepeat.check(questions, exp, b)){
					i--;
					continue;
				}
				questions.add(exp);
			}
		}else if(type.equals("2")){//分式运算��
			for (int i = 0; i < count; i++) {//生成count个分式
				exp=b.fenshi(deno);
				if(CheckRepeat.check(questions, exp,b)){
					i--;
					continue;
				}
				questions.add(exp);
			}
        }else if(type.equals("3")){//混合运算
            for (int i = 0; i < count; i++) {//生成count个混合元算表达式
                int length=r.nextInt(4)+3;//新加的符号个最最多为6个
                exp="";
                String op;
                for(int j=0;j<length;j++){
                    op=ops[r.nextInt(2)];//随机选取运算符号 '+'或'*'
                    if(op.equals("+")){
                        if(r.nextInt(2)==1){
                            exp=exp+b.zhengshu(min, max)+"+";
                        }else{
                            exp=exp+b.fenshu(deno)+"+";
                        }
                    }else if(op.equals("*")){
                        if(exp.length()==0){
                            exp=r.nextInt(9)+2+"+";
                        }
                        if(r.nextInt(2)==1){//随机选取整式或整数
                            String item=b.zhengshi(min, max);
                            while(item.contains("*")||item.contains("/")){
                                item=b.zhengshi(min, max);
                            }
                            item="("+item+")";
                            if((exp.substring(0,exp.length()-1).contains("+")||exp.substring(0,exp.length()-1).contains("-"))
                                    &&!exp.substring(0,exp.length()-1).contains("*")
                                    &&!exp.substring(0,exp.length()-1).contains("/")){
                                exp="("+exp.substring(0,exp.length()-1)+")*"+item+"+";
                            }else{
                                exp=exp.substring(0,exp.length()-1)+"*"+item+"+";
                            }
                        }else{
                            String item=b.zhengshu(min, max);
                            if((exp.substring(0,exp.length()-1).contains("+")||exp.substring(0,exp.length()-1).contains("-"))
                                    &&!exp.substring(0,exp.length()-1).contains("*")
                                    &&!exp.substring(0,exp.length()-1).contains("/")){
                                exp="("+exp.substring(0,exp.length()-1)+")*"+item+"+";
                            }else{
                                exp=exp.substring(0,exp.length()-1)+"*"+item+"+";
                            }
                        }
                    }
                }
                if(!exp.equals("")&&(exp.subSequence(exp.length()-1, exp.length()).equals("+"))){
                    exp=exp.substring(0,exp.length()-1);//剔除表达式末尾的加号
                }
                if(CheckRepeat.check(questions, exp,b)){
                    i--;
                    continue;
                }
                questions.add(exp);
            }
        }
        return questions;
    }

    /**
     * @param exp 四则运算表达式
     * @return 表达式元算结果的最简(分数)形式
     * */
    public String calculate(String exp){
        int a,b,result=0;
        String first,second,temp = "";
        int first_cop,first_deno,second_cop,second_deno;
        Stack s=new Stack();
        String[] c=exp.split("#");
        //分式求值
        if(exp.contains("/")){
            for(int i=0;i<c.length;i++){
                if(!(c[i].contains("+")||c[i].contains("-")||c[i].contains("*")||c[i].contains("/"))){
                    s.push(c[i]+"/1");//将整数化为分母为1的分数形式
                    continue;
                }else{
                    second=(String) s.pop();
                    first=(String) s.pop();
                    first_cop=Integer.parseInt(first.split("/")[0]);//第一个分数的分子
                    first_deno=Integer.parseInt(first.split("/")[1]);//第一个分数的分母
                    second_cop=Integer.parseInt(second.split("/")[0]);//第二个分数的分子
                    second_deno=Integer.parseInt(second.split("/")[1]);//第二个分数的分母
                    if(c[i].equals("+")){//分数相加
                        temp=(first_cop*second_deno+second_cop*first_deno)+"/"+(first_deno*second_deno);
                    }else if(c[i].equals("-")){//分数相减
                        temp=(first_cop*second_deno-second_cop*first_deno)+"/"+(first_deno*second_deno);
                    }else if(c[i].equals("*")){//分数相乘
                        temp=(first_cop*second_cop)+"/"+(first_deno*second_deno);
                    }else if(c[i].equals("/")){//分数相除
                        temp=(first_cop*second_deno)+"/"+(first_deno*second_cop);
                    }
                    s.push(temp);//将计算结果压入栈中
                }
            }
            //将最终结果约分后返回
            return Simplify.afterSimplify((String) s.pop());
        }else{
            //整式求值
            for(int i=0;i<c.length;i++){
                try{
                    Integer.parseInt(c[i]);//判断是否为数字
                    s.push(c[i]);
                    continue;
                }catch(Exception e){
                    b=Integer.parseInt((String) s.pop());
                    a=Integer.parseInt((String) s.pop());
                    if(c[i].equals("+")){
                        result=a+b;
                    }else if(c[i].equals("-")){
                        result=a-b;
                    }else if(c[i].equals("*")){
                        result=a*b;
                    }
                    s.push(result+"");
                }
            }
        }
        //返回整数运算结果
        return result+"";
    }

}
