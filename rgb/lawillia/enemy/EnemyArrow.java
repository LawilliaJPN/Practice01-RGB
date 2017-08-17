package rgb.lawillia.enemy;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.player.Items;
import rgb.lawillia.player.Player;
import rgb.lawillia.ui.Log;
import rgb.lawillia.ui.Score;

public class EnemyArrow {
	// 敵（矢印）の攻撃
	public static void enemyArrowAttack(int x, int y, int enemyType) {
		// ログ表示のための変数
		String assailant = SquareType.getSquareType(x, y);
		String victim = "";
		int x2 = x, y2 = y;
		int x3 = x2 + BoardNumber.boardNumber;

		// 攻撃の威力
		int damage = Enemy.getAttackDamage(enemyType);

		// 無限ループ
		while(true) {
			// 敵の向きの方向へ判定するマスを移動
			if (enemyType == SquareType.squareIsEnemyLeftArrow) x--;
			if (enemyType == SquareType.squareIsEnemyRightArrow) x++;
			if (enemyType == SquareType.squareIsEnemyUpArrow) y--;
			if (enemyType == SquareType.squareIsEnemyDownArrow) y++;

			// 盤面の外へ行くと、その攻撃は終わり
			if (x < 0) break;
			if (x > Board.numOfSquareWidth - 1) break;
			if (y < 0) break;
			if (y > Board.numOfSquareHeight - 1) break;

			// 壁に攻撃が当たった場合
			if (Board.board[y][x] == SquareType.squareIsImpassable) {
				if (damage >= 5) {
					try{
						// SEを鳴らす
						Audio.clipWallBroken.stop();
						Audio.clipWallBroken.play();
					} catch (NullPointerException e) {

					}

					int x4 = x + BoardNumber.boardNumber;
					Log.updateLog("Destroy:" + x3 + "列目の" + assailant + "が、" + x4 + "列目の壁を破壊した。");
					Board.board[y][x] = SquareType.squareIsBlank;
				} else if (damage >=3) {
					int x4 = x + BoardNumber.boardNumber;
					Log.updateLog("Attack:" + x3 + "列目の" + assailant + "が、" + x4 + "列目の壁にひびを入れた。");
					Board.board[y][x] = SquareType.squareIsCrackedWall;
				}
				break;
			} else if (Board.board[y][x] == SquareType.squareIsCrackedWall) {
				try{
					// SEを鳴らす
					Audio.clipWallBroken.stop();
					Audio.clipWallBroken.play();
				} catch (NullPointerException e) {

				}

				int x4 = x + BoardNumber.boardNumber;
				Log.updateLog("Destroy:" + x3 + "列目の" + assailant + "が、" + x4 + "列目の壁を破壊した。");
				Board.board[y][x] = SquareType.squareIsBlank;
				break;
			}

			// 敵から敵への攻撃
			if (Board.board[y][x] < 0) {
				try{
					// SEを鳴らす
					Audio.clipArrowAttack.stop();
					Audio.clipArrowAttack.play();
				} catch (NullPointerException e) {

				}

				// ログの表示
				victim = SquareType.getSquareType(x, y);
				int x4 = x + BoardNumber.boardNumber;
				Log.updateLog("Kill:" + x3 + "列目の" + assailant + "が、" + x4 + "列目の" + victim + "を倒した。");

				// スコアの追加
				int exp = Enemy.getAttackDamage(Board.board[y][x]);
				Score.updateScore(exp * Player.killCombo * 2);

				// 敵の数を減らす
				Enemy.numOfEnemy--;
				if (Board.board[y][x] == SquareType.squareIsEnemyTriangle) Enemy.numOfTriangle--;
				if (Board.board[y][x] == SquareType.squareIsEnemyShield) Enemy.numOfShield--;

				if (Board.board[y][x] == SquareType.squareIsEnemyShield) {
					Board.board[y][x] = SquareType.squareIsItemDestroyWalls;
					Log.updateLog("Item:" + victim + "はアイテム「" + Items.getItemName("itemDestroyWalls") + "」を落とした。");
				} else if (Board.board[y][x] == SquareType.squareIsEnemyTriangle){
					Board.board[y][x] = SquareType.squareIsItemTurnEnemies;
					Log.updateLog("Item:" + victim + "はアイテム「" + Items.getItemName("itemTurnEnemies") + "」を落とした。");
				} else {
					Board.board[y][x] = SquareType.squareIsBlank;
				}
				break;
			}

			// 敵からプレイヤーへの攻撃
			if (Board.board[y][x] == SquareType.squareIsPlayer) {
				try{
					// SEを鳴らす
					Audio.clipArrowAttack.stop();
					Audio.clipArrowAttack.play();
				} catch (NullPointerException e) {

				}
				Log.updateLog("Attack:" + x3 + "列目の" + assailant + "から、" + damage + "ダメージを受けた。");
				Player.subPlayerLife(damage);
				break;
			}
		}

		// 敵の向きの変更
		if (Board.board[y2][x2] == SquareType.squareIsEnemyLeftArrow) {
			if (x2 < Player.playerX) {
				Board.board[y2][x2] = SquareType.squareIsEnemyRightArrow;
				// Log.updateLog("Move:" + x3 + "列目の" + assailant + "の向きが変化した");
			}
		} else if (Board.board[y2][x2] == SquareType.squareIsEnemyRightArrow) {
			if (x2 > Player.playerX) {
				Board.board[y2][x2] = SquareType.squareIsEnemyLeftArrow;
				// Log.updateLog("Move:" + x3 + "列目の" + assailant + "の向きが変化した");
			}
		} else if (Board.board[y2][x2] == SquareType.squareIsEnemyDownArrow) {
			if (y2 > Player.playerY) {
				Board.board[y2][x2] = SquareType.squareIsEnemyUpArrow;
				// Log.updateLog("Move:" + x3 + "列目の" + assailant + "の向きが変化した");
			}
		} else if (Board.board[y2][x2] == SquareType.squareIsEnemyUpArrow) {
			if (y2 < Player.playerY) {
				Board.board[y2][x2] = SquareType.squareIsEnemyDownArrow;
				// Log.updateLog("Move:" + x3 + "列目の" + assailant + "の向きが変化した");
			}
		}
	}
}
