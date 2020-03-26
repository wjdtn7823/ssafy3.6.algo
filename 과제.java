package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 과제 {
	static class Homework implements Comparable<Homework> {
		int day;
		int score;
		public Homework (int day, int score) {
			this.day = day;
			this.score = score;
		}
		@Override
		public int compareTo(Homework hw) {
			return -Integer.compare(this.score, hw.score);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		List<Homework> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Homework(Integer.parseInt(st.nextToken()) ,Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list, new Comparator<Homework>() {
			@Override
			public int compare(Homework hw1, Homework hw2) {
				if(hw1.day == hw2.day) return -Integer.compare(hw1.score, hw2.score);
				return -Integer.compare(hw1.day, hw2.day);
			}
			
		});
		
		int total = 0;
		PriorityQueue<Homework> pq = new PriorityQueue<>();
		
		int idx = 0;
		for(int d = list.get(0).day; d > 0; d--) {
			while(idx < list.size() && list.get(idx).day == d) {
				pq.offer(list.get(idx++));
			}
			if(pq.isEmpty()) continue;
			total += pq.poll().score;
		}
		
		System.out.println(total);
	}

}
