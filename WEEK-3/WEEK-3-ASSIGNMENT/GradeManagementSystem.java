import java.util.Arrays;

public class GradeManagementSystem {
    public static void main(String[] args) {
        // ====== School-wide static setup ======
        Student.setSchoolName("Nova International School");
        Student.setPassPercentage(40.0); // pass if overall >= 40%
        // Optional custom grading scale: "Label:thresholdPercent"
        Student.setGradingScale(new String[]{"A:90", "B:80", "C:70", "D:60", "F:0"});

        // ====== Subjects (catalog) ======
        Subject math = new Subject("SUB001", "Mathematics", 4, "Dr. Rao");
        Subject sci  = new Subject("SUB002", "Science", 4, "Dr. Iyer");
        Subject eng  = new Subject("SUB003", "English", 3, "Ms. Kapoor");
        Subject hist = new Subject("SUB004", "History", 2, "Mr. Banerjee");
        Subject comp = new Subject("SUB005", "Computer Science", 4, "Ms. Gupta");

        Subject[] class9Subjects  = {math, sci, eng, hist};
        Subject[] class10Subjects = {math, sci, eng, comp};

        // ====== Students (multiple classes) ======
        Student s1 = new Student("S001", "Aarav Mehta",  "Class 9",  subjectNames(class9Subjects), 4);
        Student s2 = new Student("S002", "Diya Kumar",   "Class 9",  subjectNames(class9Subjects), 4);
        Student s3 = new Student("S003", "Ishaan Verma", "Class 10", subjectNames(class10Subjects), 4);
        Student s4 = new Student("S004", "Sara Ali",     "Class 10", subjectNames(class10Subjects), 4);
        Student s5 = new Student("S005", "Rohan Das",    "Class 10", subjectNames(class10Subjects), 4);

        Student[] all = {s1, s2, s3, s4, s5};

        // ====== Add marks (multiple assessments) ======
        // Add marks per subject; the system stores each call as the next assessment for that subject
        // Class 9
        s1.addMarks("Mathematics", 88); s1.addMarks("Science", 82); s1.addMarks("English", 75); s1.addMarks("History", 68);
        s1.addMarks("Mathematics", 92); s1.addMarks("Science", 79); s1.addMarks("English", 80); s1.addMarks("History", 74);

        s2.addMarks("Mathematics", 61); s2.addMarks("Science", 55); s2.addMarks("English", 70); s2.addMarks("History", 64);
        s2.addMarks("Mathematics", 72); s2.addMarks("Science", 63); s2.addMarks("English", 77); s2.addMarks("History", 58);

        // Class 10
        s3.addMarks("Mathematics", 95); s3.addMarks("Science", 90); s3.addMarks("English", 85); s3.addMarks("Computer Science", 94);

        s4.addMarks("Mathematics", 78); s4.addMarks("Science", 81); s4.addMarks("English", 74); s4.addMarks("Computer Science", 88);
        s4.addMarks("Mathematics", 82); s4.addMarks("Science", 79); s4.addMarks("English", 76); s4.addMarks("Computer Science", 90);

        s5.addMarks("Mathematics", 45); s5.addMarks("Science", 52); s5.addMarks("English", 60); s5.addMarks("Computer Science", 58);

        // ====== Compute GPA for each student ======
        for (Student s : all) {
            s.calculateGPA();
        }

        // ====== Individual Report Cards ======
        System.out.println("\n========== REPORT CARDS ==========");
        for (Student s : all) {
            s.generateReportCard();
        }

        // ====== Class-Level & School-Level Reports ======
        System.out.println("\n========== CLASS & SCHOOL REPORTS ==========");
        System.out.printf("Class 9 Average: %.2f%%%n", Student.calculateClassAverage(filterByClass(all, "Class 9")));
        System.out.printf("Class 10 Average: %.2f%%%n", Student.calculateClassAverage(filterByClass(all, "Class 10")));
        System.out.printf("School-wide Average: %.2f%%%n", Student.calculateClassAverage(all));

        System.out.println("\nTop 3 Performers (School-wide):");
        Student[] top3 = Student.getTopPerformers(all, 3);
        for (int i = 0; i < top3.length; i++) {
            if (top3[i] != null) {
                System.out.printf("%d) %s (%s) - GPA: %.2f, Overall: %.2f%%%n",
                        i + 1, top3[i].getStudentName(), top3[i].getClassName(),
                        top3[i].getGpa(), top3[i].getOverallPercentage());
            }
        }

        System.out.println();
        Student.generateSchoolReport(all, new String[]{"Class 9", "Class 10"});
    }

    private static String[] subjectNames(Subject[] subs) {
        String[] names = new String[subs.length];
        for (int i = 0; i < subs.length; i++) names[i] = subs[i].getSubjectName();
        return names;
    }

    private static Student[] filterByClass(Student[] students, String className) {
        int count = 0;
        for (Student s : students) if (s.getClassName().equalsIgnoreCase(className)) count++;
        Student[] out = new Student[count];
        int idx = 0;
        for (Student s : students) if (s.getClassName().equalsIgnoreCase(className)) out[idx++] = s;
        return out;
    }
}

/* ===================== Subject ===================== */
class Subject {
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
    public String getSubjectCode()  { return subjectCode; }
    public String getSubjectName()  { return subjectName; }
    public int getCredits()         { return credits; }
    public String getInstructor()   { return instructor; }
}

/* ===================== Student ===================== */
class Student {
    // ----- Instance members -----
    private String studentId;
    private String studentName;
    private String className;
    private String[] subjects;             // Subject names
    private double[][] marks;              // [assessmentIndex][subjectIndex]
    private int[] marksCountPerSubject;    // how many marks recorded per subject
    private double gpa;                    // on 4.0 scale
    private double overallPercentage;      // cached after calculation

    // ----- Static members (shared) -----
    private static int totalStudents = 0;
    private static String schoolName = "Unnamed School";
    private static String[] gradingScale = {"A", "B", "C", "D", "F"};
    private static double[] thresholds = {90, 80, 70, 60, 0}; // aligned with gradingScale
    private static double passPercentage = 40.0;

    // ----- Config -----
    private static final int MAX_ASSESSMENTS = 8;

    public Student(String studentId, String studentName, String className, String[] subjects, int expectedAssessments) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = Arrays.copyOf(subjects, subjects.length);
        int rows = Math.min(expectedAssessments, MAX_ASSESSMENTS);
        this.marks = new double[rows][subjects.length];
        for (int r = 0; r < rows; r++) Arrays.fill(this.marks[r], -1.0); // -1 means empty slot
        this.marksCountPerSubject = new int[subjects.length];
        this.gpa = 0.0;
        this.overallPercentage = 0.0;
        totalStudents++;
    }

    // ---- Core data ops ----
    public boolean addMarks(String subject, double score) {
        int si = subjectIndex(subject);
        if (si == -1) {
            System.out.println("[WARN] Subject \"" + subject + "\" not found for " + studentName);
            return false;
        }
        if (score < 0 || score > 100) {
            System.out.println("[WARN] Invalid score " + score + " (0-100) for " + studentName);
            return false;
        }
        int count = marksCountPerSubject[si];
        if (count >= marks.length) {
            System.out.println("[WARN] Max assessments reached for subject " + subject + " for " + studentName);
            return false;
        }
        marks[count][si] = score;
        marksCountPerSubject[si] = count + 1;
        return true;
    }

    public void calculateGPA() {
        // overall percentage = average of subject averages
        double sumSubjectAverages = 0.0;
        int subjectsWithMarks = 0;

        for (int s = 0; s < subjects.length; s++) {
            double avg = averageForSubject(s);
            if (!Double.isNaN(avg)) {
                sumSubjectAverages += avg;
                subjectsWithMarks++;
            }
        }

        overallPercentage = (subjectsWithMarks == 0) ? 0.0 : (sumSubjectAverages / subjectsWithMarks);
        // Map percentage to 4.0 scale via letter grade
        String letter = letterFor(overallPercentage);
        this.gpa = letterToGPA(letter);
    }

    public void generateReportCard() {
        System.out.println("\n---- Report Card : " + studentName + " (" + studentId + "), " + className + " ----");
        System.out.println("School: " + schoolName);
        for (int s = 0; s < subjects.length; s++) {
            double avg = averageForSubject(s);
            String subj = subjects[s];
            if (Double.isNaN(avg)) {
                System.out.printf("%-20s : No marks yet%n", subj);
            } else {
                System.out.printf("%-20s : Avg = %.2f%%, Grade = %s%n", subj, avg, letterFor(avg));
            }
        }
        System.out.printf("Overall %%     : %.2f%%%n", getOverallPercentage());
        System.out.printf("GPA (4.0)      : %.2f%n", gpa);
        System.out.println("Promotion Eligible: " + (checkPromotionEligibility() ? "YES" : "NO"));
    }

    public boolean checkPromotionEligibility() {
        // simple rule: overall >= passPercentage and no subject average below passPercentage
        if (overallPercentage < passPercentage) return false;
        for (int s = 0; s < subjects.length; s++) {
            double avg = averageForSubject(s);
            if (!Double.isNaN(avg) && avg < passPercentage) return false;
        }
        return true;
    }

    // ---- Helpers ----
    private int subjectIndex(String subject) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) return i;
        }
        return -1;
    }

    private double averageForSubject(int sIndex) {
        if (marksCountPerSubject[sIndex] == 0) return Double.NaN;
        double sum = 0.0;
        for (int r = 0; r < marksCountPerSubject[sIndex]; r++) sum += marks[r][sIndex];
        return sum / marksCountPerSubject[sIndex];
    }

    private static String letterFor(double percent) {
        for (int i = 0; i < thresholds.length; i++) {
            if (percent >= thresholds[i]) return gradingScale[i];
        }
        return gradingScale[gradingScale.length - 1]; // default last
    }

    private static double letterToGPA(String letter) {
        switch (letter) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default:  return 0.0; // F
        }
    }

    // ---- Static analytics & reporting ----
    public static double calculateClassAverage(Student[] students) {
        if (students == null || students.length == 0) return 0.0;
        double sum = 0.0;
        int counted = 0;
        for (Student s : students) {
            if (s == null) continue;
            // ensure GPA/overall computed
            s.calculateGPA();
            sum += s.getOverallPercentage();
            counted++;
        }
        return counted == 0 ? 0.0 : (sum / counted);
    }

    public static Student[] getTopPerformers(Student[] students, int count) {
        // simple selection on GPA then overall%
        Student[] copy = Arrays.copyOf(students, students.length);
        // compute if needed
        for (Student s : copy) if (s != null) s.calculateGPA();
        Arrays.sort(copy, (a, b) -> {
            if (a == null && b == null) return 0;
            if (a == null) return 1;
            if (b == null) return -1;
            int g = Double.compare(b.getGpa(), a.getGpa());
            if (g != 0) return g;
            return Double.compare(b.getOverallPercentage(), a.getOverallPercentage());
        });
        if (count > copy.length) count = copy.length;
        Student[] top = new Student[count];
        for (int i = 0; i < count; i++) top[i] = copy[i];
        return top;
    }

    public static void generateSchoolReport(Student[] allStudents, String[] classNames) {
        System.out.println("===== SCHOOL REPORT : " + schoolName + " =====");
        System.out.println("Total Students: " + totalStudents);
        double schoolAvg = calculateClassAverage(allStudents);
        System.out.printf("School Average: %.2f%%%n", schoolAvg);

        String bestClass = "";
        double bestAvg = -1;
        for (String cls : classNames) {
            double avg = calculateClassAverage(filterByClass(allStudents, cls));
            System.out.printf("Class %-8s Avg: %.2f%%%n", cls, avg);
            if (avg > bestAvg) { bestAvg = avg; bestClass = cls; }
        }
        System.out.println("Best Performing Class: " + bestClass + " (" + String.format("%.2f", bestAvg) + "%)");

        // Grade distribution
        int a=0,b=0,c=0,d=0,f=0;
        for (Student s : allStudents) {
            if (s == null) continue;
            s.calculateGPA();
            String L = letterFor(s.getOverallPercentage());
            switch (L) {
                case "A": a++; break;
                case "B": b++; break;
                case "C": c++; break;
                case "D": d++; break;
                default : f++; break;
            }
        }
        System.out.println("Grade Distribution (overall): A=" + a + " B=" + b + " C=" + c + " D=" + d + " F=" + f);
    }

    // helper for report
    private static Student[] filterByClass(Student[] students, String className) {
        int count = 0;
        for (Student s : students) if (s != null && s.getClassName().equalsIgnoreCase(className)) count++;
        Student[] out = new Student[count];
        int idx = 0;
        for (Student s : students) if (s != null && s.getClassName().equalsIgnoreCase(className)) out[idx++] = s;
        return out;
    }

    // ---- Static configuration setters ----
    public static void setSchoolName(String name) { schoolName = name; }
    public static String getSchoolName() { return schoolName; }

    public static void setPassPercentage(double percent) { passPercentage = percent; }
    public static double getPassPercentage() { return passPercentage; }

    public static void setGradingScale(String[] scaleSpecs) {
        // expects entries like "A:90", "B:80", ...
        String[] labels = new String[scaleSpecs.length];
        double[] th = new double[scaleSpecs.length];
        for (int i = 0; i < scaleSpecs.length; i++) {
            String[] parts = scaleSpecs[i].split(":");
            labels[i] = parts[0].trim();
            th[i] = Double.parseDouble(parts[1].trim());
        }
        gradingScale = labels;
        thresholds = th;
    }

    // ---- Getters ----
    public String getStudentId()    { return studentId; }
    public String getStudentName()  { return studentName; }
    public String getClassName()    { return className; }
    public String[] getSubjects()   { return Arrays.copyOf(subjects, subjects.length); }
    public double getGpa()          { return gpa; }
    public double getOverallPercentage() { return overallPercentage; }

    // ---- Static getters ----
    public static int getTotalStudents() { return totalStudents; }
}

/* ===================== Notes =====================
 * - Instance members (e.g., marks, subjects, className, GPA) are unique per Student.
 * - Static members (schoolName, gradingScale, passPercentage, totalStudents) are shared across all Students.
 * - addMarks() automatically appends to the next assessment slot for that subject.
 * - calculateGPA() computes an overall percentage (mean of subject averages) and maps it to a 4.0 GPA via the grading scale.
 * - Reports include class averages, school average, ranking of top performers, and grade distribution.
 */
