import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {
    //Make a quiz func. to test the user. Use streams and collections. That's the goal of this mini project.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EngTur flashcard = new EngTur();
        System.out.println("WELCOME !");

        System.out.println("\nWould you like to add new vocabulary to your flashcards ? If so please enter 'Yes'."
                + " \nFor other options please enter 'Other'\n\n");
        String ans = in.next();
        in.nextLine();

        while (true){
            if(ans.equalsIgnoreCase("yes")){
                System.out.println("\nPlease enter the word itself and then meaning and pronunciation according to the example. exp: door kapı(dor) " +
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
                        System.out.println("\nPlease enter a proper string");
                        word = in.nextLine();
                        meaning = in.nextLine();
                    }
                }
                if(flashcard.search(word)){
                    System.out.println("\nIt seems there is already a flashcard with the same word. Would you like to add another one ? ");
                    ans = in.next();
                    in.nextLine();
                }else{
                    flashcard.add(word,meaning);
                    System.out.println("\nAdded do you want to keep going ? ");
                    ans = in.next();
                    in.nextLine();
                }

            }
            else{
                boolean runCondition = true;
                while(runCondition){
                    System.out.println("\nWhat would you like to do ? Select a number... \n" +
                            " 1-) See all the words\n 2-) Search a specific word and its meaning\n " +
                            "3-) Delete a word from the flashcard\n 4-) Clear the flashcards\n " +
                            "5-) Exit from the program");
                    String optionSelected = in.nextLine();

                    switch (optionSelected){
                        case "1": {
                            flashcard.printAll();
                            System.out.println();
                        }break;
                        case "2": {
                            flashcard.showSpecificWord();
                        }break;
                        case "3": {
                            System.out.println("\nWhat is the word ?");
                            String deleteWord = in.nextLine();
                            boolean doesExists = flashcard.search(deleteWord);

                            System.out.println("\nThe word that you want to delete" + ((doesExists) ? " is deleted " : "is not in the flashcards"));
                            if(doesExists) flashcard.delete(deleteWord);
                        }break;
                        case "4": {
                            System.out.println("\nAre you sure ? There is no turning back.(Y/N)");
                            String answer = in.nextLine();
                            while(!answer.equals("Y") && !answer.equals("N")){
                                System.out.println("Please enter Y or N");
                                answer = in.next();
                                in.nextLine();
                            }
                            if (answer.equals("Y")) flashcard.deleteAll();
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
