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

public class P1790 {

	static int n, k;
	static int[] cards;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/input/P1790.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if (calc(n) < k) {
			System.out.println(-1);
			return;
		}
		
		// �̺�Ž��
		int left = 1;
		int right = n;
		int ans = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			long len = calc(mid);
			if (k > len) { // n�� ũ�� ����� ��
				left = mid + 1;
			} else { // n�� �۰� ����� ��
				ans = mid;
				right = mid - 1;
			}
		}
		String s = Long.toString(ans);
		long l = calc(ans);
		System.out.println(s.charAt((int)(s.length()-(l-k)-1)));
		
	}
	
	// n���� ���� �̾� �ٿ��� �� ����
	public static long calc(int n) {
		long ans = 0;
		for (int start=1, len=1; start <= n; start *= 10, len++) {
			int end = start*10 - 1;
			if(end > n) {
				end = n;
			}
			ans += (long)(end - start + 1) * len;
		}
		return ans;
	}
}
