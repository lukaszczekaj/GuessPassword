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
public class GuessPassword {

    public static void main(String[] args) {
        PasswordBreak pb =new PasswordBreak();
        
        pb.setPassword("adawd");
        
        System.out.println(pb.run());
        
    }

}
