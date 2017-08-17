package rgb.lawillia.game;

import rgb.lawillia.RD_GAME_BOA;
import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.player.Player;
import rgb.lawillia.ui.Score;

public class GameOver {
	// ゲームオーバー
	public static void gameOver() {
		// ゲームを停止
		Game.isGameRunning = false;
		Game.canPlayerMove = false;
		Audio.playBGM(1);

		/* 時間停止。ない方がいいかもしれない。
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/

		// 表示の変更
		Board.initBoard();
		BoardNumber.hideBoardNumber();
		Game.labelGameOver.setText("GAME OVER");
		Game.labelPressAnyKey.setText("Press ENTER or SPACE key to RESTART");

		// データの表示
		Game.labelData01.setText("Score: " + Score.totalScore);
		Game.labelData02.setText("Scroll Distance: " + BoardNumber.boardNumber);
		Game.labelData03.setText("Total Time: " + Score.getTotalTime());
		Game.labelData04.setText("Max Combo: " + Player.maxCombo);
		Game.labelData05.setText("Version: " + "ver." + RD_GAME_BOA.GAME_VERSION);
	}
}