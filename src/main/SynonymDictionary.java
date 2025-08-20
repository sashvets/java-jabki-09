package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SynonymDictionary {
    private final Map<String, Set<String>> synonyms;

    public SynonymDictionary() {
        this.synonyms = new HashMap<>();
    }

    public void addSynonym(String word, String synonym) {
        if (word == null || synonym == null) {
            throw new IllegalArgumentException("Нельзя задать синонимы со значением null.");
        } else if (word.isEmpty() || synonym.isEmpty()) {
            throw new IllegalArgumentException("Нельзя задать синонимы с пустым значением.");
        } else if (Objects.equals(word, synonym)) {
            throw new IllegalArgumentException("Нельзя задать синонимом само слово.");
        }
        Set<String> listByWord = this.synonyms.computeIfAbsent(word, k -> new HashSet<>());
        Set<String> listBySynonym = this.synonyms.computeIfAbsent(synonym, k -> new HashSet<>());

        Set<String> merged = new HashSet<>();
        merged.add(word);
        merged.add(synonym);
        merged.addAll(listByWord);
        merged.addAll(listBySynonym);

        for (String w : merged) {
            Set<String> others = new HashSet<>(merged);
            others.remove(w);
            this.synonyms.put(w, others);
        }
    }

    public Set<String> getSynonyms(String word) {
        return this.synonyms.computeIfAbsent(word, k -> new HashSet<>());
    }
}