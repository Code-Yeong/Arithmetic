package com.example.administrator.arithmetic_master.grade;


import java.util.Random;

import com.example.administrator.arithmetic_master.baseimpl.BaseGeneratorImpl;

public class GradeFive extends BaseGeneratorImpl {

	public String getIntergralExpression(){
		String reg="";
		double d = new Random().nextDouble();
        if (d <= 0.5){
        	if(d<=0.1){
        		reg = xiaoshu(100,2)+"*"+xiaoshu(100,2);	
        	}else if(d>0.1 && d<=0.2){
        		reg = xiaoshu(100,3)+"/"+xiaoshu(20,1);
        	}else{
        		reg = fenshi(30);
        	}
        }else if (d > 0.5 && d <= 0.75){
        	if(d<=0.6){
        		reg = fenshi(30)+JiaJian()+fenshu(20);	
        	}else if(d>0.6 && d<=0.7){
        		reg = xiaoshu(100,3)+"/"+xiaoshu(20,1)+JiaJian()+xiaoshu(100,2);
        	}else{
        		reg = xiaoshu(100,2)+"*"+xiaoshu(100,2)+JiaJian()+xiaoshu(100,2);
        	}
        	
        }else{
        	if(d<=0.85){
        		reg = fenshi(20)+JiaJian()+fenshi(20);
        	}else if(d>0.85 && d<=0.9){
        		reg = xiaoshu(100,2)+"*"+xiaoshu(100,2)+JiaJian()+xiaoshu(200,2)+"/"+xiaoshu(20,1);
        	}else{
        		reg = xiaoshu(100,3)+"/"+xiaoshu(50,2)+JiaJian()+xiaoshu(100,2)+"/"+xiaoshu(20,1);
        	}
        }
        return reg;
	}
}
