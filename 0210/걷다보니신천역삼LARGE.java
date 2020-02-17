package 백준;
import java.util.*;
public class 걷다보니신천역삼LARGE {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long k =2;
        k=(n==1)?0:2;
        for(int i=2;i<=n-1;i++) {
            k= (k * 3) %1000000009;
        }
        
        System.out.println(k);
    }

}
