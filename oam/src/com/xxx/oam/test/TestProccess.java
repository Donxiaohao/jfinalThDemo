package com.xxx.oam.test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class TestProccess {

	public static void main(String[] args) {
		StringBuffer a=new StringBuffer();
		String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python E:/pythonworkspace/test_python/testTime.py 1 2" );
            
            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader input = new LineNumberReader(ir);
            while((result=input.readLine())!=null ) {
            	a.append(result);
            }
            input.close();
            ir.close();
//            process.waitFor();
        } catch (Exception e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        System.out.println(a);
	}
}
