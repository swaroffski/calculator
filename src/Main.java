import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Main {
    // Создаем отображение римских цифр и их десятичных значений
    private static final Map<Character, Integer> romanNumerals = createRomanNumeralsMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) {
        // Разбиваем входную строку на отдельные токены (число, оператор, число)
        String[] tokens = input.split(" ");
        // Проверяем, что количество токенов равно 3
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Извлекаем первое и второе число, а также оператор из токенов
        String num1Str = tokens[0];
        String num2Str = tokens[2];
        int num1;
        int num2;
        char operator = tokens[1].charAt(0);

        // Проверяем, являются ли оба числа римскими цифрами
        if (isRomanNumeral(num1Str) && isRomanNumeral(num2Str)) {
            // Если оба числа римские, конвертируем их в десятичные значения
            num1 = romanToDecimal(num1Str);
            num2 = romanToDecimal(num2Str);
        }
        // Проверяем, являются ли оба числа арабскими цифрами
        else if (isNumeric(num1Str) && isNumeric(num2Str)) {
            // Если оба числа арабские, парсим их в целочисленные значения
            num1 = Integer.parseInt(num1Str);
            num2 = Integer.parseInt(num2Str);
        }
        // Если оба числа не соответствуют одному из типов (арабские или римские), выбрасываем исключение
        else {
            throw new IllegalArgumentException("Invalid input");
        }

        int result;
        // Выполняем операцию в зависимости от оператора
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                // Проверяем, что второе число не равно нулю перед делением
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    throw new IllegalArgumentException("Division by zero");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }

        // Проверяем, являются ли оба числа римскими цифрами
        if (isRomanNumeral(num1Str) && isRomanNumeral(num2Str)) {
            // Если результат отрицательный или нулевой, выбрасываем исключение,
            // так как римские цифры не могут представлять отрицательные значения или ноль
            if (result <= 0) {
                throw new IllegalArgumentException("Roman numeral result must be positive");
            }
            // Конвертируем десятичное значение в римскую цифру
            return decimalToRoman(result);
        } else {
            // Возвращаем результат операции в виде строки
            return Integer.toString(result);
        }
    }

    // Проверяем, является ли строка числом (арабским)
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Проверяем, является ли строка римской цифрой
    private static boolean isRomanNumeral(String str) {
        return str.matches("[IVX]+");
    }

    // Конвертируем римскую цифру в десятичное значение
    private static int romanToDecimal(String romanNumeral) {
        int decimal = 0;
        int prevValue = 0;

        // Обрабатываем римскую цифру справа налево
        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            char currentChar = romanNumeral.charAt(i);
            int currentValue = romanNumerals.get(currentChar);

            // Если текущее значение меньше предыдущего, вычитаем его из общего значения
            if (currentValue < prevValue) {
                decimal -= currentValue;
            } else {
                // Иначе, добавляем его к общему значению
                decimal += currentValue;
            }

            prevValue = currentValue;
        }

        return decimal;
    }

    // Конвертируем десятичное значение в римскую цифру
    private static String decimalToRoman(int decimal) {
        StringBuilder romanNumeral = new StringBuilder();

        // Обрабатываем римские цифры в порядке убывания их десятичных значений
        for (Map.Entry<Character, Integer> entry : romanNumerals.entrySet()) {
            char symbol = entry.getKey();
            int value = entry.getValue();

            // Пока десятичное значение больше или равно текущей римской цифры,
            // добавляем эту цифру к результирующей строке и уменьшаем значение
            while (decimal >= value) {
                romanNumeral.append(symbol);
                decimal -= value;
            }
        }

        return romanNumeral.toString();
    }

    // Создаем отображение римских цифр и их десятичных значений
    private static Map<Character, Integer> createRomanNumeralsMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        return map;
    }
}
