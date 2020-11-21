package project;

public class Book {
    private String title;
    private String author;
    
    public Book(){
        title = null;
        author = null;
    }
    
    public Book(String a, String b){
        title = a;
        author = b;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String toString(){
        return title + "\n" + author + "\n";
    }
}
