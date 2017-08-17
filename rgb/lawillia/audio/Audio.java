package rgb.lawillia.audio;

import java.applet.AudioClip;

public class Audio {
	// SE 「魔王魂」（http://maoudamashii.jokersounds.com/）様からお借りしました
	public static AudioClip clipKillSE;		// 敵を倒した時のSE
	public static AudioClip clipArrowAttack;	// 敵（矢印）の攻撃のSE
	public static AudioClip clipCrossAttack;	// 敵（十字）の攻撃のSE
	public static AudioClip clipWallBroken;	// 壁が壊れた時のSE
	public static AudioClip clipUseItem;		// アイテムを使用した時のSE
	public static AudioClip clipLevelUp;		// レベルアップした時のSE
	public static AudioClip clipEnemyLevelUp;	// 敵がレベルアップした時のSE

	// BGM 「Senses Circuit」様（http://www.senses-circuit.com/）様からお借りしました
	public static AudioClip clipStartMenu;	// 開始時のBGM
	public static AudioClip clipGameOver;		// ゲームオーバー時のBGM
	public static AudioClip stageBGM01;		// 以下、ステージBGM
	public static AudioClip stageBGM02;
	public static AudioClip stageBGM03;
	public static AudioClip stageBGM04;
	public static AudioClip stageBGM05;
	public static AudioClip stageBGM06;
	public static AudioClip stageBGM07;

	public static void playBGM(int BGM) {
		stopBGM();

		// BGMの再生
		try{
			switch(BGM){
			case 0:
				clipStartMenu.loop();
				break;
			case 1:
				clipGameOver.loop();
				break;
			case 2:
				stageBGM01.loop();
				break;
			case 3:
				stageBGM02.loop();
				break;
			case 4:
				stageBGM03.loop();
				break;
			case 5:
				stageBGM04.loop();
				break;
			case 6:
				stageBGM05.loop();
				break;
			case 7:
				stageBGM06.loop();
				break;
			case 8:
				stageBGM07.loop();
				break;
			}
		} catch (NullPointerException e) {

		}
	}

	public static void stopBGM() {
		// 全BGMの停止
		try{
			clipStartMenu.stop();
			clipGameOver.stop();
			stageBGM01.stop();
			stageBGM02.stop();
			stageBGM03.stop();
			stageBGM04.stop();
			stageBGM05.stop();
			stageBGM06.stop();
			stageBGM07.stop();
		} catch (NullPointerException e) {

		}
	}
}
