package rgb.lawillia.enemy;

import rgb.lawillia.board.Board;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.player.Items;
import rgb.lawillia.ui.Log;

public class Enemy {
	/* 敵に関する変数 */
	public static int numOfEnemy = 0;					// 画面上の敵の数
	public static int maxNumOfEnemy = 4;				// 画面上の敵の最大数
	public static int enemyLevel = 1;					// 敵の強さ
	public static int numOfTriangle = 0;				// 盤面上の三角形の数
	public static int numOfShield = 0;				// 盤面上の盾の数


	// 敵のターン（敵の行動）
	public static void enemyAction() {
		if (Items.turnFreezeEnemies > 0) {
			Items.turnFreezeEnemies--;
			Log.updateLog("Item:「" + Items.getItemName("itemFreezeEnemies") + "」の効果で敵は行動できない。");
			if (Items.turnFreezeEnemies  > 0) {
				Log.updateLog("残り" + Items.turnFreezeEnemies + "ターンの間、時間が停止し、敵は行動ができない。");
			} else {
				Log.updateLog("Item:「" + Items.getItemName("itemFreezeEnemies") + "」の効果が切れた。");
			}

		} else {
			for (int y = 0; y < Board.numOfSquareHeight; y++) {
				for (int x = 0; x < Board.numOfSquareWidth-1; x++) {
					if (Board.board[y][x] < 0) {
						// 敵が矢印であるとき
						if (Board.board[y][x] >= -4) {
							EnemyArrow.enemyArrowAttack(x, y, Board.board[y][x]);

						// 敵が十字であるとき
						} else if (Board.board[y][x] >= -6) {
							EnemyCross.enemyCrossAttack(x, y, Board.board[y][x]);
						}
					}
				}
			}
		}
	}

	// 攻撃の威力
	public static int getAttackDamage(int enemyType) {
		int d = 1;

		// 矢印4種
		if (enemyType >= -4) {
			d += enemyLevel/5;

			if (enemyLevel >= 3) {
				if (enemyType == SquareType.squareIsEnemyUpArrow) d++;
				if (enemyType == SquareType.squareIsEnemyDownArrow) d++;
			}

		// 十字2種
		} else if (enemyType >= -6) {
			d += enemyLevel/4;

		// 三角形
		} else if (enemyType == SquareType.squareIsEnemyTriangle) {
			d += enemyLevel/7;
			if (enemyLevel >= 11) d++;
			if (enemyLevel >= 17) d++;
			if (enemyLevel >= 19) d++;

		// 盾
		} else if (enemyType == SquareType.squareIsEnemyShield) {
			if (enemyLevel >= 13) d++;
			if (enemyLevel >= 18) d++;
		}

		// 三角形が盤面上にいるときに矢印・十字を強化
		if (Enemy.numOfTriangle > 0) {
			if (enemyType >= -6) d += getAttackDamage(SquareType.squareIsEnemyTriangle);
		}

		return d;
	}
}
