package rgb.lawillia.board;

import java.awt.Color;
import java.awt.Graphics;

import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.player.Player;

public class DrawSquare {
	// 盤面上の描画
	public static void drawOnBoard(Graphics g) {
		for (int y = 0; y < Board.numOfSquareHeight; y++) {
			for (int x = 0; x < Board.numOfSquareWidth; x++) {
				int veX = x * Board.squareSize;
				int veY = y * Board.squareSize + Board.boardY + Board.squareSize;

				if (Board.board[y][x] == SquareType.squareIsPlayer) {
					// プレイヤーがいるマス
					g.setColor(Color.RED);
					g.fillOval(veX + 3, veY + 3, Board.squareSize - 6, Board.squareSize - 6);
					// 体力の情報
					g.setColor(Color.WHITE);
					if (Player.playerLife < 10) {
						g.drawString("" + Player.playerLife, veX + Board.squareHalf - 3, veY + Board.squareHalf + 5);
					} else if (Player.playerLife < 100) {
						g.drawString("" + Player.playerLife, veX + Board.squareHalf - 7, veY + Board.squareHalf + 5);
					}

				/* 壁 */
				} else if (Board.board[y][x] == SquareType.squareIsImpassable) {
					// 壁
					g.setColor(Color.WHITE);
					g.fillRect(veX, veY, Board.squareSize, Board.squareSize);
				} else if (Board.board[y][x] == SquareType.squareIsCrackedWall) {
					// ひび割れた壁
					g.setColor(Color.WHITE);
					g.fillRect(veX, veY, Board.squareSize, Board.squareSize);
					g.setColor(Color.BLACK);
					g.drawLine(veX, veY, veX + Board.squareSize, veY + Board.squareSize);
					g.drawLine(veX + Board.squareSize, veY, veX, veY + Board.squareSize);

				/* アイテム */
				} else if (Board.board[y][x] == SquareType.squareIsItemFreezeEnemies) {
					// アイテム（停止）
					int freezeX = Board.squareSize / 5;
					g.setColor(Color.YELLOW);
					g.fillRect(veX + freezeX, veY + 10, freezeX, Board.squareSize - 20);
					g.fillRect(veX + freezeX*3, veY + 10, freezeX, Board.squareSize - 20);
				} else if (Board.board[y][x] == SquareType.squareIsItemTurnEnemies) {
					// アイテム（回転）
					g.setColor(Color.YELLOW);
					g.drawOval(veX + 10, veY + 10, Board.squareSize - 20, Board.squareSize -20);
					g.drawLine(veX + Board.squareHalf, veY + 10, veX + Board.squareHalf - 5, veY + 5);
					g.drawLine(veX + Board.squareHalf, veY + 10, veX + Board.squareHalf - 5, veY + 15);
					g.drawLine(veX + Board.squareHalf, veY + Board.squareSize - 10, veX + Board.squareHalf + 5, veY + Board.squareSize - 5);
					g.drawLine(veX + Board.squareHalf, veY + Board.squareSize - 10, veX + Board.squareHalf + 5, veY + Board.squareSize - 15);
				} else if (Board.board[y][x] == SquareType.squareIsItemDestroyWalls) {
					// アイテム（破壊）
					g.setColor(Color.YELLOW);
					g.fillRect(veX + 10, veY + 10, Board.squareSize - 20, Board.squareSize - 20);
					g.setColor(Color.BLACK);
					g.drawLine(veX + 10, veY + 10, veX + Board.squareSize - 10, veY + Board.squareSize - 10);
					g.drawLine(veX + Board.squareSize - 10, veY + 10, veX + 10, veY + Board.squareSize - 10);
				/* 敵 */
				} else if (Board.board[y][x] == SquareType.squareIsEnemyLeftArrow) {
					// 敵（左矢印）
					g.setColor(Color.GREEN);
					g.drawLine(veX, veY + Board.squareHalf, veX + Board.squareSize, veY + Board.squareHalf);
					g.drawLine(veX, veY + Board.squareHalf, veX + Board.squareHalf, veY);
					g.drawLine(veX, veY + Board.squareHalf, veX + Board.squareHalf, veY + Board.squareSize);
					drawEnemyNumber(g, x, y, veX ,veY, true);
				} else if (Board.board[y][x] == SquareType.squareIsEnemyRightArrow) {
					// 敵（右矢印）
					g.setColor(Color.GREEN);
					g.drawLine(veX, veY + Board.squareHalf, veX + Board.squareSize, veY + Board.squareHalf);
					g.drawLine(veX + Board.squareSize, veY + Board.squareHalf, veX + Board.squareHalf, veY);
					g.drawLine(veX + Board.squareSize, veY + Board.squareHalf, veX + Board.squareHalf, veY + Board.squareSize);
					drawEnemyNumber(g, x, y, veX ,veY, true);
				} else if (Board.board[y][x] == SquareType.squareIsEnemyUpArrow) {
					// 敵（上矢印）
					g.setColor(Color.GREEN);
					g.drawLine(veX + Board.squareHalf, veY, veX + Board.squareHalf, veY + Board.squareSize);
					g.drawLine(veX + Board.squareHalf, veY, veX + Board.squareSize, veY + Board.squareHalf);
					g.drawLine(veX + Board.squareHalf, veY, veX, veY + Board.squareHalf);
					drawEnemyNumber(g, x, y, veX ,veY, true);
				} else if (Board.board[y][x] == SquareType.squareIsEnemyDownArrow) {
					// 敵（下矢印）
					g.setColor(Color.GREEN);
					g.drawLine(veX + Board.squareHalf, veY, veX + Board.squareHalf, veY + Board.squareSize);
					g.drawLine(veX + Board.squareHalf, veY + Board.squareSize, veX, veY + Board.squareHalf);
					g.drawLine(veX + Board.squareHalf, veY + Board.squareSize, veX + Board.squareSize, veY + Board.squareHalf);
					drawEnemyNumber(g, x, y, veX ,veY, true);

				} else if (Board.board[y][x] == SquareType.squareIsEnemyCross) {
					// 敵（十字）
					g.setColor(Color.GREEN);
					g.drawLine(veX, veY + Board.squareHalf, veX + Board.squareSize, veY + Board.squareHalf);
					g.drawLine(veX + Board.squareHalf, veY, veX + Board.squareHalf, veY + Board.squareSize);
					drawEnemyNumber(g, x, y, veX ,veY, true);
				} else if (Board.board[y][x] == SquareType.squareIsEnemyCross2) {
					// 敵（斜め十字）
					g.setColor(Color.GREEN);
					g.drawLine(veX, veY, veX + Board.squareSize, veY + Board.squareSize);
					g.drawLine(veX, veY + Board.squareSize, veX + Board.squareSize, veY);
					drawEnemyNumber(g, x, y, veX ,veY, true);

				} else if (Board.board[y][x] == SquareType.squareIsEnemyTriangle) {
					// 敵（三角形）
					g.setColor(Color.GREEN);
					int polygonX[] = new int[3];
					int polygonY[] = new int[3];
					polygonX[0] = veX + Board.squareHalf;
					polygonX[1] = veX + 3;
					polygonX[2] = veX + Board.squareSize - 3;
					polygonY[0] = veY + 3;
					polygonY[1] = veY + Board.squareSize - 3;
					polygonY[2] = veY + Board.squareSize - 3;
					g.fillPolygon(polygonX, polygonY, 3);
					drawEnemyNumber(g, x, y, veX ,veY, false);
				} else if (Board.board[y][x] == SquareType.squareIsEnemyShield) {
					// 敵（盾）
					g.setColor(Color.GREEN);
					g.fillRect(veX + 3, veY + 3, Board.squareSize - 6, Board.squareHalf);
					int polygonX[] = new int[3];
					int polygonY[] = new int[3];
					polygonX[0] = veX + 3;
					polygonX[1] = veX + Board.squareSize - 6;
					polygonX[2] = veX + Board.squareHalf;
					polygonY[0] = veY + Board.squareHalf + 3;
					polygonY[1] = veY + Board.squareHalf + 3;
					polygonY[2] = veY + Board.squareSize - 3;
					g.fillPolygon(polygonX, polygonY, 3);
					drawEnemyNumber(g, x, y, veX ,veY, false);
				}
			}
		}
	}

	// 敵の数字の描画
	public static void drawEnemyNumber(Graphics g, int x, int y, int veX ,int veY, boolean fillOval) {
		if (fillOval) {
			g.setColor(Color.GREEN);
			g.fillOval(veX + 15, veY + 15, Board.squareSize - 30, Board.squareSize - 30);
		}
		g.setColor(Color.BLACK);
		g.drawString("" + Enemy.getAttackDamage(Board.board[y][x]) , veX + Board.squareHalf - 3, veY + Board.squareHalf + 5);
	}
}
