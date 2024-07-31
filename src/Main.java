import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {
    public static final int keyWord = 0;
    public static final int meaning = 1;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EngTur flashcard = new EngTur();
        System.out.println("WELCOME !");

        System.out.println("Would you like to add new vocabulary to your flashcards ? If so please enter 'Yes'."
                + " \nFor other options please enter 'Other' ");
        String ans = in.nextLine();

        while (true){
            if(ans.equalsIgnoreCase("yes")){
                System.out.println("Please enter the word itself and then meaning and pronunciation according to the example. exp: door kapı(dor)");
                String word = in.nextLine();
                boolean allLetters = word.chars()
                        .mapToObj(e -> (char)e)
                        .allMatch(Character::isLetter);
                if(!allLetters){
                    while(!StringUtils.isAlpha(word)){
                        System.out.println("Please enter a proper string");
                        word = in.nextLine();
                    }
                }
                String[] wordArr = word.split(" ");
                flashcard.add(wordArr[keyWord],wordArr[meaning]);
                if(flashcard.search(wordArr[keyWord])){
                    System.out.println("It seems there is already a flashcard with the same word. Would you like to add another one ? ");
                    ans = in.nextLine();
                }
            }
        }




    }
}
