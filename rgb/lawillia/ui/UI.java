package rgb.lawillia.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import rgb.lawillia.RD_GAME_BOA;
import rgb.lawillia.board.Board;
import rgb.lawillia.player.Player;

public class UI {
	/* 全体 */
	// 情報表示部分の囲み線
	public static void drawEnclosure(Graphics g) {
		// 画面左下：プレイヤーの情報
		if (Player.isPlayerInjured){
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
		g.drawRoundRect(Board.squareHalf -5, Board.boardY2 + 5,
				Board.squareSize * 6 + 10, RD_GAME_BOA.windowHeight - Board.boardY2 - Board.squareHalf - 15,
				(int)Board.squareHalf / 4, (int)Board.squareHalf / 8);

		// 画面右下：スコアの情報
		g.setColor(Color.WHITE);
		g.drawRoundRect(Board.squareSize * 7 + Board.squareHalf -5, Board.boardY2 + 5,
				Board.squareSize * 8 + 10, RD_GAME_BOA.windowHeight - Board.boardY2 - Board.squareHalf - 15,
				(int)Board.squareHalf / 4, (int)Board.squareHalf / 8);

		// 画面上：ログ
		g.setColor(Color.GRAY);
		g.drawRoundRect(Board.squareHalf -5, 0,
				RD_GAME_BOA.windowWidth - Board.squareHalf*2 + 5, Board.boardY,
				(int)Board.squareHalf / 4, (int)Board.squareHalf / 8);
	}

	/* 機能別メソッド */
	// JLabelの登録
	public static void initJLabel(JLabel label, int color, int alignment,int size) {
		// 文字色
		if (color == 0){
			label.setForeground(Color.WHITE);
		} else if (color == 1) {
			label.setForeground(Color.GRAY);
		} else if (color == 2) {
			label.setForeground(Color.RED);
		}

		// 位置
		if (alignment == 0) {
			label.setVerticalAlignment(JLabel.BOTTOM);
		} else if (alignment == 1) {
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.BOTTOM);
		} else if (alignment == 2) {
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
		}

		label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, size));
	}

}
