/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Owner
 */
public class CheckedOuts {
    private String title;
    private String author;
    private String user;
    
    public CheckedOuts(){
        title = null;
        author = null;
        user = null;
    }
    
    public CheckedOuts(String a, String b, String c){
        title = a;
        author = b;
        user = c;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getUser(){
        return user;
    }
    
    public String toString(){
        return title + "\n" + author + "\n" + user + "\n";
    }
}
