package ����;

import java.util.*;
import java.io.*;

public class ��������Ʈ {
    public static int[] a, result;
    public static int n;
    public static boolean[] v;
    public static int answer = 0;
    public static void checkCycle(int idx) {
        //System.out.printf("idx =%d\n",idx);
        v[idx] = true;
        int next= a[idx];
        
        if(!v[next]) { //�湮�� ���
            checkCycle(next);
        }
        else if(result[next]==0) //�湮������ ����Ŭ ����� Ȯ�ε��� ���� ���
        {
            
            for(int i = next;i!=idx;i=a[i]) {
                //System.out.printf("i=%d\n",i);
               answer++;//����Ŭ ����
            }
            answer++; //�ڱ��ڽ�����
            
        }
        result[idx] = 1; //��� üũ
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("res/input_��������Ʈ.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int test_num = Integer.parseInt(bf.readLine());

        for (int tc = 1; tc <= test_num; tc++) {
            n = Integer.parseInt(bf.readLine());
            a = new int[n + 1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            answer = 0;
            result = new int[n + 1];
            v= new boolean[n+1];
            for (int i = 1; i <=n ; i++) {
               // System.out.printf("i=%d\n",i);
                if(!v[i])
                checkCycle(i);
               }
            
           
            /*for(int i = 1;i<=n;i++) {
           //     System.out.printf("%d ",result[i]);
            }*/
            

            System.out.println(n-answer);
        }
    }

}
