
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael
 */
public class KMP {
    File arquivo;
    KMP(File novo){
        arquivo = novo;
    }
    
    String buscar(String padrao) throws FileNotFoundException, IOException{
        String s = kmp(padrao);
        return s;
    }
    
    int[] prefixo(String padrao){
        int i = 1;
        int j = 0;
        int[] f = new int[padrao.length()];
        f[0] = 0;
        
        while(i < padrao.length()){
            if(padrao.substring(j,j+1).equals(padrao.substring(i,i+1))){
                f[i] = j+1;
                i++;
                j++;
            }
            else if(j > 0){
                j = f[j-1];
            }
            else{
                f[i] = 0;
                i++;
            }
        }
        return f;
    }
    
    String kmp(String padrao) throws FileNotFoundException, IOException{
        int[] f = prefixo(padrao);
        FileReader reader = new FileReader(arquivo);
        BufferedReader ler = new BufferedReader(reader);
        String linha = ler.readLine();
        String fim = linha;
        while(ler.readLine() != null){
            linha+=ler.readLine();
        }
        int i = 0;
        int j = 0;
        while (i < linha.length()){
            if(padrao.substring(j,j+1).equals(linha.substring(i,i+1))){
                if (j == padrao.length() - 1){
                    return "Padrao entrou no Texto";
                }
                i++;
                j++;
            }
            else if (j>0){
                j = f[j-1];
            }
            else{
                i++;
            }
        }
        return "Padrao nao encontrado no Texto";
    }
}
