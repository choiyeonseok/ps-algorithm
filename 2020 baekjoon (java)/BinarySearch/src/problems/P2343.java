package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P2343 {
	
	static int n, m;
	static int[] a;
	
	// go: ũ�Ⱑ size�� ��緹�̷� ��ȭ���� �� m�� ������ ��緹�̰� ��������? (���� �����ȿ� ������)
	// go -> true�̸� size�� �� �۰� ����� �� �ȴٴ� ���̴�.
	public static boolean go(int size) {
		int cnt = 1;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (sum + a[i] > size) {
				cnt += 1;
				sum = a[i];
			} else {
				sum += a[i];
			}
		}
		return cnt <= m;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/input/P2343.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		for (int i =0 ; i < n; i++) {
			if (left < a[i]) {
				left = a[i];
			}
			right += a[i];
		}
		
		int ans = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (go(mid)) { // �����ϸ� ������ �� �۰� (�ִ��� �ּҰ��� ���ϹǷ�)
				ans = mid;
				right = mid - 1;
			} else { // �Ұ����ϸ� ������ �� ũ��
				left = mid + 1;
			}
		}
		
		System.out.println(ans);
		
	}	
}
