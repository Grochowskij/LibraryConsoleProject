package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

//Author: Justin Grochowski
//This program will take you through the multiple paths of my library software. It can check out, return, and add books to the system

public class Project {
    
    public static void HomePage(){
        
        Scanner scnr = new Scanner(System.in);
        //Simply prints out the multiple paths
        PrintHome();
        //based on input number the program will take one of the paths
        int input = scnr.nextInt();
        while(input < 0 && input > 5){
            System.out.println("Enter a valid number.");
        }
        switch(input){
                case 1: 
                    PrintVerify();
                    break;
                case 2:
                    printReturn();
                    break;
                case 3:
                    printAddition();
                    break;
                case 4:
                    printOut();
                    break;
                case 5:
                    break;
        }
    }
    
    public static void printOut(){
        //Will print out who has books checked out along with the book they have checked out
        System.out.println("Enter list for all checkouts or enter a book title.");
        Scanner scnr = new Scanner(System.in);
        String input = scnr.next();
        //makes array list of the checked out books for easy searching
        ArrayList Checked = makeChecked();
        if(input.equals("list")){
            //prints out all of the checked out books
            for(int i = 0; i < Checked.size(); ++i){
                System.out.println(Checked.get(i));
            }
        } else {
            //prints out a specific checked out book with its information
            for(int i = 0; i < Checked.size(); ++i){
                if(((CheckedOuts)Checked.get(i)).getTitle().equals(input)){
                    System.out.println((CheckedOuts)Checked.get(i));
                }
            }
        }
    }
    
    public static void printAddition(){
        //adds to the catalogue of books
        ArrayList Books = makeCat();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the title of the book you would like to enter.");
        String Title = scnr.nextLine();
        System.out.println("Enter the Author of the book you would like to enter.");
        String Author = scnr.nextLine();
        Book b = new Book(Title,Author);
        Books.add(b);
        updateCat(Books);
    }
    
    public static void updateCat(ArrayList a){
        //rewrites the file to update it
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter("Books.txt");
        } catch (IOException e){
            System.out.println("Exception");
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        String input = "";
        for (int i = 0; i < a.size(); ++i){
            input = input + ((Book)a.get(i)).getTitle() + "\n" + ((Book)a.get(i)).getAuthor() + "\n";
        }
        printWriter.printf(input);
        printWriter.close();
    }
    
    public static void printReturn(){
        //makes the 2 array lists of books
        ArrayList Checks = makeChecked();
        ArrayList Cat = makeCat();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the book's title");
        String input = scnr.nextLine();
        //searches for the book to be returned
        for(int i = 0; i < Checks.size(); ++i){
            if(((CheckedOuts)Checks.get(i)).getTitle().toLowerCase().equals(input.toLowerCase())){
                //removes and adds the book from each list
                Book a = new Book(((CheckedOuts)Checks.get(i)).getTitle(),((CheckedOuts)Checks.get(i)).getAuthor());
                Cat.add(a);
                Checks.remove(i);
            }
        }
        //update both files
        updateCat(Cat);
        updateChecks(Checks);
    }
    
    public static ArrayList buildList(){
        //makes the list of admins using the admin file
        ArrayList a = new ArrayList();
        File f = new File("Admins.txt");
        Scanner fileRead = null;
        try{
            fileRead = new Scanner(f);
        } catch (IOException e){
            System.out.println("Exception");
        }
        
        while(fileRead.hasNextLine()){
            String one = fileRead.nextLine();
            String two = fileRead.nextLine();
            Admins buffer = new Admins(one,two);
            a.add(buffer);
        }
        
        return a;
    }
    
    public static ArrayList makeCat(){
        //makes the book catalogue array list using the Books text file
        ArrayList a = new ArrayList();
        
        File f = new File("Books.txt");
        Scanner fileRead = null;
        try{
            fileRead = new Scanner(f);
        } catch (IOException e){
            System.out.println("Exception");
        }
        
        while(fileRead.hasNextLine()){
            String one = fileRead.nextLine();
            String two = fileRead.nextLine();
            Book buffer = new Book(one,two);
            a.add(buffer);
        }
        
        return a;
    }
    
    public static ArrayList makeChecked(){
        //makes the Checked out books array list using the checked text file
        ArrayList a = new ArrayList();
        
        File f = new File("Checked.txt");
        Scanner fileRead = null;
        try{
            fileRead = new Scanner(f);
        } catch (IOException e){
            System.out.println("Exception");
        }
        
        while(fileRead.hasNextLine()){
            String one = fileRead.nextLine();
            String two = fileRead.nextLine();
            String three = fileRead.nextLine();
            CheckedOuts buffer = new CheckedOuts(one,two,three);
            a.add(buffer);
        }
        
        return a;
    }
    
    public static void Checkout(){
        //checks out a book from the catalogue
        ArrayList Catalogue = makeCat();
        ArrayList Checked = makeChecked();
        System.out.println("Enter name or author of the book you will check out, enter list for a list of books, or end to return to the main menu.");
        Scanner scnr = new Scanner(System.in);
        String input = scnr.nextLine();
        //returns to homepage if end
        if(input.equals("end")){
            HomePage();
        }
        //prints out list if prompted
        if(input.equals("list")){
            for(int i = 0; i < Catalogue.size(); ++i){
                System.out.println(Catalogue.get(i));
            }
        } else {
            //search for the book to be checked out
            for(int i = 0; i < Catalogue.size(); ++i){
                if(((Book)Catalogue.get(i)).getAuthor().equals(input)||((Book)Catalogue.get(i)).getTitle().equals(input)){
                    System.out.println("Enter a user to bind the book to. (The user doesn't matter you can add anything)");
                    input = scnr.next();
                    //convert a book to checked out book
                    String title = ((Book)Catalogue.get(i)).getTitle();
                    String author = ((Book)Catalogue.get(i)).getAuthor();
                    CheckedOuts temp = new CheckedOuts(title,author,input);
                    Checked.add(temp);
                    Catalogue.remove(i);
                    //updates files
                    updateCat(Catalogue);
                    updateChecks(Checked);
                } else {
                    //cant find the book so check checked out ones
                    for(int k = 0; k < Checked.size(); k++){
                        if(((CheckedOuts)Checked.get(k)).getAuthor().equals(input)||((CheckedOuts)Checked.get(k)).getTitle().equals(input)){
                            System.out.println("The book is already checked out. Try another book.");
                            Checkout();
                        //if not in either then it doesnt exist
                        } else {
                            System.out.println("The book does not exist in our library.");
                        }
                    }
                }
            }
        }
        System.out.println("Would you like to check out another book? Enter yes to do so, anything else will lead to the home page.");
        input = scnr.next();
        if(input.equals("yes")){
            Checkout();
        } else {
            HomePage();
        }
    }
    
    public static void updateChecks(ArrayList a){
        //updates the file using the array list and deleting all data in the file
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter("Checked.txt");
        } catch (IOException e){
            System.out.println("Exception");
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        String input = "";
        for (int i = 0; i < a.size(); ++i){
            input = input + ((CheckedOuts)a.get(i)).getTitle() + "\n" + ((CheckedOuts)a.get(i)).getAuthor() + "\n" + ((CheckedOuts)a.get(i)).getUser() + "\n";
        }
        printWriter.printf(input);
        printWriter.close();
    }
    
    public static void main(String[] args) {
        HomePage();
    }
    
    public static void PrintVerify(){
        //checks if the user is an admin so they can check out books
        System.out.println("Please enter an administrative login and password (JG, abc)");
        Scanner scnr = new Scanner(System.in);
        System.out.println("Username:");
        String username = scnr.next();
        System.out.println("Password:");
        String password = scnr.next();
        ArrayList a = buildList();
        boolean verify = false;
        for(int i = 0; i < a.size(); ++i){
            if(((Admins)a.get(i)).compareTo(username,password) == true){
                verify = true;
                break;
            }
        }
        if(verify == true){
            Checkout();
        } else {
            System.out.println("Your login information was incorrect try again");
            PrintVerify();
        }
    }
    
    public static void PrintHome(){
        //prints for the home page
        System.out.println("Thank you for using Justin's Library Manager!");
        System.out.println("Please enter a number for what action you would like to take.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("1 - Check out a book from the library");
        System.out.println("2 - Return a book for the library");
        System.out.println("3 - Make an addition to the library");
        System.out.println("4 - View Current checkouts");
        System.out.println("5 - Close");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Please enter a number for what action you would like to take.");
    }
}
