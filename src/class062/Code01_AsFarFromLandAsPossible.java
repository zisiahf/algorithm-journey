package class062;

// 地图分析
// 你现在手里有一份大小为 n x n 的 网格 grid
// 上面的每个 单元格 都用 0 和 1 标记好了其中 0 代表海洋，1 代表陆地。
// 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
// 并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：
// (x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
// 测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/
public class Code01_AsFarFromLandAsPossible {

	public static int MAXN = 101;

	public static int MAXM = 101;

	public static int[][] queue = new int[MAXN * MAXM][2];

	public static int l, r;

	public static boolean[][] visited = new boolean[MAXN][MAXM];

	public static int maxDistance(int[][] grid) {
		l = r = 0;
		int n = grid.length;
		int m = grid[0].length;
		int seas = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = false;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					visited[i][j] = true;
					queue[r][0] = i;
					queue[r++][1] = j;
				} else {
					seas++;
				}
			}
		}
		if (seas == 0 || seas == n * m) {
			return -1;
		}
		int level = -1;
		while (l < r) {
			int size = r - l;
			for (int i = 0, x, y; i < size; i++) {
				x = queue[l][0];
				y = queue[l++][1];
				add(x - 1, y, n, m, grid);
				add(x + 1, y, n, m, grid);
				add(x, y - 1, n, m, grid);
				add(x, y + 1, n, m, grid);
			}
			level++;
		}
		return level;
	}

	public static void add(int i, int j, int n, int m, int[][] grid) {
		if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == 0 && !visited[i][j]) {
			visited[i][j] = true;
			queue[r][0] = i;
			queue[r++][1] = j;
		}
	}

}
