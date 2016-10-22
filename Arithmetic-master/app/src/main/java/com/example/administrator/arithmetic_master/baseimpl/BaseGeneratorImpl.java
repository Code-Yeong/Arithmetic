package com.example.administrator.arithmetic_master.baseimpl;

import com.example.administrator.arithmetic_master.base.BaseGenerator;
import com.example.administrator.arithmetic_master.utils.CheckCoprime;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.StringTokenizer;

public class BaseGeneratorImpl extends BaseGenerator {

	String[] ops={"+","-","*","/"};
	
	public String zhengshi(int min,int max) {
		Random r=new Random();
		int a=(r.nextInt(max-min+1)+min);
		int b=(r.nextInt(max-min+1)+min);
		String op=ops[r.nextInt(3)];
		if(op.equals("-")){
			if(a-b<0){
				return b+op+a;		
			}
		}
		return a+op+b;
	}

	
	public String fenshi(int deno) {
		int first_cop=0;
		int first_deno=0;
		int second_cop=0;
		int second_deno=0;
		Random r=new Random();
		int a=(r.nextInt(deno)+1);
		int b=(r.nextInt(deno)+1);
		while(!CheckCoprime.isCoprime(a, b)||(a==b)){
			a=(r.nextInt(deno)+1);
			b=(r.nextInt(deno)+1);
		}
		if(a>b){
			first_cop=b;
			first_deno=a;
		}else{
			first_cop=a;
			first_deno=b;
		}
		a=(r.nextInt(deno)+1);
		b=(r.nextInt(deno)+1);
		while(!CheckCoprime.isCoprime(a,b)||(a==b)){
			a=(r.nextInt(deno)+1);
			b=(r.nextInt(deno)+1);
		}
		if(a>b){
			second_cop=b;
			second_deno=a;
		}else{
			second_cop=a;
			second_deno=b;
		}
		String op=ops[r.nextInt(2)];
		if(op.equals("-")){
			if((first_cop*second_deno-second_cop*first_deno)<0){
				return "("+second_cop+"/"+second_deno+")"+op+"("+first_cop+"/"+first_deno+")"; 
			}
		}
		return "("+first_cop+"/"+first_deno+")"+op+"("+second_cop+"/"+second_deno+")";
	}

	public String zhengshu(int min, int max) {
		Random r=new Random();
		return (r.nextInt(max-min+1)+min)+"";
	}

	public String fenshu(int deno) {
		Random r=new Random();
		int a=(r.nextInt(deno)+1);
		int b=(r.nextInt(deno)+1);
		while(!CheckCoprime.isCoprime(a,b)||(a==b)){
			a=(r.nextInt(deno)+1);
			b=(r.nextInt(deno)+1);
		}
		if(a>b){
			return "("+b+"/"+a+")";
		}
		return "("+a+"/"+b+")";
	}

	//write by hwt  to get the plus,minus signs randomly
	public String JiaJian(){
		String sign;
		double d = new Random().nextDouble();
		if (d >= 0.5){
			sign = "+";
		}else{
			sign = "-";
		}
		return sign;
	}

	public String zhengshizhengchu(int min,int max) {
		Random r=new Random();
		int a=(r.nextInt(max-min+1)+min);
		int b=(r.nextInt(max-min+1)+min);
		int i = r.nextInt(4);
		if(i==4){
			i = 3;
		}
		String op=ops[i];
		if(op.equals("-")){
			if(a-b<0){
				return b+op+a;		
			}
		}else if(op.equals("/")){
			if(a<b){
				int temp = a;
				a = b;
				b = temp;
			}
			while(a%b!=0){
				a=a+(b-a%b);
			}	
		}
		return a+op+b;
	}
	public String xiaoshuyunsuan(int deno) {
		DecimalFormat dformat = new DecimalFormat("##0.00");
		int first_cop=0;
		int first_deno=0;
		int second_cop=0;
		int second_deno=0;
		Random r=new Random();
		int a=(r.nextInt(deno)+1);
		int b=(r.nextInt(deno)+1);
		while(!CheckCoprime.isCoprime(a, b)||(a==b)){
			a=(r.nextInt(deno)+1);
			b=(r.nextInt(deno)+1);
		}
		if(a>b){
			first_cop=b;
			first_deno=a;
		}else{
			first_cop=a;
			first_deno=b;
		}
		a=(r.nextInt(deno)+1);
		b=(r.nextInt(deno)+1);
		while(!CheckCoprime.isCoprime(a,b)||(a==b)){
			a=(r.nextInt(deno)+1);
			b=(r.nextInt(deno)+1);
		}
		if(a>b){
			second_cop=b;
			second_deno=a;
		}else{
			second_cop=a;
			second_deno=b;
		}
		String op=ops[r.nextInt(2)];
		float f2 = second_cop;
		float f1 = first_cop;
		if(op.equals("-")){
			if((first_cop*second_deno-second_cop*first_deno)<0){
				return dformat.format(f2/second_deno)+op+dformat.format(f1/first_deno); 
			}
		}
		return dformat.format(f1/first_deno)+op+dformat.format(f2/second_deno);
	}
	public String xiaoshu(int deno,int count) {
		DecimalFormat dformat;
		if(count==1){
			dformat = new DecimalFormat("##0.0");
		}else if(count==2){
			dformat = new DecimalFormat("##0.00");
		}else{
			dformat = new DecimalFormat("##0.000");
		}
		float f;
		Random r=new Random();
		int a=(r.nextInt(deno)+1);
		int b=(r.nextInt(deno)+1);
		while(!CheckCoprime.isCoprime(a,b)||(a==b)){
			a=(r.nextInt(deno)+1);
			b=(r.nextInt(deno)+1);
		}
		f = a;
		if(a>b){
			return dformat.format(b/f);
		}
		return dformat.format(f/b);
	}
	public String fushu(int min, int max) {
		Random r=new Random();
		return "(-"+(r.nextInt(max-min+1)+min)+")";
	}
	
}
