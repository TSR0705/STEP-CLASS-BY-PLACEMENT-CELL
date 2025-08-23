import java.util.*;

public class FileOrganizer {

    // Structure to store file details
    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String newName;
        String subCategory;
        boolean valid;
    }

    // Step b: Extract file components
    public static FileInfo extractFileInfo(String filename) {
        FileInfo info = new FileInfo();
        info.originalName = filename;

        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            info.valid = false;
            return info;
        }

        info.baseName = filename.substring(0, dotIndex);
        info.extension = filename.substring(dotIndex + 1).toLowerCase();

        // Validate name (letters, numbers, underscore, dash only)
        info.valid = true;
        for (int i = 0; i < info.baseName.length(); i++) {
            char c = info.baseName.charAt(i);
            if (!(c >= 'a' && c <= 'z') &&
                !(c >= 'A' && c <= 'Z') &&
                !(c >= '0' && c <= '9') &&
                c != '_' && c != '-') {
                info.valid = false;
                break;
            }
        }
        return info;
    }

    // Step c: Categorize files by extension
    public static void categorize(FileInfo info) {
        switch (info.extension) {
            case "txt": case "doc": case "pdf":
                info.category = "Document";
                break;
            case "jpg": case "png": case "gif":
                info.category = "Image";
                break;
            case "mp3": case "wav":
                info.category = "Audio";
                break;
            case "mp4": case "mkv":
                info.category = "Video";
                break;
            case "java": case "py": case "c":
                info.category = "Code";
                break;
            default:
                info.category = "Unknown";
        }
    }

    // Step d: Generate new file names
    public static String generateNewName(FileInfo info, int counter) {
        StringBuilder sb = new StringBuilder();
        sb.append(info.category.toUpperCase());
        sb.append("_");
        sb.append(System.currentTimeMillis() / 1000); // timestamp
        if (counter > 1) sb.append("_").append(counter);
        sb.append(".").append(info.extension);
        info.newName = sb.toString();
        return info.newName;
    }

    // Step e: Simulate content-based analysis (simplified)
    public static void analyzeContent(FileInfo info, String content) {
        if (info.category.equals("Document")) {
            if (content.toLowerCase().contains("resume")) info.subCategory = "Resume";
            else if (content.toLowerCase().contains("report")) info.subCategory = "Report";
            else info.subCategory = "General Document";
        } else if (info.category.equals("Code")) {
            if (content.toLowerCase().contains("class")) info.subCategory = "Java Source";
            else info.subCategory = "General Code";
        } else {
            info.subCategory = "N/A";
        }
    }

    // Step f: Display report
    public static void displayReport(List<FileInfo> files) {
        System.out.println("\n--- File Organization Report ---");
        System.out.printf("%-20s %-12s %-25s %-15s\n", "Original Name", "Category", "New Suggested Name", "SubCategory");
        System.out.println("--------------------------------------------------------------------------------");

        Map<String, Integer> categoryCounts = new HashMap<>();
        int invalidCount = 0, unknownCount = 0;

        for (FileInfo f : files) {
            System.out.printf("%-20s %-12s %-25s %-15s\n", 
                              f.originalName, f.category, f.newName, f.subCategory);

            categoryCounts.put(f.category, categoryCounts.getOrDefault(f.category, 0) + 1);
            if (!f.valid) invalidCount++;
            if (f.category.equals("Unknown")) unknownCount++;
        }

        System.out.println("\n--- Category Counts ---");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nFiles needing attention:");
        System.out.println("Invalid file names: " + invalidCount);
        System.out.println("Unknown file types: " + unknownCount);
    }

    // Step g: Generate batch rename commands
    public static void generateRenameCommands(List<FileInfo> files) {
        System.out.println("\n--- Batch Rename Commands ---");
        for (FileInfo f : files) {
            if (f.valid && !f.category.equals("Unknown")) {
                System.out.println("rename \"" + f.originalName + "\" -> \"" + f.newName + "\"");
            }
        }
    }

    // Step h: Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();

        System.out.println("Enter file names (end with empty line):");
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;

            FileInfo info = extractFileInfo(line);

            if (info.valid) {
                categorize(info);
                generateNewName(info, files.size() + 1);
                // Fake content for testing
                String content = (info.extension.equals("txt") ? "This is a resume file" : "");
                analyzeContent(info, content);
            } else {
                info.category = "Invalid";
                info.newName = "N/A";
                info.subCategory = "N/A";
            }
            files.add(info);
        }

        displayReport(files);
        generateRenameCommands(files);
    }
}
