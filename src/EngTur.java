import java.io.*;
import java.util.HashMap;
import java.util.Map;

// From English to Turkish. exp. door = kapı (dor).
public class EngTur implements Flashcard,Serializable{
    private static Map<String,String> words;
    private int size = 0;
    public EngTur(){
        fromFile();
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
}
