package app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class маин {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr =Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
}
