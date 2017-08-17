package rgb.lawillia.stage;

import java.util.Random;

import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.SquareType;

public class StageSetWalls {
	public static int terrainNorth = 0, terrainSouth = 0;			// 壁の上下生成
	public static int adjustSetCrackedWall;						// 乱数への補正


	// 壁の設置
	public static void setImpassable() {
		int num = terrainNorth + terrainSouth;
		Random rand = new Random();

		// 盤面上部への壁の設置
		if (terrainNorth >= 1) {
			for (int y = 0; y < terrainNorth; y++) {
				Board.board[y][Board.numOfSquareWidth-1] = SquareType.squareIsImpassable;
			}
		}

		// 盤面下部への壁の設置
		if (terrainSouth >= 1) {
			for (int y = 0; y < terrainSouth; y++) {
				Board.board[Board.numOfSquareHeight-y-1][Board.numOfSquareWidth-1] = SquareType.squareIsImpassable;
			}
		}

		// 画面中央への壁の設置
		if (num <= 2) {
			int terrainEquator = rand.nextInt(5-num);
			if (BoardNumber.boardNumber%8 == 0) {
				for (int y = 0; y < terrainEquator; y++) {
					if ((adjustSetCrackedWall >= 8) || (rand.nextInt(8) == 0)) {
						// 8個に1個は最初からひび割れた壁
						Board.board[terrainNorth+y+2][Board.numOfSquareWidth-1] = SquareType.squareIsCrackedWall;
						adjustSetCrackedWall = 0;
					} else {
						Board.board[terrainNorth+y+2][Board.numOfSquareWidth-1] = SquareType.squareIsImpassable;
						adjustSetCrackedWall ++;
					}
				}
			} else if (BoardNumber.boardNumber%4 == 0) {
				for (int y = 0; y < terrainEquator; y++) {
					if ((adjustSetCrackedWall >= 8) || (rand.nextInt(8) == 0)) {
						// 8個に1個は最初からひび割れた壁
						Board.board[Board.numOfSquareHeight-terrainSouth-y-3][Board.numOfSquareWidth-1] = SquareType.squareIsCrackedWall;
						adjustSetCrackedWall = 0;
					} else {
						Board.board[Board.numOfSquareHeight-terrainSouth-y-3][Board.numOfSquareWidth-1] = SquareType.squareIsImpassable;
						adjustSetCrackedWall ++;
					}
				}
			}
		}

		// 次の列の地形の決定
		if (num <= 0) {
			switch (rand.nextInt(8)) {
			case 0:
			case 1:
				terrainNorth++;
				break;
			case 2:
			case 3:
				terrainSouth++;
				break;
			case 4:
				terrainNorth++;
				terrainSouth++;
				break;
			}
		} else if (num >= 5) {
			switch (rand.nextInt(8)) {
			case 0:
				break;
			case 1:
				terrainNorth--;
				break;
			case 2:
				terrainSouth--;
				break;
			default:
				terrainNorth--;
				terrainSouth--;
			}
		} else if (num == 1 || num == 2) {
			switch (rand.nextInt(8)) {
			case 0:
				terrainNorth--;
				break;
			case 1:
				terrainSouth--;
				break;
			case 2:
				terrainNorth++;
				break;
			case 3:
				terrainSouth++;
				break;
			case 4:
				terrainNorth--;
				terrainSouth--;
				break;
			case 5:
				terrainNorth++;
				terrainSouth++;
				break;
			}
		} else if (num == 3 || num == 4) {
			switch (rand.nextInt(8)) {
			case 0:
				terrainNorth++;
				break;
			case 1:
				terrainSouth++;
				break;
			case 2:
			case 3:
				terrainNorth--;
				break;
			case 4:
			case 5:
				terrainSouth--;
				break;
			default:
				terrainNorth--;
				terrainSouth--;
			}
		}

		// 補正
		if (terrainNorth < 0) terrainNorth = 0;
		if (terrainSouth < 0) terrainSouth = 0;
		if (terrainNorth > 4) terrainNorth = 4;
		if (terrainSouth > 4) terrainSouth = 4;
	}
}
