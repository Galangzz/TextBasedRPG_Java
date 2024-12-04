package com.auth.view;

import java.util.Scanner;

public class SuperV {

    private String email;
    private static String password;
    private static Scanner scanner = new Scanner(System.in);

    public SuperV(){
        // this.email = email;
        // this.password = password;
    }  

    public void setEmail(){
        this.email = scanner.nextLine();
    }

    public void setPassword(){
        password = scanner.nextLine();
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void view(){
        System.out.print(
        """
        \nPlease Enter Your Email!! 
        Email: """);
        setEmail();


        System.out.print(
        """
        \nPlease Enter Your Password!!
        Password: """);
        setPassword();
    }



}
