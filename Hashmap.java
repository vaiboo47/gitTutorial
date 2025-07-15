import java.util.*;
public class Hashmap {
    public static void main(String[] args) {
        int[] a = {1, 15, 12, 1, 2, 3, 12};        
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<a.length;i++)
        {
            int key = a[i];
            int freq=0;
            if(hm.containsKey(key))
            freq = hm.get(key);
            freq++;
            hm.put(key, freq);
        }
        for(Integer i:hm.keySet())
        {
            System.out.println("Key: "+i+", Value: "+hm.get(i));
        }
        int max = Collections.max(hm.values());
        System.out.println("Maximum Occurences");
        for(Integer i:hm.keySet())
        {
            if(hm.get(i)==max)
            {
                System.out.println(i+" Appears "+max+" times");
            }
        }
    }
}
