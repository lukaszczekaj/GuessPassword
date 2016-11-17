/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guesspassword;

/**
 *
 * @author lukasz
 */
public class PasswordBreak {

    private String password;

    public PasswordBreak() {
        
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String run() {
        return "1234";
    }

}
