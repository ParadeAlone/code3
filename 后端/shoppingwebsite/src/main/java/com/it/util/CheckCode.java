package com.it.util;
/**
 * 验证码生成类
 */
import java.util.Random;

public class CheckCode {

	public String getCheckCode(){
		Random random = new Random();
		String sRand="";
		for (int i=0;i<6;i++){
	    String rand=String.valueOf(random.nextInt(10));
	    sRand+=rand;
		}
	    return sRand;
	}
	
	public String OrderNumber(){
		Random random = new Random();
		String sRand="DD";
		for (int i=0;i<10;i++){
		String rand=String.valueOf(random.nextInt(10));
	    sRand+=rand;
		}
	    return sRand;
	}

    public String getYzm(){
        Random random = new Random();
        String sRand="";
        for (int i=0;i<6;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand;
        }
        return sRand;
    }

    public String getRandom(){
        String sRand="";
        String lettersAndNumbers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char letterOrNumber = lettersAndNumbers.charAt(random.nextInt(lettersAndNumbers.length()));
            sRand+=letterOrNumber;
        }
        return sRand;
    }

    public static void main(String[] args) {
        System.out.println(new CheckCode().getRandom());
    }


}
