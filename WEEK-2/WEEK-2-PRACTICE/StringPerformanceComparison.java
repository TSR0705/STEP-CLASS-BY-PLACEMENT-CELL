public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test 1: String concatenation performance
        long startTime = System.nanoTime();
        String result1 = concatenateWithString(1000);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        // Test 2: StringBuilder performance
        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        // Test 3: StringBuffer performance
        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        // Demonstrations
        System.out.println("\n=== STRINGBUILDER METHODS DEMO ===");
        demonstrateStringBuilderMethods();

        System.out.println("\n=== THREAD SAFETY DEMO ===");
        demonstrateThreadSafety();

        System.out.println("\n=== STRING COMPARISON DEMO ===");
        compareStringComparisonMethods();

        System.out.println("\n=== MEMORY EFFICIENCY DEMO ===");
        demonstrateMemoryEfficiency();
    }

    // Method using String concatenation (inefficient)
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    // Method using StringBuilder (efficient, not thread-safe)
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Method using StringBuffer (efficient, thread-safe)
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Demonstrating StringBuilder methods
    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");

        sb.append("!!!");
        System.out.println("After append: " + sb);

        sb.insert(6, "Java ");
        System.out.println("After insert: " + sb);

        sb.delete(6, 11);
        System.out.println("After delete: " + sb);

        sb.deleteCharAt(5);
        System.out.println("After deleteCharAt: " + sb);

        sb.reverse();
        System.out.println("After reverse: " + sb);
        sb.reverse(); // Restore

        sb.replace(0, 5, "Hi");
        System.out.println("After replace: " + sb);

        sb.setCharAt(0, 'h');
        System.out.println("After setCharAt: " + sb);

        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(100);
        System.out.println("After ensureCapacity(100): " + sb.capacity());

        sb.trimToSize();
        System.out.println("After trimToSize: " + sb.capacity());
    }

    // Demonstrating StringBuffer thread safety
    public static void demonstrateThreadSafety() {
        StringBuffer safeBuffer = new StringBuffer("Safe");
        StringBuilder unsafeBuilder = new StringBuilder("Unsafe");

        Runnable bufferTask = () -> {
            for (int i = 0; i < 5; i++) {
                safeBuffer.append("X");
            }
        };

        Runnable builderTask = () -> {
            for (int i = 0; i < 5; i++) {
                unsafeBuilder.append("Y");
            }
        };

        Thread t1 = new Thread(bufferTask);
        Thread t2 = new Thread(bufferTask);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuffer (thread-safe): " + safeBuffer);

        Thread u1 = new Thread(builderTask);
        Thread u2 = new Thread(builderTask);
        u1.start();
        u2.start();

        try {
            u1.join();
            u2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuilder (not thread-safe): " + unsafeBuilder);
    }

    // Comparing string comparison methods
    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("str1 == str2 : " + (str1 == str2)); // true, same pool reference
        System.out.println("str1 == str3 : " + (str1 == str3)); // false, different object

        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true, content
        System.out.println("str1.equalsIgnoreCase(\"hello\"): " + str1.equalsIgnoreCase("hello"));

        System.out.println("str1.compareTo(str3): " + str1.compareTo(str3)); // 0
        System.out.println("str1.compareToIgnoreCase(\"hello\"): " + str1.compareToIgnoreCase("hello"));
    }

    // Demonstrating memory efficiency
    public static void demonstrateMemoryEfficiency() {
        String s1 = "Java";  // goes into string pool
        String s2 = "Java";  // refers to same pooled object
        String s3 = new String("Java"); // new object outside pool

        System.out.println("s1 == s2 (pool check): " + (s1 == s2));
        System.out.println("s1 == s3 (pool vs new): " + (s1 == s3));
        System.out.println("s1.equals(s3): " + s1.equals(s3));

        StringBuilder sb = new StringBuilder();
        System.out.println("Initial capacity: " + sb.capacity());
        for (int i = 0; i < 100; i++) {
            sb.append("Data");
        }
        System.out.println("Capacity after appending: " + sb.capacity());
    }
}
