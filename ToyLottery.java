import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class ToyLottery {
    private PriorityQueue<Toy> toysQueue;
    private Set<Toy> toysSet;
    private Scanner scanner;

    public ToyLottery() {
        //toysQueue = new PriorityQueue<>(new ToyComparator());
        toysSet = new HashSet<>();
    }

    public static void main(String[] args) {
        ToyLottery lottery = new ToyLottery();
        lottery.askNumberOfToys();
    }

    private void askNumberOfToys() {
        scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите количество игрушек, которые вы хотите добавить в коллекцию:");
                int iterationNums = Integer.parseInt(scanner.nextLine());
                createToys(iterationNums);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
            }
        }
    }

    private void createToys(int iterationNums) {
        for (int i = 0; i < iterationNums; i++) {
            acceptToyDataFromUser();
        }
    }

    private void acceptToyDataFromUser() {
        while (true) {
            try {
                System.out.println(
                        "Введите данные для игрушки, разделяя их запятой, без пробела в формате 'id,Название,Частота выпадения':");
                String toyDataString = scanner.nextLine();

                if (toyDataString.trim().isEmpty()) {
                    System.out.println("Ошибка: Введите данные для игрушки.");
                    continue;
                }

                String[] toyData = toyDataString.split(",");

                if (toyData.length != 3) {
                    System.out.println("Ошибка: Введите все три параметра (id,Название,Частота выпадения).");
                    continue;
                }

                int tempId = Integer.parseInt(toyData[0]);
                String tempName = toyData[1];
                int tempFrequency = Integer.parseInt(toyData[2]);

                if (!checkDuplicate(tempId)) {
                    replaceToyIfExists(new Toy(tempId, tempName, tempFrequency));
                    continue;
                }

                Toy toy = new Toy(tempId, tempName, tempFrequency);
                addToy(toy);
                System.out.printf("Игрушка %s c частотой выпадения: %d добавлена в коллекцию.%n", toy,
                        toy.getFrequency());

                break;

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ошибка: Некорректный ввод. Повторите попытку.");
                scanner.nextLine();
            }
        }
    }
}
