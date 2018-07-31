package com.xxx.oam.test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class TestProccess {

	public static void main(String[] args) {
		String result = "";
		int count=0;
        try {
            Process process = Runtime.getRuntime().exec("python E:/pythonworkspace/test_python/testTime.py" );
            
            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader input = new LineNumberReader(ir);
            while((result=input.readLine())!=null ) {
            	/*result+=input.readLine();
            	count++;*/
            	System.out.println(result);
            }
            input.close();
            ir.close();
//            process.waitFor();
        } catch (Exception e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        System.out.println(result);
	}
}
