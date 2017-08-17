package rgb.lawillia.board;

import javax.swing.JLabel;

import rgb.lawillia.ui.UI;

public class BoardNumber {
	/* 盤面の数字に関する変数 */
	public static JLabel
		labelBoardNumber1, labelBoardNumber2, labelBoardNumber3, labelBoardNumber4,
		labelBoardNumber5, labelBoardNumber6, labelBoardNumber7, labelBoardNumber8,
		labelBoardNumber9, labelBoardNumber10, labelBoardNumber11, labelBoardNumber12,
		labelBoardNumber13, labelBoardNumber14, labelBoardNumber15, labelBoardNumber16;
	public static int boardNumber;											// 盤面の一番左の列の番号

	// 盤面の数字の初期化
	public static void initBoardNumber() {
		int i = 0;

		labelBoardNumber1 = new JLabel("");
		UI.initJLabel(labelBoardNumber1, 0, 1, 12);
		labelBoardNumber1.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber2 = new JLabel("");
		UI.initJLabel(labelBoardNumber2, 0, 1, 12);
		labelBoardNumber2.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber3 = new JLabel("");
		UI.initJLabel(labelBoardNumber3, 0, 1, 12);
		labelBoardNumber3.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber4 = new JLabel("");
		UI.initJLabel(labelBoardNumber4, 0, 1, 12);
		labelBoardNumber4.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber5 = new JLabel("");
		UI.initJLabel(labelBoardNumber5, 0, 1, 12);
		labelBoardNumber5.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber6 = new JLabel("");
		UI.initJLabel(labelBoardNumber6, 0, 1, 12);
		labelBoardNumber6.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber7 = new JLabel("");
		UI.initJLabel(labelBoardNumber7, 0, 1, 12);
		labelBoardNumber7.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber8 = new JLabel("");
		UI.initJLabel(labelBoardNumber8, 0, 1, 12);
		labelBoardNumber8.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber9 = new JLabel("");
		UI.initJLabel(labelBoardNumber9, 0, 1, 12);
		labelBoardNumber9.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber10 = new JLabel("");
		UI.initJLabel(labelBoardNumber10, 0, 1, 12);
		labelBoardNumber10.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber11 = new JLabel("");
		UI.initJLabel(labelBoardNumber11, 0, 1, 12);
		labelBoardNumber11.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber12 = new JLabel("");
		UI.initJLabel(labelBoardNumber12, 0, 1, 12);
		labelBoardNumber12.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber13 = new JLabel("");
		UI.initJLabel(labelBoardNumber13, 0, 1, 12);
		labelBoardNumber13.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber14 = new JLabel("");
		UI.initJLabel(labelBoardNumber14, 0, 1, 12);
		labelBoardNumber14.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber15 = new JLabel("");
		UI.initJLabel(labelBoardNumber15, 0, 1, 12);
		labelBoardNumber15.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
		i++;

		labelBoardNumber16 = new JLabel("");
		UI.initJLabel(labelBoardNumber16, 0, 1, 12);
		labelBoardNumber16.setBounds(i * Board.squareSize + 2, Board.boardY + 2, Board.squareSize - 8, Board.squareSize - 8);
	}

	// 盤面の数字の反映
	public static void updateBoardNumber() {
		int i = boardNumber;

		labelBoardNumber1.setText("" + i);
		i++;

		labelBoardNumber2.setText("" + i);
		i++;

		labelBoardNumber3.setText("" + i);
		i++;

		labelBoardNumber4.setText("" + i);
		i++;

		labelBoardNumber5.setText("" + i);
		i++;

		labelBoardNumber6.setText("" + i);
		i++;

		labelBoardNumber7.setText("" + i);
		i++;

		labelBoardNumber8.setText("" + i);
		i++;

		labelBoardNumber9.setText("" + i);
		i++;

		labelBoardNumber10.setText("" + i);
		i++;

		labelBoardNumber11.setText("" + i);
		i++;

		labelBoardNumber12.setText("" + i);
		i++;

		labelBoardNumber13.setText("" + i);
		i++;

		labelBoardNumber14.setText("" + i);
		i++;

		labelBoardNumber15.setText("" + i);
		i++;

		labelBoardNumber16.setText("" + i);
		i++;
	}

	// 盤面の数字の非表示
	public static void hideBoardNumber() {
		labelBoardNumber1.setText("");
		labelBoardNumber2.setText("");
		labelBoardNumber3.setText("");
		labelBoardNumber4.setText("");
		labelBoardNumber5.setText("");
		labelBoardNumber6.setText("");
		labelBoardNumber7.setText("");
		labelBoardNumber8.setText("");
		labelBoardNumber9.setText("");
		labelBoardNumber10.setText("");
		labelBoardNumber11.setText("");
		labelBoardNumber12.setText("");
		labelBoardNumber13.setText("");
		labelBoardNumber14.setText("");
		labelBoardNumber15.setText("");
		labelBoardNumber16.setText("");
	}
}
