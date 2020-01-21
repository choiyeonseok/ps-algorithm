package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110 {

	static int n, c;
	static int[] a;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/input/P2110.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(a);
		int ans = 1; 
		int left = 1;
		int right = a[n-1] - a[0];  // ���� ���ʰ� �������� �Ÿ� ���� max

		while (left <= right) {
			int mid = (left + right) / 2;
			if (possible(a, c, mid)) { 
				ans = Math.max(ans,  mid);
				left = mid + 1; //  �Ÿ��� ��  �ø���, �Ÿ� �ִ밪�� �����Ѵ�.
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(ans);
		
	}	

	public static boolean possible(int[] a, int c, int mid) {
		int cnt = 1;
		int last = a[0];
		for (int house : a) {
			if (house - last >= mid) { // ���Ⱑ �ٽ�.
				cnt += 1;
				last = house;
			}
		}
		return cnt >= c; // ���� ������ �� ������ c ���� ������ �Ÿ��� �� �÷��� �ȴ�.
	}
}
