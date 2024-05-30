//×××××××××××××画切片地图××××××××××××××

import javax.microedition.lcdui.*;
import java.io.*;

public final class GameMap {
  public int trapData[][];
  static int SCREEN_WIDTH; //屏幕宽度
  static int SCREEN_HEIGHT; //屏幕高度
  static byte tileWidth; //切片的宽度
  static byte tileHight; //切片的高度
  int[] mapSize = new int[2]; //地图大小 :[gameRank][0] ->地图宽的切片数 [gameRank][1] ->地图高的切片数
  short[] tileProperties;
  String[] name;
  static int setOffX; //屏幕坐标
  static int setOffY;

  static short index;
  static short state = 0;
  static short state1 = 0;
  static short state2 = 0;
  static short statei = 0;

//  short roleY;
//  short roleX;
  byte[] mapData; //地图数据
  byte gameRank; //当前关数

  public void free() {
    mapData = null;
    System.gc();
  }

  public GameMap(int sw, int sh) {
    SCREEN_WIDTH = sw;
    SCREEN_HEIGHT = sh;
    setOff(0, 0);
  }

  public static void setOff(int x, int y) {
    setOffX = x;
    setOffY = y;
  }

  //初始化地图数据和图片
  public void init(int GameRank) {
    this.gameRank = (byte) GameRank;
    free();
    switch (gameRank) {
      case 0:
        loadMap("map_0.dat");
        Tools.createImage(name, 1);
        break;
    }
  }

  //画切片
  public void setTile() {
    //计算起点
    int n = ( (setOffX / tileWidth) *
             mapSize[1] +
             (setOffY / tileHight));
    //画的数量
    int nw = (SCREEN_WIDTH / tileWidth) + 2;
    int nh = (SCREEN_HEIGHT / tileHight) + 2;
    for (int j = 0; j < nw; j++) {
      int s = n + j * mapSize[1];
      for (int i = s; i < s + nh; i++) {
        if (i >= mapData.length || i < 0 || mapData[i] == (byte) 0xff) {
          continue;
        }
        int dataTemp = getIndex(mapData[i]);

        int x = (i / mapSize[1]) * tileHight;
        int y = (i % mapSize[1]) * tileWidth;

        Tools.addImage(Tools.GRUOP_MAX + dataTemp,
                       x, y, 0, 0,
                       tileWidth, tileHight,
                       Graphics.TOP | Graphics.LEFT,
                       (byte) (getTrans(mapData[i])),
                       Tools.getProperties(tileProperties[dataTemp],
                                           Tools.TILE_HIT) ? 30 : 20
                       );
      }
    }
  }

  static int dex[] = {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  static int dey[] = {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  static int sete[] = {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

  static int statx[] = {
      120, 192, 72, 24, 168, 120, 144, 216, 24, 24, 96, 240, 120, 216, 48, 96,
      216, 192};
  static int staty[] = {
      72, 72, 264, 312, 96, 456, 144, 456, 456, 192, 288, 408, 120, 120, 360,
      360, 312, 480};
  static int stax[]={24,96,240};

  static int decClip[][] = {
      {
      0, Tools.IMG_IRONSHORT, 120, 72, 48, 24, Tools.TRANS_NONE, 30, state,
      index}
      , {
      1, Tools.IMG_IRONSHORT, 192, 72, 48, 24, Tools.TRANS_NONE, 30, state,
      index}
      , {
      2, Tools.IMG_IRONSHORT, 72, 264, 48, 24, Tools.TRANS_NONE, 30, state,
      index}
      , {
      3, Tools.IMG_IRONSHORT, 24, 312, 48, 24, Tools.TRANS_NONE, 30, state,
      index}
      , {
      4, Tools.IMG_IRONLONG1, 168, 96, 24, 72, Tools.TRANS_NONE, 30, state,
      index}
      , {
      5, Tools.IMG_IRONLONG1, 120, 456, 24, 72, Tools.TRANS_NONE, 30, state,
      index}
      , {
      6, Tools.IMG_IRONLONG, 144 + dex[6] * 24, 144 + dey[6] * 24, 72, 24,
      Tools.TRANS_NONE, 30, state,
      index}
      , {
      7, Tools.IMG_SIG, 216, 456, 72, 24, Tools.TRANS_NONE, 30, state1, index}
      , {
      8, Tools.IMG_OPENC, 24, 456, 48, 24, Tools.TRANS_NONE, 30, state2, index}
      , {
      9, Tools.IMG_RAN, 24, 192, 24, 24, Tools.TRANS_NONE, 30, sete[9], index}
      , {
      10, Tools.IMG_RAN, 96, 288, 24, 24, Tools.TRANS_NONE, 30, sete[10], index}
      , {
      11, Tools.IMG_RAN, 240, 408, 24, 24, Tools.TRANS_NONE, 30, sete[11],
      index}
      , {
      12, Tools.IMG_IRON, 120, 120, 24, 24, Tools.TRANS_NONE, 30, sete[12],
      index}
      , {
      13, Tools.IMG_IRON, 216, 120, 24, 24, Tools.TRANS_NONE, 30, sete[13],
      index}
      , {
      14, Tools.IMG_IRON, 48, 360, 24, 24, Tools.TRANS_NONE, 30, sete[14],
      index}
      , {
      15, Tools.IMG_IRON, 96, 360, 24, 24, Tools.TRANS_NONE, 30, sete[15],
      index}
      , {
      16, Tools.IMG_IRON, 216, 312, 24, 24, Tools.TRANS_NONE, 30, sete[16],
      index}
      , {
      17, Tools.IMG_IRON, 192, 480, 24, 24, Tools.TRANS_NONE, 30, sete[17],
      index}

  };
  static byte[][] mapClip = {
      {
      0, 0, 24, 24}
      , {
      24, 0, 24, 24}
      , {
      48, 0, 24, 24}
      , {
      0, 0, 0, 0}
  };

  //画地图
  public static void drawdeClip() {

    for (int i = 0; i < decClip.length; i++) {
      decClip[i][2] = statx[i] + dex[i] *4;
      decClip[i][3] = staty[i] + dey[i] *4;

      if (decClip[i][1] != Tools.IMG_IRON && decClip[i][1] != Tools.IMG_OPENC &&
          decClip[i][1] != Tools.IMG_SIG) {
        Tools.addImage(decClip[i][1], decClip[i][2], decClip[i][3],
                       Tools.BOTTOM,
                       (byte) decClip[i][6], decClip[i][7]);
      }
      else if (decClip[i][1] == Tools.IMG_SIG) {
        Tools.addImage(decClip[i][1], decClip[i][2], decClip[i][3],
                       mapClip[state1], Tools.BOTTOM,
                       (byte) decClip[i][6], decClip[i][7]);
      }
      else if (decClip[i][1] == Tools.IMG_OPENC) {
        Tools.addImage(decClip[i][1], decClip[i][2], decClip[i][3],
                       mapClip[state2], Tools.BOTTOM,
                       (byte) decClip[i][6], decClip[i][7]);
      }

      else {
        Tools.addImage(decClip[i][1], decClip[i][2], decClip[i][3],
                       mapClip[sete[i]], Tools.BOTTOM,
                       (byte) decClip[i][6], decClip[i][7]);
      }

    }

  }

  public void setMap(Graphics g) {

    if (mapData != null) {
      setTile();
      drawdeClip();
    }
  }

  public static final byte SCEEN_MOVE = 4;
  int posX;
  int posY;
  public void AdjustSrceen(GameRole role, int x, int y) {
    posX = 80;
    posY = 100;
//    if (role.curStatus == GameInterface.STATUS_SEE) {
//      setOffY = (short) Math.min(setOffY,
//                                 mapSize[1] * tileHight -
//                                 SCREEN_HEIGHT);
//      return;
//    }

    if (Math.abs(x - setOffX - posX) > SCEEN_MOVE) {
      if (x - setOffX - posX >= SCEEN_MOVE) {
        setOffX += SCEEN_MOVE;
      }
      else {
        setOffX -= SCEEN_MOVE;
      }
    }
    else {
      setOffX = (short) (x - posX);
    }
    setOffX = (short) Math.max(setOffX, 0);
    setOffX = (short) Math.min(setOffX, mapSize[0] * tileWidth -
                               SCREEN_WIDTH);

    if (Math.abs(y - setOffY - posY) > SCEEN_MOVE) {
      if (y - setOffY - posY >= SCEEN_MOVE) {
        setOffY += SCEEN_MOVE;
      }
      else {
        setOffY -= SCEEN_MOVE;
      }
    }
    else {
      setOffY = (short) (y - posY);
    }

    setOffY = (short) Math.max(setOffY, 0);
    setOffY = (short) Math.min(setOffY,
                               mapSize[1] * tileHight -
                               SCREEN_HEIGHT);

  }

  public boolean canMove(int x, int y) {
    if (inMapData(x, y) == (byte) 0xff ||
        !Tools.getProperties(tileProperties[getIndex(inMapData(x, y))],
                             Tools.TILE_EMPTY)) {
      return false;
    }
    for (int i = 0; i < decClip.length; i++) {
      if (decClip[i][1] == Tools.IMG_OPENC) {
        i++;
      }
      if (decClip[i][1] == Tools.IMG_SIG) {
        if (state1 == 3) {
          decClip[i][2] = -100;
        }
        else if (x / 24 * 24 == decClip[i][2] &&
                 y / 24 * 24 + 24 == decClip[i][3]) {
          return false;
        }

      }
      if (decClip[i][1] == Tools.IMG_RAN && sete[i] != 1) {
        if (x / 24 * 24 == decClip[i][2] && y / 24 * 24 + 24 == decClip[i][3]) {
          return false;
        }
      }

      if (decClip[i][1] == Tools.IMG_SIG && state1 != 3) {
        decClip[i][2] = 216;
      }
      if (sete[i] == 1) {

        if (i == 9 || i == 10 || i == 11) {
          dex[i] = -50;
        }
        else {
          i++;
        }
      }
      int jxl = decClip[i][4] / 24;
      int jyl = decClip[i][5] / 24;
//      for (int j = 0; j < decClip.length; j++) {
//        if (j == i || decClip[j][1] == Tools.IMG_RAN ||
//            decClip[i][1] == Tools.IMG_RAN) {
//          j++;
//        }
//        else {
//          if (jxl > jyl) {
//            for (int m = 0; m < jxl; m++) {
//              if (decClip[j][3] == decClip[i][3] &&
//                  decClip[j][2] + m * 24 == decClip[i][2]) {
//                return false;
//              }
//            }
//          }
//          else if (jxl < jyl) {
//            for (int m = 0; m < jyl; m++) {
//              if (decClip[j][2] == decClip[i][2] &&
//                  decClip[j][3] + m * 24 == decClip[i][3]) {
//                return false;
//              }
//            }
//          }
//          else if (jxl == jyl) {
//            if (decClip[j][3] == decClip[i][3] &&
//                decClip[j][2] == decClip[i][2]) {
//              return false;
//
//            }
//          }
//
//        }
//      }
    }//for



    return true;
  }

//******************
//计算传入的x,y在地图数据中的位置
//******************
    public byte inMapData(int x, int y) {
      int tx = x / tileWidth;
      int ty = y / tileHight;
      if (tx < 0 || tx >= mapSize[0] || ty <= 0 || ty >= mapSize[1]) {
        return (byte) 0xff;
      }
      int n = ( (tx) * mapSize[1] +
               (ty));
      return mapData[n];

    }

  public void setTrans(int x, int y, int trans) {
    int v = getIndex(x, y) + (trans << 6);
    setTileValue(x, y, v);
  }

  public void setTileValue(int x, int y, int value) {
    int tx = x / tileWidth;
    int ty = y / tileHight;
    if (tx < 0 || tx >= mapSize[0] || ty <= 0 || ty >= mapSize[1]) {
      return;
    }
    int n = (tx * mapSize[1] + (ty));
    mapData[n] = (byte) value;
  }

  public void setIndex(int x, int y, int index) {
    int v = (getTrans(x, y) << 6) + index;
    setTileValue(x, y, v);
  }

//fall
  public static int getTrans(int v) {
    return ( (v >> 6) & 0x03);
  }

  public int getTrans(int x, int y) {
    return getTrans(inMapData(x, y));
  }

  public static int getIndex(int v) {
    return v & 0x3f;
  }

  public int getIndex(int x, int y) {
    return getIndex(inMapData(x, y));
  }

  static int bToi(byte byte0) {
    int i = byte0;
    if (byte0 < 0) {
      i += 256;
    }
    return i;
  }

  public static void drawFullScreenBG(int imgIndex, int height, int speed,
                                      int level) {

    Tools.addRect(Tools.TYPE_RECT, 100, 100, 200, 200, true, Tools.TOP, 0, 0);

  }

  public void loadMap(String file) {
    mapData = null;
    tileProperties = null;
    DataInputStream in = new DataInputStream(getClass().getResourceAsStream(
        file));
    try {
      //******************
       //读取地图数据
       //******************

        mapSize[0] = bToi(in.readByte());
      mapSize[1] = bToi(in.readByte());
      tileWidth = in.readByte();
      tileHight = in.readByte();
      int size = (int) (mapSize[0] *
                        mapSize[1]);
      mapData = new byte[size];
      in.read(mapData);
      //******************
       //读取tile属性
       //******************

      int len = in.readShort();
      name = new String[len];
      tileProperties = new short[len];
      for (int i = 0; i < len; i++) {
        int nameLen = in.readByte();
        byte[] names = new byte[nameLen];
        in.read(names);
        name[i] = new String(names);
        tileProperties[i] = in.readShort();
      }

    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
