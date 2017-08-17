package rgb.lawillia.ui;

import java.awt.Color;

import javax.swing.JLabel;

import rgb.lawillia.RD_GAME_BOA;
import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.game.GameOver;
import rgb.lawillia.player.Player;
import rgb.lawillia.stage.StageSetItems;

public class PlayerData {
	// プレイヤー
	public static JLabel labelPlayer;						// 「Player」
	public static JLabel labelPlayerLife;					// プレイヤーのHPの表示
	public static JLabel labelScoreKill;					// 敵を倒した数の表示
	public static JLabel labelMoveTurn;					// プレイヤーの移動数・ターン数


	/* 画面左下：プレイヤー */
	// プレイヤーの情報の表示
	public static void initPlayerData() {
		int i = (RD_GAME_BOA.windowHeight - Board.boardY2 - Board.squareHalf - 20) / 4;

		labelPlayer = new JLabel("PLAYER");
		UI.initJLabel(labelPlayer, 0, 0,24);
		labelPlayer.setBounds(Board.squareHalf, Board.boardY2, Board.squareSize * 6, i);

		labelPlayerLife = new JLabel("Life:" + Player.playerLife + "/" + Player.playerMaxLife);
		UI.initJLabel(labelPlayerLife, 0, 0,24);
		labelPlayerLife.setBounds(Board.squareHalf, Board.boardY2 + i, Board.squareSize * 6, i);

		labelScoreKill = new JLabel("Kill:" + Player.countKill + " (Exp:" + Player.killExp + "/" + Player.playerMaxLife + ")");
		UI.initJLabel(labelScoreKill, 0, 0,24);
		labelScoreKill.setBounds(Board.squareHalf, Board.boardY2 + i*2, Board.squareSize * 6, i);

		labelMoveTurn = new JLabel("Move:" + Player.totalMove + " (Turn:" + Player.moveTurn + ")");
		UI.initJLabel(labelMoveTurn, 0, 0,24);
		labelMoveTurn.setBounds(Board.squareHalf, Board.boardY2 + i*3, Board.squareSize * 6, i);
	}

	// プレイヤーの情報の色変更
	public static void setColorPlayerData() {
		// 体力が減るとプレイヤーの情報が赤文字で表示されるようになる
		if (Player.playerLife <= Player.playerMaxLife/3){
			labelPlayer.setForeground(Color.RED);
			labelPlayerLife.setForeground(Color.RED);
			labelScoreKill.setForeground(Color.RED);
			labelMoveTurn.setForeground(Color.RED);
			Player.isPlayerInjured = true;
		} else {
			labelPlayer.setForeground(Color.WHITE);
			labelPlayerLife.setForeground(Color.WHITE);
			labelScoreKill.setForeground(Color.WHITE);
			labelMoveTurn.setForeground(Color.WHITE);
			Player.isPlayerInjured = false;
		}
	}

	// プレイヤーの情報の更新
	public static void updatePlayerData() {
		// 経験値によるレベルアップ
		if (Player.killExp >= Player.playerMaxLife) {
			try{
				// SEを鳴らす
				Audio.clipLevelUp.stop();
				Audio.clipLevelUp.play();
			} catch (NullPointerException e) {

			}

			Player.killExp -= Player.playerMaxLife;
			Player.playerLife++;
			Player.playerMaxLife++;
			Log.updateLog("");
			Log.updateLog("Level Up!!:最大HPが1増えた。");

			// 10レベル上がるごとに、ボーナスアイテム。
			if (Player.playerMaxLife%10 == 0) {
				StageSetItems.defaultPlaceItems = 0;
			}
		}

		// 数値の更新
		int lv = Player.playerMaxLife - Player.playerDefaultLife + 1;
		Player.moveTurn = Player.totalMove % 12 + 1;

		// 表示の更新
		setColorPlayerData();
		labelPlayer.setText("PLAYER (Lv." + lv + ")");
		labelPlayerLife.setText("Life:" + Player.playerLife + "/" + Player.playerMaxLife);
		labelScoreKill.setText("Kill:" + Player.countKill + " (Exp:" + Player.killExp + "/" + Player.playerMaxLife + ")");
		labelMoveTurn.setText("Move:" + Player.totalMove + " (Turn:" + Player.moveTurn + ")");

		// ゲームオーバー
		if (Player.playerLife <= 0) {
			Player.playerLife = 0;
			labelPlayerLife.setText("Life:" + Player.playerLife + "/" + Player.playerMaxLife);
			GameOver.gameOver();
		}
	}
}
