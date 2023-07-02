import java.io.*;
import java.util.*;

public class Othello {
	int turn;
	int winner;
	int board[][];
	// add required class variables here
	int cur = -1000000000; // :0
	int arr[][];
	HashMap<String, Integer> mp = new HashMap<>();

	public Othello(String filename) throws Exception {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		turn = sc.nextInt();
		board = new int[8][8];
		arr = new int[8][8];
		mp.clear();
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				board[i][j] = sc.nextInt();
			}
		}
		winner = -1;
		// Student can choose to add preprocessing here
	}

	// add required helper functions here

	public String hash(int[][] br) {
		String jk = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				jk += (char) (br[i][j] + 1);
			}
		}
		return jk;
	}

	public String[] hsh(int[][] br) {
		String[] st = new String[8];
		String jk = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[0] = jk;
		jk = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 7; j >= 0; j--) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[1] = jk;
		jk = "";
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[2] = jk;
		jk = "";
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[3] = jk;
		jk = "";
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[4] = jk;
		jk = "";
		for (int j = 7; j >= 0; j--) {
			for (int i = 0; i < 8; i++) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[5] = jk;
		jk = "";
		for (int j = 0; j < 8; j++) {
			for (int i = 7; i >= 0; i--) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[6] = jk;
		jk = "";
		for (int j = 7; j >= 0; j--) {
			for (int i = 7; i >= 0; i--) {
				jk += (char) (br[i][j] + 1);
			}
		}
		st[7] = jk;
		return st;
	}

	public int boardScore() {
		/*
		 * Complete this function to return num_black_tiles - num_white_tiles if turn =
		 * 0,
		 * and num_white_tiles-num_black_tiles otherwise.
		 */
		int w = 0, b = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				b += (board[i][j] == 0 ? 1 : 0);
				w += (board[i][j] == 1 ? 1 : 0);
			}
		}
		return (turn == 0 ? (b - w) : (w - b));
	}

	public int boardScore(int[][] br, int tr) {
		/*
		 * Complete this function to return num_black_tiles - num_white_tiles if turn =
		 * 0,
		 * and num_white_tiles-num_black_tiles otherwise.
		 */
		int w = 0, b = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				b += (br[i][j] == 0 ? 1 : 0);
				w += (br[i][j] == 1 ? 1 : 0);
			}
		}
		return (tr == 0 ? (b - w) : (w - b));
	}

	public ArrayList<Integer> val(int[][] br, int r, int c){
		ArrayList<Integer> val = new ArrayList<>();
		// System.out.println(r+" "+c);
		int curi = r-1, curj = c-1;
		int co = 0;
		while (curi>=0 && curj>=0 && br[curi][curj]==(br[r][c]+1)%2){
			curi--;
			curj--;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curi>=0 && curj>=0 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r+1;
		curj = c-1;
		co = 0;
		while (curi<8 && curj>=0 && br[curi][curj]==(br[r][c]+1)%2){
			curi++;
			curj--;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curi<8 && curj>=0 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r+1;
		curj = c+1;
		co=0;
		while (curi<8 && curj<8 && br[curi][curj]==(br[r][c]+1)%2){
			curi++;
			curj++;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curi<8 && curj<8 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r-1;
		curj = c+1;
		co = 0;
		while (curi>=0 && curj<8 && br[curi][curj]==(br[r][c]+1)%2){
			curi--;
			curj++;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curi>=0 && curj<8 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r-1;
		curj = c;
		co = 0;
		while (curi>=0 && br[curi][curj]==(br[r][c]+1)%2){
			curi--;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curi>=0 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r;
		curj = c-1;
		co = 0;
		while (curj>=0 && br[curi][curj]==(br[r][c]+1)%2){
			curj--;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curj>=0 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r;
		curj = c+1;
		co = 0;
		while (curj<8 && br[curi][curj]==(br[r][c]+1)%2){
			curj++;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curj<8 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}

		curi = r+1;
		curj = c;
		co = 0;
		while (curi<8 && br[curi][curj]==(br[r][c]+1)%2){
			curi++;
			co++;
		}
		// System.out.println(curi+" "+curj+"  coor");
		if (curi<8 && br[curi][curj]==-1 && co>0){
			val.add(8*curi+curj);
		}
		return val;
	}

	public ArrayList<Integer> valmo(int[][] br, int tr){
		ArrayList<Integer> arr = new ArrayList<>();
		int[] don = new int[64];
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if (br[i][j]==tr){
					ArrayList<Integer> jk = val(br, i, j);
					for (Integer x:jk){
						if (don[x]==0){
							arr.add(x);
							don[x]++;
						}
					}
				}
			}
		}
		return arr;
	}

	public int[][] play(int[][] bar, int tr, int r, int c){
		int[][] br = new int[8][8];
		for (int i = 0; i<8; i++){
			for (int j = 0; j<8; j++){
				br[i][j] = bar[i][j];
			}
		}
		br[r][c] = tr;
		int curi = r-1, curj = c-1;
		int co = 0;
		ArrayList<Integer> hu = new ArrayList<>();
		while (curi>=0 && curj>=0 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curi--;
			curj--;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();
		curi = r+1;
		curj = c-1;
		co = 0;
		while (curi<8 && curj>=0 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curi++;
			curj--;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		curi = r+1;
		curj = c+1;
		co=0;
		while (curi<8 && curj<8 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curi++;
			curj++;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		curi = r-1;
		curj = c+1;
		co = 0;
		while (curi>=0 && curj<8 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curi--;
			curj++;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		curi = r-1;
		curj = c;
		co = 0;
		while (curi>=0 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curi--;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		curi = r;
		curj = c-1;
		co = 0;
		while (curj>=0 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curj--;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		curi = r;
		curj = c+1;
		co = 0;
		while (curj<8 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curj++;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		curi = r+1;
		curj = c;
		co = 0;
		while (curi<8 && br[curi][curj]==(br[r][c]+1)%2){
			hu.add(8*curi + curj);
			curi++;
			co++;
		}
		if (co>0 && curi>=0 && curj>=0 && curi<8 && curj<8 && br[curi][curj]==tr){
			for (Integer x:hu){
				br[x/8][x%8] = tr;
			}
		}
		hu.clear();

		return br;
	}

	public void dfs(int[][] board, int move, int k, int turn) {
		// System.out.println(move+"  pl");
		String[] hh = hsh(board);
		for (int i=0; i<hh.length; i++){
			int jo = move+1;
			while (jo>0){
				hh[i]+=(char)(jo%10);
				jo/=10;
			}
			mp.put(hh[i], 1);
		}
		// for (int i=0; i<8; i++){
		// 	for (int j=0; j<8; j++){
		// 		System.out.print(board[i][j]+" ");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();
		if (move == k) {
			int curo = boardScore(board, (move+turn)%2);
			// System.out.println(curo+" "+cur+" "+k);
			if (k%2==0){
				if (curo>cur){
					cur = curo;
					arr = board;
				}
			} else {
				if (curo<cur){
					cur = curo;
					arr = board;
				}
			}
		} else {
			ArrayList<Integer> huh = valmo(board, (move+turn)%2);
			for (Integer x:huh){
				// System.out.println(x/8+ " " + x%8);
				int[][] yo = play(board, (move+turn)%2, x/8, x%8);
				String jo = hash(yo);
				int hi = move+2;
				while (hi>0){
					jo+=(char)(hi%10);
					hi/=10;
				}
				if (mp.containsKey(jo)==false){
					dfs(yo, move+1, k, turn);
				}
			}
			if (huh.size()==0){
				dfs(board, move+1, k, turn);
			}
		}
	}

	public int bestMove(int k) {
		/*
		 * Complete this function to build a Minimax tree of depth k (current board
		 * being at depth 0),
		 * for the current player (siginified by the variable turn), and propagate
		 * scores upward to find
		 * the best move. If the best move (move with max score at depth 0) is i,j;
		 * return i*8+j
		 * In case of ties, return the smallest integer value representing the tile with
		 * best score.
		 * 
		 * Note: Do not alter the turn variable in this function, so that the
		 * boardScore() is the score
		 * for the same player is same throughout the Minimax tree.
		 */
		if (k % 2 == 0) {
			cur = -1000000000;
		} else {
			cur = 1000000000;
		}
		dfs(board, 0, k, turn);
		return cur;
	}

	public ArrayList<Integer> fullGame(int k) {
		/*
		 * Complete this function to compute and execute the best move for each player
		 * starting from
		 * the current turn using k-step look-ahead. Accordingly modify the board and
		 * the turn
		 * at each step. In the end, modify the winner variable as required.
		 */
		return new ArrayList<Integer>();
	}
}
