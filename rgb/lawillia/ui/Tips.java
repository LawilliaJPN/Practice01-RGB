package rgb.lawillia.ui;

import rgb.lawillia.player.Items;

public class Tips {
	// 表示するTipsの選択をするための変数
	public static int arrowTips = 0;						// 敵（矢印）のTips
	public static int crossTips = 0;						// 敵（十字）のTips
	public static int triangleTips = 0;					// 敵（矢印）のTips
	public static int shieldTips = 0;						// 敵（十字）のTips
	public static int numOfTips;							// 進行によるTips

	// 進行によるTips(プレイのヒント)の表示
	public static void displayTips() {
		switch (numOfTips){
		case 0:		// 5
			Log.updateLog("Tips:上下左右キーやWASDキーでプレイヤー（赤い丸）を動かせる。");
			break;
		case 1:		// 25
			Log.updateLog("Tips:マウスでマスの中央をクリックすると、その詳細が表示される。");
			break;
		case 2:		// 45
			Log.updateLog("Tips:「Shift(Ctrl)キー」を押しながら「↓(S)キー」押すと、所持アイテムを確認できる。");
			break;
		case 3:		// 65
			Log.updateLog("Tips:高スコアを目指すには「コンボ」と「ラップタイム」が重要だ。");
			break;
		case 4:		// 85
			Log.updateLog("Tips:ダメージを受けずに敵を倒していくとコンボが増える。");
			Log.updateLog("Tips:ダメージを避けられない時には、アイテムを上手く利用しよう。");
			break;
		case 5:		// 105
			Log.updateLog("Tips:プレイヤーレベルが高いほど、アイテムが入手できる確率が上がる。");
			break;
		case 6:		// 125
			Log.updateLog("Tips:プレイヤーレベルが高いほど、レベルクリア時の体力回復が増える。");
			Log.updateLog("Tips:ラップタイムが速いほど、レベルクリア時の体力回復が増える。");
			break;
		case 7:		// 145
			Log.updateLog("Tips:150マス横スクロールする度にレベルクリアとなる。");
			break;
		}
		numOfTips++;
	}

	// 敵（矢印）が現れた時のTipsの表示
	public static void arrowTips() {
		switch (arrowTips) {
		case 0:
			Log.updateLog("Tips:敵（矢印）の向いている範囲に入ると、ダメージを受けてしまう。");
			break;
		case 1:
			Log.updateLog("Tips:敵（矢印）は、同じマスに重なることで倒すことができる。");
			break;
		case 2:
			Log.updateLog("Tips:敵（矢印）の攻撃は、間に壁があれば防ぐことができる。");
			break;
		case 3:
			Log.updateLog("Tips:敵（矢印）に描かれた数字は、攻撃の威力だ");
			break;
		case 4:
			Log.updateLog("Tips:敵（矢印）は、プレイヤーの位置に合わせて方向を変えることがある。");
			break;
		case 5:
			Log.updateLog("Tips:敵（矢印）を利用して、他の敵を倒すことができる。");
			break;
		}
		arrowTips++;
	}

	// 敵（十字）が現れた時のTipsの表示
	public static void crossTips() {
		switch (crossTips) {
		case 0:
			Log.updateLog("Tips:敵（十字）は、上下左右1マス、あるいは斜め四方向1マスに攻撃する。");
			break;
		case 1:
			Log.updateLog("Tips:敵（十字）は、回転して「十字」と「斜め十字」を交互に変化する。");
			break;
		case 2:
			Log.updateLog("Tips:敵（十字）は、プレイヤーと同じ列にいると回転しない。");
			break;
		case 3:
			Log.updateLog("Tips:敵（十字）に描かれた数字は、攻撃の威力だ");
			break;
		}
		crossTips++;
	}

	// 敵（十字）が現れた時のTipsの表示
	public static void triangleTips() {
		switch (triangleTips) {
		case 0:
			Log.updateLog("Tips:敵（三角形）は、他の敵の攻撃の威力を強化する。");
			break;
		case 1:
			Log.updateLog("Tips:壁は威力3以上の敵の攻撃でひびが入る。");
			break;
		case 2:
			Log.updateLog("Tips:壁は威力5以上の敵の攻撃で一撃で破壊される。");
			break;
		case 3:
			Log.updateLog("Tips:敵（三角形）自身は、攻撃してこない。");
			break;
		case 4:
			Log.updateLog("Tips:敵（三角形）が複数いても、威力の上昇は変わらない。");
			break;
		case 5:
			Log.updateLog("Tips:敵（盾）が敵（矢印）に倒されると、アイテム「" + Items.getItemName("itemTurnEnemies") + "」を落とす");
			break;
		}
		triangleTips++;
	}

	// 敵（十字）が現れた時のTipsの表示
	public static void shieldTips() {
		switch (shieldTips) {
		case 0:
			Log.updateLog("Tips:敵（盾）は、プレイヤーから他の敵への攻撃を防ぐ。");
			break;
		case 1:
			Log.updateLog("Tips:敵（盾）の守りは、アイテム「" + Items.getItemName("itemDestroyWalls") + "」で無視することができる。");
			break;
		case 2:
			Log.updateLog("Tips:敵（盾）が敵（矢印）に倒されると、アイテム「" + Items.getItemName("itemDestroyWalls") + "」を落とす");
			break;
		case 3:
			Log.updateLog("Tips:敵（盾）に描かれた数字は、アイテム「" + Items.getItemName("itemDestroyWalls") + "」の消耗回数だ。");
			break;
		}
		shieldTips++;
	}
}
