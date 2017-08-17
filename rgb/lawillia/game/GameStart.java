package rgb.lawillia.game;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.enemy.Enemy;
import rgb.lawillia.player.Items;
import rgb.lawillia.player.Player;
import rgb.lawillia.stage.Stage;
import rgb.lawillia.stage.StageSetEnemies;
import rgb.lawillia.stage.StageSetItems;
import rgb.lawillia.stage.StageSetWalls;
import rgb.lawillia.ui.Log;
import rgb.lawillia.ui.PlayerData;
import rgb.lawillia.ui.Score;
import rgb.lawillia.ui.Tips;

public class GameStart {
	// ゲームの開始
	public static void setSP() {
		// 盤面の初期化
		Board.initBoard();
		BoardNumber.boardNumber = 0;

		// 敵関連の初期化
		Enemy.numOfEnemy = 0;
		Enemy.maxNumOfEnemy = 4;
		Enemy.enemyLevel = 1;
		Enemy.numOfTriangle = 0;
		Enemy.numOfShield = 0;

		// ステージ関連の初期化
		Stage.stage = 1;
		StageSetWalls.terrainNorth = StageSetWalls.terrainSouth = 0;
		StageSetItems.canPlaceItems = StageSetItems.defaultPlaceItems = 0;
		Stage.stageScroll(8);
		/* [注意]プレイヤーの位置の初期化よりも先にスクロールさせなければいけない */

		// プレイヤーの位置の初期化
		Player.playerX = 0;
		Player.playerY = 4;
		Board.board[Player.playerY][Player.playerX] = SquareType.squareIsPlayer;

		// プレイヤーの体力の初期化
		Player.playerLife = Player.playerDefaultLife;
		Player.playerMaxLife = Player.playerDefaultLife;
		Player.isPlayerInjured = false;

		// プレイヤーの情報の初期化
		Player.countKill = 0;
		Player.killExp = 0;
		Player.killCombo = 0;
		Player.maxCombo = 0;
		Player.totalMove = 0;
		Player.moveTurn = 0;

		// スコア関連の初期化
		Score.totalScore = 0;
		Score.lastStageScore = 0;
		Score.totalTime = 0;
		Score.lapTime = 0;
		Score.lapTimeBonus = 0;

		// Tips関連の初期化
		Tips.arrowTips = 0;
		Tips.crossTips = 0;
		Tips.triangleTips = 0;
		Tips.shieldTips = 0;
		Tips.numOfTips = 0;

		// アイテム関連の初期化
		Items.itemTurnEnemies = 0;
		Items.itemFreezeEnemies = 0;
		Items.itemDestroyWalls = 0;
		Items.setTurnFreezeEnemies = 10;
		Items.setPlayerCanDestroyWalls = 5;
		Items.turnFreezeEnemies = 0;
		Items.playerCanDestroyWalls = 0;

		// 乱数補正の初期化
		StageSetItems.adjustSetTurnEnemiesInWall = 0;
		StageSetWalls.adjustSetCrackedWall = 0;
		StageSetEnemies.adjustSetArrow = 0;
		StageSetEnemies.adjustSetCross = 0;
		StageSetEnemies.adjustSetShield = 0;

		// 情報の表示の更新
		PlayerData.updatePlayerData();
		Score.updateScore(0);
		Score.updateTime();
		BoardNumber.updateBoardNumber();
		Game.hideLabelGame();

		// ログの初期化
		for (int i = 0; i < 6; i++) {
			Log.updateLog("");
		}
		Tips.displayTips();

		// ゲーム開始
		Game.isGameRunning = true;
		Game.canPlayerMove = true;
		Audio.playBGM(2);
	}
}
