public interface Flashcard {
    void add(String engWord,String meaning);
    boolean delete(String engWord,String meaning);
    boolean search(String engWord);

}
