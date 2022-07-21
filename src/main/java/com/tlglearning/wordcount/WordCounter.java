package com.tlglearning.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// final on a class makes it to where the class can NOT be inherited
public final class WordCounter {

  // the final keyword just means it can only point to one place in memory
  // thus, we CAN NOT point it to something else
  private final Map<String, Integer> counts;

  // .split("\\s+") replaces white space with nothing
  public WordCounter(String text) {
    String[] words = splitWords(text);
    // This is called a Decorator. It puts wrapper around out Map so it isn't modifiable
    counts = Collections.unmodifiableMap(countWords(words));
  }

  public Set<String> words() {
    return counts.keySet();
  }

  public int getCount(String word) {
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return counts;
  }

  @Override
  public String toString() {
    // return the counts map to string
    return counts.toString();
  }

  // Method is package private, can be seen in package but not outside of package
  String[] splitWords(String text) {
    return text
        .toLowerCase()
        .split("[\\W_]+");
  }

  // Method is package private
  Map<String, Integer> countWords(String[] words) {
    Map<String, Integer> counts = new HashMap<>();
    for(String word: words) {
      // Done Check if word is already present as a key in counts;
      //  if it's not present, add it to counts with a value of 1;
      //  otherwise, get the current value, add 1 to it, and update the map with the new value.
      if (!counts.containsKey(word)) {
        counts.put(word, 1);
      } else {
        int previousCount = counts.get(word);
        counts.put(word, previousCount + 1);
      }
    }
    return counts;
  }

}
