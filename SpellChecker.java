package edu.odu.cs.cs350;

import java.io.Reader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpellChecker {

  private static String stripPunctuation(String str) {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < str.length(); ++i) {
      char c = str.charAt(i);
      if (Character.isAlphabetic(c) || c == '-') {
        buf.append(c);
      } else {
        break;
      }
    }
    return buf.toString();
  }

  /**
   * Checks the spelling of a set of words supplied as
   * command-line parameters. If no command-line parameters
   * are supplied, prompts for a line of input containing
   * one or more words.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("** Must supply a path to a text document via the command line");
      System.exit(1);
    }
    try {
      Dictionary dictionary = new Dictionary();
      NearHomophones soundsLike = new NearHomophones(dictionary);

      try (Reader in = new FileReader(args[0])) {
        checkSpelling(in, dictionary, soundsLike);
      } catch (IOException ex) {
        System.err.println("Could not read " + args[0]);
        System.exit(2);
      }
    } catch (IOException ex) {
      System.err.println("Could not load dictionary.");
    }
  }

  private static void checkSpelling(Reader in, Dictionary dictionary, NearHomophones soundsLike) {
    Set<String> alreadyChecked = new HashSet<>();
    try (Scanner input = new Scanner(new BufferedReader(in))) {
      while (input.hasNext()) {
        String phrase = input.next();
        String word = stripPunctuation(phrase);
        if (word != "") {
          word = word.toLowerCase();
          if (!alreadyChecked.contains(word)) {
            alreadyChecked.add(word);
            if (!dictionary.contains(word)) {
              System.out.println("Misspelled: " + word + "\nSuggestions:");
              Set<String> suggestions = soundsLike.soundsLike(word);
              System.out.print("  ");
              if (suggestions.size() == 0) {
                System.out.println("[none]");
              }
              int limit = 10;
              for (String suggestion : suggestions) {
                if (!suggestion.equals(word)) {
                  System.out.print(suggestion);
                  System.out.print(' ');
                  --limit;
                  if (limit <= 0) {
                    break;
                  }
                }
              }
              System.out.println("\n");
            }
          }
        }
      }
    }
  }
}