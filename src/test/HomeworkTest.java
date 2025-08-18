package test;

import main.Homework;
import main.SynonymDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class HomeworkTest {

    @Test
    void testAreAnagrams() {
        String first = "Карета";
        String second = "ракетА";
        Assertions.assertTrue(Homework.areAnagrams(first, second));
        Assertions.assertFalse(Homework.areAnagrams(null, null));
        Assertions.assertFalse(Homework.areAnagrams("", " "));
        Assertions.assertFalse(Homework.areAnagrams("", ""));
    }

    @Test
    void testHasDuplicates() {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 4, 5));
        Assertions.assertTrue(Homework.hasDuplicates(numbers));
        Assertions.assertFalse(Homework.hasDuplicates(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void testGetTopStudent() {
        Map<String, Integer> students = new HashMap<>();
        students.put("Сидоров", 33);
        students.put("Иванов", 37);
        students.put("Петров", 41);
        students.put("Назин", 48);
        students.put("Сергеенков", 47);
        students.put("Орлов", 48);
        Assertions.assertEquals("[Назин, Орлов]", Homework.getTopStudent(students).toString());
    }

    @Test
    void testSynonymDictionary() {
        SynonymDictionary dict = new SynonymDictionary();
        dict.addSynonym("список", "реестр");
        dict.addSynonym("список", "перечень");
        dict.addSynonym("список", "каталог");
        dict.addSynonym("список", "табель");
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> dict.addSynonym("список", "список"));
        Assertions.assertEquals("Нельзя задать синонимом само слово.", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> dict.addSynonym(null, null));
        Assertions.assertEquals("Нельзя задать синонимы со значением null.", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> dict.addSynonym("", ""));
        Assertions.assertEquals("Нельзя задать синонимы с пустым значением.", exception.getMessage());
        Set<String> expected = Set.of("перечень", "реестр", "табель", "список");
        Assertions.assertEquals(expected,dict.getSynonyms("каталог"));
    }
}