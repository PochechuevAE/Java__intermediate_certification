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

    }
}
