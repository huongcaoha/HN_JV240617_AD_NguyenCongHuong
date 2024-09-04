import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input ;
        while (true){
            System.out.println("Enter ISBN include 10 digit : ");
            input = scanner.nextLine().trim();
            if(input.matches("^[0-9]{10}$")){
                break ;
            }else {
                System.err.println("Input invalid !");
            }
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < input.length() ; i++){
            try {
                int tmp = input.charAt(i);
                stack.push(tmp);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        int sum = 0 ;
        for( int i = 1 ; i <= 10 ; i++){
            sum += (stack.pop()* i);
        }
        if(sum % 11 == 0){
            System.out.println(input + " is ISBN !");
        }else {
            System.out.println(input + " is not ISBN !");
        }

    }
}
