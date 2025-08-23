import java.util.Scanner;

public class lengthstring {
    public static int[] findAllIndexes(String text, char ch) {
// The count is used to find the number of occurrences of the character
int count = 0;
for (int i = 0; i < text.length(); i++) {
if (text.charAt(i) == ch) {
count++;
}
}
// Create an array to store the indexes of the character
int[] indexes = new int[count];
int j = 0;
for (int i = 0; i < text.length(); i++) {
if (text.charAt(i) == ch) {
indexes[j] = i;
j++;
}
}
return indexes;
}
    
    public static void main (String[]args){
        // Take user input for Text and Character to check Occurrences
        System.out.println("Enter your Name:");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        System.out.println("Your Name is:"+text);
        System.out.print("Enter a character to find the occurrences: ");
char ch = sc.nextLine().charAt(0);
int[] indexes = findAllIndexes(text, ch);
// Display the occurrences of the character
System.out.println("Indexes of the character '" + ch + "': ");
for (int i = 0; i < indexes.length; i++) {
System.out.print(indexes[i] + " ");
}
 
       
    }
}

