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
                boolean runCondition = true;
                while(runCondition){
                    System.out.println("What would you like to do ? Select a number... \n" +
                            " 1-) See all the words\n 2-) Search a specific word and its meaning\n " +
                            "3-) Delete a word from the flashcard\n 4-) Clear the flashcards\n" +
                            "5-) Exit from the program");
                    String optionSelected = in.nextLine();

                    switch (optionSelected){
                        case "1": {
                            flashcard.printAll();
                            System.out.println();
                        }break;
                        case "2": {
                            System.out.println("What is the word ?");
                            String searchWord = in.nextLine();
                            boolean doesExists = flashcard.search(searchWord);

                            System.out.println("The word you are looking for" + ((doesExists) ? " exists. " : " does not exists in this list"));
                            if(doesExists) flashcard.printSpecificWord(searchWord);
                        }break;
                        case "3": {
                            System.out.println("What is the word ?");
                            String deleteWord = in.nextLine();
                            boolean doesExists = flashcard.search(deleteWord);

                            System.out.println("The word that you want to delete" + ((doesExists) ? " is deleted " : "is not in the flashcards"));
                            if(doesExists) flashcard.delete(deleteWord);
                        }break;
                        case "4": {
                            System.out.println("Are you sure ? There is no turning back.");
                            boolean order = in.nextBoolean(); // check here
                            if (order) flashcard.deleteAll();
                        }break;
                        case "5" :{
                            runCondition = false;
                        }break;
                    }
                }
                if(!runCondition) break;
            }
        }




    }
}
