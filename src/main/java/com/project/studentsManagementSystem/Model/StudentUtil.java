package com.project.studentsManagementSystem.Model;

import java.util.Random;

public class StudentUtil {
   public static String netidGenerator(Student obj) {
	   Random random=new Random();
	   String netid=obj.getFirstname();
	   for(int i=0;i<4;i++) {
		   netid+=random.nextInt(9);
	   }
	   return netid;
   }
 
   public static String passwordGenerator() {
	   Random random=new Random();
	   String passwordreference="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^?&*()";
	   String password="";
	   int n=passwordreference.length();
	   for(int i=0;i<8;i++) {
		   int num=random.nextInt(n-1);
		   password+=passwordreference.charAt(num);
	   }
	   return password;
   }
}
