package rgb.lawillia;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import rgb.lawillia.audio.Audio;
import rgb.lawillia.board.Board;
import rgb.lawillia.board.BoardNumber;
import rgb.lawillia.board.DrawSquare;
import rgb.lawillia.board.SquareType;
import rgb.lawillia.game.Game;
import rgb.lawillia.game.GameStart;
import rgb.lawillia.game.LevelClear;
import rgb.lawillia.player.Items;
import rgb.lawillia.player.Player;
import rgb.lawillia.ui.Log;
import rgb.lawillia.ui.PlayerData;
import rgb.lawillia.ui.Score;
import rgb.lawillia.ui.UI;

public class RD_GAME_BOA extends JFrame {
    final public static int windowWidth = 800;		// ウィンドウの幅
    final public static int windowHeight = 800;		// ウィンドウの高さ

    // ゲームのバージョン
    final public static String GAME_VERSION = "1.0.0";

    public static void main(String[] args){
        new RD_GAME_BOA();
    }

    public RD_GAME_BOA() {
		// スクリーンの大きさを取得する。
		Dimension dimOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
		// ウィンドウをスクリーンの中央に設定する。
		setBounds(dimOfScreen.width/2 - windowWidth/2, dimOfScreen.height/2 - windowHeight/2,
				windowWidth, windowHeight);
		// ウィンドウの大きさを固定する。
		setResizable(false);

		setTitle("RD-GANE-BOA");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		MyJPanel panel= new MyJPanel();
		Container c = getContentPane();
		c.add(panel);
		setVisible(true);
	    }

	    public class MyJPanel extends JPanel
			implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
		/* 全体の設定に関する変数 */
		Dimension dimOfPanel;
		Timer timer;

		public MyJPanel() {
			/* 全体の設定 */
			setLayout(null);					// レイアウトのリセット
			setBackground(Color.black);			// 背景色を黒に
			addMouseListener(this);				// マウスの操作の取得
			addMouseMotionListener(this);		// マウスの移動の取得
			setFocusable(true);					// キーボード受け付けの前提
			addKeyListener(this);				// キーボードの操作の取得
			timer = new Timer(1000, this);		// タイマーの追加

			BoardNumber.initBoardNumber();		// 何列目であるかの描画
			PlayerData.initPlayerData();		// プレイヤーの情報
			Log.initLog();						// ログ
			Score.initScoreBoard();				// スコアの情報
			Game.initLabelGame();				// ゲーム進行用ラベル
			initAudio();						// BGM・SEの取得

			addLabel();							// Labelの表示
			Audio.playBGM(0);					// BGMの再生

			// クレジット表記
			Log.updateLog("製作：Lawillia (Copyright © 2017 Lawillia)");
			Log.updateLog("BGM：「Senses Circuit」様（http://www.senses-circuit.com/）");
			Log.updateLog("SE：「魔王魂」様（http://maoudamashii.jokersounds.com/）");
			Log.updateLog("");
			Log.updateLog("THANK YOU FOR PLAYING!!");
		}

		/* パネル上の描画 */
	    public void paintComponent(Graphics g) {
			super.paintComponent(g);			// 描画のリセット

			if (Game.isGameRunning) {
				Board.drawBoard(g);				// 盤面の描画
				DrawSquare.drawOnBoard(g);		// 盤面上の描画
				BoardNumber.updateBoardNumber();// 何列目であるかの描画の更新
			}
			UI.drawEnclosure(g);				// 盤面周りの描画
		}

		/* ActionListener に対する処理 */
		public void actionPerformed(ActionEvent e) {
			// タイマーが動いている時
			if (timer.isRunning()) {
				if (!Game.isGameRunning) {
					timer.stop();
				} else {
					if (Items.turnFreezeEnemies == 0) {
						Score.totalTime++;
						Score.lapTime++;
					}
				}
			} else {
				if (Game.isGameRunning) {
					timer.start();
				}
			}

			Score.updateTime();
			repaint();							// 再描画
		}

		/* KeyListener に対する処理 */
		public void keyTyped(KeyEvent e) {
			/* キーがタイプされたときの処理 */
		}

		public void keyPressed(KeyEvent e) {
			/* キーが押されたときの処理 */
			int key = e.getKeyCode();
			int keyM = e.getModifiersEx();

			// ゲーム中以外
			if (Game.isStageClear) {
				if((key == KeyEvent.VK_ENTER) || key == KeyEvent.VK_SPACE) {
					// ゲーム再開
					LevelClear.nextStage();
					timer.start();
				}
			} else if (!Game.isGameRunning) {
				if((key == KeyEvent.VK_ENTER) || key == KeyEvent.VK_SPACE) {
					// ゲーム開始
					GameStart.setSP();
					timer.start();
				}
			}

			// ゲーム中
			if (Game.canPlayerMove && Game.isGameRunning) {
				// アイテムの使用
				if((keyM == InputEvent.SHIFT_DOWN_MASK) || (keyM == InputEvent.CTRL_DOWN_MASK)) {
					if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
						Game.leftPressed = true;
						Items.useItem();
					} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			        	Game.rightPressed = true;
			        	Items.useItem();
					} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
						Game.upPressed = true;
						Items.useItem();
			        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			        	Game.downPressed = true;
			        	Items.useItem();
		        	}

				// プレイヤーの移動
				} else {
					if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
						Game.leftPressed = true;
						Player.movePlayer();
			        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			        		Game.rightPressed = true;
						Player.movePlayer();
					} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
						Game.upPressed = true;
						Player.movePlayer();
			        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			        	Game.downPressed = true;
						Player.movePlayer();
		        	}
				}
			}

			repaint();							// 再描画
		}

		public void keyReleased(KeyEvent e) {
			/* キーが離されたときの処理 */
		}

		/* MouseListener に対する処理 */
		public void mouseClicked(MouseEvent e) {
			/* マウスボタンをクリックしたときの処理 */
			int x = e.getX() / Board.squareSize;
			int y = (e.getY() - Board.boardY - Board.squareSize) / Board.squareSize;

			if (Game.isGameRunning) {
				if ((y >= 0) && (y <= Board.numOfSquareHeight)) {
					SquareType.getTipsSquare(x, y);
				}
			}

			repaint();
		}

		public void mousePressed(MouseEvent e) {
			/* マウスボタンを押したときの処理 */
		}

		public void mouseReleased(MouseEvent e) {
			/* マウスボタンを離したときの処理 */
		}

		public void mouseExited(MouseEvent e) {
	    	/* マウスが対象のコンポーネントから出たときの処理 */
	    }

	    public void mouseEntered(MouseEvent e) {
	    	/* マウスが対象のコンポーネントに入ったときの処理 */
	    }


	    /* MouseMotionListener に対する処理 */
	    public void mouseMoved(MouseEvent e) {
	    	/* マウスを動かしたときの処理 */
	    }

	    public void mouseDragged(MouseEvent e) {
	    	/* マウスをドラッグしたときの処理 */
	    }


	    /* 他のクラスで行う方法が分からない処理 */
	    // ラベル関連
	    public void addLabel() {
	    	// 画面左下のプレイヤー情報
			add(PlayerData.labelPlayer);
			add(PlayerData.labelPlayerLife);
			add(PlayerData.labelScoreKill);
			add(PlayerData.labelMoveTurn);

			// 画面上部のログ
			add(Log.labelLog1);
			add(Log.labelLog2);
			add(Log.labelLog3);
			add(Log.labelLog4);
			add(Log.labelLog5);
			add(Log.labelLog6);

			// 画面右下のスコア
			add(Score.labelScore);
			add(Score.labelTotalScore);
			add(Score.labelTime);
			add(Score.labelTotalTime);
			add(Score.labelLapTime);

			// 盤面の数字
			add(BoardNumber.labelBoardNumber1);
			add(BoardNumber.labelBoardNumber2);
			add(BoardNumber.labelBoardNumber3);
			add(BoardNumber.labelBoardNumber4);
			add(BoardNumber.labelBoardNumber5);
			add(BoardNumber.labelBoardNumber6);
			add(BoardNumber.labelBoardNumber7);
			add(BoardNumber.labelBoardNumber8);
			add(BoardNumber.labelBoardNumber9);
			add(BoardNumber.labelBoardNumber10);
			add(BoardNumber.labelBoardNumber11);
			add(BoardNumber.labelBoardNumber12);
			add(BoardNumber.labelBoardNumber13);
			add(BoardNumber.labelBoardNumber14);
			add(BoardNumber.labelBoardNumber15);
			add(BoardNumber.labelBoardNumber16);

			// ゲーム進行関連
			add(Game.labelGameOver);
			add(Game.labelStageClear);
			add(Game.labelPressAnyKey);
			add(Game.labelData01);
			add(Game.labelData02);
			add(Game.labelData03);
			add(Game.labelData04);
			add(Game.labelData05);
	    }

	    // オーディオ関連
	    public void initAudio() {
	    	// SE
	    	Audio.clipKillSE = getAudio("se_maoudamashii_battle14");
	    	Audio.clipArrowAttack = getAudio("se_maoudamashii_battle_gun05");
	    	Audio.clipCrossAttack = getAudio("se_maoudamashii_battle11");
	    	Audio.clipWallBroken = getAudio("se_maoudamashii_battle17");
	    	Audio.clipUseItem = getAudio("se_maoudamashii_onepoint07");
	    	Audio.clipLevelUp = getAudio("se_maoudamashii_onepoint16");
	    	Audio.clipEnemyLevelUp = getAudio("se_maoudamashii_onepoint13");

	    	// BGM
	    	Audio.clipStartMenu = getAudio("loop_79");
	    	Audio.clipGameOver = getAudio("loop_16");
	    	Audio.stageBGM01 = getAudio("loop_39_n");
	    	Audio.stageBGM02 = getAudio("loop_14");
	    	Audio.stageBGM03 = getAudio("loop2_81");
	    	Audio.stageBGM04 = getAudio("loop2_46");
	    	Audio.stageBGM05 = getAudio("loop2_36");
	    	Audio.stageBGM06 = getAudio("loop2_135");
	    	Audio.stageBGM07 = getAudio("loop2_104");
	    }

	    public AudioClip getAudio(String fileName) {
	    	AudioClip audio = null;
	    	URL url = null;
	    	String str = new String("");
	    	Boolean canGetAudio = true;

	    	str = getClass().getResource("RD_GAME_BOA.class").toString().replaceAll("RD_GAME_BOA.class", "audio/"+ fileName + ".wav");

	    	try {
	    		url = new URL(str);
			}
			catch (MalformedURLException e) {
				System.err.println(e);
				canGetAudio = false;
			}

	    	try{
	    		audio = Applet.newAudioClip(url);
	    	} catch (NullPointerException e) {
				System.err.println(e);
				canGetAudio = false;
	    	}

	    	if (!canGetAudio) {
	    		System.out.println("オーディオ素材" + fileName + ".wavを取得できませんでした。");
	    	}

	    	return audio;
	    }

    }
}
