package controller;

import dao.Storage;
import model.ResultDTO;
import model.StringWithLength;

import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    public static void main(String[] args) {
        Storage<StringWithLength> storage = new Storage<>();

        Scanner scanner = new Scanner(System.in);

        String firstInput = scanner.nextLine();
        String secondInput = scanner.nextLine();

        StringTokenizer tokenizer = new StringTokenizer(firstInput, " ");
        while (tokenizer.hasMoreTokens()) {
            storage.put(new StringWithLength(tokenizer.nextToken()));
        }

        tokenizer = new StringTokenizer(secondInput, " ");
        while (tokenizer.hasMoreTokens()) {
            Optional<ResultDTO> byKey = Optional.empty();
            try {
                byKey = storage.getDTOByKey(Integer.parseInt(tokenizer.nextToken()));
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Second string has bad format (u can use only digits)");
            }
            byKey.ifPresent(System.out::println);
        }

    }
}
