import java.util.*;
import java.io.*;
 
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
     
     
    static class Box implements Comparable<Box>{
        int x,y;
 
        public Box(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
 
        @Override
        public int compareTo(Box o) {
            // TODO Auto-generated method stub
            if(this.x*this.y==o.x*o.y) return this.x-o.x;
            return this.x *this.y - o.x* o.y;
        }
    }
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stubs
        //System.setIn(new FileInputStream("res/input_hang.txt"));
        MyScanner sc = new MyScanner();
        int test_num = sc.nextInt();
         
        for(int tc = 1; tc<=test_num;tc++) {
            int n  = sc.nextInt();
            int map[][] = new int[n][n];
            int answer = 0;
            boolean v[][] = new boolean[n][n];
            for(int i = 0 ;i <n;i++) {
                 
                for(int j =0 ; j<n;j++) {
                    map[i][j] =sc.nextInt();
                }
            }
            ArrayList <Box> ans =  new ArrayList <Box>();
            for(int i  =0  ;i < n;i++) {
                for(int j =0 ; j <n;j++) {
                    if(map[i][j]!=0&&!v[i][j]) {
                        answer++;
                        //System.out.printf("i=%d j=%d\n",i,j);
                        int k1 = 0 ; int k2=0;
                         
                        while((i+k1)<n&&map[i+k1][j]!=0) {
                            k1++;
                             
                        }
                        while((j+k2)<n&&map[i][j+k2]!=0) {
                            k2++;
                             
                        }
                        for(int p=0 ;p<k1 ;p++) {
                            for(int q= 0 ; q<k2;q++) {
                                v[i+p][j+q] = true;
                            }
                        }
                        ans.add(new Box(k1,k2));
                         
                    }
                }
            }
             
            Collections.sort(ans);
             
             
             
             
            System.out.printf("#%d %d ",tc , answer);
             
            for(int i = 0 ; i<ans.size();i++) {
                System.out.printf("%d %d ",ans.get(i).x,ans.get(i).y);
            }
            System.out.println();
             
        }
         
    }
 
}