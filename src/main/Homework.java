package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Homework {
    public static void main(String[] args) {

        /**
         * Set / HashSet
         * Создание множества
         * Создайте HashSet<String>, добавьте 5 слов и выведите все элементы
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("basic");
        hashSet.add("java");
        hashSet.add("pascal");
        hashSet.add("python");
        hashSet.add("php");
        System.out.println(hashSet);

        /**
         * Проверка на наличие элемента
         * Проверьте, содержится ли слово "java" в множестве
         */
        System.out.printf("В множестве содержится слово \"java\": %s\n", hashSet.contains("java"));
        System.out.println();

        /**
         * Удаление элемента
         * Удалите слово "python" из множества (если оно есть)
         */
        String pythonWord = "python";
        if (hashSet.remove(pythonWord)) {
            System.out.printf("Из множества удалено слово \"%s\"\n", pythonWord);
        }
        System.out.println();

        /**
         * Подсчёт уникальных
         * Дан список строк с повторами.
         * Добавьте их в HashSet и выведите количество уникальных слов
         */
        HashSet<String> hashSetUniq = new HashSet<>(List.of("basic", "basic", "java", "java", "pascal", "pascal", "python", "php"));
        System.out.printf("Количество уникальных элементов: %s\n", hashSetUniq.size());
        System.out.println();

        /**
         * Map / HashMap
         * Создание и вывод Map
         * Создайте HashMap<String, Integer> с названиями предметов и оценками.
         * Выведите все пары ключ-значение
         */
        Map<String, Integer> gradeOfSubjectMap = new HashMap<>(
                Map.of(
                        "География", 4,
                        "Math", 5,
                        "Окружающий мир", 5,
                        "Геометрия", 5,
                        "Русский язык", 5,
                        "English", 3,
                        "History", 3,
                        "Physics", 3
                ));
        for (Map.Entry<String, Integer> grade : gradeOfSubjectMap.entrySet()) {
            System.out.printf("По предмету %s оценка %s\n", grade.getKey(), grade.getValue());
        }
        System.out.println();

        /**
         * Получение значения по ключу
         * Выведите значение по ключу "Math".
         */
        String keyValue = "Math";
        System.out.printf("Значение по ключу \"%s\" равно %s\n", keyValue, gradeOfSubjectMap.get(keyValue));
        System.out.println();

        /**
         * Изменение значения
         * Измените значение по ключу "English" на 5
         */
        String keyForEdit = "English";
        Integer valueForEdit = gradeOfSubjectMap.get(keyForEdit);
        if (gradeOfSubjectMap.replace(keyForEdit, valueForEdit, 5)) {
            System.out.printf("Измененное значение по ключу \"%s\" с %s на %s\n", keyForEdit, valueForEdit, gradeOfSubjectMap.get(keyForEdit));
        }
        System.out.println();

        /**
         * Проверка наличия ключа
         * Проверьте, есть ли в мапе ключ "History"
         */
        String keyValueContain = "History";
        System.out.printf("Ключ \"%s\" содержится в мапе: %s\n", keyValueContain, gradeOfSubjectMap.containsKey(keyValueContain));
        System.out.println();

        /**
         * Удаление ключа
         * Удалите ключ "Physics" из мапы
         */
        String keyDelete = "Physics";
        Integer valueDeleted = gradeOfSubjectMap.remove(keyDelete);
        if (valueDeleted != null) {
            System.out.printf("Удален ключ \"%s\" со значением %s\n", keyDelete, valueDeleted);
        }
        System.out.println();

        /**
         * Medium
         * Анаграммы
         * Напишите метод areAnagrams(String a, String b), который возвращает true,
         * если строки состоят из одинаковых символов (используйте Map<Character, Integer>).
         */
        String a = "ЛЗЬЕЕН";
        String b = "Зелень";
        System.out.printf("Слова \"%s\" и \"%s\" %s анаграммами друг друга\n\n", a, b, areAnagrams(a, b) ? "являются" : "не являются");

        /**
         * Поиск дубликатов с помощью Set
         * Напишите метод hasDuplicates(List<Integer> list) — возвращает true, если в списке есть дубликаты
         */
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 4, 5));
        System.out.printf("В списке есть дубликаты: %s\n", hasDuplicates(numbers));

        /**
         * Рейтинг студентов
         * Дан Map<String, Integer> (имя студента → балл).
         * Верните имя студента с максимальным баллом
         */
        Map<String, Integer> studentGrades = new HashMap<>(
                Map.of(
                        "Сидоров", 33,
                        "Иванов", 37,
                        "Петров", 41,
                        "Назин", 48,
                        "Сергеенков", 47,
                        "Орлов", 48
                ));
        System.out.printf("Студент(ы) с максимальным балом %s\n", getTopStudent(studentGrades));

        /**
         * Словарь синонимов
         * Создайте Map<String, Set<String>> — ключ: слово, значение: набор синонимов.
         * Реализуйте метод addSynonym(String word, String synonym)
         */
        SynonymDictionary dict = new SynonymDictionary();
        dict.addSynonym("список", "реестр");
        dict.addSynonym("список", "перечень");
        dict.addSynonym("список", "каталог");
        dict.addSynonym("список", "табель");
        System.out.println(dict.getSynonyms("каталог"));
    }

    /**
     * Анаграммы
     * Напишите метод areAnagrams(String a, String b), который возвращает true,
     * если строки состоят из одинаковых символов (используйте Map<Character, Integer>).
     */
    public static boolean areAnagrams(String a, String b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty() || a.length() != b.length()) {
            return false;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : a.toLowerCase().toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        for (char c : b.toLowerCase().toCharArray()) {
            if (!charMap.containsKey(c) || charMap.get(c) == 0) {
                return false;
            }
            charMap.put(c, charMap.getOrDefault(c, 0) - 1);
        }
        return true;
    }

    /**
     * Поиск дубликатов с помощью Set
     * Напишите метод hasDuplicates(List<Integer> list) — возвращает true, если в списке есть дубликаты
     */
    public static boolean hasDuplicates(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return set.size() < list.size();
    }

    /**
     * Дан Map<String, Integer> (имя студента → балл).
     * Верните имя студента с максимальным баллом
     */
    public static List<String> getTopStudent(Map<String, Integer> studentGrades) {
        int maxGrade = Collections.max(studentGrades.values());
        List<String> topStudents = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            if (entry.getValue().equals(maxGrade)) {
                topStudents.add(entry.getKey());
            }
        }
        return topStudents;
    }
}