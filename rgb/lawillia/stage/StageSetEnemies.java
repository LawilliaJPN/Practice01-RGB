package rgb.lawillia.stage;

import java.util.Random;

import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.ui.Tips;

public class StageSetEnemies {
	// 乱数への補正
	public static int adjustSetArrow = 0;
	public static int adjustSetCross = 0;
	public static int adjustSetShield = 0;

	// 敵の設置
	public static void setEnemy() {
		Random rand = new Random();

		// 矢印の配置
		if (Enemy.numOfEnemy < Enemy.maxNumOfEnemy) {
			if (BoardNumber.boardNumber%4 == 2) {
				// 配置する位置の決定
				int r = rand.nextInt(Board.numOfSquareHeight-2);


				if ((Board.board[r+1][Board.numOfSquareWidth-1] == SquareType.squareIsBlank) && (Board.board[r+1][Board.numOfSquareWidth-2] == SquareType.squareIsBlank)) {
					// 配置する敵の決定
					int re = 0;
					if (adjustSetArrow <= -3) {
						re = rand.nextInt(2);
						adjustSetArrow = 0;
					} else if (adjustSetArrow >= 3) {
						re = 2 + rand.nextInt(2);
						adjustSetArrow = 0;
					} else {
						re = rand.nextInt(4);
					}

					// 敵（矢印）の設置
					switch (re) {
					case 0:
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyLeftArrow;
						adjustSetArrow++;
						break;
					case 1:
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyRightArrow;
						adjustSetArrow++;
						break;
					case 2:
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyUpArrow;
						adjustSetArrow--;
						break;
					case 3:
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyDownArrow;
						adjustSetArrow--;
						break;
					}

					// 敵（矢印）の説明
					Tips.arrowTips();

					Enemy.numOfEnemy++;
				}
			}
		}

		// 十字の配置
		if (Enemy.numOfEnemy < Enemy.maxNumOfEnemy) {
			if ((Enemy.enemyLevel >= 2) && (BoardNumber.boardNumber%4 == 3)) {
				// 配置する位置の決定
				int r = rand.nextInt(Board.numOfSquareHeight-2);

				if ((Board.board[r+1][Board.numOfSquareWidth-1] == SquareType.squareIsBlank) && (Board.board[r+1][Board.numOfSquareWidth-2] == SquareType.squareIsBlank)) {
					// 配置する敵の決定
					int re = 0;
					if (adjustSetCross <= -3) {
						adjustSetCross = 0;
					} else if (adjustSetCross >= 3) {
						re = 1;
						adjustSetCross = 0;
					} else {
						re = rand.nextInt(2);
					}

					// 敵（十字）の設置
					switch (re) {
					case 0:
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyCross;
						adjustSetCross++;
						break;
					case 1:
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyCross2;
						adjustSetCross--;
						break;
					}

					// 敵（十字）の説明
					Tips.crossTips();

					Enemy.numOfEnemy++;
				}
			}
		}

		// 三角形の配置
		if (Enemy.numOfEnemy < Enemy.maxNumOfEnemy && Enemy.numOfTriangle <= 0) {
			if ((Enemy.enemyLevel >= 6) && (BoardNumber.boardNumber%8 == 0)) {
				// 配置する位置の決定
				int r = rand.nextInt(Board.numOfSquareHeight-2);

				if ((Board.board[r+1][Board.numOfSquareWidth-1] == SquareType.squareIsBlank) && (Board.board[r+1][Board.numOfSquareWidth-2] == SquareType.squareIsBlank)) {
					// 敵（三角形）の設置
					Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyTriangle;

					// 敵（三角形）の説明
					Tips.triangleTips();

					Enemy.numOfEnemy++;
					Enemy.numOfTriangle++;
				}
			}
		}

		// 盾の配置
		if (Enemy.numOfEnemy < Enemy.maxNumOfEnemy && Enemy.numOfShield <= 0) {
			if (adjustSetShield == 0){
				if ((Enemy.enemyLevel >= 9) && (BoardNumber.boardNumber%4 == 1)) {
					// 配置する位置の決定
					int r = rand.nextInt(Board.numOfSquareHeight-2);

					if ((Board.board[r+1][Board.numOfSquareWidth-1] == SquareType.squareIsBlank) && (Board.board[r+1][Board.numOfSquareWidth-2] == SquareType.squareIsBlank)) {
						// 敵（盾）の設置
						Board.board[r+1][Board.numOfSquareWidth-1] = SquareType.squareIsEnemyShield;

						// 敵（盾）の説明
						Tips.shieldTips();

						Enemy.numOfEnemy++;
						Enemy.numOfShield++;

						// 敵（盾）が出すぎないようにするための補正
						if(Enemy.enemyLevel > 20) adjustSetShield = 15;
						else if (Enemy.enemyLevel > 15) adjustSetShield = 20;
						else if (Enemy.enemyLevel > 10) adjustSetShield = 25;
						else adjustSetShield = 30;
					}
				}
			} else {
				adjustSetShield--;
			}

		}
	}
}
