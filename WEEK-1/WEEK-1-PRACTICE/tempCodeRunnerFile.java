public class StringArrays {

    // Method to find the longest name
    public static String findLongestName(String[] names) {
        if (names == null || names.length == 0) {
            return "";
        }
        String longest = names[0];
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        return longest;
    }

    // Method to count names starting with a given letter (case-insensitive)
    public static int countNamesStartingWith(String[] names, char letter) {
        int count = 0;
        for (String name : names) {
            if (!name.isEmpty() && Character.toLowerCase(name.charAt(0)) == Character.toLowerCase(letter)) {
                count++;
            }
        }
        return count;
    }

    // Method to format all names to "Last, First"
    public static String[] formatNames(String[] names) {
        String[] formatted = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String[] parts = names[i].split(" ");
            if (parts.length >= 2) {
                String first = parts[0];
                String last = parts[parts.length - 1]; // Handles middle names
                formatted[i] = last + ", " + first;
            } else {
                formatted[i] = names[i]; // If only one name exists
            }
        }
        return formatted;
    }

    public static void main(String[] args) {
        String[] students = {"John Smith", "Alice Johnson", "Bob Brown", "Carol Davis", "David Wilson"};

        // Test findLongestName
        String longestName = findLongestName(students);
        System.out.println("Longest Name: " + longestName);

        // Test countNamesStartingWith
        char letter = 'A';
        int count = countNamesStartingWith(students, letter);
        System.out.println("Number of names starting with '" + letter + "': " + count);

        // Test formatNames
        String[] formattedNames = formatNames(students);
        System.out.println("\nFormatted Names:");
        for (String name : formattedNames) {
            System.out.println(name);
        }
    }
}
