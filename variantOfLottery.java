import java.util.Formatter;
import java.util.Scanner;
import java.util.function.Function;

import javax.xml.validation.Validator;

public class variantOfLottery {

     private <T> T getUserInput(Scanner scanner, String prompt, Function<String, T> mapper, Validator<T> validator) {
        while (true) {
            try {
                System.out.println(prompt);
                String inputString = scanner.nextLine();
                T input = mapper.apply(inputString);

                if (validator.isValid(input)) {
                    return input;
                } else {
                    System.out.println("Некорректный ввод. Повторите попытку:");
                }
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Повторите попытку:");
            }
        }
    }

    interface Validator<T> {
        boolean isValid(T value);

        static Validator<Integer> rangeValidator(int min, int max) {
            return value -> value >= min && value <= max;
        }

        static Validator<String> regexValidator(String regex) {
            return value -> value.matches(regex);
        }
    }
    private void askForLottery() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
    
        int choice = getUserInput(scanner, "Хотите провести лотерею? (1 - Да, 2 - Нет)",
                Integer::parseInt, Validator.rangeValidator(1, 2));
    
        try {
            if (choice == 1) {
                generateLottery();
                System.out.println("Лотерея успешно проведена. Данные лотереи записаны в файл lottery_results.txt");
            } else if (choice == 2) {
                System.out.println("Лотерея не проведена.");
            } else {
                System.out.println("Некорректный ввод. Лотерея не проведена.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Лотерея не проведена.");
        } finally {
            scanner.close();
        }
    }

  public void generateLottery() {
        try {
            Formatter formatter = new Formatter("lottery_results.txt");
            for (int i = 0; i < 10; i++) {
                Toy toy = drawToy();
                formatter.format("Draw %d: %s%n", i + 1, (toy != null ? toy : "null"));
            }
            formatter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Toy drawToy() {
        if (toysQueue.isEmpty()) {
            return null;
        }

        int totalFrequency = toysQueue.stream().mapToInt(Toy::getFrequency).sum();
        int randomNumber = (int) (Math.random() * totalFrequency) + 1;

        int currentFrequency = 0;
        for (Toy toy : toysQueue) {
            currentFrequency += toy.getFrequency();
            if (randomNumber <= currentFrequency) {
                return toy;
            }
        }

        return null;
    }

}
