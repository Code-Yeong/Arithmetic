package com.example.administrator.arithmetic_master.grade;


import java.util.Random;

import com.example.administrator.arithmetic_master.baseimpl.BaseGeneratorImpl;

public class GradeTwo extends BaseGeneratorImpl {

	public String getIntergralExpression(){
		String reg;
		double d = new Random().nextDouble();
        if (d <= 0.3){
        	reg = zhengshi(1,30);   
        }else if (d > 0.3 && d <= 0.6){
        	String str = zhengshi(1,50);
        	if(str.contains("*")){
        		if(d >= 0.45){
        			reg = zhengshi(1,25)+JiaJian()+zhengshu(1,50);
        		}else{
        			reg = zhengshu(1,50)+JiaJian()+zhengshi(1,25);
        		}
        	}else{
        		reg = zhengshu(1,50)+JiaJian()+zhengshi(1,30);
        	}
        }else{
        	if(d>=0.75){
        		reg = zhengshi(1,20)+"+"+zhengshi(10,30);
        	}else{
        		reg = zhengshi(1,20)+"*"+zhengshi(1,20);
        	}
        }
        return reg;
    }
}
