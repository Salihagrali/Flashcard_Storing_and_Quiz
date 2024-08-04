import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EngTur flashcard = new EngTur();
        System.out.println("WELCOME !");

        System.out.println("Would you like to add new vocabulary to your flashcards ? If so please enter 'Yes'."
                + " \nFor other options please enter 'Other' ");
        String ans = in.next();
        in.nextLine();

        while (true){
            if(ans.equalsIgnoreCase("yes")){
                System.out.println("Please enter the word itself and then meaning and pronunciation according to the example. exp: door kapı(dor) " +
                        "\nAfter entering the word first press enter then enter the meaning please");
                String word = in.nextLine();
                String meaning = in.nextLine();

                boolean allLetters = (word.chars()
                        .mapToObj(e -> e)
                        .allMatch(Character::isLetter)) && (meaning.chars()
                        .mapToObj(e -> e)
                        .allMatch(Character::isLetter));
                if(!allLetters){
                    while(!StringUtils.isAlpha(word) || !StringUtils.isAlpha(meaning)){
                        System.out.println("Please enter a proper string");
                        word = in.nextLine();
                        meaning = in.nextLine();
                    }
                }
                if(flashcard.search(word)){
                    System.out.println("It seems there is already a flashcard with the same word. Would you like to add another one ? ");
                    ans = in.next();
                    in.nextLine();
                }else{
                    flashcard.add(word,meaning);
                    System.out.println("Added do you want to keep going ? ");
                    ans = in.next();
                    in.nextLine();
                }

            }
            else{
                flashcard.printAll();
                break;
            }
        }




    }
}
