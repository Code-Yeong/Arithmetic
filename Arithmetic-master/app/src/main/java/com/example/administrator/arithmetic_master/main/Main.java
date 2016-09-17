//package com.example.administrator.arithmetic_master.main;
//
//import com.example.administrator.arithmetic_master.base.BaseGenerator;
//import com.example.administrator.arithmetic_master.grade.GradeOne;
//import com.example.administrator.arithmetic_master.utils.Convert;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        //类型 z(整式) 最小min 最大max  数目count
//        //类型 f(分式) 分母deno 数目count
//        //整数 zs 最小min 最大max 数目count
//        //整数 fs 分母deno 数目count
//        boolean isContinue=true;
//        int grade;
//        String path="C://Exercise/";
//        String filename="";
//        Scanner sc=new Scanner(System.in);
//        while(isContinue){
//            System.out.print("请输入年级(1、2、3、4、5、6):");
//            grade=sc.nextInt();
//            while(grade<1||grade>6){
//                System.out.print("年级输入有误，请重新输入:");
//                grade=sc.nextInt();
//            }
//            System.out.println("-------------选项--------------");
//            System.out.println("|       整式运算请输入 1          |");
//            System.out.println("|       分式运算请输入 2          |");
//            System.out.println("|       混合运算请输入 3          |");
//            System.out.println("------------------------------");
//            System.out.print("请输入:");
//            String type=sc.next();
//            int min=0,max=0,count=0,deno=0;
//            System.out.println("-------------参数设定--------------");
//            if(type.equals("1")){
//                System.out.print("请输入整数最小值:");
//                min=sc.nextInt();
//                System.out.print("请输入整数最大值:");
//                max=sc.nextInt();
//            }else if(type.equals("2")){
//                System.out.print("请输入分母最大值:");
//                deno=sc.nextInt();
//            }else if(type.equals("3")){
//                System.out.print("请输入整数最小值:");
//                min=sc.nextInt();
//                System.out.print("请输入整数最大值:");
//                max=sc.nextInt();
//                System.out.print("请输入分母最大值:");
//                deno=sc.nextInt();
//            }
//            System.out.print("请输入题目数量:");
//            count=sc.nextInt();
//            BaseGenerator b=new GradeOne();
//            List questions=b.show(type,min,max,deno,count);
//            List results=new ArrayList();
//            List answers=new ArrayList();
//            System.out.println("-------------开始答题--------------");
//            for(int i=0;i<questions.size();i++){
//                System.out.print("第"+(i+1)+"题:"+questions.get(i)+"=");
//                answers.add(sc.next());
//            }
//            for(int i=0;i<questions.size();i++){
//                results.add(b.calculate(Convert.infix2postfix((String)questions.get(i))));
//            }
//            System.out.println("-------------答题结果--------------");
//            System.out.print("正确答案:");
//            for(int i=0;i<results.size();i++){
//                System.out.print(results.get(i)+" ");
//            }
//            System.out.println();
//            for(int i=0;i<results.size();i++){
//                if(results.get(i).equals(answers.get(i))){
//                    answers.set(i, "1");
//                }else{
//                    answers.set(i, "0");
//                }
//            }
//
//            if(!answers.contains("1")){
//                System.out.println("\t全部答错，请再接再厉...！");
//            }else{
//                System.out.println("以下题目回答正确:");
//                for(int i=0;i<answers.size();i++){
//                    if(answers.get(i).equals("1")){
//                        System.out.print("\t第"+(i+1)+"题");
//                        if(i%5==0&&i>0){
//                            System.out.println();
//                        }
//                    }
//                }
//                System.out.println();
//                System.out.println("以下题目回答错误:");
//                for(int i=0;i<answers.size();i++){
//                    if(answers.get(i).equals("0")){
//                        System.out.print("\t第"+(i+1)+"题");
//                        if(i%5==0&&i>0){
//                            System.out.println();
//                        }
//                    }
//                }
//            }
//            System.out.println();
//
//            System.out.println("-------------保存选项--------------");
//            String isSave="n";
//            System.out.print("是(y或Y)否(n或N)保存题目以及正确答案?:");
//            isSave=sc.next();
//            while(!(isSave.equals("N")||isSave.equals("n")||isSave.equals("Y")||isSave.equals("y"))){
//                System.out.print("输入错误，请重新输入:");
//                isSave=sc.next();
//            }
//            if(isSave.equals("N")||isSave.equals("n")){
//            }else if(isSave.equals("y")||isSave.equals("Y")){
//                System.out.print("是(y/Y)否(n/N)修改保存路径(默认路径:"+path+")?:");
//                isSave=sc.next();
//                if(isSave.equals("y")||isSave.equals("Y")){
//                    System.out.print("path=");
//                    path=sc.next();
//                }
//                System.out.print("请输入文件名:");
//                System.out.print(path+"/");
//                filename=sc.next();
//                System.out.println("保存成功!");
//            }
//            System.out.println("---------------------------------");
//
//
//            System.out.print("输入‘Y/y’继续答题，输入‘N/n’结束答题:");
//            String status=sc.next();
//            while(!(status.equals("N")||status.equals("n")||status.equals("Y")||status.equals("y"))){
//                System.out.print("输入错误，请重新输入:");
//                status=sc.next();
//            }
//            if(status.equals("N")||status.equals("n")){
//                isContinue=false;
//            }else if(status.equals("Y")||status.equals("y")){}
//        }
//        System.out.println("-------------答题结束--------------");
//        System.out.println("谢谢使用");
//
//    }
//}
