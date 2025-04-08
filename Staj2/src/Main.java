import java.util.*;

public static void main(String[] args)
{
    Scanner scanner=new Scanner(System.in);
    System.out.println("Въведете текст:");
    String text=scanner.nextLine();
    scanner.close();

    text=text.toLowerCase().replaceAll("[\\p{Punct}]","");
    String[] words=text.split("\\s+");

    Map<String,Integer> wordCount=new HashMap<>();
    for(String word:words)
    {
        if(!word.isEmpty()) wordCount.put(word,wordCount.getOrDefault(word,0)+1);
    }

    List<Map.Entry<String,Integer>> sorted=new ArrayList<>(wordCount.entrySet());
    sorted.sort((a,b)->
    {
        int freqComp=b.getValue().compareTo(a.getValue());
        return(freqComp!=0)?freqComp:a.getKey().compareTo(b.getKey());
    });

    System.out.println("\nРезултат:");
    for(Map.Entry<String,Integer> entry:sorted)
    {
        System.out.println(entry.getKey()+":"+entry.getValue());
    }
}