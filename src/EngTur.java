import java.util.HashMap;
import java.util.Map;

// From English to Turkish. exp. door = kapı (dor).
public class EngTur implements Flashcard{
    private static Map<String,String> words = new HashMap<>();
    private int size = 0;

    @Override
    public void add(String engWord,String meaning) {
        String word = words.putIfAbsent(engWord,meaning);
        size++;
        if(word == null) System.out.println(engWord + " already exists");
    }
    @Override
    public boolean delete(String engWord,String meaning) {
        if(!search(engWord)){
            System.out.println("No such word exists in this list");
            return false;
        }
        size--;
        return (words.remove(engWord)) != null;

    }
    @Override
    public boolean search(String engWord) {
        return (words.containsKey(engWord));
    }
}
