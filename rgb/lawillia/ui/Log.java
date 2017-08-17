package rgb.lawillia.ui;

import javax.swing.JLabel;

import rgb.lawillia.RD_GAME_BOA;
import rgb.lawillia.board.Board;

public class Log {
	// ログ
	public static JLabel
		labelLog1, labelLog2, labelLog3,
		labelLog4, labelLog5, labelLog6;				// ログの表示
	public static String
	strLog1, strLog2, strLog3,
	strLog4, strLog5, strLog6;							// ログの文字列


	/* 画面上：ログ */
	// ログの表示
	public static void initLog() {
		// ログは6行、最新6行のみ表示。
		int i = Board.boardY / 6;

		labelLog1 = new JLabel("");
		UI.initJLabel(labelLog1, 0, 0,14);
		labelLog1.setBounds(Board.squareHalf, i * 5, RD_GAME_BOA.windowWidth, i);

		labelLog2 = new JLabel("");
		UI.initJLabel(labelLog2, 1, 0,14);
		labelLog2.setBounds(Board.squareHalf, i * 4, RD_GAME_BOA.windowWidth, i);

		labelLog3 = new JLabel("");
		UI.initJLabel(labelLog3, 1, 0,14);
		labelLog3.setBounds(Board.squareHalf, i * 3, RD_GAME_BOA.windowWidth, i);

		labelLog4 = new JLabel("");
		UI.initJLabel(labelLog4, 1, 0,14);
		labelLog4.setBounds(Board.squareHalf, i * 2, RD_GAME_BOA.windowWidth, i);

		labelLog5 = new JLabel("");
		UI.initJLabel(labelLog5, 1, 0,14);
		labelLog5.setBounds(Board.squareHalf, i, RD_GAME_BOA.windowWidth, i);

		labelLog6 = new JLabel("");
		UI.initJLabel(labelLog6, 1, 0,14);
		labelLog6.setBounds(Board.squareHalf, 0, RD_GAME_BOA.windowWidth, i);
	}

	// ログの更新
	public static void updateLog(String newLog) {
		// 新しいログを一番下に、古いログを上へ流していく
		strLog6 = strLog5;
		strLog5 = strLog4;
		strLog4 = strLog3;
		strLog3 = strLog2;
		strLog2 = strLog1;
		strLog1 = newLog;

		labelLog1.setText(strLog1);
		labelLog2.setText(strLog2);
		labelLog3.setText(strLog3);
		labelLog4.setText(strLog4);
		labelLog5.setText(strLog5);
		labelLog6.setText(strLog6);
	}
}
