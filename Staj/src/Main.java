public static String reverse(String input)
{
    int[] codePoints=input.codePoints().toArray();
    StringBuilder reversed=new StringBuilder();
    for(int i=codePoints.length-1;i>=0;i--) reversed.appendCodePoint(codePoints[i]);
    return reversed.toString();
}

public void main(String[] args)
{
    Scanner scanner=new Scanner(System.in); //Добавих текстът да се въвежда от конзолата и да се работи с нея вместо с предварително зададен string
    String original=scanner.next();
    String reversed=reverse(original);
    System.out.println("Оригинал:"+original);
    System.out.println("Обърнат:"+reversed);
}
