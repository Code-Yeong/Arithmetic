package com.example.administrator.arithmetic_master.grade;


import java.util.Random;

import com.example.administrator.arithmetic_master.baseimpl.BaseGeneratorImpl;

public class GradeThree extends BaseGeneratorImpl {

	public String getIntergralExpression(){
		String reg;
        double d = new Random().nextDouble();
        if (d <= 0.3){
        	reg = zhengshizhengchu(1,40);
        	
        }else if (d > 0.3 && d <= 0.6){
        	String str = zhengshizhengchu(1,30);
        	if(str.contains("*")){
        		if(d > 0.3 && d <= 0.4){
        			reg = str+"+"+zhengshu(10,100);
        		}else if(d > 0.4 && d <= 0.5){
        			reg = str +"*"+zhengshu(1,30);
        		}else{
        			reg = zhengshu(0,1000)+"+"+str;
        		}
        	}else{
        		if(d > 0.3 && d <= 0.4){
        			reg = zhengshu(0,1000)+"+"+str;
        		}else if(d > 0.4 && d <= 0.5){
        			reg = zhengshu(0,30)+"*"+str;
        		}else{
        			reg = str+"+"+zhengshu(1,100);
        		}
        	}
        	
        }else{
        	if(d <= 0.75){
        		String str = zhengshizhengchu(1,20);
        		if(str.contains("*")){
        			reg = str+"*"+zhengshizhengchu(1,20);
        		}else{
        			reg = str+"+"+zhengshizhengchu(1,50);
        		}
        	}else{
        		reg = zhengshizhengchu(1,30)+"+"+zhengshizhengchu(1,30);
        	}
        }
        return reg;
    }
}
