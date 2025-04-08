import java.util.*;

public static void main(String[] args)
{
    List<Integer> list=new ArrayList<>();
    for(int i=0;i<1_000_000;i++) list.add(i);

    long start,end;

    start=System.nanoTime();
    for(int i=0;i<list.size();i++)
    {
        int val=list.get(i);
    }
    end=System.nanoTime();
    System.out.println("for:"+(end-start)+" ns");

    start=System.nanoTime();
    int i=0;
    while(i<list.size())
    {
        int val=list.get(i++);
    }
    end=System.nanoTime();
    System.out.println("while:"+(end-start)+" ns");

    start=System.nanoTime();
    Iterator<Integer> iter=list.iterator();
    while(iter.hasNext())
    {
        int val=iter.next();
    }
    end=System.nanoTime();
    System.out.println("iterator:"+(end-start)+" ns");
}
