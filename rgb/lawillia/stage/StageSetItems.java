package rgb.lawillia.stage;

import java.util.Random;

import rgb.lawillia.board.Board;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.player.Player;
import rgb.lawillia.ui.Log;

public class StageSetItems {
	public static int canPlaceItems = 0;							// アイテム生成
	public static int defaultPlaceItems = 0;						// アイテム生成（確定）
	public static int adjustSetTurnEnemiesInWall = 0;				// 乱数への補正

	public static void setItem() {
		if (canPlaceItems == 0) {
			Random rand = new Random();
			int r = rand.nextInt(500);
			int i = Player.playerMaxLife - Player.playerDefaultLife - Enemy.enemyLevel;
			int st = SquareType.squareIsItemTurnEnemies;

			// 補正
			if(i < 1) i = 1;
			if(Player.moveTurn%4 == 0) i *= 2;
			if(Player.isPlayerInjured) i *= 2;
			if(i > 250) i = 250;
			if(defaultPlaceItems < 4) i = 250;

			// 設置するかどうかの判定
			if (r < i){
				// アイテムの抽選
				r = rand.nextInt(4);
				if (r < 3) {
					st = SquareType.squareIsItemTurnEnemies;
				} else {
					st = SquareType.squareIsItemDestroyWalls;
				}

				// 確定
				if (defaultPlaceItems == 0) {
					st = SquareType.squareIsItemTurnEnemies;
					Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
					defaultPlaceItems++;
				} else if (defaultPlaceItems == 1) {
					st = SquareType.squareIsItemDestroyWalls;
					Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
					defaultPlaceItems++;
				} else if (defaultPlaceItems == 2) {
					st = SquareType.squareIsItemFreezeEnemies;
					Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
					defaultPlaceItems++;
				} else if (defaultPlaceItems == 3) {
					st = SquareType.squareIsItemTurnEnemies;
					defaultPlaceItems++;
				}
				// 盤面上部に設置
				if(r%2 == 0){
					for (int y = 0; y < Board.numOfSquareHeight; y++) {
						if (Board.board[y][Board.numOfSquareWidth-1] == SquareType.squareIsBlank) {
							Board.board[y][Board.numOfSquareWidth-1] = st;
							canPlaceItems = 12;
							break;
						}
					}
				// 盤面下部に設置
				} else {
					for (int y = 0; y < Board.numOfSquareHeight; y++) {
						if (Board.board[Board.numOfSquareHeight-y-1][Board.numOfSquareWidth-1] == SquareType.squareIsBlank) {
							Board.board[Board.numOfSquareHeight-y-1][Board.numOfSquareWidth-1] = st;
							canPlaceItems = 12;
							break;
						}
					}
				}
			}

		} else {
			canPlaceItems--;
		}
	}

	public static void setItemInWall(int r) {
		int lv = Player.playerMaxLife - Player.playerDefaultLife - Enemy.enemyLevel;
		if (lv < 0) lv = 0;
		if (lv > 10) lv = 10;

		if (r < 10 + lv) { // 10～20%
			Board.board[Player.playerY][Player.playerX] = SquareType.squareIsItemTurnEnemies;
			adjustSetTurnEnemiesInWall = 0;
		} else if (r < 15 + lv*2) { // 5～15%
			Board.board[Player.playerY][Player.playerX] = SquareType.squareIsItemDestroyWalls;
		} else if (r < 20 + lv*3) { // 5～15%
			Board.board[Player.playerY][Player.playerX] = SquareType.squareIsItemFreezeEnemies;
		} else {
			if (adjustSetTurnEnemiesInWall >= 5) {
				// アイテムが出ないことが続くと「ターンエネミー」が出るように補正
				Board.board[Player.playerY][Player.playerX] = SquareType.squareIsItemTurnEnemies;
				adjustSetTurnEnemiesInWall = 0;
			} else {
				Board.board[Player.playerY][Player.playerX] = SquareType.squareIsBlank;
				adjustSetTurnEnemiesInWall++;
			}
		}
	}
}
