package rgb.lawillia.ui;

import javax.swing.JLabel;

import rgb.lawillia.RD_GAME_BOA;
import rgb.lawillia.board.Board;
import rgb.lawillia.game.Game;
import rgb.lawillia.stage.Stage;

public class Score {
	// スコアのJLabel
	public static JLabel labelScore;						// 「Score」
	public static JLabel labelTotalScore;					// スコアの表示

	// タイムのJLabel
	public static JLabel labelTime;						// 「Time」
	public static JLabel labelTotalTime;					// 総プレイ時間の表示
	public static JLabel labelLapTime;					// 各レベルのプレイ時間の表示

	// カウント
	public static int totalScore;							// スコアのカウント
	public static int totalTime;							// 総プレイ時間のカウント
	public static int lapTime;							// 各レベルのプレイ時間のカウント

	// 他
	public static int lastStageScore;						// 前回のレベルまでのスコア
	public static int lapTimeBonus;						// Lap Timeのランクを保存

	/* 画面右下：スコア・タイム */
	// スコア等の表示
	public static void initScoreBoard() {
		int i = (RD_GAME_BOA.windowHeight - Board.boardY2 - Board.squareHalf - 20) / 4;
		int j = Board.squareSize * 7 + Board.squareHalf;

		labelScore = new JLabel("SCORE");
		UI.initJLabel(labelScore, 0, 0, 24);
		labelScore.setBounds(j, Board.boardY2, Board.squareSize * 8, i);

		labelTotalScore = new JLabel("Score:0");
		UI.initJLabel(labelTotalScore, 0, 0, 24);
		labelTotalScore.setBounds(j, Board.boardY2 + i, Board.squareSize * 8, i);

		labelTime = new JLabel("TIME");
		UI.initJLabel(labelTime, 0, 0, 24);
		labelTime.setBounds(j, Board.boardY2 + i*2, Board.squareSize * 8, i);

		labelTotalTime = new JLabel("Total:00:00");
		UI.initJLabel(labelTotalTime, 0, 0, 24);
		labelTotalTime.setBounds(j, Board.boardY2 + i*3, Board.squareSize * 4, i);

		labelLapTime = new JLabel("Lap:00:00");
		UI.initJLabel(labelLapTime, 0, 0, 24);
		labelLapTime.setBounds(j + Board.squareSize * 4, Board.boardY2 + i*3, Board.squareSize * 4, i);
	}

	// スコアの表示の更新
	public static void updateScore(int i) {
		totalScore += i;
		labelTotalScore.setText("Score:" + totalScore);
	}

	// タイムの表示の更新
	public static void updateTime() {
		// トータルタイム
		int minute = totalTime / 60;
		int second = totalTime % 60;
		String strMinute = "";
		String strSecond = "";

		if (minute == 0) {
			strMinute = "0";
		} else if (minute < 10) {
			strMinute = "0";
		} else {
			strMinute = "";
		}

		if (second == 0) {
			strSecond = "0";
		} else if (second < 10) {
			strSecond = "0";
		} else {
			strSecond = "";
		}

		labelTotalTime.setText("Total:" + strMinute + minute + ":" + strSecond + second);

		// ラップタイム
		minute = lapTime / 60;
		second = lapTime % 60;

		if (minute == 0) {
			strMinute = "0";
		} else if (minute < 10) {
			strMinute = "0";
		} else {
			strMinute = "";
		}

		if (second == 0) {
			strSecond = "0";
		} else if (second < 10) {
			strSecond = "0";
		} else {
			strSecond = "";
		}

		labelLapTime.setText("Lap:" + strMinute + minute + ":" + strSecond + second);
	}

	// トータルタイムの取得
	public static String getTotalTime() {
		int minute = totalTime / 60;
		int second = totalTime % 60;
		String strMinute = "";
		String strSecond = "";
		String str = "";

		if (minute == 0) {
			strMinute = "0";
		} else if (minute < 10) {
			strMinute = "0";
		} else {
			strMinute = "";
		}

		if (second == 0) {
			strSecond = "0";
		} else if (second < 10) {
			strSecond = "0";
		} else {
			strSecond = "";
		}

		str = "" + strMinute + minute + ":" + strSecond + second;

		return str;
	}

	// ラップタイムの取得
	public static String getLapTime() {
		int minute = lapTime / 60;
		int second = lapTime % 60;
		String strMinute = "";
		String strSecond = "";
		String str = "";

		if (minute == 0) {
			strMinute = "0";
		} else if (minute < 10) {
			strMinute = "0";
		} else {
			strMinute = "";
		}

		if (second == 0) {
			strSecond = "0";
		} else if (second < 10) {
			strSecond = "0";
		} else {
			strSecond = "";
		}

		str = "" + strMinute + minute + ":" + strSecond + second;

		return str;
	}

	// ラップタイムからステージクリア時のボーナススコアを計算
	public static void scoreLapTime() {
		int time = lapTime / 30;
		int scoreBase = 1200 * Stage.stage;
		int score = 0;
		String strBS = "";

		if (time <= 1) {
			// 1分以内
			score = scoreBase;
			strBS = "S";
			lapTimeBonus = 6;
		} else if (time == 2) {
			// 1分30秒以内
			score = scoreBase/2;
			strBS = "A";
			lapTimeBonus = 5;
		} else if (time <= 3) {
			// 2分以内
			score = scoreBase/3;
			strBS = "B";
			lapTimeBonus = 4;
		} else if (time <= 5) {
			// 3分以内
			score = scoreBase/4;
			strBS = "C";
			lapTimeBonus = 3;
		} else if (time <= 9) {
			// 5分以内
			score = scoreBase/6;
			strBS = "D";
			lapTimeBonus = 2;
		} else if (time <= 19) {
			// 10分以内
			score = scoreBase/12;
			strBS = "E";
			lapTimeBonus = 1;
		} else {
			strBS = "F";
			lapTimeBonus = 0;
		}

		String strLT = getLapTime();
		int stageScore = totalScore - lastStageScore;
		Game.labelData01.setText("Lap Time:" + strLT + " (Rank:" + strBS + ")");
		Game.labelData02.setText("Stage Score:" + stageScore +"（Bonus:" + score + ")");
		Log.updateLog("Lap Time:" + strLT + " (Bonus Score:" + score + ")");

		updateScore(score);
		lapTime = 0;
	}
}
