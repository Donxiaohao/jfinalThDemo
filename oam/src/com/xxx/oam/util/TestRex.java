package com.xxx.oam.util;

public class TestRex {
	 public static String makeQueryStringAllRegExp(String str) {
		  
		        return str.replace("\\", "\\\\").replace("*", "\\*")
		                .replace("+", "\\+").replace("|", "\\|")
		            .replace("{", "\\{").replace("}", "\\}")
		                 .replace("(", "\\(").replace(")", "\\)")
				.replace("^", "\\^").replace("$", "\\$").replace("[", "\\[").replace("]", "\\]").replace("?", "\\?")
				.replace(",", "\\,").replace(".", "\\.").replace("&", "\\&")
				.replace("<", "&lt").replace(">", "&gt");
		        
	}
	 
	 public static String testA(String str) {
		  
	        return str.replace("'", "\\'").replace("\"", "\\\"");
	        
	 }
	 
	 
	public static void main(String[] args) {
		String tet="111=222"
				+ "333=444"
				+ "555= 666";
		System.out.println(tet);
	}
}
