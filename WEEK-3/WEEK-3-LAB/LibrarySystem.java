public class LibrarySystem {
    public static void main(String[] args) {
        // ----- Create Books -----
        Book[] books = new Book[3];
        books[0] = new Book("The Alchemist", "Paulo Coelho");
        books[1] = new Book("Java Programming", "James Gosling");
        books[2] = new Book("Clean Code", "Robert C. Martin");

        // ----- Create Members -----
        Member[] members = new Member[2];
        members[0] = new Member("Rahul Sharma");
        members[1] = new Member("Ananya Singh");

        // ----- Demonstrate Borrowing -----
        members[0].borrowBook(books[0]); // Rahul borrows The Alchemist
        members[0].borrowBook(books[1]); // Rahul borrows Java Programming
        members[1].borrowBook(books[0]); // Ananya tries to borrow unavailable book

        // ----- Display Info -----
        for (Book b : books) {
            b.displayBookInfo();
        }
        for (Member m : members) {
            m.displayMemberInfo();
        }

        // ----- Demonstrate Returning -----
        members[0].returnBook(books[0].getBookId(), books); // Rahul returns The Alchemist
        members[1].borrowBook(books[0]); // Now Ananya borrows it

        // ----- Final Status -----
        System.out.println("\n--- Final Status ---");
        for (Book b : books) {
            b.displayBookInfo();
        }
        for (Member m : members) {
            m.displayMemberInfo();
        }

        // ----- Statistics -----
        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}

// ===================== Book Class =====================
class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookCounter = 0;

    // Constructor
    public Book(String title, String author) {
        this.bookId = generateBookId();
        this.title = title;
        this.author = author;
        this.isAvailable = true;

        totalBooks++;
        availableBooks++;
    }

    // Methods
    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            System.out.println(title + " issued successfully.");
        } else {
            System.out.println(title + " is not available.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            System.out.println(title + " returned successfully.");
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Available: " + (isAvailable ? "Yes" : "No"));
    }

    // Getters
    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Static methods
    private static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

// ===================== Member Class =====================
class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;

    private static int memberCounter = 0;

    // Constructor
    public Member(String memberName) {
        this.memberId = generateMemberId();
        this.memberName = memberName;
        this.booksIssued = new String[5]; // Max 5 books issued
        this.bookCount = 0;
    }

    // Borrow Book
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            if (bookCount < booksIssued.length) {
                book.issueBook();
                booksIssued[bookCount++] = book.getBookId();
                System.out.println(memberName + " borrowed " + book.getBookId());
            } else {
                System.out.println(memberName + " cannot borrow more books!");
            }
        } else {
            System.out.println(book.getBookId() + " is not available for " + memberName);
        }
    }

    // Return Book
    public void returnBook(String bookId, Book[] books) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                // Shift array elements
                for (int j = i; j < bookCount - 1; j++) {
                    booksIssued[j] = booksIssued[j + 1];
                }
                booksIssued[--bookCount] = null;

                // Find the book object and mark as returned
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        break;
                    }
                }
                System.out.println(memberName + " returned book " + bookId);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(memberName + " does not have book " + bookId);
        }
    }

    // Display Member Info
    public void displayMemberInfo() {
        System.out.print("Member ID: " + memberId + ", Name: " + memberName + ", Books Issued: ");
        if (bookCount == 0) {
            System.out.print("None");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.print(booksIssued[i] + " ");
            }
        }
        System.out.println();
    }

    // Static method to generate unique ID
    private static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }
}
