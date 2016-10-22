package com.example.administrator.arithmetic_master.test;

import com.example.administrator.arithmetic_master.base.BaseGenerator;
import com.example.administrator.arithmetic_master.baseimpl.BaseGeneratorImpl;
import com.example.administrator.arithmetic_master.grade.*;
import com.example.administrator.arithmetic_master.utils.Convert;

/**
 * Created by Administrator on 2016/10/19.
 */
public class TestCase {
    public static void main(String[] args){
    	testGradeOne();
    	testGradeTwo();
    	testGradeThree();
    	testGradeFour();
    	testGradeFive();
    	testGradeSix();
    	
//    	BaseGenerator b = new BaseGeneratorImpl();
//    	String s = b.calculate(Convert.infix2postfixMdf("(1/11)*(12/13)+(1/11)/(7/16)"));
//    	System.out.println(s);
    }
    static void testGradeOne(){
    	GradeOne g1 = new GradeOne();
        for (int i = 0; i < 10; i++) {
            System.out.print(g1.getIntergralExpression()+"\n");
        }
    }
    
    static void testGradeTwo(){
    	GradeTwo g2 = new GradeTwo();
        for (int i = 0; i < 10; i++) {
            System.out.print(g2.getIntergralExpression()+"\n");
        }
    }
    
    static void testGradeThree(){
    	GradeThree g3 = new GradeThree();
        for (int i = 0; i < 20; i++) {
            System.out.print(g3.getIntergralExpression()+"\n");
        }
    }
    
    static void testGradeFour(){
    	GradeFour g4 = new GradeFour();
        for (int i = 0; i< 20; i++) {
            System.out.print(g4.getIntergralExpression()+"\n");
        }
    }
    
    static void testGradeFive(){
    	GradeFive g5 = new GradeFive();
        for (int i = 0; i< 20; i++) {
//            System.out.print(g5.getIntergralExpression()+"\n");
        }
    }
    static void testGradeSix(){
    	GradeSix g6 = new GradeSix();
        for (int i = 0; i< 20; i++) {
//            System.out.print(g6.getIntergralExpression()+"\n");
        }
    }
    
}
