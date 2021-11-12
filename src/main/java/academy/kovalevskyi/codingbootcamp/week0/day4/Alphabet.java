package academy.kovalevskyi.codingbootcamp.week0.day4;

public class Alphabet {
  public static char[] generateAlphabet() {
    char[] normAlphabet = new char[26];
    for (int i = 0; i <= 25; i++) {
      normAlphabet[i] = (char) (i + 97);
    }
    return normAlphabet;
  }

  public static char[] generateReversedAlphabet() {
    char[] revAlphabet = new char[26];
    for (int i = 0; i <= 25; i++) {
      revAlphabet[i] = (char) (122 - i);
    }
    return revAlphabet;
  }
}
