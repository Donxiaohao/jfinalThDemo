package com.xxx.oam.test;



import java.io.File;
import java.io.IOException;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;

public class TestOam {
	public static void main(String[] args) {
		System.out.println("项目要启动啦~~~~");
		String baseBath = String.valueOf(TestOam.class.getProtectionDomain().getCodeSource().getLocation());
        String classPath, webRootPath, jarPath;
        if (StrKit.notBlank(baseBath) && baseBath.contains("file:/")) {
            // 获取运行操作系统的运行方式  window和linux的细微区别
            boolean windows = System.getProperties().getProperty("os.name").contains("Windows");
            System.out.println(System.getProperties().getProperty("os.name"));
            jarPath = (windows ? "" : "/") + baseBath.substring("file:/".length());
            classPath = (windows ? "" : "/") + jarPath.substring(0, jarPath.lastIndexOf("/"));
            System.out.println("jarPath:" + jarPath);
            System.out.println("classPath:" + classPath);
            webRootPath = classPath;
//           ZipUtil.unzip(jarPath, classPath);
            // 这两步是核心指定 webapp目录和classpath目录
            PathKit.setWebRootPath(webRootPath);
            PathKit.setRootClassPath(classPath);
            // eclipse 启动是4个参数    
        }
		//启动项目 进行测试
		JFinal.start("WebRoot", 8989, "/",5);
	}
}
