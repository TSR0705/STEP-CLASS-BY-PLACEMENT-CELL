import java.util.*;

public class TextCalculator {

    // Step b: Validate expression format
    public static boolean validateExpression(String expr) {
        int parenCount = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // Check valid characters: digits, operators, spaces, parentheses
            if (!( (c >= '0' && c <= '9') || c == '+' || c == '-' || c == '*' || c == '/' ||
                   c == '(' || c == ')' || c == ' ')) {
                return false;
            }

            // Parentheses check
            if (c == '(') parenCount++;
            if (c == ')') parenCount--;
            if (parenCount < 0) return false; // too many closing
        }
        return parenCount == 0;
    }

    // Step c: Parse numbers and operators from string
    public static Object[] parseExpression(String expr) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (c >= '0' && c <= '9') {
                int start = i;
                while (i < expr.length() && expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                    i++;
                }
                int num = Integer.parseInt(expr.substring(start, i));
                numbers.add(num);
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                operators.add(c);
            }
            i++;
        }
        return new Object[]{numbers, operators};
    }

    // Step d: Evaluate expression without parentheses (order of operations)
    public static int evaluateSimpleExpression(String expr, StringBuilder steps) {
        Object[] parsed = parseExpression(expr);
        List<Integer> numbers = (List<Integer>) parsed[0];
        List<Character> operators = (List<Character>) parsed[1];

        // Handle * and /
        for (int i = 0; i < operators.size();) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                int a = numbers.get(i);
                int b = numbers.get(i + 1);
                int result = (op == '*') ? (a * b) : (a / b);

                steps.append(a).append(" ").append(op).append(" ").append(b)
                     .append(" = ").append(result).append("\n");

                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
            } else {
                i++;
            }
        }

        // Handle + and -
        int result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            int b = numbers.get(i + 1);
            int before = result;
            if (op == '+') result += b;
            else result -= b;

            steps.append(before).append(" ").append(op).append(" ").append(b)
                 .append(" = ").append(result).append("\n");
        }

        return result;
    }

    // Step e: Handle parentheses
    public static int evaluateWithParentheses(String expr, StringBuilder steps) {
        while (expr.contains("(")) {
            int open = expr.lastIndexOf("(");
            int close = expr.indexOf(")", open);
            String inside = expr.substring(open + 1, close);
            int value = evaluateSimpleExpression(inside, steps);
            expr = expr.substring(0, open) + value + expr.substring(close + 1);
        }
        return evaluateSimpleExpression(expr, steps);
    }

    // Step f: Display calculation steps
    public static void displayCalculation(String expr) {
        System.out.println("\nExpression: " + expr);

        if (!validateExpression(expr)) {
            System.out.println("Invalid expression format!");
            return;
        }

        StringBuilder steps = new StringBuilder();
        int result = evaluateWithParentheses(expr, steps);

        System.out.println("Steps:");
        System.out.println(steps.toString());
        System.out.println("Final Result: " + result);
    }

    // Step g: Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of expressions:");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter expression " + (i + 1) + ":");
            String expr = sc.nextLine();
            displayCalculation(expr);
        }

        sc.close();
    }
}
