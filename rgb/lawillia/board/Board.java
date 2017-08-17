package rgb.lawillia.board;

import java.awt.Color;
import java.awt.Graphics;

public class Board {
	/* 盤面に関する変数 */
	final public static int squareSize = 50;								// マスのサイズ
	final public static int squareHalf = squareSize / 2;					// マスのサイズの半分
	final public static int numOfSquareHeight = 9;						// マスの数(高さ)
	final public static int numOfSquareWidth = 16;						// マスの数(幅)
	final public static int boardY = 100;									// 盤面より上の高さ

	public static int boardHeight = squareSize * numOfSquareHeight;		// 盤面の高さ
	public static int boardWidth = squareSize * numOfSquareWidth;			// 盤面の幅
	public static int boardY2 = boardY + boardHeight + squareSize;			// 盤面より下の高さ
	public static int[][] board = new int[numOfSquareHeight][numOfSquareWidth];

	/* 盤面 */
	// 盤面の初期化
	public static void initBoard() {
		for (int y = 0; y < numOfSquareHeight; y++) {
			for (int x = 0; x < numOfSquareWidth; x++) {
				board[y][x] = SquareType.squareIsBlank;
			}
		}
	}

	// 盤面の描画
	public static void drawBoard(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(boardWidth - squareSize, boardY + squareHalf, squareSize, boardHeight + squareHalf);

		g.setColor(Color.WHITE);
		// 縦線
		for(int i = 1; i < numOfSquareWidth; i++) {
			g.drawLine(i*squareSize, boardY + squareSize, i*squareSize, boardHeight + boardY + squareSize);
		}
		// 横線
		for(int i = 2; i < numOfSquareHeight+1; i++) {
			g.drawLine(0, boardY + i*squareSize, boardWidth, boardY + i*squareSize);
		}
		// 外枠
		g.drawRect(0, boardY + squareSize, boardWidth, boardHeight);
	}
}
