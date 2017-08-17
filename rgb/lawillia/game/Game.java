package rgb.lawillia.game;

import java.awt.Color;

import javax.swing.JLabel;

import rgb.lawillia.RD_GAME_BOA;
import rgb.lawillia.board.Board;
import rgb.lawillia.ui.UI;

public class Game {
	/* キーボード操作に関する変数 */
	public static boolean leftPressed, rightPressed;			// キーボードの左右が押されているか
	public static boolean upPressed, downPressed;			// キーボードの上下が押されているか
	public static boolean canPlayerMove;						// プレイヤーが移動できるかどうか

	/* ゲームの進行に関する変数 */
	public static boolean isGameRunning;						// ゲーム中かどうか
	public static boolean isStageClear = false;				// ステージクリアのメニュー時

	/* ゲーム進行に関連するラベル */
	public static JLabel labelGameOver;						// 「Game Over」ラベル
	public static JLabel labelStageClear;						// 「Stage Clear」ラベル
	public static JLabel labelPressAnyKey;					// 「Press Any Key」ラベル
	public static JLabel labelData01;							// データ1
	public static JLabel labelData02;							// データ2
	public static JLabel labelData03;							// データ3
	public static JLabel labelData04;							// データ4
	public static JLabel labelData05;							// データ5

	// ゲーム進行関連のラベル等
	public static void initLabelGame() {
		// ゲームオーバー
		labelGameOver = new JLabel("");
		UI.initJLabel(labelGameOver, 2, 2, 120);
		labelGameOver.setBounds(0, Board.boardY, RD_GAME_BOA.windowWidth, Board.squareSize * 3);

		// ステージクリア
		labelStageClear = new JLabel("RD-GAME-BOA");
		UI.initJLabel(labelStageClear, 0, 2, 100);
		labelStageClear.setBounds(0, Board.boardY, RD_GAME_BOA.windowWidth, Board.squareSize * 3);

		// 押すキーの指定
		labelPressAnyKey = new JLabel("Press ENTER or SPACE key to START");
		UI.initJLabel(labelPressAnyKey, 0, 2, 36);
		labelPressAnyKey.setBounds(0, Board.boardY + Board.squareSize * 9, RD_GAME_BOA.windowWidth, Board.squareSize);

		// データ①：ラップタイム、トータルタイム
		labelData01 = new JLabel("ver." + RD_GAME_BOA.GAME_VERSION);
		UI.initJLabel(labelData01, 0, 2, 36);
		labelData01.setBounds(0, Board.boardY + Board.squareSize * 3, RD_GAME_BOA.windowWidth, Board.squareSize);

		// データ②：ボーナススコア、トータルスコア
		labelData02 = new JLabel("");
		UI.initJLabel(labelData02, 0, 2, 36);
		labelData02.setBounds(0, Board.boardY + Board.squareSize * 4, RD_GAME_BOA.windowWidth, Board.squareSize);

		// データ③
		labelData03 = new JLabel("");
		UI.initJLabel(labelData03, 0, 2, 36);
		labelData03.setBounds(0, Board.boardY + Board.squareSize * 5 + Board.squareHalf, RD_GAME_BOA.windowWidth, Board.squareSize);

		// データ④
		labelData04 = new JLabel("");
		UI.initJLabel(labelData04, 0, 2, 36);
		labelData04.setBounds(0, Board.boardY + Board.squareSize * 6 + Board.squareHalf, RD_GAME_BOA.windowWidth, Board.squareSize);

		// データ⑤
		labelData05 = new JLabel("");
		UI.initJLabel(labelData05, 0, 2, 36);
		labelData05.setBounds(0, Board.boardY + Board.squareSize * 7 + Board.squareHalf, RD_GAME_BOA.windowWidth, Board.squareSize);
	}

	// ゲーム進行関連のラベルをすべて非表示に
	public static void hideLabelGame() {
		labelGameOver.setText("");
		labelStageClear.setText("");
		labelPressAnyKey.setText("");
		labelData01.setText("");
		labelData02.setText("");
		labelData03.setText("");
		labelData04.setText("");
		labelData05.setText("");

		// 色を戻す
		labelData03.setForeground(Color.WHITE);
		labelData04.setForeground(Color.WHITE);
		labelData05.setForeground(Color.WHITE);
	}
}
