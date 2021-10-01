package DynamicProgramming;

class library {
    String[] books;
    int no_of_books = 0;

    library() {
        this.books = new String[100];
        this.no_of_books = 0;
    }

    void addbooks(String book) {
         this.books[no_of_books]=book;
         no_of_books++;
        System.out.println(book+" has been addded");
    }
    void showavailablebooks(){
        System.out.println("Available books are:");
        for(String book:this.books){
            if(book==null){
                continue;
            }
            System.out.println("*"+book);
        }
    }
    void issuebooks(String book){
        for(int i=0; i<this.books.length; i++){
            if(this.books[i].equals(book)){
                System.out.println("the book has been issued");
                this.books[i]=null;
                return;
            }

        }
        System.out.println("this book not exist");
    }
    void returnbooks(String book){
        addbooks(book);
    }
}

public class onlinelibrary {
    public static void main(String[] args) {
        library centerlibrary=new library();
        centerlibrary.addbooks("think and grow rich");
        centerlibrary.addbooks("Mindset");
        centerlibrary.addbooks("alchemist");
        centerlibrary.showavailablebooks();
        centerlibrary.issuebooks("Mindset");
        centerlibrary.showavailablebooks();
        centerlibrary.returnbooks("Mindset");

    }
}

