import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Flower implements Comparable<Flower> {
	int start, end;

	Flower(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Flower o) {
		// TODO Auto-generated method stub
		if (this.end == o.end)
			return -(this.start - o.start);
		else
			return this.end - o.end;
	}
}

public class 공주님의정원 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_garden.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		Flower flowers[] = new Flower[n];
		for (int i = 0; i < n; i++) {
			int start;
			int finish;

			st = new StringTokenizer(bf.readLine());
			start = 100 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			finish = 100 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			flowers[i] = new Flower(start, finish);
		}

		Arrays.sort(flowers);

		int start = 1201;// 12월 31일
		int idx = n - 1;
		int answer = 0;

		while (start > 301) {
			int i, j;
			int min_start = 9999;

			for (i = idx; i >= 0; i--) {
				if (flowers[i].end < start)
					break;
				if (min_start > flowers[i].start)
					min_start = flowers[i].start;
			}

			if (start == min_start)
				break;
			start = min_start;

			answer++;

		}

		if (start <= 301)
			System.out.println(answer);
		else
			System.out.println("0");
	}

}
