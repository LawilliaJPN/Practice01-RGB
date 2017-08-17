package rgb.lawillia.board;

import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.player.Items;
import rgb.lawillia.ui.Log;

public class SquareType {
	/* マスごとの性質に関する変数 */
	final public static int squareIsBlank = 0;						// 何もないマス
	final public static int squareIsPlayer = 1;					// プレイヤー
	final public static int squareIsImpassable = 2;				// 通行不可なマス（壁）
	final public static int squareIsCrackedWall = 3;				// ひび割れた壁

	final public static int squareIsItemTurnEnemies = 4;			// 敵を回転させるアイテム
	final public static int squareIsItemFreezeEnemies = 5;		// 敵を停止させるアイテム
	final public static int squareIsItemDestroyWalls = 6;		// 壁を破壊できるアイテム

	final public static int squareIsEnemyLeftArrow = -1;			// 敵（左矢印）
	final public static int squareIsEnemyUpArrow = -2;			// 敵（上矢印）
	final public static int squareIsEnemyRightArrow = -3;		// 敵（右矢印）
	final public static int squareIsEnemyDownArrow = -4;			// 敵（下矢印）

	final public static int squareIsEnemyCross = -5;				// 敵（十字）
	final public static int squareIsEnemyCross2 = -6;				// 敵（斜め十字）

	final public static int squareIsEnemyTriangle = -7;			// 敵（三角形）
	final public static int squareIsEnemyShield = -8;				// 敵（盾）

		// 特定のマスの種類を取得
		public static String getSquareType(int x, int y) {
			if (Board.board[y][x] == squareIsPlayer) return "プレイヤー";

			if (Board.board[y][x] == squareIsImpassable) return "壁";
			if (Board.board[y][x] == squareIsCrackedWall) return "ひび割れた壁";

			if (Board.board[y][x] == squareIsItemTurnEnemies) return Items.getItemName("itemTurnEnemies");
			if (Board.board[y][x] == squareIsItemFreezeEnemies) return Items.getItemName("itemFreezeEnemies");
			if (Board.board[y][x] == squareIsItemDestroyWalls) return Items.getItemName("itemDestroyWalls");

			if (Board.board[y][x] == squareIsEnemyLeftArrow) return "敵（左矢印）";
			if (Board.board[y][x] == squareIsEnemyUpArrow) return "敵（上矢印）";
			if (Board.board[y][x] == squareIsEnemyRightArrow) return "敵（右矢印）";
			if (Board.board[y][x] == squareIsEnemyDownArrow) return "敵（下矢印）";

			if (Board.board[y][x] == squareIsEnemyCross) return "敵（十字）";
			if (Board.board[y][x] == squareIsEnemyCross2) return "敵（斜め十字）";

			if (Board.board[y][x] == squareIsEnemyTriangle) return "敵（三角形）";
			if (Board.board[y][x] == squareIsEnemyShield) return "敵（盾）";

			return "";
		}

		// 特定のマスの説明を表示
		public static void getTipsSquare(int x, int y) {
			if (Board.board[y][x] != squareIsBlank) {
				if (x == 15) Log.updateLog("Tips:画面右端の列の敵は行動できない。");
				else Log.updateLog("");
			}

			if (Board.board[y][x] == squareIsPlayer) {
				Log.updateLog("【赤い丸：プレイヤー】");
				Log.updateLog("十字キーかWASDキーで、上下左右に移動が可能だ。");
				Log.updateLog("敵の攻撃範囲内に入ると、ダメージを受けてしまう。");
				Log.updateLog("敵のいるマスに移動することにより、その敵を倒すことが可能だ。");
				Log.updateLog("丸の中に描かれている数字は、残りの体力だ。");

			// 壁
			} else if (Board.board[y][x] == squareIsImpassable) {
				Log.updateLog("【白いマス：壁】");
				Log.updateLog("プレイヤーも敵も移動不可なマスだ。");
				Log.updateLog("威力の低い敵の攻撃から身を守ることができる。");
				Log.updateLog("威力3～4の攻撃により、ひびを入れることが可能だ。");
				Log.updateLog("威力5以上の攻撃により、一撃で破壊することが可能だ。");
			} else if (Board.board[y][x] == squareIsCrackedWall) {
				Log.updateLog("【×印の白いマス：ひび割れた壁】");
				Log.updateLog("移動や攻撃により、容易に破壊することが可能な状態の壁だ。");
				Log.updateLog("プレイヤーが破壊すると一定確率でアイテムを入手できる。");
				Log.updateLog("敵の攻撃から一度だけ身を守ることができる。");

			// アイテム
			} else if (Board.board[y][x] == squareIsItemTurnEnemies) {
				Log.updateLog("【黄色の回転印：アイテム（" + Items.getItemName("itemTurnEnemies") + "）】");
				Log.updateLog("このマスに移動するとアイテム「" + Items.getItemName("itemTurnEnemies") + "」を入手することができる。");
				Log.updateLog(Items.getItemName("itemTurnEnemies") + "は、「Shift(Ctrl)」＋「→(D)」で使用可能だ。");
				Log.updateLog("使用した直後に、方向転換可能な敵を全て強制的に方向転換させるアイテムだ。");
			} else if (Board.board[y][x] == squareIsItemFreezeEnemies) {
				Log.updateLog("【黄色の縦2本線：アイテム（" + Items.getItemName("itemFreezeEnemies") + "）】");
				Log.updateLog("このマスに移動するとアイテム「" + Items.getItemName("itemFreezeEnemies") + "」を入手することができる。");
				Log.updateLog(Items.getItemName("itemFreezeEnemies") + "は、「Shift(Ctrl)」＋「←(A)」で使用可能だ。");
				Log.updateLog("使用後" + Items.setTurnFreezeEnemies + "ターンの間、時間が止まり敵が一切行動ができなくなるアイテムだ。");
			} else if (Board.board[y][x] == squareIsItemDestroyWalls) {
				Log.updateLog("【黄色の×四角：アイテム（" + Items.getItemName("itemDestroyWalls") + "）】");
				Log.updateLog("このマスに移動するとアイテム「" + Items.getItemName("itemDestroyWalls") + "」を入手することができる。");
				Log.updateLog(Items.getItemName("itemDestroyWalls") + "は、「Shift(Ctrl)」＋「↑(W)」で使用可能だ。");
				Log.updateLog("使用後" + Items.setPlayerCanDestroyWalls + "回まで、「壁」を破壊することができるようになるアイテムだ。");

			// 敵（矢印）
			} else if (Board.board[y][x] == squareIsEnemyLeftArrow) {
				Log.updateLog("【緑の左矢印：敵（左矢印）】");
				Log.updateLog("矢印の向いている範囲に入ると、プレイヤーや他の敵はダメージを受けてしまう。");
				Log.updateLog("プレイヤーよりも左側に入る場合、「右矢印」に切り替わる。");
				Log.updateLog("現在の「左矢印」による攻撃の威力は" + Enemy.getAttackDamage(squareIsEnemyLeftArrow) + "だ。");
				Log.updateLog("丸の中に描かれている数字は、攻撃の威力だ。");
			} else if (Board.board[y][x] == squareIsEnemyRightArrow) {
				Log.updateLog("【緑の右矢印：敵（右矢印）】");
				Log.updateLog("矢印の向いている範囲に入ると、プレイヤーや他の敵はダメージを受けてしまう。");
				Log.updateLog("プレイヤーよりも右側に入る場合、「左矢印」に切り替わる。");
				Log.updateLog("現在の「右矢印」による攻撃の威力は" + Enemy.getAttackDamage(squareIsEnemyRightArrow) + "だ。");
				Log.updateLog("丸の中に描かれている数字は、攻撃の威力だ。");
			} else if (Board.board[y][x] == squareIsEnemyUpArrow) {
				Log.updateLog("【緑の上矢印：敵（上矢印）】");
				Log.updateLog("矢印の向いている範囲に入ると、プレイヤーや他の敵はダメージを受けてしまう。");
				Log.updateLog("プレイヤーよりも上側に入る場合、「下矢印」に切り替わる。");
				Log.updateLog("現在の「上矢印」による攻撃の威力は" + Enemy.getAttackDamage(squareIsEnemyUpArrow) + "だ。");
				Log.updateLog("丸の中に描かれている数字は、攻撃の威力だ。");
			} else if (Board.board[y][x] == squareIsEnemyDownArrow) {
				Log.updateLog("【緑の下矢印：敵（下矢印）】");
				Log.updateLog("矢印の向いている範囲に入ると、プレイヤーや他の敵はダメージを受けてしまう。");
				Log.updateLog("プレイヤーよりも下側に入る場合、「上矢印」に切り替わる。");
				Log.updateLog("現在の「下矢印」による攻撃の威力は" + Enemy.getAttackDamage(squareIsEnemyDownArrow) + "だ。");
				Log.updateLog("丸の中に描かれている数字は、攻撃の威力だ。");

			// 敵（十字）
			} else if (Board.board[y][x] == squareIsEnemyCross) {
				Log.updateLog("【緑の十字（＋）：敵（十字）】");
				Log.updateLog("上下左右に1マスに攻撃する。");
				Log.updateLog("攻撃の威力は、丸の中に描かれた数字の通りだ。現在の威力は" + Enemy.getAttackDamage(squareIsEnemyCross) + "だ。");
				Log.updateLog("プレイヤーに攻撃しなかった場合は、回転して「斜め十字」に変わる。");
				Log.updateLog("プレイヤーが同じ列にいる時は、回転しなくなる。");
			} else if (Board.board[y][x] == squareIsEnemyCross2) {
				Log.updateLog("【緑の十字（×）：敵（斜め十字）】");
				Log.updateLog("斜め四方向1マスに攻撃する。");
				Log.updateLog("攻撃の威力は、丸の中に描かれた数字の通りだ。現在の威力は" + Enemy.getAttackDamage(squareIsEnemyCross2) + "だ。");
				Log.updateLog("プレイヤーに攻撃しなかった場合は、回転して「十字」に変わる。");
				Log.updateLog("プレイヤーが同じ列にいる時は、回転しなくなる。");

			// 敵（三角形・盾）
			} else if (Board.board[y][x] == squareIsEnemyTriangle) {
				Log.updateLog("【緑の三角形：敵（三角形）】");
				Log.updateLog("この敵が存在する時、他の敵の攻撃の威力が上昇する。この敵自身は攻撃してこない。");
				Log.updateLog("描かれた数字の分（現在は" + Enemy.getAttackDamage(squareIsEnemyTriangle) + "）だけ、他の敵の攻撃の威力は上昇する。");
				Log.updateLog("敵（矢印）に倒された時に、アイテム「" + Items.getItemName("itemTurnEnemies") + "」を落とす。");
			} else if (Board.board[y][x] == squareIsEnemyShield) {
				Log.updateLog("【緑のホームベース型：敵（盾）】");
				Log.updateLog("この敵が存在する時、他の敵を倒すことができなくなる。この敵自身は攻撃してこない。");
				Log.updateLog("アイテム「" + Items.getItemName("itemDestroyWalls") + "」使用中のみ、他の敵を倒すことができる。");
				Log.updateLog("描かれた数字の分（現在は" + Enemy.getAttackDamage(squareIsEnemyTriangle) + "）だけ、アイテム「" + Items.getItemName("itemDestroyWalls") + "」が消耗する。");
				Log.updateLog("敵（矢印）に倒された時に、アイテム「" + Items.getItemName("itemDestroyWalls") + "」を落とす。");
			}
		}
}
