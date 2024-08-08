import java.io.*;
import java.security.SecureRandom;
import java.util.*;

// From English to Turkish. exp. door = kapı (dor).
public class EngTur implements Flashcard,Serializable{
    private static Map<String,String> words;
    private int size = 0;
    private Scanner in = new Scanner(System.in);
    public EngTur(){
        fromFile();
        size = words.size();
    }

    @Override
    public void add(String engWord,String meaning) {
        String word = words.putIfAbsent(engWord,meaning);
        size++;
        if(word != null) System.out.println(engWord + " already exists");
        else toFile();

    }
    @Override
    public boolean delete(String engWord) {
        if(!search(engWord)){
            System.out.println("No such word exists in this list");
            return false;
        }
        size--;
        words.remove(engWord);
        toFile();
        return true;
    }

    public boolean deleteAll(){
        if(size == 0)return false;
        words.clear();
        toFile();
        return true;
    }
    @Override
    public boolean search(String engWord) {
        return (words.containsKey(engWord));
    }

    private void toFile(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("file.bin"))){
                objectOutputStream.writeObject(words);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void fromFile(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("file.bin"))){
            words = (Map<String, String>) objectInputStream.readObject();
        }catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAll(){
        words.entrySet().stream().forEach((e) -> System.out.println(e.getKey() + " = " + e.getValue()));
    }

    public void printSpecificWord(String word){
        words.entrySet().stream().filter(e -> e.getKey().equals(word)).forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
    }

    public void showSpecificWord(){
        System.out.println("\nWhat is the word ?");
        String searchWord = in.nextLine();
        boolean doesExists = search(searchWord);

        System.out.println("\nThe word which is you are looking for" + ((doesExists) ? " exists. " : " does not exists in this list"));
        if(doesExists) printSpecificWord(searchWord);
    }
    public void quiz(){
        System.out.println("----- QUIZ STARTING -----\n");
        List<String> alreadyAsked = new ArrayList<>();
        List<String> keyWords = new ArrayList<>();

        for(Map.Entry<String,String> entry : words.entrySet()){
            keyWords.add(entry.getKey());
        }
        Random random = new SecureRandom();
        while(alreadyAsked.size() != words.size()){
            int index = random.nextInt(keyWords.size());
            String keyWord = keyWords.get(index);

            if(!alreadyAsked.contains(keyWord)){
                System.out.println("What is the meaning of " + keyWord);
                String answer = in.nextLine();
                if(answer.equals(words.get(keyWord))){
                    System.out.println("CORRECT ANSWER !!!!!!!!!!");
                    alreadyAsked.add(keyWord);
                }
                else{
                    System.out.println("Unfortunately answer is wrong :(\n " +
                             "Would you like to see the answer ? Y/N");
                    String yesno = in.nextLine();
                    if(yesno.equals("Y")){
                        System.out.println("The answer is: " + words.get(keyWord));
                        alreadyAsked.add(keyWord);
                    }else{
                        System.out.println("Continuing with other words. Good Luck Next Time :)");
                    }
                }
            }
        }
        System.out.println("----- Quiz is over -----");
    }
}
