package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private static class Task {
        private final String text;
        private boolean done;

        Task(String text) {
            this.text = text;
        }
    }

    private final List<Task> tasks = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                tasks.add(new Task(item));
            }
        }
    }

    public boolean remove(int index) {
        if (isValidIndex(index)) {
            tasks.remove(index);
            return true;
        }
        return false;
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        for (Task task : tasks) {
            result.add(task.text);
        }
        return result;
    }

    public int size() {
        return tasks.size();
    }

    public boolean markDone(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).done = true;
            return true;
        }
        return false;
    }

    public boolean isDone(int index) {
        return isValidIndex(index) && tasks.get(index).done;
    }

    public void clear() {
        tasks.clear();
    }

    /** Возвращает индексы задач, текст которых содержит подстроку (без учёта регистра). */
    public List<Integer> search(String query) {
        List<Integer> found = new ArrayList<>();
        if (query == null || query.isBlank()) {
            return found;
        }
        String needle = query.trim().toLowerCase();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).text.toLowerCase().contains(needle)) {
                found.add(i);
            }
        }
        return found;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}