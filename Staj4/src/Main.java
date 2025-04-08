import java.util.*;

public static void main(String[] args)
{

    Scanner scanner=new Scanner(System.in); //Добавих текстът да се въвежда от конзолата и да се работи с нея вместо с предварително зададен string
    String input=scanner.next();
    Map<Integer,Integer> freq=new HashMap<>();

    input.codePoints().forEach(cp->freq.put(cp,freq.getOrDefault(cp,0)+1));

    freq.entrySet().stream()
            .filter(e->e.getValue()>1)
            .forEach(e->System.out.println(Character.toString(e.getKey())+":"+e.getValue()));
}
