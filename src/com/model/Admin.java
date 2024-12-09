package com.model;

import com.auth.config.DB;
import com.view.AdminView;
import java.util.List;

public class Admin {
    
    public void showAllAccount(){
        // int a = DB.countData("akun");
        List<String[]> data = DB.showAllAccount();
        new AdminView().showAllAccount(data);
    }
    
}
