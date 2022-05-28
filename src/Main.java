import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws opEr {
        Scanner s = new Scanner(System.in);
        double a = 0;
        double b = 0;
        boolean bWasChanged= false;
        String str = s.nextLine();
        String [] ar = str.split(" ");

        try {
            a = Double.parseDouble(ar[0]);
            b = Double.parseDouble(ar[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error! Not number");
            return;
        }

        Pattern pattern = Pattern.compile("[-+/*]");
        if(!ar[1].matches(pattern.pattern()))
            try {
                throw new opEr("Operation Error!");
            } catch (Main.opEr ignored) {
                return;
            }

        if (b==0 && ar[1].equals("/")) {
            try {
                throw new zeroEx("Error! Division by zero");
            } catch (Main.zeroEx ignored) {return;}
        }

        switch (ar[1]) {
            case "*": System.out.println(a * b); break;
            case "-": System.out.println(a - b); break;
            case "+": System.out.println(a + b); break;
            case "/": System.out.println(a / b); break;
        }


    }

    static class zeroEx extends Exception{
        public zeroEx(String s) {
            System.out.println(s);
        }
    }
    static class opEr extends Exception{
        public opEr(String s) {
            System.out.println(s);
        }
    }
}



