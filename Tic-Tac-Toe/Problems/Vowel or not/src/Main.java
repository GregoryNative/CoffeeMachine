import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static char vowels[] = new char[] { 'a', 'e', 'i', 'o', 'u' };

    public static boolean isVowel(char ch) {
        char chLowerCase = Character.toLowerCase(ch);

        for (char vowel: vowels) {
            if (chLowerCase == vowel) {
                return true;
            }
        }

        return false;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.nextLine().charAt(0);
        System.out.println(isVowel(letter) ? "YES" : "NO");
    }
}