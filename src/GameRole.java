import java.util.*;
import javax.microedition.lcdui.*;

public final class GameRole
    extends GameInterface {
  public final static byte ROLE_MOVE = 4; //人物移动距离

  public static GameEngine engine;
  public static boolean isMove = false;
  public static boolean isFinish = false;
  int ability;
  Vector roleShot = new Vector();

  public static byte lastStatus;
  public static byte nextStatus;

  short attackMax = 4;
  short skillMax = 12;
  int sx;
  int sy;

  static boolean isAuto = false;

  byte curIndex; //当前显示人物动作的索引

  public GameRole(GameEngine ge) {
    engine = ge;
  }

  public void init(int gameRank) {
    x = 192;
    y = 528;
    w = 24;
    h = 24;
    setStatus(STATUS_STOP);
    //dir = DIR_DOWN;
  }

  byte lastDir;

  //攻击
  public void attack() {
    switch (lastDir) {
      case DIR_UP:
        if (x == 216 && y - 24 == 456 && GameMap.state1 < 3) {
          GameMap.state1++;
        }
//        System.out.println(GameMap.decClip.length);
        else {
          for (int i = 0; i < GameMap.decClip.length; i++) {
            if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                   GameMap.sete[i] == 1)) {
              if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                  GameMap.decClip[i][1] != Tools.IMG_SIG &&
                  GameMap.decClip[i][1] != Tools.IMG_RAN
                  && GameMap.decClip[i][2] <= x &&
                  x < GameMap.decClip[i][2] + GameMap.decClip[i][4] &&
                  GameMap.decClip[i][3] == y - 24 &&
                  y > 48) {
                GameMap.dey[i] -= 6;
              }
            }
          }
        }
        break;
      case DIR_DOWN:
        if (x == 216 && y + 24 == 456 && GameMap.state1 < 3) {
          GameMap.state1++;
        }
        else {
          for (int i = 0; i < GameMap.decClip.length; i++) {
            if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                   GameMap.sete[i] == 1)) {
              if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                  GameMap.decClip[i][1] != Tools.IMG_SIG &&
                  GameMap.decClip[i][1] != Tools.IMG_RAN
                  && GameMap.decClip[i][2] <= x &&
                  x < GameMap.decClip[i][2] + GameMap.decClip[i][4] &&
                  GameMap.decClip[i][3] - GameMap.decClip[i][5] == y &&
                  y < 528) {
                GameMap.dey[i] += 6;
              }
            }
          }
        }

        break;
      case DIR_RIGHT:
        if (x + 24 == 216 && y == 456 && GameMap.state1 < 3) {
          GameMap.state1++;
        }
        else {
          for (int i = 0; i < GameMap.decClip.length; i++) {
            if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                   GameMap.sete[i] == 1)) {
              if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                  GameMap.decClip[i][1] != Tools.IMG_SIG &&
                  GameMap.decClip[i][1] != Tools.IMG_RAN
                  && GameMap.decClip[i][2] == x + 24 &&
                  GameMap.decClip[i][3] - GameMap.decClip[i][5] < y &&
                  y <= GameMap.decClip[i][3] &&
                  x < 240) {
                GameMap.dex[i] += 6;

              }
            }
          }
        }

        break;
      case DIR_LEFT:
        if (x - 24 == 216 && y == 456 && GameMap.state1 < 3) {
          GameMap.state1++;
        }
        else {
          for (int i = 0; i < GameMap.decClip.length; i++) {
            if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                   GameMap.sete[i] == 1)) {
              if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                  GameMap.decClip[i][1] != Tools.IMG_SIG &&
                  GameMap.decClip[i][1] != Tools.IMG_RAN
                  && GameMap.decClip[i][2] + GameMap.decClip[i][4] == x &&
                  GameMap.decClip[i][3] - GameMap.decClip[i][5] < y &&
                  y <= GameMap.decClip[i][3] &&
                  x > 48) {
                GameMap.dex[i] -= 6;

              }
            }
          }
        }

        break;

      default:
        break;
    }
//    for (int i = 0; i < GameMap.decClip.length; i++) {
//      if (GameMap.decClip[i][1] == Tools.IMG_IRON &&
//          (GameMap.decClip[i][2] == 24 && GameMap.decClip[i][3] == 192 ||
//           GameMap.decClip[i][2] == 96 && GameMap.decClip[i][3] == 288 ||
//           GameMap.decClip[i][2] == 240 && GameMap.decClip[i][3] == 408)
//          ) {
//        GameMap.sete[i] = 1;
//        if (GameMap.decClip[i][2] == 24 && GameMap.decClip[i][3] == 192) {
//          GameMap.sete[9] = 1;
//        }
//        if (GameMap.decClip[i][2] == 96 && GameMap.decClip[i][3] == 288) {
//          GameMap.sete[10] = 1;
//        }
//        if (GameMap.decClip[i][2] == 240 && GameMap.decClip[i][3] == 408) {
//          GameMap.sete[11] = 1;
//        }
//      }
//
//    }

  }

  public void move() {
    switch (curStatus) {
      case STATUS_MOVE:
        switch (dir) {
          case DIR_UP:
            motion = new byte[] {
                4, 3, 5, 3};
            curIndex = motion[index];
            if (y > 24) {
              roleMove(0, -ROLE_MOVE);
            }
            if (++index >= motion.length) {
              index = 0;
            }

            break;
          case DIR_DOWN:
            motion = new byte[] {
                1, 0, 2, 0};
            curIndex = motion[index];
            if (y < 528) {
              roleMove(0, ROLE_MOVE);
            }
            if (++index >= motion.length) {
              index = 0;
            }
            break;
          case DIR_LEFT:
            motion = new byte[] {
                6, 7, 8, 7};
            curIndex = motion[index];
            if (x > 0) {
              roleMove( -ROLE_MOVE, 0);
            }
            if (++index >= motion.length) {
              index = 0;
            }
            break;
          case DIR_RIGHT:
            motion = new byte[] {
                6, 7, 8, 7};
            curIndex = motion[index];
            if (x < 264) {
              roleMove(ROLE_MOVE, 0);
            }
            if (++index >= motion.length) {
              index = 0;
            }
            break;
          case 5:
            switch (lastDir) {
              case DIR_UP:
                motion = new byte[] {
                    12, 13, 14};

                break;
              case DIR_RIGHT:
                motion = new byte[] {
                    15, 16, 17};

//                curIndex = motion[index];
//                if (++index >= motion.length) {
//                  index = 0;
//                }

                break;
              case DIR_DOWN:
                motion = new byte[] {
                    9, 10, 11};

//                curIndex = motion[index];
//                if (++index >= motion.length) {
//                  index = 0;
//                }

                break;
              case DIR_LEFT:
                motion = new byte[] {
                    15, 16, 17};

//                curIndex = motion[index];
//                if (++index >= motion.length) {
//                  index = 0;
//                }

                break;
            }
//            attack();
          default:
            break;
        }
        break;
      case STATUS_STOP:
        switch (dir) {
          case DIR_UP:
            if (y % 24 != 0) {
              motion = new byte[] {
                  4, 3, 5, 3};
              curIndex = motion[index];
              y -= ROLE_MOVE;
              if (++index >= motion.length) {
                index = 0;
              }
              for (int i = 0; i < GameMap.dey.length; i++) {
                if (GameMap.dey[i] % 6 != 0) {
                  GameMap.dey[i]--;
                }
              }
            }
            else {
              curIndex = 3;
            }
            break;
          case DIR_DOWN:
            if (y % 24 != 0) {
              motion = new byte[] {
                  1, 0, 2, 0};
              curIndex = motion[index];
              roleMove(0, ROLE_MOVE);
              if (++index >= motion.length) {
                index = 0;
              }
              for (int i = 0; i < GameMap.dey.length; i++) {
                if (GameMap.dey[i] % 6 != 0) {
                  GameMap.dey[i]++;
                }
              }

            }

            else {
              curIndex = 0;
            }
            break;
          case DIR_LEFT:
            if (x % 24 != 0) {
              motion = new byte[] {
                  6, 7, 8, 7};
              curIndex = motion[index];
              x -= ROLE_MOVE;
              if (++index >= motion.length) {
                index = 0;
              }
              for (int i = 0; i < GameMap.dex.length; i++) {
                if (GameMap.dex[i] % 6 != 0) {
                  GameMap.dex[i]--;
                }
              }

            }

            else {
              curIndex = 7;
            }
            break;
          case DIR_RIGHT:
            if (x % 24 != 0) {
              motion = new byte[] {
                  6, 7, 8, 7};
              curIndex = motion[index];
              x += ROLE_MOVE;
              if (++index >= motion.length) {
                index = 0;
              }
              for (int i = 0; i < GameMap.dex.length; i++) {
                if (GameMap.dex[i] % 6 != 0) {
                  GameMap.dex[i]++;
                }
              }

            }

            else {
              curIndex = 7;
            }
            break;
        }
        break;
      default:
        break;
    }

  }

  byte[][] roleClip = {
      {
      0, 0, 26, 31}
      ,
      /*图片说明*/{
      26, 0, 26, 29}
      ,
      /*图片说明*/{
      52, 0, 27, 22}
      ,
      /*图片说明*/{
      54, 22, 24, 13}
      ,
      /*图片说明*/{
      79, 0, 12, 28}
      ,
      /*图片说明*/{
      91, 0, 2, 28}
      ,
      /*图片说明*/{
      0, 31, 27, 31}
      ,
      /*图片说明*/{
      27, 29, 27, 18}
      ,
      /*图片说明*/{
      27, 47, 26, 13}
      ,
      /*图片说明*/{
      54, 35, 25, 13}
      ,
      /*图片说明*/{
      79, 28, 13, 28}
      ,
      /*图片说明*/{
      53, 64, 20, 11}
      ,
      /*图片说明*/{
      53, 76, 19, 9}
      ,
      /*图片说明*/{
      72, 78, 7, 6}
      ,
      /*图片说明*/{
      94, 0, 5, 8}
      ,
      /*图片说明*/{
      94, 8, 5, 7}
      ,
      /*图片说明*/{
      94, 15, 4, 9}
      ,
      /*图片说明*/{
      93, 24, 4, 9}
      ,
      /*图片说明*/{
      98, 24, 2, 10}
      ,
      /*图片说明*/{
      92, 34, 6, 13}
      ,
      /*图片说明*/{
      99, 36, 1, 6}
      ,
      /*图片说明*/{
      74, 48, 5, 10}
      ,
      /*图片说明*/{
      17, 62, 15, 5}
      ,
      /*图片说明*/{
      18, 67, 11, 2}
      ,
      /*图片说明*/{
      0, 83, 6, 2}
      ,
      /*图片说明*/{
      35, 73, 4, 8}
      ,
      /*图片说明*/{
      40, 74, 3, 4}
      ,
      /*图片说明*/{
      44, 78, 8, 7}
      ,
      /*图片说明*/{
      53, 54, 10, 9}
      ,
      /*图片说明*/{
      92, 47, 8, 10}
      ,
      /*图片说明*/{
      0, 62, 12, 21}
      ,
      /*图片说明*/{
      12, 62, 5, 8}
      ,
      /*图片说明*/{
      12, 70, 12, 14}
      ,
      /*图片说明*/{
      24, 69, 11, 11}
      ,
      /*图片说明*/{
      35, 60, 9, 13}
      ,
      /*图片说明*/{
      44, 60, 9, 17}
      ,
      /*图片说明*/{
      63, 52, 10, 11}
      ,
      /*图片说明*/{
      75, 59, 14, 10}
      ,
      /*图片说明*/{
      79, 69, 11, 16}
      ,
      /*图片说明*/{
      90, 57, 10, 28}
      ,
      /*图片说明*/{
      7, 84, 2, 1}
      ,
      /*图片说明*/{
      24, 81, 3, 4}
      ,
      /*图片说明*/{
      28, 81, 4, 3}
      ,
      /*图片说明*/{
      33, 81, 4, 3}
      ,
      /*图片说明*/{
      38, 81, 3, 4}
  }; /*图片说明*/
  byte roleFrame[][] = {
      {
      4, 4, -1, 18, 0, 5, 16, -1, 16, 1, 4, 18, -1, 4, 1, 27, 5, 0, 21, 0, 27,
      21, 0, 5, 1, 14, 0, -10, 29, 0, 14, 29, -10, 0, 1, 44, 15, -29, 16, 0}
      , {
      0, 4, 0, 3, 0, 41, 17, -31, 13, 0, 15, 0, -17, 28, 0, 16, 29, -9, 0, 0}
      , {
      0, 3, 0, 4, 1, 15, 28, -17, 0, 1, 16, 0, -9, 29, 1, 41, 13, -31, 17, 1}
      , {
      10, 4, -5, 17, 0, 10, 17, -5, 4, 1, 18, 4, -10, 28, 0, 18, 28, -10, 4, 1,
      25, 0, -10, 30, 0, 25, 30, -10, 0, 1, 44, 15, 0, 16, 2, 23, 6, -3, 17, 0,
      23, 17, -3, 6, 1, 20, 5, -5, 28, 0, 20, 28, -5, 5, 0, 40, 16, -33, 16, 0}
      , {
      1, 3, -4, 4, 0, 21, 0, -8, 28, 0, 19, 27, -11, 0, 1, 22, 12, 0, 6, 0, 26,
      24, -3, 6, 1, 20, 26, -3, 6, 0, 40, 13, -33, 18, 0}
      , {
      20, 6, -4, 26, 0, 19, 0, -11, 27, 0, 20, 6, -3, 26, 0, 21, 6, -1, 22, 0,
      22, 6, 0, 12, 1, 1, 4, -4, 3, 1, 21, 28, -8, 0, 1, 40, 18, -33, 13, 0}
      , {
      7, 0, -14, 1, 0, 8, 0, -1, 2, 0, 43, 24, -11, 0, 0, 13, 14, 0, 7, 0, 26,
      1, -23, 24, 0}
      , {
      2, 0, -11, 2, 0, 11, 5, 0, 4, 0, 42, 25, -11, 0, 0}
      ,

      {
      24, 5, 0, 18, 0, 43, 24, -7, 1, 2, 26, 26, -20, 0, 1, 23, 17, -18, 1, 0,
      6, 0, -2, 2, 0}
      , {
      4, 7, -4, 21, 0, 4, 21, -4, 7, 1, 5, 19, -4, 19, 0, 27, 8, -3, 24, 0, 27,
      24, -3, 8, 1, 30, 28, 0, 0, 0, 30, 0, 0, 28, 1, 44, 18, -32, 19, 0}
      ,

      {
      4, 6, -1, 20, 0, 4, 20, -1, 6, 1, 5, 18, -1, 18, 0, 27, 7, 0, 23, 0, 27,
      23, 0, 7, 1, 38, 27, -11, 0, 0, 38, 0, -11, 27, 1, 44, 17, -29, 18, 0}
      , {
      4, 2, -15, 16, 0, 4, 16, -15, 2, 1, 5, 14, -15, 14, 0, 27, 3, -14, 19, 0,
      27, 19, -14, 3, 1, 39, 0, 0, 20, 0, 39, 20, 0, 0, 1, 44, 13, -43, 14, 0}
      , {
      23, 7, -3, 18, 0, 23, 18, -3, 7, 1, 20, 6, -5, 29, 0, 20, 29, -5, 6, 0,
      17, 3, -11, 29, 0, 17, 29, -11, 3, 1, 34, 27, -19, 0, 0, 34, 0, -19, 27,
      1, 10, 5, -5, 18, 0, 10, 18, -5, 5, 1, 44, 16, 0, 17, 2, 40, 17, -33, 17,
      0}
      , {
      23, 16, -3, 5, 1, 20, 4, -5, 27, 0, 20, 27, -5, 4, 0, 17, 1, -11, 27, 0,
      17, 27, -11, 1, 1, 10, 16, -5, 3, 1, 44, 14, 0, 15, 2, 23, 5, -3, 16, 0,
      24, 6, -2, 20, 0, 24, 20, -2, 6, 1, 32, 0, -23, 20, 1, 32, 20, -23, 0, 0,
      10, 3, -5, 16, 0, 10, 3, -7, 16, 0, 10, 16, -7, 3, 1, 17, 0, -14, 28, 0,
      17, 28, -14, 0, 1, 40, 15, -35, 15, 0}
      , {
      20, 8, -4, 31, 0, 20, 31, -4, 8, 0, 17, 5, -10, 31, 0, 17, 31, -10, 5, 1,
      10, 7, -4, 20, 0, 10, 20, -4, 7, 1, 23, 8, -3, 21, 0, 23, 21, -3, 8, 1,
      44, 18, 0, 19, 3, 35, 31, -11, 0, 0, 35, 0, -11, 31, 1, 40, 19, -32, 19,
      0}
      , {
      12, 9, 0, 4, 0, 37, 1, 0, 17, 0, 29, 0, -18, 24, 0, 2, 4, -9, 1, 0, 42,
      28, -6, 0, 0}
      , {
      3, 9, 0, 4, 0, 36, 1, 0, 26, 0, 42, 33, -6, 0, 0, 2, 7, -9, 3, 0, 33, 0,
      -15, 26, 0}
      , {
      2, 0, -9, 1, 0, 9, 1, 0, 2, 0, 42, 24, -7, 0, 0, 31, 1, -22, 22, 0}
      , {
      0, 7, -2, 7, 0, 30, 28, 0, 0, 0, 30, 0, -3, 28, 1, 41, 20, -33, 17, 0}
      , {
      0, 7, -2, 7, 1, 30, 28, -3, 0, 0, 30, 0, 0, 28, 1, 41, 17, -33, 20, 1}
      , {
      17, 29, -11, 3, 1, 20, 29, -5, 6, 0, 17, 4, -11, 28, 0, 26, 27, -4, 6, 0,
      22, 15, 0, 6, 0, 34, 27, -20, 0, 0, 34, 0, -18, 27, 0, 1, 6, -4, 4, 0}
      , {
      26, 7, -4, 26, 0, 22, 7, 0, 14, 1, 20, 7, -5, 28, 0, 17, 3, -11, 29, 0,
      17, 29, -11, 3, 1, 34, 27, -18, 0, 0, 34, 0, -20, 27, 1, 1, 5, -4, 5, 1}
      , {
      43, 29, -5, 0, 2, 29, 3, -18, 22, 0, 6, 5, 0, 1, 0, 37, 0, 0, 19, 0}
      , {
      8, 7, 0, 2, 0, 37, 7, 0, 14, 0, 31, 6, -19, 24, 0, 20, 7, -18, 27, 0, 29,
      0, -19, 27, 0, 7, 6, -13, 2, 0, 43, 31, -10, 0, 0}

  };
  byte[][] indent = {
      {
      5, 5, 8, 1}
      , { // index:0
      4, 5, 7, 4}
      , { // index:1
      3, 6, 8, 3}
      , { // index:2
      5, 5, 6, 4}
      , { // index:3
      3, 6, 6, 4}
      , { // index:4
      5, 4, 6, 4}
      , { // index:5
      0, 4, 5, 3}
      , { // index:6
      0, 5, 6, 3}
      , { // index:7
      0, 5, 5, 4}
      , { // index:8
      8, 8, 8, 4}
      , { // index:9
      6, 8, 9, 0}
      , { // index:10
      3, 3, 8, 15}
      , { // index:11
      5, 7, 6, 4}
      , { // index:12
      4, 4, 9, 4}
      , { // index:13
      7, 9, 5, 4}
      , { // index:14
      5, 3, 7, 0}
      , { // index:15
      10, 3, 7, 0}
      , { // index:16
      4, 0, 7, 0}
      , // index:17
  };
  public void roleMove(int mx, int my) {
    GameMap map = engine.map;
    int dx = x + mx; // + (isMir ? 0 : w);
    int dy = y + my;
    if (my > 0) {
      if (!map.canMove(dx, dy)) {
        y = (dy / map.tileHight * map.tileHight);

      }
      else {
//        y += my;
        for (int i = 0; i < GameMap.decClip.length; i++) {
          boolean canRun = true;
          int jxl = map.decClip[i][4] / 24;
          int jyl = map.decClip[i][5] / 24;
          if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                 GameMap.sete[i] == 1)) {
            if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                GameMap.decClip[i][1] != Tools.IMG_SIG &&
                GameMap.decClip[i][1] != Tools.IMG_RAN
                && GameMap.decClip[i][2] <= x &&
                x < GameMap.decClip[i][2] + GameMap.decClip[i][4] &&
                GameMap.decClip[i][3] - GameMap.decClip[i][5] == y &&
                y <= 528) {
              if (jxl > jyl) {
                for (int j = 0; j < jxl; j++) {
                  if (map.dey[i] % 6 == 0) {
                    canRun = map.canMove(map.decClip[i][2] + j * 24,
                                         dy + jyl * 24);
                  }
                  if (!canRun) {
                    j = jxl + 1;
                  }
                }
              }
              if (jxl <= jyl) {
                if (map.dey[i] % 6 == 0) {
                  canRun = map.canMove(x, dy + jyl * 24);
                  if (jxl == jyl) {
                    if (x == 24 && map.decClip[i][3] + 24 == 192 ||
                        x == 96 && map.decClip[i][3] + 24 == 288 ||
                        x == 240 && map.decClip[i][3] + 24 == 408) {
                      canRun = true;
                    }
                  }
                }
              }
              for (int j = 0; j < map.decClip.length; j++) {
                int jxl1 = map.decClip[j][4] / 24;
                int jyl1 = map.decClip[j][5] / 24;
                if (map.decClip[j][1] == Tools.IMG_RAN) {
                  j++;
                }
                else {
                  if (map.dey[i] % 6 == 0) {
                    if (map.decClip[i][2] > map.decClip[j][2]) {
                      for (int m = 0; m < jxl1; m++) {
                        if (map.decClip[i][2] == map.decClip[j][2] + m * 24 &&
                            map.decClip[i][3] + 24 == map.decClip[j][3]) {
                          canRun = false;
                          m = jxl1 + 1;
                        }
                      }
                    }
                    else if (map.decClip[i][2] < map.decClip[j][2]) {
                      for (int m = 0; m < jxl; m++) {
                        if (map.decClip[i][2] == map.decClip[j][2] - m * 24 &&
                            map.decClip[i][3] + 24 == map.decClip[j][3]) {
                          canRun = false;
                          m = jxl1 + 1;
                        }
                      }
                    }
                    else if (map.decClip[i][2] == map.decClip[j][2]) {
                      if (map.decClip[i][3] + jyl1 * 24 == map.decClip[j][3]) {
                        canRun = false;
                      }
                    }
                  }
                }
              }

              if (canRun) {
                GameMap.dey[i]++;
              }
              else {
                y -= my;
              }
            }

          }
        }

        y += my;
      }
    } //if down
    if (my < 0) {
      if (!map.canMove(dx, dy - 24)) {
        y = ( (dy + map.tileHight) / map.tileHight * map.tileHight);

      }
      else {
//        y += my;
        for (int i = 0; i < GameMap.decClip.length; i++) {
          boolean canRun = true;
          int jxl = map.decClip[i][4] / 24;
          int jyl = map.decClip[i][5] / 24;

          if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                 GameMap.sete[i] == 1)) {
            if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                GameMap.decClip[i][1] != Tools.IMG_SIG &&
                GameMap.decClip[i][1] != Tools.IMG_RAN
                && GameMap.decClip[i][2] <= x &&
                x < GameMap.decClip[i][2] + GameMap.decClip[i][4] &&
                GameMap.decClip[i][3] == y - 24 &&
                y >= 48) {
              if (jxl > jyl) {
                for (int j = 0; j < jxl; j++) {
                  if (map.dey[i] % 6 == 0) {
                    canRun = map.canMove(map.decClip[i][2] + j * 24,
                                         map.decClip[i][3] - 48);
                  }
                  if (!canRun) {
                    j = jxl + 1;
                  }
                }
              }
              if (jxl <= jyl) {
                if (map.dey[i] % 6 == 0) {
                  canRun = map.canMove(x, map.decClip[i][3] - jyl * 24 - 4);
                  if (jxl == jyl) {
                    if (x == 24 && map.decClip[i][3] - 24 == 192 ||
                        x == 96 && map.decClip[i][3] - 24 == 288 ||
                        x == 240 && map.decClip[i][3] - 24 == 408) {
                      canRun = true;
                    }
                  }
                }
              }
              for (int j = 0; j < map.decClip.length; j++) {
                int jxl1 = map.decClip[j][4] / 24;
                int jyl1 = map.decClip[j][5] / 24;
                if (map.decClip[j][1] == Tools.IMG_RAN) {
                  j++;
                }
                else {
                  if (map.dey[i] % 6 == 0) {
                    if (map.decClip[i][2] > map.decClip[j][2]) {
                      for (int m = 0; m < jxl1; m++) {
                        if (map.decClip[i][2] == map.decClip[j][2] + m * 24 &&
                            map.decClip[i][3] - jyl * 24 == map.decClip[j][3]) {
                          canRun = false;
                          m = jxl1 + 1;
                        }
                      }
                    }
                    else if (map.decClip[i][2] < map.decClip[j][2]) {
                      for (int m = 0; m < jxl; m++) {
                        if (map.decClip[i][2] == map.decClip[j][2] - m * 24 &&
                            map.decClip[i][3] - jyl * 24 == map.decClip[j][3]) {
                          canRun = false;
                          m = jxl1 + 1;
                        }
                      }
                    }
                    else if (map.decClip[i][2] == map.decClip[j][2]) {
                      if (map.decClip[i][3] - jyl * 24 == map.decClip[j][3]) {
                        canRun = false;
                      }
                    }
                  }
                }
              }

              if (canRun) {
                GameMap.dey[i]--;
              }
              else {
                y -= my;
              }

            }
          }
        }
        y += my;
      }
    } //if up
    if (mx < 0) {
      if (!map.canMove(dx, dy - h / 2)) {
        x = ( (dx + map.tileHight) / map.tileWidth * map.tileWidth);
      }
      else {
//        x += mx;
        for (int i = 0; i < GameMap.decClip.length; i++) {
          boolean canRun = true;
          int jxl = map.decClip[i][4] / 24;
          int jyl = map.decClip[i][5] / 24;

          if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                 GameMap.sete[i] == 1)) {
            if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                GameMap.decClip[i][1] != Tools.IMG_SIG &&
                GameMap.decClip[i][1] != Tools.IMG_RAN
                && GameMap.decClip[i][2] + GameMap.decClip[i][4] == x &&
                GameMap.decClip[i][3] - GameMap.decClip[i][5] < y &&
                y <= GameMap.decClip[i][3] &&
                x >= 48) {
              System.out.println("---------x = " + x);
              if (jxl < jyl) {
                for (int j = 0; j < jyl; j++) {
                  if (map.dex[i] % 6 == 0) {
                    canRun = map.canMove(map.decClip[i][2] - 24,
                                         map.decClip[i][3] - j * 24 - 4);
                  }
                  if (!canRun) {
                    j = jyl + 1;
                  }
                }
              }
              if (jxl >= jyl) {
                System.out.println("x = " + x + "-------");
                if (map.dex[i] % 6 == 0) {
                  canRun = map.canMove(map.decClip[i][2] - 24,
                                       map.decClip[i][3] - 4);
                  if (jxl == jyl) {
                    if (map.decClip[i][2] - 24 == 24 &&
                        map.decClip[i][3] == 192 ||
                        map.decClip[i][2] - 24 == 96 &&
                        map.decClip[i][3] == 288 ||
                        map.decClip[i][2] - 24 == 240 &&
                        map.decClip[i][3] == 408) {
                      canRun = true;
                    }
                  }
                }
              }

              for (int j = 0; j < map.decClip.length; j++) {
                int jxl1 = map.decClip[j][4] / 24;
                int jyl1 = map.decClip[j][5] / 24;
                if (map.decClip[j][1] == Tools.IMG_RAN) {
                  j++;
                }
                else {
                  if (map.dex[i] % 6 == 0) {
                    if (map.decClip[i][3] > map.decClip[j][3]) {
                      for (int m = 0; m < jyl; m++) {
                        if (map.decClip[i][2] - 24 == map.decClip[j][2] &&
                            map.decClip[i][3] - m * 24 == map.decClip[j][3]) {
                          canRun = false;
                          m = jxl1 + 1;

                        }
                      }
                    }
                    else if (map.decClip[i][3] < map.decClip[j][3]) {
                      for (int m = 0; m < jyl1; m++) {
                        if (map.decClip[i][2] - 24 == map.decClip[j][2] &&
                            map.decClip[i][3] == map.decClip[j][3] - m * 24) {
                          canRun = false;
                          m = jxl1 + 1;
                        }
                      }
                    }
                    else if (map.decClip[i][3] == map.decClip[j][3]) {
                      if (map.decClip[i][2] - 24 * jxl1 == map.decClip[j][2]) {
                        canRun = false;
                      }
                    }
                  }
                }
              }

              if (canRun) {
                GameMap.dex[i]--;
              }
              else {
                x -= mx;
              }

            }
          }
        }
        x += mx;
      }
    } // if left
    if (mx > 0) {
      if (!map.canMove(dx + map.tileHight, dy - h / 2)) {
        x = (dx / map.tileWidth * map.tileWidth);

      }
      else {
//        x += mx;
        for (int i = 0; i < GameMap.decClip.length; i++) {
          boolean canRun = true;
          int jxl = map.decClip[i][4] / 24;
          int jyl = map.decClip[i][5] / 24;

          if (! (GameMap.decClip[i][1] == Tools.IMG_IRON &&
                 GameMap.sete[i] == 1)) {
            if (GameMap.decClip[i][1] != Tools.IMG_OPENC &&
                GameMap.decClip[i][1] != Tools.IMG_SIG &&
                GameMap.decClip[i][1] != Tools.IMG_RAN
                && GameMap.decClip[i][2] == x + 24 &&
                GameMap.decClip[i][3] - GameMap.decClip[i][5] < y &&
                y <= GameMap.decClip[i][3] &&
                x <= 240) {
              if (jxl < jyl) {
                for (int j = 0; j < jyl; j++) {
                  if (map.dex[i] % 6 == 0) {
                    canRun = map.canMove(map.decClip[i][2] + 24,
                                         map.decClip[i][3] - j * 24 - 4);
                  }
                  if (!canRun) {
                    j = jxl + 1;
                  }
                }
              }
              if (jxl >= jyl) {
                if (map.dex[i] % 6 == 0) {
                  canRun = map.canMove(map.decClip[i][2] + jxl * 24,
                                       map.decClip[i][3] - 4);
                  if (jxl == jyl) {
                    if (map.decClip[i][2] + 24 == 24 &&
                        map.decClip[i][3] == 192 ||
                        map.decClip[i][2] + 24 == 96 &&
                        map.decClip[i][3] == 288 ||
                        map.decClip[i][2] + 24 == 240 &&
                        map.decClip[i][3] == 408) {
                      canRun = true;
                    }
                  }
                }
              }
              for (int j = 0; j < map.decClip.length; j++) {
                int jxl1 = map.decClip[j][4] / 24;
                int jyl1 = map.decClip[j][5] / 24;
                if (map.decClip[j][1] == Tools.IMG_RAN) {
                  j++;
                }
                else {
                  if (map.dex[i] % 6 == 0) {
                    if (map.decClip[i][3] > map.decClip[j][3]) {
                      for (int m = 0; m < jyl; m++) {
                        if (map.decClip[i][2] + jxl * 24 == map.decClip[j][2] &&
                            map.decClip[i][3] - m * 24 == map.decClip[j][3]) {
                          canRun = false;
                          m = jxl1 + 1;

                        }
                      }
                    }
                    else if (map.decClip[i][3] < map.decClip[j][3]) {
                      for (int m = 0; m < jyl1; m++) {
                        if (map.decClip[i][2] + jxl * 24 == map.decClip[j][2] &&
                            map.decClip[i][3] == map.decClip[j][3] - m * 24) {
                          canRun = false;
                          m = jxl1 + 1;
                        }
                      }
                    }
                    else if (map.decClip[i][3] == map.decClip[j][3]) {
                      if (map.decClip[i][2] + jxl * 24 == map.decClip[j][2]) {
                        canRun = false;
                      }
                    }
                  }
                }
              }

              if (canRun) {
                GameMap.dex[i]++;
              }
              else {
                x -= mx;
              }

            }
          }
        }
        x += mx;
      }
    } //if right
    for (int i = 0; i < GameMap.decClip.length; i++) {
      if (GameMap.decClip[i][1] == Tools.IMG_IRON &&
          (GameMap.decClip[i][2] == map.stax[0] && GameMap.decClip[i][3] == 192 ||
           GameMap.decClip[i][2] == map.stax[1] && GameMap.decClip[i][3] == 288 ||
           GameMap.decClip[i][2] == map.stax[2] && GameMap.decClip[i][3] == 408)
          ) {
        GameMap.sete[i] = 1;
        if (GameMap.decClip[i][2] == 24 && GameMap.decClip[i][3] == 192) {
          GameMap.sete[9] = 1;
          map.stax[0] = -100;
        }
        if (GameMap.decClip[i][2] == 96 && GameMap.decClip[i][3] == 288) {
          GameMap.sete[10] = 1;
          map.stax[1] = -100;
        }
        if (GameMap.decClip[i][2] == 240 && GameMap.decClip[i][3] == 408) {
          GameMap.sete[11] = 1;
          map.stax[2] = -100;
        }
      }

    }

    if (dx == 24 && dy == 456) {
      for (int i = 0; i < map.dex.length; i++) {
        map.dex[i] = 0;
      }
      for (int i = 0; i < map.dey.length; i++) {
        map.dey[i] = 0;
      }
      for (int i = 0; i < map.sete.length; i++) {
        map.sete[i] = 0;
      }
      map.stax[0] = 24;
      map.stax[1] = 96;
      map.stax[2] = 240;
      map.state1 = 0;
      map.statei = 0;
      map.state2 = 1;
      map.drawdeClip();
    }
    if (dx != 24 || dy != 456) {
      map.state2 = 0;
    }
  } //roleMove

  int timeG = 0;
  int num = 0;

  public void paint() {

    if (dir != 5) {

      if (dir != DIR_RIGHT) {
        Tools.drawFrame(Tools.IMG_ROLE, roleFrame, roleClip,
//                    x - (isMir ? indent[curIndex][1] :indent[curIndex][0]),
                        x - indent[curIndex][0],
                        y + indent[curIndex][3], curIndex, isMir,
                        30);
      }

      else {
        Tools.drawFrame(Tools.IMG_ROLE, roleFrame, roleClip,
//                    x - (isMir indent? [curIndex][1] : indent[curIndex][0]),
                        x - indent[curIndex][1],
                        y + indent[curIndex][3], curIndex, !isMir,
                        30);

      }

    }
    if (dir == 5) {
      timeG++;
      num = timeG % 3;

      curIndex = motion[num];
      if (lastDir != DIR_RIGHT) {
        Tools.drawFrame(Tools.IMG_ROLE, roleFrame, roleClip,
//                    x - (isMir ? indent[curIndex][1] :indent[curIndex][0]),
                        x - indent[curIndex][0],
                        y + indent[curIndex][3], curIndex, isMir,
                        30);
      }

      else {
        Tools.drawFrame(Tools.IMG_ROLE, roleFrame, roleClip,
//                    x - (isMir ? indent[curIndex][1] : indent[curIndex][0]),
                        x - indent[curIndex][1],
                        y + indent[curIndex][3], curIndex, !isMir,
                        30);

      }
      if (num == 2) {
        attack();
        if (dir != lastDir) {
          dir = lastDir;
        }
        if (dir == lastDir) {
          setStatus(STATUS_STOP);
        }
      }

    }

  }

  //人物控制
  public void ctrl(int KeyCode) {
    if (y % 24 != 0 || x % 24 != 0) {
      return;
    }

//Tools.addARC();
//    switch (curStatus) {
//      case STATUS_STOP:
//        break;
//      case STATUS_MOVE:
//        switch (KeyCode) {
//          case MyGameCanvas.KEY_UP:
//          case MyGameCanvas.KEY_2:
//            dir = DIR_UP;
//            setStatus(STATUS_MOVE);
//            break;
//          case MyGameCanvas.KEY_DOWN:
//          case MyGameCanvas.KEY_8:
//            dir = DIR_DOWN;
//            setStatus(STATUS_MOVE);
//            break;
//          case MyGameCanvas.KEY_LEFT:
//          case MyGameCanvas.KEY_4:
//            dir = DIR_LEFT;
//            setStatus(STATUS_MOVE);
//            break;
//          case MyGameCanvas.KEY_RIGHT:
//          case MyGameCanvas.KEY_6:
//            dir = DIR_RIGHT;
//            setStatus(STATUS_MOVE);
//            break;
//          default:
//            break;
//        }
//        break;
//      default:
//        break;
//    }

    switch (KeyCode) {
      case MyGameCanvas.KEY_UP:
      case MyGameCanvas.KEY_2:
        dir = DIR_UP; //0
        setStatus(STATUS_MOVE);
        break;
      case MyGameCanvas.KEY_DOWN:
      case MyGameCanvas.KEY_8:
        dir = DIR_DOWN; //2
        setStatus(STATUS_MOVE);
        break;
      case MyGameCanvas.KEY_LEFT:
      case MyGameCanvas.KEY_4:
        dir = DIR_LEFT; //3
        setStatus(STATUS_MOVE);
        break;
      case MyGameCanvas.KEY_RIGHT:
      case MyGameCanvas.KEY_6:
        dir = DIR_RIGHT; //1
        setStatus(STATUS_MOVE);
        break;
      case MyGameCanvas.KEY_OK:
      case MyGameCanvas.KEY_5:
        if (dir != 5) {
          lastDir = dir;
        }
        dir = 5;
        setStatus(STATUS_MOVE);
        break;

      default:
        break;

    }
  }

  public void ctrlReleased(int KeyCode) {
    if (curStatus == STATUS_MOVE || nextStatus == STATUS_MOVE) {
      setStatus(STATUS_STOP);
    }

  }

  public void setStatus(byte status) {
    timeG = 0;
    index = 0;
    lastStatus = curStatus;
    curStatus = status;
  }
}