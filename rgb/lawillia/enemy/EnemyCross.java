package rgb.lawillia.enemy;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.player.Player;
import rgb.lawillia.ui.Log;

public class EnemyCross {
	// 敵（十字）の攻撃
	public static void enemyCrossAttack(int x, int y, int enemyType) {
		// ログ表示のための変数
		String assailant = SquareType.getSquareType(x, y);
		// String victim = "";

		// 元の位置の記憶
		int x2 = x, y2 = y;
		int x3 = x2 + BoardNumber.boardNumber;

		// 攻撃の威力
		int damage = Enemy.getAttackDamage(enemyType);

		for(int i = 0; i < 4; i++) {
			x = x2;
			y = y2;

			// 判定するマスを移動
			if (enemyType == SquareType.squareIsEnemyCross) {
				switch(i){
				case 0:
					x--;
					break;
				case 1:
					x++;
					break;
				case 2:
					y--;
					break;
				case 3:
					y++;
					break;
				}
			} else if (enemyType == SquareType.squareIsEnemyCross2) {
				switch(i){
				case 0:
					x--;
					y--;
					break;
				case 1:
					x++;
					y--;
					break;
				case 2:
					x--;
					y++;
					break;
				case 3:
					x++;
					y++;
					break;
				}
			}

			try{
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
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}

			try{
				// 敵からプレイヤーへの攻撃
				if (Board.board[y][x] == SquareType.squareIsPlayer) {
					try{
						// SEを鳴らす
						Audio.clipCrossAttack.stop();
						Audio.clipCrossAttack.play();
					} catch (NullPointerException e) {

					}

					Log.updateLog("Attack:" + x3 + "列目の" + assailant + "から、" + damage + "ダメージを受けた。");
					Player.subPlayerLife(damage);
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		// 敵の向きの変更
		if (Board.board[y2][x2] == SquareType.squareIsEnemyCross) {
			if (x2 != Player.playerX) {
				Board.board[y2][x2] = SquareType.squareIsEnemyCross2;
				// Log.updateLog("Move:" + x3 + "列目の" + assailant + "が回転した。");
			}
		} else if (Board.board[y2][x2] == SquareType.squareIsEnemyCross2) {
			if (x2 != Player.playerX) {
				Board.board[y2][x2] = SquareType.squareIsEnemyCross;
				// Log.updateLog("Move:" + x3 + "列目の" + assailant + "の向きが変化した");
			}
		}

	}
}
