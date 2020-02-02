package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class EGG{
	int durability, weight;
	boolean live;
	EGG(){}
	EGG(int d1, int w1, boolean l1){
		durability = d1; weight = w1; live = l1;
	}
}

public class Solution_달걀로_달걀깨기 {

	static int n, Ans;
	static EGG egg[];
	
	static void dfs(int i, int cnt) {
		if(i == n) {
			Ans = Math.max(Ans, cnt); return;
		}
		
		boolean isCracked = false;
		for(int j=0; j<n; j++) {
			if(i == j || !egg[j].live || !egg[i].live) continue;
			isCracked = true;
			egg[i].durability -= egg[j].weight; egg[j].durability -= egg[i].weight;
			int cntt = 0;
			if(egg[i].durability <= 0) {
				egg[i].live = false; cntt++;
			}if(egg[j].durability <= 0) {
				egg[j].live = false; cntt++;
			}
			dfs(i + 1, cntt + cnt);
			egg[i].live = true; egg[j].live = true;
			egg[i].durability += egg[j].weight; egg[j].durability += egg[i].weight;
		}
		if(!isCracked) dfs(i + 1, cnt);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		egg = new EGG[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			egg[i] = new EGG(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), true);
		}dfs(0, 0);
		
		System.out.println(Ans);
	}

}
