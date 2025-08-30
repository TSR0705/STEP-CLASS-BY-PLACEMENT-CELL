public class Student {
    // Private instance variables
    private String studentId;
    private String name;
    private double grade;
    private String course;

    // Default constructor
    public Student() {
        this.studentId = "";
        this.name = "";
        this.grade = 0.0;
        this.course = "";
    }

    // Parameterized constructor
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    // Getter and Setter methods
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Method to calculate letter grade
    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    // Method to display student info
    public void displayStudent() {
        System.out.println("Student Information:");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Grade: " + grade);
        System.out.println("Letter Grade: " + calculateLetterGrade());
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        // Creating student using default constructor
        Student s1 = new Student();
        // Setting values using setters
        s1.setStudentId("S101");
        s1.setName("Rahul Sharma");
        s1.setGrade(85.5);
        s1.setCourse("Computer Science");

        // Creating student using parameterized constructor
        Student s2 = new Student("S102", "Ananya Singh", 72.0, "Mathematics");

        // Demonstrating getter methods
        System.out.println("Getting student1 details using getters:");
        System.out.println("ID: " + s1.getStudentId());
        System.out.println("Name: " + s1.getName());
        System.out.println("Course: " + s1.getCourse());
        System.out.println("Grade: " + s1.getGrade());
        System.out.println("Letter Grade: " + s1.calculateLetterGrade());
        System.out.println("-----------------------------");

        // Displaying full information using displayStudent()
        s1.displayStudent();
        s2.displayStudent();
    }
}
