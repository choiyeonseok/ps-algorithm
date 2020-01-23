package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1202 {
	
	static int n, m;
	static Jewel[] a;
	static long ans;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static class Jewel implements Comparable<Jewel>{
		int weight;
		int value;
		int isJewel;
		Jewel(int weight){
			this.weight = weight;
			isJewel = 1;
		}
		Jewel(int weight, int value){
			this.weight = weight;
			this.value = value;
			isJewel = 0;
		}
		@Override
		public int compareTo(Jewel jj) {
			if (this.weight < jj.weight) {
				return -1;
			} else if (this.weight == jj.weight) {
				if (this.isJewel < jj.isJewel) {
					return -1;
				} else if (this.isJewel == jj.isJewel) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
			
		}
		
		@Override
		public String toString() {
			if (isJewel == 0) {
				return " " + this.weight + " " + this.value;
			} else {
				return " " + this.weight + " ";	
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/input/P1202.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		a = new Jewel[n+m];
		// 1. �Է� ��
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			a[i] = new Jewel(w, v);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			a[i + n] = new Jewel(w);
		}
		 
		Arrays.sort(a);	// ����,	
		for (Jewel p : a) {
			if (p.isJewel == 0) { // ����
				pq.offer(-p.value); // �ִ����� ���ϱ� ���ؼ� ������ �������
			}
			else {  //  �����̸�
				 if (!pq.isEmpty()) {
					 ans += (long)-pq.poll();
				 }
			}
		}
		System.out.println(ans);
	}	
	
	
}
