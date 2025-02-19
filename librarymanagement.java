import java.util.*;

class Book {
    private static int lastBookId = 1000;  
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.bookId = ++lastBookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    public void displayBook() {
        System.out.println("🆔 Book ID: " + bookId);
        System.out.println("📖 Title: " + title);
        System.out.println("✍️ Author: " + author);
        System.out.println("📌 Status: " + (isIssued ? "Issued" : "Available"));
        System.out.println("-".repeat(30));
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("✅ Book added successfully!");
    }

    public void issueBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (!book.isIssued()) {
                    book.issueBook();
                    System.out.println("✅ Book issued successfully!");
                } else {
                    System.out.println("❌ Book is already issued!");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (book.isIssued()) {
                    book.returnBook();
                    System.out.println("✅ Book returned successfully!");
                } else {
                    System.out.println("❌ Book is already in library!");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("📚 No books in library!");
            return;
        }
        System.out.println("\n📚 LIBRARY CATALOG");
        System.out.println("=".repeat(30));
        for (Book book : books) {
            book.displayBook();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n📚 LIBRARY MANAGEMENT SYSTEM");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                    
                case 2:
                    System.out.print("Enter book ID: ");
                    int issueId = scanner.nextInt();
                    library.issueBook(issueId);
                    break;
                    
                case 3:
                    System.out.print("Enter book ID: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                    
                case 4:
                    library.displayAllBooks();
                    break;
                    
                case 5:
                    System.out.println("👋 Thank you for using Library Management System!");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}