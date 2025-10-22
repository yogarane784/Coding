import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'findSmallestMissingPositive' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY orderNumbers as parameter.
     */

    public static int findSmallestMissingPositive(List<Integer> orderNumbers) {
    
       int n = orderNumbers.size();
        
       for(int i=0;i<n;i++) {
        
        int current = orderNumbers.get(i);
        while(current != i+1 
        && current <= n 
        && current > 0 
        && orderNumbers.get(current-1)!=current) {
            //swap
            int temp = orderNumbers.get(orderNumbers.get(i)-1); 
            orderNumbers.set(orderNumbers.get(i)-1, orderNumbers.get(i));
            orderNumbers.set(i, temp);

            current = orderNumbers.get(i);
        } 
       }
       
       for(int i=0;i<n;i++) {
            if(orderNumbers.get(i) != i+1) {
                return i+1;
            }
        }
       return orderNumbers.size()+1;
    }
}
}
