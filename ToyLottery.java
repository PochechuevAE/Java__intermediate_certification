import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
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
        lottery.displayCurrentCollection();
        lottery.askForAddNewToy();
        lottery.displayCurrentCollection();
        lottery.askStartLottery();
        lottery.closeScanner();
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

    private void replaceToyIfExists(Toy newToy) {
        int newToyId = newToy.getId();
        Toy existingToy = findToyById(newToyId);

        if (existingToy != null) {
            // Предложение заменить существующую игрушку
            System.out.println("Игрушка с ID " + newToyId + " уже существует:");
            System.out.println(existingToy);
            System.out.println("Хотите заменить её? (1 - Да, 2 - Нет)");

            try {
                int replaceChoice = Integer.parseInt(scanner.nextLine());

                if (replaceChoice == 1) {
                    // Удаляем существующий элемент с заданным id
                    toysSet.remove(existingToy);
                    addToy(newToy);
                    System.out.println("Существующая игрушка с ID " + newToyId + " была заменена.");
                } else if (replaceChoice == 2) {
                    System.out.println("Изменение игрушки отменено.");
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, введите 1 (Да) или 2 (Нет).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный ввод. Пожалуйста, введите 1 (Да) или 2 (Нет).");
            }
        }
    }

    private Toy findToyById(int id) {
        return toysSet.stream()
                .filter(toy -> toy.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private boolean checkDuplicate(int id) {
        return toysSet.stream().noneMatch(t -> t.getId() == id);
    }

    private void addToy(Toy toy) {
        toysSet.add(toy);
    }

    public class DisplaySet {

        public static <T> void displaySet(Set<T> set) {
            System.out.println("Элементы в коллекции:");
            for (T element : set) {
                System.out.println(element);
            }
        }
    }

    private void askForAddNewToy() {
        while (true) {
            try {
                System.out.println("Хотите добавить новую игрушку? (1 - Да, 2 - Нет)");

                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    acceptToyDataFromUser();
                } else if (choice == 2) {
                    System.out.println("Добавление новой игрушки отменено.");
                    break;
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, введите 1 (Да) или 2 (Нет).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный ввод. Пожалуйста, введите 1 (Да) или 2 (Нет).");
            }
        }

    }

    private void askStartLottery() {
        while (true) {
            try {
                System.out.println("Хотите начать лотерею? (1 - Да, 2 - Нет)");

                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    addToysToQueue();
                    getAndWriteToysToFile();
                } else if (choice == 2) {
                    System.out.println("Лотерея игрушек отменена.");
                    break;
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, введите 1 (Да) или 2 (Нет).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный ввод. Пожалуйста, введите 1 (Да) или 2 (Нет).");
            }
        }
    }

    private void addToysToQueue() {
        PriorityQueue<Toy> sortedToysQueue = new PriorityQueue<>(new ToyComparator());

        for (Toy toy : toysSet) {
            int frequency = toy.getFrequency();
            for (int i = 0; i < frequency; i++) {
                sortedToysQueue.add(toy);
            }
        }

        toysQueue = sortedToysQueue;

        System.out.println("Подготовка к лотерее завершена!");
        System.out.println(toysSet);
    }

    public class ToyComparator implements Comparator<Toy> {
        @Override
        public int compare(Toy toy1, Toy toy2) {
            return Integer.compare(toy2.getFrequency(), toy1.getFrequency());
        }
    }

    public void displayCurrentCollection() {
        DisplaySet.displaySet(toysSet);
    }

    private void getAndWriteToysToFile() {
        int count = 10;
        try (FileWriter writer = new FileWriter("lottery_results.txt")) {

            for (int i = 0; i < count; i++) {
                Toy toy = getToy();
                writer.write("Выпавшая игрушка № " + (i + 1) + ": " + (toy != null ? toy.toString() : "null") + "\n");
            }
            System.out.println("Результаты успешно записаны в файл lottery_results.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private Toy getToy() {
        Toy toy = toysQueue.poll();
        if (toy != null) {
            System.out.println("Выпавшая игрушка: " + toy);
        } else {
            System.out.println("Очередь пуста.");
        }
        return toy;
    }

    public void closeScanner() {
        scanner.close();
    }
}
