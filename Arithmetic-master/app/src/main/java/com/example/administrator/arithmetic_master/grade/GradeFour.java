package com.example.administrator.arithmetic_master.grade;


import java.util.Random;

import com.example.administrator.arithmetic_master.baseimpl.BaseGeneratorImpl;

public class GradeFour extends BaseGeneratorImpl {
	public String getIntergralExpression(){
		String reg="";
		double d = new Random().nextDouble();
        if (d <= 0.5){
        	if(d<=0.15){
        		reg = xiaoshuyunsuan(200);	
        	}else{
        		reg = zhengshizhengchu(1,200);
        	}
        }else if (d > 0.5 && d <= 0.75){
        	String str = JiaJian();
        	if(d<=0.6){
        		reg = xiaoshuyunsuan(500)+str+xiaoshu(100,3);
        	}else if(d>0.6 && d<=0.7){
        		reg = zhengshizhengchu(1,100)+str+xiaoshu(100,3);
        	}else{
        		reg = zhengshu(1,15)+"*"+zhengshizhengchu(1,100);
        	}
        }else{
        	if(d<=0.85){
        		reg = xiaoshuyunsuan(500)+"+"+xiaoshuyunsuan(500);
        	}else if(d>0.85 && d<=0.9){
        		reg = zhengshizhengchu(1,100)+"+"+xiaoshuyunsuan(100);
        	}else{
        		reg = zhengshizhengchu(1,100)+"+"+zhengshizhengchu(1,100);
        	}
        }
        return reg;
	}

}
