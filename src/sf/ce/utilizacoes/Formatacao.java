/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sf.ce.utilizacoes;

/**
 *
 */
public class Formatacao {
    public static String removeFormat(String num)
    {               
        //5.000.000,00
        String str1 = num.replaceAll("\\.", "");
           
        return str1.replace(",00", "");     
    }
}
