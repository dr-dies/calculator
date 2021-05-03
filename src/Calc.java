import java.util.Scanner;

public class Calc {
    public static void main(String[] args){
        Parser expr = new Parser();
        expr.getExpr();
        int a = expr.getNumberA();
        int b = expr.getNumberB();
        String operand = expr.getOperand();
        Calculate calculator = new Calculate();
        if(expr.isLatin()==1) {
            System.out.println("Result: " + calculator.action(a, b, operand));
        }
        else {
            System.out.println("Result: " + expr.toLatin(calculator.action(a, b, operand)));
        }
        //System.out.println(expr.getNumberA());
        //System.out.println(expr.getNumberB());
        //System.out.println(expr.getOperand());
    }

}
class Parser {
    private String expr;
    private int latin = 0;
    private int posOp = 0;

    public static String romanDigit(int n, String one, String five, String ten){

        if(n >= 1)
        {
            if(n == 1)
            {
                return one;
            }
            else if (n == 2)
            {
                return one + one;
            }
            else if (n == 3)
            {
                return one + one + one;
            }
            else if (n==4)
            {
                return one + five;
            }
            else if (n == 5)
            {
                return five;
            }
            else if (n == 6)
            {
                return five + one;
            }
            else if (n == 7)
            {
                return five + one + one;
            }
            else if (n == 8)
            {
                return five + one + one + one;
            }
            else if (n == 9)
            {
                return one + ten;
            }

        }
        return "";
    }


    public String toLatin(int num) {
        String result = "";
        if(num<0) {
            result = "Negative value ";
        }
        if(num==0){
            result = "Nulla";
        }
        String romanOnes = romanDigit( Math.abs(num%10), "I", "V", "X");
        num /=10;
        String romanTens = romanDigit( Math.abs(num%10), "X", "L", "C");

        result = result + romanTens + romanOnes;

        return result;

    }
    public int isLatin() {
        return latin;
    }

    public int toArab(String number) {

        String [] l = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        for(int i=0; i<10; i++) {
            if(number.equals(l[i]))
                return i+1;
        }
        System.out.println("Incorrect expression!");
        System.exit(-1);
        return 0;
    }
    public int getNumberA() {
        String a = this.expr.substring(0,this.posOp);
        if(latin==1) {
            if (Integer.parseInt(a) > 0 || Integer.parseInt(a) <= 10) {
                return Integer.parseInt(a);
            } else {
                System.out.println("Incorrect expression!");
                System.exit(-1);
                return 0;
            }
        }
        else {
            return toArab(a);
        }
    }

    public int getNumberB() {
        String b = this.expr.substring(this.posOp+1);
        if(latin==1) {
            if (Integer.parseInt(b) > 0 || Integer.parseInt(b) <= 10) {
                return Integer.parseInt(b);
            }
            else {
                System.out.println("Incorrect expression!");
                System.exit(-1);
                return 0;
            }
        }
        else {
            return toArab(b);
        }
    }
    public String getOperand() {
        return this.expr.substring(this.posOp, this.posOp+1);
    }
    public String getExpr() {

        String [] op = {"+","-","*","/"};
        Scanner in = new Scanner(System.in);
        System.out.print("Input expression: ");
        this.expr = in.nextLine();
        this.expr = this.expr.replaceAll("\\s+","");
        if ('9' >= this.expr.charAt(0) && this.expr.charAt(0) > '0') {
            this.latin = 1;
        }
        for(int i=0; i<4; i++) {
            if(this.expr.indexOf(op[i]) != -1) {
                this.posOp = this.expr.indexOf(op[i]);
            }

        }


        return expr;
    }


}

class Calculate {
    public int add(int a, int b) {
        return a+b;
    }
    public int sub(int a, int b) {
        return a-b;
    }
    public int div(int a, int b) {
        return a/b;
    }
    public int mult(int a, int b) {
        return a*b;
    }
    public int action (int a, int b, String operator){
        switch (operator) {
            case "+":
                return  this.add(a, b);
            case "-":
                return  this.sub(a, b);
            case "/":
                return this.div(a, b);
            case "*":
                return this.mult(a, b);

            default: {
                System.out.println("Incorrect expression!");
                return 0;
            }
        }
    }
}
