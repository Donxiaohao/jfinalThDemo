package com.xxx.oam.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import com.jfinal.core.Const;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;

public class TestPropKitUpdate {
	private static SafeProperties safeProperties=null;
	public static void updateConfig(String fileName,String key,String value) {
		if(safeProperties==null) {
			new TestPropKitUpdate(fileName);
		}
		safeProperties.setProperty(key, value);
		FileOutputStream fos = null;
		try {
			String path = PathKit.class.getClassLoader().getResource(fileName).toURI().getPath();
			fos = new FileOutputStream(path);
			safeProperties.store(fos, "Copyright (c) Boxcode Studio");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				// 关闭流
				if(fos!=null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public TestPropKitUpdate(String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = getClassLoader().getResourceAsStream(fileName);		// properties.load(Prop.class.getResourceAsStream(fileName));
			if (inputStream == null) {
				throw new IllegalArgumentException("Properties file not found in classpath: " + fileName);
			}
			safeProperties = new SafeProperties();
			safeProperties.load(new InputStreamReader(inputStream, Const.DEFAULT_ENCODING));
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		}
		finally {
			if (inputStream != null) try {inputStream.close();} catch (IOException e) {LogKit.error(e.getMessage(), e);}
		}
	}
	
	private ClassLoader getClassLoader() {
		ClassLoader ret = Thread.currentThread().getContextClassLoader();
		return ret != null ? ret : getClass().getClassLoader();
	}
}
