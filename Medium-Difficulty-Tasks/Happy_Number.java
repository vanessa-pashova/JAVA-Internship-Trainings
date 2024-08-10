import java.util.HashSet;

public class Happy_Number {

    // Метод, който проверява дали дадено число е щастливо
    public static boolean isHappy(int num) {
        // Създаваме HashSet за да съхраним числата, които вече сме срещнали
        HashSet<Integer> seenNumbers = new HashSet<>();

        // Продължаваме, докато числото не стане 1 или не срещнем вече видяно число (цикъл)
        while (num != 1 && !seenNumbers.contains(num)) {
            // Добавяме текущото число в HashSet-а
            seenNumbers.add(num);
            // Пресмятаме сумата на квадратите на цифрите на текущото число
            num = sumOfSquares(num);
        }

        // Ако числото е 1, то е щастливо; иначе не е щастливо
        return num == 1;
    }

    // Метод, който пресмята сумата на квадратите на цифрите на дадено число
    private static int sumOfSquares(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10; // Вземаме последната цифра на числото
            sum += digit * digit; // Добавяме квадрата на цифрата към сумата
            num /= 10; // Премахваме последната цифра от числото
        }
        return sum;
    }

    public static void main(String[] args) {
        // Примерни числа за тестване на алгоритъма
        int[] testNumbers = {19, 2, 7, 20};

        // Проверяваме всяко число дали е щастливо и отпечатваме резултата
        for (int num : testNumbers) {
            System.out.println("Is " + num + " a happy number? " + isHappy(num));
        }
    }
}
