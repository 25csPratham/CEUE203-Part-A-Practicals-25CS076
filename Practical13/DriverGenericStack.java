import java.util.EmptyStackException;

public class DriverGenericStack {
    public static void main(String[] args) {
        System.out.println("=== 1. GenericStack<Integer> Test ===");
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        System.out.println("Stack size: " + intStack.size());
        System.out.println("Peek top element: " + intStack.peek());
        System.out.println("Popped: " + intStack.pop());
        System.out.println("Popped: " + intStack.pop());
        System.out.println("Remaining size: " + intStack.size());

        System.out.println("\n=== 2. Reusing GenericStack<String> Test ===");
        GenericStack<String> strStack = new GenericStack<>();
        strStack.push("First");
        strStack.push("Second");
        strStack.push("Third");
        System.out.println("Popped from String stack: " + strStack.pop());
        System.out.println("Popped from String stack: " + strStack.pop());
        System.out.println("Popped from String stack: " + strStack.pop());

        System.out.println("\n=== 3. Testing Empty Stack Handling ===");
        try {
            System.out.println("Attempting pop on empty stack...");
            strStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Caught Expected Exception: EmptyStackException successfully handled!");
        }
    }
}