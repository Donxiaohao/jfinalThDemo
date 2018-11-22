package com.xxx.oam.interceptor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class TestInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		/*final ExecutorService exec=Executors.newSingleThreadExecutor();
		Future<String> future =null;
		Callable<String> call=new Callable<String>(){
			@Override
			public String call() throws Exception {
				inv.invoke();
				return "eeeeeee";
			}
		};
		try {
		future=exec.submit(call);
		String string = future.get(1000*5, TimeUnit.MILLISECONDS);
		System.out.println(string);
		}catch (TimeoutException e) {
			System.out.println("method name :   "+inv.getMethodName()+"    response timeout   ");
			future.cancel(true);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		System.out.println("拦截前~~~~~~~~~~");
		inv.invoke();
		System.out.println("拦截后~~~~~~~~~~");
	}

}
