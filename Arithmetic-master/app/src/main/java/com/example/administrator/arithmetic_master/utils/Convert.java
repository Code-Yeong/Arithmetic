package com.example.administrator.arithmetic_master.utils;

import java.util.Stack;

public class Convert {

    /**
     * @param infix  中缀表达�?
     * @returned String 后缀表达�?
     * */
    public static String infix2postfix(String infix){
        String postfix="";
        int length=infix.length();
        Stack st = new Stack();
        String c;
        for (int i = 0; i < length; i++){
            c = infix.substring(i, i+1);
            if (c .equals("(")){
                st.push(c);
            }else if (c.equals(")")){
                while (!st.peek().equals("(")){
                    postfix+= (st.pop()+"#");
                }
                st.pop();
            }else{
                try{
                    Integer.parseInt(c);//判断读到的字符是否为数字
                    for(int j=0;j<5;j++){//如读到数字，则继续向后读取，直到读到运算符为�?
                        String c_temp="";
                        if((i+j+2)>length){//判断是否到达输入的字符串的结�?
                            break;
                        }
                        c_temp=infix.substring(i+j+1,i+j+2);
                        try {
                            Integer.parseInt(c_temp);//判断独到的字符是否为数字
                            c+=c_temp;
                            continue;
                        } catch (Exception e) {
                            break;
                        }
                    }
                    i+=(c.length()-1);
                    postfix+= (c+"#");
                }catch(Exception e){
                    while (!st.empty()&& (comparePri((String)st.peek(), c) >= 0)){
                        postfix += (st.pop()+"#");
                    }
                    st.push(c);
                }
            }
        }
        while (!st.empty()){//输入栈中剩余的所有元�?
            postfix +=(st.pop()+"#");
        }
        return postfix;
    }

    /**
     *@param op1 op2 运算�?
     *@return int op1、op2的优先级比较结果
     * */
    public static int comparePri(String op1, String op2){
        if (op1 .equals("(")){
            return -1;
        }else if (op1 .equals("+") || op1 .equals("-")){
            if (op2.equals("*") || op2.equals("/")){
                return -1;
            }
        }else if (op1 .equals("*") ||op1 .equals("/")){
            if (op2 .equals("+") || op2 .equals("-")){
                return 1;
            }
        }
        return 0;
    }
    
    public static String infix2postfixMdf(String infix){
        String postfix="";			//�����
        int length=infix.length();	//����������
        Stack st = new Stack();		//ջ
        String c;
        for (int i = 0; i < length; i++){
            c = infix.substring(i, i+1);
            if (c.equals("(")){
                st.push(c);
                if(infix.substring(i+1, i+2).equals("-")){
                	postfix+= ("0#");
                }
            }else if (c.equals(")")){
                while (!st.peek().equals("(")){
                    postfix+= (st.pop()+"#");
                }
                st.pop();
            }else{
	                try{
	                    Integer.parseInt(c);//判断读到的字符是否为数字
	                    for(int j=0;j<5;j++){//如读到数字，则继续向后读取，直到读到运算符为�?
	                        String c_temp="";
	                        if((i+j+2)>length){//判断是否到达输入的字符串的结�?
	                            break;
	                        }
	                        c_temp=infix.substring(i+j+1,i+j+2);
	                        try {
	                        	if(!c_temp.equals(".")){
	                        		Integer.parseInt(c_temp);//判断独到的字符是否为数字
	                        	}
	                            c+=c_temp;
	                            continue;
	                        } catch (Exception e) {
	                            break;
	                        }
	                    }
	                    i+=(c.length()-1);
	                    if(c.contains(".")){
	                    	int location = c.indexOf(".");
	                    	int cl = c.length();
	                    	int bottom = 1;
	                    	for (int j = 0; j < cl-1-location; j++) {
								bottom = bottom*10;
							}
	                    	c=(int)(Double.parseDouble(c)*bottom)+"#"+bottom;
	                    	st.push("/");
	                    }
	                    postfix+= (c+"#");
	                }catch(Exception e){
	                    while (!st.empty()&& (comparePri((String)st.peek(), c) >= 0)){
	                        postfix += (st.pop()+"#");
	                    }
	                    st.push(c);
	                }
			
            }
        }
        while (!st.empty()){//输入栈中剩余的所有元�?
            postfix +=(st.pop()+"#");
        }
        return postfix;
    }
}