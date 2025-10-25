package dev.gerasimova;

import java.util.Scanner;

/**
 * Обновленный консольный калькулятор.
 * Принимает два числа и оператор, выводит результат вычислений.
 * Операции вынесены в отдельные методы.
 * Поддерживает проверку вводных данных и проверку при делении на ноль.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean shouldContinue = true;
        while (shouldContinue) {
            double x = 0, y = 0;
            String operator = "";
            boolean validInput = false;

            while (!validInput) {

                System.out.println("Введите первое число: ");
                String inputX = scanner.nextLine();
                try {
                    x = Double.parseDouble(inputX);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: '" + inputX + "' не является числом!");
                }
            }
            validInput = false;
            while (!validInput) {
                System.out.println("Введите второе число: ");
                String inputY = scanner.nextLine();
                try {
                    y = Double.parseDouble(inputY);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: '" + inputY + "' не является числом!");
                }
            }
            validInput = false;
            String operations = "+-*/";
            while (!validInput) {
                System.out.println("Введите оператор (+, -, *, /): ");
                String inputOperator = scanner.nextLine();

                if(operations.contains(inputOperator)) {
                    operator = inputOperator;
                    validInput = true;
                }
                else {
                    System.out.println("Ошибка: '" + inputOperator + "' некорректный оператор!");
                }
            }

            System.out.printf("Вы ввели: %f %s %f\n", x, operator, y);

            double result;
            result = switch (operator){
                case "+" -> Calculator.add(x,y);
                case "-" -> Calculator.subtract(x,y);
                case "*" -> Calculator.multiply(x,y);
                case "/" -> Calculator.divide(x,y);
                default -> Double.NaN;
            };
            System.out.println("Результат: " + result);
            System.out.println("Введите 'exit' для выхода или нажмите Enter для продолжения: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                shouldContinue = false;
            }
        }
        scanner.close();

    }
}