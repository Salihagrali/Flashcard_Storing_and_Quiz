**Hi everyone, this is my first public mini project on Github. In this project, I tried to implement different concepts that I want to practice. I used data structures and serialization to store flashcards which are maps. 
And in other things like searching, inserting, deleting, I used streams and external classes which I needed to use maven dependencies for(StringUtils).
This is the maven dependency that I used:**
```
  <dependencies>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.15.0</version>
    </dependency>
  </dependencies>
```

**And that's how I used it in my code:**
```
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
```
**I am using StringUtils.isAlpha() to check if word contains any symbols besides letters. boolean allLetters does the same thing but with streams.**
