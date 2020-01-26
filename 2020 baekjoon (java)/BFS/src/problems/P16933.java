package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16933 {

	static class Point{
		int x;
		int y;
		int k;
		int t;
		Point(int x, int y, int k, int t){
			this.x = x;
			this.y = y;
			this.k = k;
			this.t = t;
		}
	}
	static int n,m,l;
	static int[][] a;
	static int[][][][] d;
	static Queue<Point> q = new LinkedList<>();
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/input/P16933.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		a = new int[n+1][m+1];
		d = new int[n+1][m+1][l+1][2];
		for (int i = 1; i <= n; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 1; j <= m; j++) {
				a[i][j] = row[j-1] - '0';
			}
		}
		
		d[1][1][0][0] = 1; // �����ϴ� ĭ�� �����ؼ� ����.
		q.offer(new Point(1, 1, 0, 0));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int k = cur.k;
			int t = cur.t;
			for (int p = 0; p < 4; p++) {
				int nx = x + dx[p];
				int ny = y + dy[p];
				if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
				
				//  ���� �μ��� ������ �� �ִ� ��� (������ �μ��� �ִ�.)
				if (t == 0 && k+1 <= l && d[nx][ny][k+1][1-t] == 0 && a[nx][ny] == 1) {
					q.offer(new Point(nx, ny, k+1, 1-t));
					d[nx][ny][k+1][1-t] = d[x][y][k][t] + 1;
				} 
				//  ���� �ƴ� ���
				if (d[nx][ny][k][1-t] == 0 && a[nx][ny] == 0) {
					q.offer(new Point(nx, ny, k, 1-t));
					d[nx][ny][k][1-t] = d[x][y][k][t] + 1;
				}
			}
			// �̵����� �ʰ� ���� ĭ�� �ӹ����� ���
			if (d[x][y][k][1-t] == 0) {
				d[x][y][k][1-t] = d[x][y][k][t] + 1;
				q.offer(new Point(x, y, k, 1-t));
			}
		}
		
		// -1�� ������ �����ϰ� ���� ���� �� ���.
		// ���δ� -1�϶��� -1�� ���
		int ans = -1;
		for (int t = 0; t < 2; t++) {
			for (int i = 0; i <= l; i ++) {
				if (d[n][m][i][t] == 0) continue;
				if (ans == -1 || ans > d[n][m][i][t]) {
					ans = d[n][m][i][t];
				}
			}
		}
		System.out.println(ans);
	}

}