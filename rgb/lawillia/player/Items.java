package rgb.lawillia.player;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.game.Game;
import rgb.lawillia.ui.Log;

public class Items {
	/* アイテムに関する変数 */
	public static int itemTurnEnemies = 0;					// 敵を回転させるアイテムの所持数
	public static int itemFreezeEnemies = 0;					// 敵を停止させるアイテムの所持数
	public static int itemDestroyWalls = 0;					// 壁を破壊できるアイテムの所持数
	public static int setTurnFreezeEnemies = 10;				// 敵を停止させるターン数
	public static int turnFreezeEnemies = 0;					// 敵を停止させる残りのターン数
	public static int setPlayerCanDestroyWalls = 5;			// 壁を破壊できる回数
	public static int playerCanDestroyWalls = 0;				// 壁を破壊できる残りの回数

	// アイテムの使用
	public static void useItem() {
		Game.canPlayerMove = false;

		// 敵を停止させるアイテム
		if (Game.leftPressed) {
			if (turnFreezeEnemies > 0) {
				Log.updateLog("Item:「" + getItemName("itemFreezeEnemies") + "」は既に使用中です。");
				Log.updateLog("残り" + turnFreezeEnemies + "ターンの間、時間が停止し、敵は行動ができない。");
			} else if (itemFreezeEnemies > 0) {
				itemFreezeEnemies--;
				Log.updateLog("Item:「" + getItemName("itemFreezeEnemies") + "」を使用した。");

				try{
					// SEを鳴らす
					Audio.clipUseItem.stop();
					Audio.clipUseItem.play();
				} catch (NullPointerException e) {

				}

				turnFreezeEnemies = setTurnFreezeEnemies;
				Log.updateLog(turnFreezeEnemies + "ターンの間、時間が停止し、敵は行動ができない。");
			} else {
				itemFreezeEnemies = 0;
				Log.updateLog("Item:「" + getItemName("itemFreezeEnemies") + "」は持っていません。");
				Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
			}

		// 敵の向きを変えるアイテム
		} else if (Game.rightPressed) {
			if (itemTurnEnemies > 0) {
				itemTurnEnemies--;
				Log.updateLog("Item:「" + getItemName("itemTurnEnemies") + "」を使用した。");
				Log.updateLog("敵の向きを変更した。");

				try{
					// SEを鳴らす
					Audio.clipUseItem.stop();
					Audio.clipUseItem.play();
				} catch (NullPointerException e) {

				}

				for (int y = 0; y < Board.numOfSquareHeight; y++) {
					for (int x = 0; x < Board.numOfSquareWidth-1; x++) {
						if (Board.board[y][x] < 0) {
							if (Board.board[y][x] == SquareType.squareIsEnemyLeftArrow) {
								Board.board[y][x] = SquareType.squareIsEnemyRightArrow;
							} else if (Board.board[y][x] == SquareType.squareIsEnemyRightArrow) {
								Board.board[y][x] = SquareType.squareIsEnemyLeftArrow;
							} else if (Board.board[y][x] == SquareType.squareIsEnemyDownArrow) {
								Board.board[y][x] = SquareType.squareIsEnemyUpArrow;
							} else if (Board.board[y][x] == SquareType.squareIsEnemyUpArrow) {
								Board.board[y][x] = SquareType.squareIsEnemyDownArrow;
							} else if (Board.board[y][x] == SquareType.squareIsEnemyCross) {
								Board.board[y][x] = SquareType.squareIsEnemyCross2;
							} else if (Board.board[y][x] == SquareType.squareIsEnemyCross2) {
								Board.board[y][x] = SquareType.squareIsEnemyCross;
							}
						}
					}
				}

			} else {
				itemTurnEnemies = 0;
				Log.updateLog("Item:「" + getItemName("itemTurnEnemies") + "」は持っていません。");
				Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
			}

		// 壁を破壊できるようになるアイテム
		} else if (Game.upPressed) {
			if (playerCanDestroyWalls > 0) {
				Log.updateLog("Item:「" + getItemName("itemDestroyWalls") + "」は既に使用中です。");
				Log.updateLog("壁は残り" + playerCanDestroyWalls + "個破壊することができます。");
			} else if (itemDestroyWalls > 0) {
				itemDestroyWalls--;
				Log.updateLog("Item:「" + getItemName("itemDestroyWalls") + "」を使用した。");

				try{
					// SEを鳴らす
					Audio.clipUseItem.stop();
					Audio.clipUseItem.play();
				} catch (NullPointerException e) {

				}

				playerCanDestroyWalls = setPlayerCanDestroyWalls;
				Log.updateLog("壁を"+ playerCanDestroyWalls +"個破壊することができるようになった。");
			} else {
				itemDestroyWalls = 0;
				Log.updateLog("Item:「" + getItemName("itemDestroyWalls") + "」は持っていません。");
				Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
			}

		} else if (Game.downPressed){
			Log.updateLog("▼所持アイテムの一覧");
			Log.updateLog(getItemName("itemFreezeEnemies") + "：" + itemFreezeEnemies + "個。「Shift(Ctrl)」＋「←(A)」で使用。");
			Log.updateLog(getItemName("itemTurnEnemies") + "：" + itemTurnEnemies + "個。「Shift(Ctrl)」＋「→(D)」で使用。");
			Log.updateLog(getItemName("itemDestroyWalls") + "：" + itemDestroyWalls + "個。「Shift(Ctrl)」＋「↑(W)」で使用。");
		}

		Game.leftPressed = Game.rightPressed = Game.upPressed = Game.downPressed = false;
		Game.canPlayerMove = true;
	}

	// アイテムの名前
	public static String getItemName(String str) {
		// アイテム名を考えることを後回しにするためのメソッド
		if (str == "itemFreezeEnemies") {
			return "フリーズタイム";
		} else if (str == "itemTurnEnemies") {
			return "ターンエネミー";
		} else if (str == "itemDestroyWalls") {
			return "ブレイクウォール";
		}
		return "No Name";
	}
}
