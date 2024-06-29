import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Member {
    private String name;
    private int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the library: " + book.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added to the library: " + member.getName());
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public Book findBook(String bookTitle) {
        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                return book;
            }
        }
        return null;
    }

    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void borrowBook(int memberId, String bookTitle) {
        Member member = findMember(memberId);
        Book book = findBook(bookTitle);

        if (member != null && book != null) {
            if (book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book borrowed successfully: " + book.getTitle());
            } else {
                System.out.println("Book not available for borrowing: " + book.getTitle());
            }
        } else {
            System.out.println("Member or book not found.");
        }
    }

    public void returnBook(int memberId, String bookTitle) {
        Member member = findMember(memberId);
        Book book = findBook(bookTitle);

        if (member != null && book != null) {
            if (!book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book returned successfully: " + book.getTitle());
            } else {
                System.out.println("Book is already available: " + book.getTitle());
            }
        } else {
            System.out.println("Member or book not found.");
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Adding some initial books and members for demonstration purposes
        library.addBook(new Book("The Will of Fire", "APJ AbdulKalam"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addMember(new Member("Dev", 1));
        library.addMember(new Member("Doe", 2));

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    int memberIdBorrow = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Book Title to Borrow: ");
                    String bookTitleBorrow = scanner.nextLine();
                    library.borrowBook(memberIdBorrow, bookTitleBorrow);
                    break;
                case 3:
                    System.out.print("Enter Member ID: ");
                    int memberIdReturn = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Book Title to Return: ");
                    String bookTitleReturn = scanner.nextLine();
                    library.returnBook(memberIdReturn, bookTitleReturn);
                    break;
                case 4:
                    System.out.println("Exiting Library Management System. Bye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}