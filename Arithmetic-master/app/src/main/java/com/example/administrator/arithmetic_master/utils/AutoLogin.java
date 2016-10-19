package com.example.administrator.arithmetic_master.utils;

import android.content.Context;
import android.icu.text.IDNA;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;  
import java.io.InputStreamReader;  
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;  
import java.io.FileWriter; 

public class AutoLogin {

    private static final String filePath= "/autoLogin.txt";
	public static boolean save(Context cxt,String loginname, String password){
		CreateJson c = new CreateJson();
		c.add("loginname", loginname);
		c.add("password", password);
		try {
            File writename = new File(cxt.getFilesDir()+filePath);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            out.write(c.toString());
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;
        }  
	}
	public static String read(Context cxt){
		try {
            File filename = new File(cxt.getFilesDir() +filePath);
            InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";  
            line = br.readLine();  
            br.close();
            return line;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return "";	
	}
}