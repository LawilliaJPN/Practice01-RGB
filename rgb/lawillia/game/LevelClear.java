package rgb.lawillia.game;

import java.awt.Color;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.player.Player;
import rgb.lawillia.stage.Stage;
import rgb.lawillia.ui.Log;
import rgb.lawillia.ui.PlayerData;
import rgb.lawillia.ui.Score;
import rgb.lawillia.ui.Tips;

public class LevelClear {
	// 200マス進むごとにレベルクリア
	public static void levelClear() {
		// ゲームを停止
		Game.isGameRunning = false;
		Game.canPlayerMove = false;
		Game.isStageClear = true;

		// 表示の変更
		BoardNumber.hideBoardNumber();
		Game.labelStageClear.setText("STAGE" + Stage.stage + " CLEAR");
		Game.labelPressAnyKey.setText("Press ENTER or SPACE key");

		// BGM・SE
		Audio.stopBGM();
		try{
			// SEを鳴らす
			Audio.clipEnemyLevelUp.stop();
			Audio.clipEnemyLevelUp.play();
		} catch (NullPointerException e) {

		}

		// 敵の強化
		Enemy.enemyLevel++;
		if (Enemy.enemyLevel%2 == 0) Enemy.maxNumOfEnemy++;
		Log.updateLog("");
		Log.updateLog("Enemy Level Up!!:敵の強さが一段階上がった。");

		// 経験値獲得
		int exp = Enemy.enemyLevel*10;
		Player.killExp += exp;
		Log.updateLog("経験値を" + exp + "獲得した。");

		// スコアの加算
		Score.scoreLapTime();
		Score.lastStageScore = Score.totalScore;

		// 次のステージの情報
		informationNextStage(Stage.stage);
		Tips.numOfTips = 0;
	}

	// レベルクリアからの再開
	public static void nextStage() {
		Game.isGameRunning = true;
		Game.canPlayerMove = true;
		Game.isStageClear = false;

		// 体力の回復
		int lv = Score.lapTimeBonus + Player.playerMaxLife - Player.playerDefaultLife - Stage.stage;
		if (lv < 1) lv = 1;
		if (lv > Stage.stage*3) lv = Stage.stage*3;
		Player.addPlayerLife(lv);
		PlayerData.updatePlayerData();

		// 表示の変更
		BoardNumber.updateBoardNumber();
		Game.hideLabelGame();

		// BGMを鳴らす
		Stage.stage++;
		int audio = (Stage.stage -1)%7 +2;
		Audio.playBGM(audio);
	}

	public static void informationNextStage(int st) {
		/* ステージごとの情報メモ（Enemyクラス getAttackDamageメソッド）
		 * No/Mx/YA/TA/Cr/Tr/Sh/
		 * 01/04/01/01/00/00/00/
		 * 02/05/01/01/01/00/00/十字追加★
		 * 03/05/01/02/01/00/00/縦矢印強化
		 * 04/06/01/02/02/00/00/十字強化
		 * 05/06/02/03/02/00/00/矢印強化
		 * 06/07/02/03/02/01/00/三角形追加★
		 * 07/07/02/03/02/02/00/三角形強化
		 * 08/08/02/03/03/02/00/十字強化
		 * 09/08/02/03/03/02/01/盾追加★
		 * 10/09/03/04/03/02/01/矢印強化
		 * 11/09/03/04/03/03/01/三角形強化
		 * 12/10/03/04/04/03/01/十字強化
		 * 13/10/03/04/04/03/02/盾強化
		 * 14/11/03/04/04/04/02/三角形強化
		 * 15/11/04/05/04/04/02/矢印強化
		 * 16/12/04/05/05/04/02/十字強化
		 * 17/12/04/05/05/05/03/三角形強化
		 * 18/13/04/05/05/05/03/盾強化
		 * 19/13/04/05/05/06/03/三角形強化
		 * 20/14/05/06/06/06/03/矢印強化・十字強化
		 */
		Game.labelData03.setForeground(Color.GRAY);
		Game.labelData04.setForeground(Color.GRAY);
		Game.labelData05.setForeground(Color.GRAY);
		Game.labelData03.setText("▼次のステージの情報");
		st++;

		switch(st) {
		case 2:
			Game.labelData04.setText("新たな敵（十字・斜め十字）が登場。");
			Game.labelData05.setText("敵（斜め十字）の上下が弱点だ。");
			break;
		case 3:
			Game.labelData04.setText("敵（縦矢印）の攻撃の威力が上がった。");
			Game.labelData05.setText("倒した時の経験値やスコアも増える。");
			break;
		case 4:
			Game.labelData04.setText("敵（十字）の攻撃の威力が上がった。");
			Game.labelData05.setText("倒した時の経験値やスコアも増える。");
			break;
		case 5:
			Game.labelData04.setText("敵（矢印）の攻撃の威力が上がった。");
			Game.labelData05.setText("威力3で、壁を破壊されるようになる。");
			break;
		case 6:
			Game.labelData04.setText("新たな敵（三角形）が登場。");
			Game.labelData05.setText("他の敵の攻撃の威力を強化する敵だ。");
			break;
		case 7:
			Game.labelData04.setText("敵（三角形）が強くなった。");
			Game.labelData05.setText("他の敵の攻撃の威力がより強化される。");
			break;
		case 8:
			Game.labelData04.setText("敵（十字）の攻撃の威力が上がった。");
			Game.labelData05.setText("威力3で、壁を破壊されるようになる。");
			break;
		case 9:
			Game.labelData04.setText("新たな敵（盾）が登場。");
			Game.labelData05.setText("他の敵をプレイヤーから守る敵だ。");
			break;
		case 10:
			Game.labelData04.setText("敵（矢印）の攻撃の威力が上がった。");
			Game.labelData05.setText("威力3で、壁を破壊されるようになる。");
			break;
		case 11:
			Game.labelData04.setText("敵（三角形）が強くなった。");
			Game.labelData05.setText("他の敵の攻撃の威力がより強化される。");
			break;
		case 12:
			Game.labelData04.setText("敵（十字）の攻撃の威力が上がった。");
			Game.labelData05.setText("少ないダメージで切り抜けていこう。");
			break;
		case 13:
			Game.labelData04.setText("敵（盾）が強くなった。");
			Game.labelData05.setText("アイテムの消耗が激しくなる。");
			break;
		case 14:
			Game.labelData04.setText("敵（三角形）が強くなった。");
			Game.labelData05.setText("他の敵の攻撃の威力がより強化される。");
			break;
		case 15:
			Game.labelData04.setText("敵（矢印）の攻撃の威力が上がった。");
			Game.labelData05.setText("威力5では、壁を一撃で破壊される。");
			break;
		case 16:
			Game.labelData04.setText("敵（十字）の攻撃の威力が上がった。");
			Game.labelData05.setText("威力5では、壁を一撃で破壊されるぞ。");
			break;
		case 17:
			Game.labelData04.setText("敵（三角形）が強くなった。");
			Game.labelData05.setText("他の敵の攻撃の威力がより強化される。");
			break;
		case 18:
			Game.labelData04.setText("敵（盾）が強くなった。");
			Game.labelData05.setText("アイテムの消耗が激しくなる。");
		break;
		case 19:
			Game.labelData04.setText("敵（三角形）が強くなった。");
			Game.labelData05.setText("他の敵の攻撃の威力がより強化される。");
			break;
		case 20:
			Game.labelData04.setText("敵（矢印・十字）の攻撃の威力が上がった。");
			Game.labelData05.setText("開発者の想定しているレベルはここまでだ。");
			break;
		default:
			Game.labelData04.setText("Stage21以降はバランス調整がされていない。");
			Game.labelData05.setText("開発者が想定していないレベルだ。");
			break;
		}
	}
}
