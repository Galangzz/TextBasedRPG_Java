package com.auth.view;

import com.PrintDelay;
import java.util.Scanner;

public class SuperV {

    private String email;
    private static String password;
    private static final Scanner scanner = new Scanner(System.in);

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
        PrintDelay.print(
        """
        \nPlease Enter Your Email!! 
        Email: """);
        setEmail();


        PrintDelay.print(
        """
        \nPlease Enter Your Password!!
        Password: """);
        setPassword();
    }



}
