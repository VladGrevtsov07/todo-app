package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private static final String COMMANDS =
            "Commands: add <task>, remove <index>, done <index>, search <text>, list, clear, exit";

    public static void main(String[] args) {
        TodoList list = new TodoList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Todo CLI. " + COMMANDS);

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
                case "add" -> {
                    if (parts.length > 1) {
                        list.add(parts[1]);
                        System.out.println("Added.");
                    } else {
                        System.out.println("Usage: add <task>");
                    }
                }
                case "remove" -> {
                    if (parts.length > 1) {
                        Integer index = parseIndex(parts[1]);
                        if (index == null) System.out.println("Invalid index.");
                        else if (list.remove(index)) System.out.println("Removed.");
                        else System.out.println("Index out of range.");
                    } else {
                        System.out.println("Usage: remove <index>");
                    }
                }
                case "done" -> {
                    if (parts.length > 1) {
                        Integer index = parseIndex(parts[1]);
                        if (index == null) System.out.println("Invalid index.");
                        else if (list.markDone(index)) System.out.println("Marked as done.");
                        else System.out.println("Index out of range.");
                    } else {
                        System.out.println("Usage: done <index>");
                    }
                }
                case "search" -> {
                    if (parts.length > 1) {
                        List<Integer> found = list.search(parts[1]);
                        List<String> all = list.getAll();
                        if (found.isEmpty()) {
                            System.out.println("Nothing found.");
                        } else {
                            for (int index : found) {
                                printTask(list, index, all.get(index));
                            }
                        }
                    } else {
                        System.out.println("Usage: search <text>");
                    }
                }
                case "list" -> {
                    List<String> all = list.getAll();
                    for (int i = 0; i < all.size(); i++) {
                        printTask(list, i, all.get(i));
                    }
                    if (all.isEmpty()) System.out.println("(empty)");
                }
                case "clear" -> {
                    list.clear();
                    System.out.println("All tasks cleared.");
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Unknown command. " + COMMANDS);
            }
        }
    }

    private static Integer parseIndex(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static void printTask(TodoList list, int index, String text) {
        String mark = list.isDone(index) ? "x" : " ";
        System.out.printf("%d: [%s] %s%n", index, mark, text);
    }
}