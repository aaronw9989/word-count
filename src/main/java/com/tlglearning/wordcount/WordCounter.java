package com.tlglearning.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// final on a class makes it to where the class can NOT be inherited
public class WordCounter {

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
    for (String word : words) {
      // Done Check if word is already present as a key in counts;
      //  if it's not present, add it to counts with a value of 1;
      //  otherwise, get the current value, add 1 to it, and update the map with the new value.
      counts.put(word, get(word) + 1);
      // increment total words
      totalWords++;
    }
  }

}
