import org.jetbrains.annotations.NotNull;
import java.util.Scanner;                               // Этот оператор импортирует класс Scanner из пакета java.util, который позволяет считывать ввод пользователя

class Main {                                            // Объявление класса Main и его метода main. Метод main является точкой входа в программу
    public static void main(String[] args) {            //
        Scanner scanner = new Scanner(System.in);       // Создание объекта scanner класса Scanner для считывания пользовательского ввода
        String input = scanner.nextLine();              // Чтение строки, введенной пользователем, и сохранение ее в переменной input
        String result = calc(input);                    // Вызов метода calc с аргументом input и сохранение результата в переменной result
        System.out.println(result);                     // Вывод результата на консоль
    }

    public static @NotNull String calc(String input) {   // Объявление метода calc, который принимает строку input в качестве аргумента и возвращает строку
                                                         //     Аннотация @NotNull указывает на то, что метод calc возвращает строку, которая не может быть равна null
                                                         //     Это гарантирует, что вызывающий код не будет получать null значения при использовании результата этого метода
        String[] tokens = input.split(" ");        // Разделение строки input на подстроки с помощью пробела и сохранение результатов в массиве строк tokens
        int num1 = Integer.parseInt(tokens[0]);          // Преобразование первой и третьей подстрок из массива tokens в целочисленные значения num1 и num2
        int num2 = Integer.parseInt(tokens[2]);          //     с помощью метода parseInt
        char operator = tokens[1].charAt(0);             // Извлечение первого символа из второй подстроки массива tokens и сохранение его в переменной operator
        int result = 0;                                  // Инициализация переменной result, которая будет содержать результат операции

        switch (operator) {                              // Оператор switch используется для выполнения разных операций в зависимости от значения переменной operator
            case '+':                                    // В данном случае происходит проверка значения operator и выполнение соответствующей арифметической операции
                result = num1 + num2;                    //
                break;                                   //
            case '-':                                    //
                result = num1 - num2;                    //
                break;                                   //
            case '*':                                    //
                result = num1 * num2;                    //
                break;                                   //
            case '/':                                    //
                if (num2 != 0) {                         //
                    result = num1 / num2;                //
                } else {
                    return "Division by zero";           // Если выполняется деление на ноль, будет возвращена строка "Деление на ноль"
                }
                break;
            default:
                return "Invalid operator";                // Если оператор не является одним из допустимых (+, -, *, /), будет возвращена строка "Неверный оператор"
        }

        return Integer.toString(result);                  // Преобразование значения result в строку с помощью метода toString() класса Integer
                                                          //    и возвращение этой строки в качестве результата метода calc.
    }
}                                                         // Закрытие класса Main и метода main.

// Дополнительный код