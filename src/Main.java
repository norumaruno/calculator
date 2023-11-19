import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] array = input.split(" ");

        if (array.length != 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор.");
        }

        String operand1 = array[0];
        String operand2 = array[2];
        String operator = array[1];

        if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
            throw new Exception("Введен неверный оператор");
        }

        if (isRomanNumeral(operand1) && isRomanNumeral(operand2)) {
            int arabResult = calculation(conversionRomToArab(operand1), conversionRomToArab(operand2), operator);
            if (arabResult > 0) {
                return conversionArabToRom(arabResult);
            } else {
                throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }
        } else if (Character.isDigit(operand1.charAt(0)) && Character.isDigit(operand2.charAt(0))) {
            int firstOperand = 0;
            int secondOperand = 0;

            try {
                firstOperand = Integer.parseInt(operand1);
                secondOperand = Integer.parseInt(operand2);
            } catch (Exception e) {
                throw new Exception("Введено недопустимое число.");
            }

            int result = calculation(firstOperand, secondOperand, operator);

            return Integer.toString(result);
        } else {
            throw new Exception("Используются одновременно разные системы счисления.");
        }
    }

    public static int calculation(int operand1, int operand2, String operator) throws Exception {
        if (operand1 >= 1 && operand1 <= 10 && operand2 >= 1 && operand2 <= 10) {
            int result = 0;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
            }

            return result;
        } else {
            throw new Exception("Введено неверное число.");
        }
    }

    public static boolean isRomanNumeral(String operand) throws Exception {
        if (operand.contains("I") || operand.contains("V") || operand.contains("X")) {
            return true;
        } else {
            return false;
        }
    }

    public static int conversionRomToArab(String romNum) {
        return romNum
                .replace("IV", "IIII")
                .replace("V", "IIIII")
                .replace("IX", "IIIIIIIII")
                .replace("X", "IIIIIIIIII")
                .length();
    }

    public static String conversionArabToRom(int arabNum) {
        int arabUnits = arabNum % 10; //определяем количество единиц в числе
        int arabTens = arabNum / 10; //сначала исключаем количество сотен из числа, затем считаем количество десятков
        int arabHundreds = arabNum / 100; //определяем количество сотен в числе

        return romHundred(arabHundreds) + romTens(arabTens) + romUnits(arabUnits);
    }

    public static String romUnits(int units) {
        String strRomanUnits = "";
        switch (units) {
            case 1:
                strRomanUnits = "I";
                break;
            case 2:
                strRomanUnits = "II";
                break;
            case 3:
                strRomanUnits = "III";
                break;
            case 4:
                strRomanUnits = "IV";
                break;
            case 5:
                strRomanUnits = "V";
                break;
            case 6:
                strRomanUnits = "VI";
                break;
            case 7:
                strRomanUnits = "VII";
                break;
            case 8:
                strRomanUnits = "VIII";
                break;
            case 9:
                strRomanUnits = "IX";
                break;
        }

        return strRomanUnits;
    }

    public static String romTens(int tens) {
        String strRomanTens = "";
        switch (tens) {
            case 1:
                strRomanTens = "X";
                break;
            case 2:
                strRomanTens = "XX";
                break;
            case 3:
                strRomanTens = "XXX";
                break;
            case 4:
                strRomanTens = "XL";
                break;
            case 5:
                strRomanTens = "L";
                break;
            case 6:
                strRomanTens = "LX";
                break;
            case 7:
                strRomanTens = "LXX";
                break;
            case 8:
                strRomanTens = "LXXX";
                break;
            case 9:
                strRomanTens = "XC";
                break;
        }

        return strRomanTens;
    }

    public static String romHundred(int hundred) {
        String strRomanHundred = "";
        if (hundred == 1) {
            strRomanHundred = "C";
        }

        return strRomanHundred;
    }
}