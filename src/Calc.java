import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Calc {
    double a;
    double b;
    char operation;
    void answer() {
        switch (operation) {
            case '*': System.out.println(a * b); break;
            case '-': System.out.println(a - b); break;
            case '+': System.out.println(a + b); break;
            case '/': System.out.println(a / b); break;
        }
    }

    static Scanner s = new Scanner(System.in);

    public Calc(double a, double b, char operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    public static void main(String[] args) /*throws opEr*/ {
        String line = insert();
        checkLine(line);
        String stAr [] = line.split(" ");
        Calc calc = new Calc(Double.parseDouble(stAr[0]), Double.parseDouble(stAr[2]), stAr[1].charAt(0));
        calc.answer();
//после выполнения метода должна быть заданы а б и оператор);
    }

    private static String insert() {
        String tmp = "";
        Scanner s = new Scanner(System.in);
        while (tmp.equals("")) {
            System.out.println("Would you like to read from file or insert by keyboard? please enter 'F' or 'K' ");
            tmp = s.nextLine();
            if (tmp.equals(""))
                System.out.println("empty insert, try again");
            else {
                char c = tmp.charAt(0);
                switch (c) {
                    case ('F'): {
                        System.out.println("From file");
                        return readFromFile();
                    }
                    case ('K'): {
                        System.out.println("From keyboard");
                        return readFromKeyboard();
                    }
                    default: {
                        System.out.println("wrong insert, try again");
                        tmp ="";
                    }
                }
            }
        }
        return "";
    }

    private static String readFromKeyboard () {
        System.out.println("Insert task");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    private static String readFromFile () {
        try {
            System.out.println("Please insert file path");
            Scanner scnFile = new Scanner(System.in);
            String filePath = scnFile.nextLine();
            FileReader fileReader = new FileReader(filePath);
            Scanner scn = new Scanner(fileReader);
            String tmp = scn.nextLine();
            System.out.println(tmp);
            return tmp;
        } catch (Exception e) {
            System.out.println("Some error, try again");
        }
        return "";
    }

    private static void checkLine(String s) {
        String[] ar = s.split(" ");
        try {
            double a = Double.parseDouble(ar[0]);
            //System.out.println(a);
            double b = Double.parseDouble(ar[2]);
            //System.out.println(b);
            char op = ar[1].charAt(0);
            //System.out.println(op);
        } catch (NumberFormatException e) {
            System.out.println("Error! Not number");
        }
        Pattern pattern = Pattern.compile("[-+/*]");
        if (!ar[1].matches(pattern.pattern()))
            try {
                throw new opEr("Operation Error!");
            } catch (Calc.opEr ignored) {}
        if (Double.parseDouble(ar[2]) == 0 && ar[1].equals("/")) {
            try {
                throw new zeroEx("Error! Division by zero");
            } catch (Calc.zeroEx ignored) {}
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