package com.tlglearning.wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

// final on a class makes it to where the class can NOT be inherited

/**
 * Demonstrates the practice of word counting, used (for example) in authorship studies.
 * This demonstration uses (in the included methods) implementation of the {@link java.util.Map},
 * {@link java.util.Map.Entry} and {@link java.util.stream.Stream} interfaces.
 */
public class WordCounter {

  private static final Set<String> BORING_WORDS = Set.of("and", "of", "the", "in", "on", "i", "then", "than", "out", "a", "if");

  // the final keyword just means it can only point to one place in memory
  // thus, we CAN NOT point it to something else
  private final Map<String, Integer> counts = new HashMap<>(); // fields have default values
  private int totalWords; // defaults to zero

  // .split("\\s+") replaces white space with nothing

  public Set<String> words() {
    return counts.keySet();
  }

  public int get(String word) {
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    // Collections.unmodifiableMap() is a decorator pattern
    // Makes our HashMap unmodifiable
    return Collections.unmodifiableMap(counts);
  }

  public void add(String text) {
    String trimmedLine = text.trim();
    if (!trimmedLine.isEmpty()) {
      String[] words = splitWords(trimmedLine);
      countWords(words);
    }
  }

  public int size() {
    return counts.size();
  }

  public int total() {
    return totalWords;
  }

  @Override
  public String toString() {
    // return the counts map to string
    return counts.toString();
  }

  // Method is package private, can be seen in package but not outside of package
  String[] splitWords(String text) {
    // trim() gets rid of leading and trailing whitespace
    return text
        .toLowerCase()
        .split("[\\W_]+");
  }

  // Method is package private
  void countWords(String[] words) {
    Arrays
        .stream(words)
        .map(String::trim)
        .filter((s) -> !s.isEmpty())
        .filter((word) -> word.length() > 5)
        .filter((s) -> !BORING_WORDS.contains(s))
        .forEach((word) -> counts.put(word, 1 + counts.getOrDefault(word, 0)));
  }

}
