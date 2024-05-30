import javax.microedition.lcdui.Graphics;
import java.util.Random;
import java.util.Vector;

public final class GameEngine {
  MyGameCanvas canvas;
  GameMap map; //地图
  GameRole role; //人物
  final byte tileWidth = 16;
  int index;
  Vector enemyShot;

  byte gameRank;
  public static int gameTime = 0;
  static Random rnd = new Random();

  public GameEngine() {
    canvas = MyGameCanvas.me;
    map = new GameMap(MyGameCanvas.SCREEN_WIDTH, MyGameCanvas.SCREEN_HEIGHT);
    role = new GameRole(this);

  }

  public void ctrlReleased(int keyCode) {
    role.ctrlReleased(keyCode);

  }
  //游戏控制
  public void ctrl(int keyCode) {
    role.ctrl(keyCode);
  }

  //显示游戏
  public void drawGame(Graphics g, boolean isDrawRole) {
    map.setMap(g);
    g.setColor(0);
    if (isDrawRole) {
      role.paint();
    }
  }

  public void rankEnd() {

  }

  public void runGame() {
    role.move();
    map.AdjustSrceen(role, role.x + role.w / 2, role.y);
    rankEnd();
    screenShake();
  }

  static byte shakeTime = 0;
  public void screenShake() {
    if (shakeTime > 0) {
      shakeTime--;
      map.setOffY = shakeTime % 2 == 0 ? (short) (map.setOffY + 2) :
          (short) (map.setOffY - 2);
    }
    map.setOffY = (short) Math.max(map.setOffY, 0);
    map.setOffY = (short) Math.min(map.setOffY,
                                   map.mapSize[1] * map.tileHight -
                                   map.SCREEN_HEIGHT);
  }

//游戏初始化

  public void initGame() {
//    gameRank =18;
    gameTime = 0;
    map.init(gameRank);
    role.init(gameRank);
//    role.x = 80;
//    role.y = 80;
    role.speed = 4;

  }

  // 升成 n 到 m
  public static int nextInt(int n, int m) {
    if (n == m) {
      return n;
    }
    if (n > m) {
      int temp = n;
      n = m;
      m = temp;
    }
    return (int) ( (Math.abs(rnd.nextInt()) % (m - n + 1)) + n);
  }

}