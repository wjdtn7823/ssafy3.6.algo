import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    public static class MyScanner{
        BufferedReader bf;
        StringTokenizer st;
         
         MyScanner(){
            bf = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while(st==null|| !st.hasMoreTokens()) {
                try{st = new StringTokenizer(bf.readLine());}
                catch(Exception e) {
                    e.printStackTrace();
                }
                 
            }
            return st.nextToken();
        }
         
        int nextInt() {
            return Integer.parseInt(next());
        }
         
        
         
    }
     
    static long answer = 0;
    static int n=0;
     
     
    public static class Worm{
        int x,y;
 
        public Worm(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
         
    }
    static Worm worms[];
    static boolean v[];
     
     
    public static void dfs(int cnt , int idx) {
        if(cnt==n/2) {
            long x1=0,x2=0;
            long y1=0,y2=0;
            for(int i =0  ;i <n;i++) {
                if(v[i]==false) {
                    x1+=worms[i].x;
                    y1+=worms[i].y;
                }
                else {x2+=worms[i].x;
                    y2+=worms[i].y;
                }
            }
            answer = Math.min(answer, (x1-x2)*(x1-x2)+ (y1-y2)*(y1-y2));
        }
        else {
            for(int i = idx;i<n;i++) {
                v[i] = true;
                dfs(cnt+1,i+1);
                v[i] = false;
            }
             
             
             
        }
    }
     
     
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyScanner sc = new MyScanner();
         
        int test_num = sc.nextInt();
         
        for(int tc = 1; tc<=test_num;tc++) {
             
             
            answer= 999999999999999l;
            n = sc.nextInt();
            worms = new Worm[n];
            v = new boolean[n];
            for(int i = 0 ;i <n;i++) {
                int x = sc.nextInt();
                int y=  sc.nextInt();
                worms[i] = new Worm(x,y);
            }
             
            dfs(0,0);
             
             
             
            System.out.printf("#%d %d\n", tc , answer);
             
             
             
        }
    }
 
}