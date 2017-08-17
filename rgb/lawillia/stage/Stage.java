package rgb.lawillia.stage;

import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.game.LevelClear;
import rgb.lawillia.ui.Tips;

public class Stage {
	public static int stage = 0;

	/* ステージの設定 */
	// 横スクロール
	public static void stageScroll(int j) {
		// スクロール処理
		for (int i = 0; i < j; i++) {
			// 一番左のマスの確認
			for (int y = 0; y < Board.numOfSquareHeight; y++) {
				if (Board.board[y][0] < 0) {
					Enemy.numOfEnemy--;
					if (Board.board[y][0] == SquareType.squareIsEnemyTriangle) Enemy.numOfTriangle--;
					if (Board.board[y][0] == SquareType.squareIsEnemyShield) Enemy.numOfShield--;
				}
			}

			// 左に1マスずつズラす
			for (int y = 0; y < Board.numOfSquareHeight; y++) {
				for (int x = 0; x < Board.numOfSquareWidth-1; x++) {
					Board.board[y][x] = Board.board[y][x+1];
				}
			}

			// 一番右の列のマスの配置
			for (int y = 0; y < Board.numOfSquareHeight; y++) {
				// 一度すべて空白のマスで埋める
				Board.board[y][Board.numOfSquareWidth-1] = SquareType.squareIsBlank;
			}
			// 一番右の列のマスに壁・敵・アイテムを配置
			StageSetWalls.setImpassable();
			StageSetEnemies.setEnemy();
			StageSetItems.setItem();
		}

		// 150マス進むごとに敵強化&経験値獲得
		if (BoardNumber.boardNumber != 0) {
			if (BoardNumber.boardNumber%150 == 0) {
				LevelClear.levelClear();
			} else if(BoardNumber.boardNumber%20 == 5) {
				Tips.displayTips();
			}
		}
	}
}
