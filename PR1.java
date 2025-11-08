public class PR1 {
    static int steps1 = 0; // Count recursive calls
    static int steps2 = 0; // Count iterative steps

    public static int fibonacci(int n) {
        steps1++; // Count each call
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
        
    public static int fibonacci_NR(int n) {
        steps2 = 0; // Reset for each call
        int a = 0, b = 1, c = 0;
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            steps2++; // Count each loop as a step
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 5;
        int resultR = fibonacci(n);
        System.out.println("Recursive: Fibonacci of " + n + ": " + resultR);
        System.out.println("Recursive: Steps taken: " + steps1);

        int resultNR = fibonacci_NR(n);
        System.out.println("Iterative: Fibonacci of " + n + ": " + resultNR);
        System.out.println("Iterative: Steps taken: " + steps2);
    }
}
