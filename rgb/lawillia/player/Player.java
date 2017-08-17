package rgb.lawillia.player;

import java.util.Random;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.game.Game;
import rgb.lawillia.stage.Stage;
import rgb.lawillia.stage.StageSetItems;
import rgb.lawillia.ui.Log;
import rgb.lawillia.ui.PlayerData;
import rgb.lawillia.ui.Score;

public class Player {
	/* プレイヤーに関する変数 */
	public static int playerX, playerY;						// プレイヤーの盤面上の位置
	public static int playerDefaultLife = 20;					// プレイヤーの初期HP
	public static int playerLife = playerDefaultLife;			// プレイヤーのHP
	public static int playerMaxLife = playerDefaultLife;		// プレイヤーの最大HP
	public static int countKill = 0;							// 敵を倒した回数
	public static int killExp = 0;							// 経験値
	public static int killCombo = 0;							// 連続キル数
	public static int maxCombo = 0;							// 最大コンボ数
	public static int totalMove = 0;							// 移動数
	public static int moveTurn = 0;							// ターン数
	public static boolean isPlayerInjured = false;			// プレイヤーが瀕死かどうか

	// プレイヤーの移動
	public static void movePlayer() {
		Game.canPlayerMove = false;
		Random rand = new Random();
		Log.updateLog("");

		// プレイヤーの移動前の座標記憶
		int tempX = playerX;
		int tempY = playerY;
		boolean playerMove = false;

		// プレイヤーの移動先
		if (Game.leftPressed) playerX--;
		if (Game.rightPressed) playerX++;
		if (Game.upPressed) playerY--;
		if (Game.downPressed) playerY++;

		// プレイヤーの移動を行う
		if ((playerX < 0) || (playerY < 0) || (playerY > Board.numOfSquareHeight-1)) {
			playerX = tempX;
			playerY = tempY;

		// 移動先が「ひび割れた壁」の場合
		} else if (Board.board[playerY][playerX] == SquareType.squareIsCrackedWall) {
			try{
				// SEを鳴らす
				Audio.clipWallBroken.stop();
				Audio.clipWallBroken.play();
			} catch (NullPointerException e) {

			}

			// ひび割れた壁は壊すことができ、一定確率でアイテムが現れる
			StageSetItems.setItemInWall(rand.nextInt(100));

			// スコアの追加
			Score.updateScore(1);

			playerX = tempX;
			playerY = tempY;
			playerMove = true;

		// 移動先が「空白」「敵」「アイテム」の場合
		} else if (Board.board[playerY][playerX] != SquareType.squareIsImpassable) {
			// 移動先が「アイテム」の場合
			if (Board.board[playerY][playerX] >= 4) {
				try{
					// SEを鳴らす
					Audio.clipUseItem.stop();
					Audio.clipUseItem.play();
				} catch (NullPointerException e) {

				}

				if (Board.board[playerY][playerX] == SquareType.squareIsItemDestroyWalls) {
					Items.itemDestroyWalls++;
					Log.updateLog("Item:「" + Items.getItemName("itemDestroyWalls") + "」を入手した。「Shift(Ctrl)」＋「↑(W)」で使用可能だ。");
				} else if (Board.board[playerY][playerX] == SquareType.squareIsItemFreezeEnemies) {
					Items.itemFreezeEnemies++;
					Log.updateLog("Item:「" + Items.getItemName("itemFreezeEnemies") + "」を入手した。「Shift(Ctrl)」＋「←(A)」で使用可能だ。");
				} else if (Board.board[playerY][playerX] == SquareType.squareIsItemTurnEnemies) {
					Items.itemTurnEnemies++;
					Log.updateLog("Item:「" + Items.getItemName("itemTurnEnemies") + "」を入手した。「Shift(Ctrl)」＋「→(D)」で使用可能だ。");
				}

				// スコアの追加
				Score.updateScore(1);
			}

			// 移動先が「敵」の場合
			if (Board.board[playerY][playerX] < 0) {
				// 敵（盾）によって守られている敵の場合
				if (Enemy.numOfShield > 0 && Board.board[playerY][playerX] != SquareType.squareIsEnemyShield) {
					if (Items.playerCanDestroyWalls > 0) {
						// 「ブレイクウォール」使用中は盾を無視して敵を倒すことができる。
						Log.updateLog("Item:「" + Items.getItemName("itemDestroyWalls") + "」の効果で盾を貫通して攻撃した。");
						killEnemy();

						Items.playerCanDestroyWalls -= Enemy.getAttackDamage(SquareType.squareIsEnemyShield);
						if (Items.playerCanDestroyWalls < 0) Items.playerCanDestroyWalls = 0;

						if (Items.playerCanDestroyWalls  > 0) {
							Log.updateLog("残り" + Items.playerCanDestroyWalls + "個の壁を破壊することが可能だ。");
						} else {
							Log.updateLog("Item:「" + Items.getItemName("itemDestroyWalls") + "」の効果が切れた。");
						}

					} else {
						// 敵を倒すことができない
						Log.updateLog("Tips:敵（盾）に守られているため、敵に攻撃することができない。");
						playerX = tempX;
						playerY = tempY;
					}

					// 敵（盾）によって守られていない敵の場合
				} else {
					killEnemy();
				}
			}

			Board.board[tempY][tempX] = SquareType.squareIsBlank;
			Board.board[playerY][playerX] = SquareType.squareIsPlayer;
			playerMove = true;

		// 移動先が「壁」の場合
		} else {
			if (Items.playerCanDestroyWalls > 0) {
				// アイテム使用中は壁を破壊することができる。
				Items.playerCanDestroyWalls--;
				Board.board[playerY][playerX] = SquareType.squareIsBlank;
				Log.updateLog("Item:「" + Items.getItemName("itemDestroyWalls") + "」の効果で壁を破壊した。");
				if (Items.playerCanDestroyWalls  > 0) {
					Log.updateLog("残り" + Items.playerCanDestroyWalls + "個の壁を破壊することが可能だ。");
				} else {
					Log.updateLog("Item:「" + Items.getItemName("itemDestroyWalls") + "」の効果が切れた。");
				}

				// スコアの追加
				Score.updateScore(1);

				playerMove = true;

			} else {
				// 普段は壁を破壊することができない。
				if (Log.strLog1 != "Tips:白いマス（壁）は、通行不可だ。") {
					Log.updateLog("Tips:白いマス（壁）は、通行不可だ。");
				}
			}

			playerX = tempX;
			playerY = tempY;
		}

		// 必要に応じて、横スクロール
		if (playerX >= 6) {
			Stage.stageScroll(1);
			playerX = tempX;
			BoardNumber.boardNumber++;
		}

		// プレイヤーが移動に成功した場合の処理
		if (playerMove) {
			totalMove++;
			Enemy.enemyAction();
		}

		// プレイヤーの情報の表示の更新
		PlayerData.updatePlayerData();

		Game.leftPressed = Game.rightPressed = Game.upPressed = Game.downPressed = false;
		Game.canPlayerMove = true;
	}

	// 敵を倒した時の処理
	public static void killEnemy(){
		try{
			// SEを鳴らす
			Audio.clipKillSE.stop();
			Audio.clipKillSE.play();
		} catch (NullPointerException e) {

		}

		// 経験値やスコアの計算
		int exp = Enemy.getAttackDamage(Board.board[playerY][playerX]);
		countKill++;
		killCombo++;
		if(maxCombo < killCombo) maxCombo = killCombo;

		// 盤面上の敵の数を減らす
		Enemy.numOfEnemy--;
		if (Board.board[playerY][playerX] == SquareType.squareIsEnemyTriangle) Enemy.numOfTriangle--;
		if (Board.board[playerY][playerX] == SquareType.squareIsEnemyShield) Enemy.numOfShield--;

		// ログへの反映
		String victim = SquareType.getSquareType(playerX, playerY);
		if (killCombo >= 2) {
			Log.updateLog("Kill:" + victim + "を倒した。(Combo:" + killCombo + ")");
		} else {
			Log.updateLog("Kill:" + victim + "を倒した。");
		}

		// 経験値・スコアの追加
		killExp += exp;
		Score.updateScore(exp * killCombo);
	}

	/* プレイヤー関連の変数の操作 */
	// プレイヤーの体力の回復
	public static void addPlayerLife(int damage) {
		Player.playerLife = Player.playerLife + damage;

		Log.updateLog("体力を" + damage + "回復した。");
		if (Player.playerLife > Player.playerMaxLife) {
			damage = Player.playerLife - Player.playerMaxLife;
			Player.playerLife = Player.playerMaxLife;

			Player.killExp += damage;
			damage = (damage +Score.lapTimeBonus) *Stage.stage;
			Score.updateScore(damage);
			Log.updateLog("体力回復分の一部が経験値に変換された。");
		}
	}

	// プレイヤーの体力の減少
	public static void subPlayerLife(int damage) {
		Player.playerLife = Player.playerLife - damage;
		Player.killCombo = 0;
	}
}
