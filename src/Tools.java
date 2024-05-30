//Nokia 7610 Tools.java
//2005.12

import javax.microedition.lcdui.*;
import java.util.*;
import java.io.*;

//import javax.microedition.lcdui.game.Sprite;
//import com.nokia.mid.ui.DirectGraphics;
//import com.nokia.mid.ui.DirectUtils;

//import javax.microedition.lcdui.game.Sprite;

public final class Tools {
//  public static Random rnd = new Random();

  public static final int GRUOP_MAX = 300;
  public final static String[] FILESNAME = {
      "cp", "kj_bj", "kj_c1", "kj_c2", "kj_f", "kj_r", "kj_t", "kj_w", "logo",
      "monternet", "sp", "role","ironshort","ironlong","iron","ironlong1","sig"
      ,"openc","ran"};

  public final static byte IMG_CP = 0;
  public final static byte IMG_KJ_BJ = 1;
  public final static byte IMG_KJ_C1 = 2;
  public final static byte IMG_KJ_C2 = 3;
  public final static byte IMG_KJ_F = 4;
  public final static byte IMG_KJ_R = 5;
  public final static byte IMG_KJ_T = 6;
  public final static byte IMG_KJ_W = 7;
  public final static byte IMG_LOGO = 8;
  public final static byte IMG_MONTERNET = 9;
  public final static byte IMG_SP = 10;
  public final static byte IMG_ROLE = 11;
  public final static byte IMG_IRONSHORT = 12;
  public final static byte IMG_IRONLONG = 13;
  public final static byte IMG_IRON = 14;
  public final static byte IMG_IRONLONG1 = 15;
  public final static byte IMG_SIG = 16;
  public final static byte IMG_OPENC = 17;
   public final static byte IMG_RAN = 18;
  public final static int TOP = Graphics.TOP | Graphics.LEFT;
  public final static int BOTTOM = Graphics.BOTTOM | Graphics.LEFT;
  public final static int CENTER = Graphics.BOTTOM | Graphics.HCENTER;

  private static Image[] imgs = new Image[FILESNAME.length];
  private static Image[] buff = new Image[FILESNAME.length];
  private static Image[][] imgGroup = new Image[3][];

  public static final byte TRANS_NONE = 0;
  public static final byte TRANS_HORIZONTAL = 1;
  public static final byte TRANS_VERTICAL = 2;
  public static final byte TRANS_CENTER = 3;
  public static void drawFrame(short imgIndex, byte frameData[][],
                               byte imgData[][],
                               int x, int y, int index,
                               boolean isLeft, int drawLevel) {
    byte[] data = frameData[index];
    for (int i = 0; i < data.length; i += 5) {
      Tools.addImage(imgIndex, x + data[i + (isLeft ? 3 : 1)],
                     y + data[i + 2], imgData[data[i]],
                     Graphics.BOTTOM | Graphics.LEFT,
                     (byte) (isLeft ? (data[i + 4] ^ 1) : (data[i + 4])),
                     drawLevel);
    }
  }

  public static void drawFrame(short imgIndex, short frameData[][],
                               byte imgData[][],
                               int x, int y, int index,
                               boolean isLeft, int drawLevel) {
    short[] data = frameData[index];
    for (int i = 0; i < data.length; i += 5) {
      Tools.addImage(imgIndex, x + data[i + (isLeft ? 3 : 1)],
                     y + data[i + 2], imgData[data[i]],
                     Graphics.BOTTOM | Graphics.LEFT,
                     (byte) (isLeft ? (data[i + 4] ^ 1) : (data[i + 4])),
                     drawLevel);
    }
  }

//  public static boolean percent(int per) {
//    if (GameEngine.nextInt(100) <= per) {
//      return true;
//    }
//    return false;
//  }

  public static Image getImageFromGroup(int index, int trans) {
    int group = index / GRUOP_MAX;
    int i = index % GRUOP_MAX;
    return imgGroup[group][i];
  }

//  public static Image[] createImage(String[] indexs, int startIndex) {
//    imgGroup[startIndex] = null;
//    imgGroup[startIndex] = new Image[indexs.length][1];
//    for (int i = 0; i < indexs.length; i++) {
//      try {
//        imgGroup[startIndex][i][0] = Image.createImage("/images/map/" +
//            indexs[i] + ".png");
//      }
//      catch (IOException ex) {
//        ex.printStackTrace();
//      }
//    }
//    return null;
//  }

  public static Image createBuff(int w, int h) {
    return Image.createImage(w, h);
  }

  /*绘制剪切区域的图片
   *img被剪切的图片
   *int x,inty 要显示的x y坐标
   *int clipX,int clipY被剪切的小图片的x y在源图片中的坐标
   *int clipW,int clipH被剪切的小图片的宽和高
   */
//创建图片的函数
  public static Image createImage(String imgName) {
    try {
      return Image.createImage("/images/" + imgName + ".png");
    }
    catch (Exception e) {
      System.out.println("imgName:" + imgName);
      e.printStackTrace();
      return null;
    }
  }

  public static void removeImage(int index) {
//    index = index >= 0 ? index : 127 - index;
    imgs[index] = null;
//    imgs[index] = new Image[1];
  }

  public static void removeAllSinleImage(int[] noRemoveImage) {
    for (int i = 0; i < imgs.length; i++) {
      boolean isRemove = true;
      for (int j = 0; j < noRemoveImage.length; j++) {
        if (noRemoveImage[j] == i) {
          isRemove = false;
          break;
        }
      }
      if (isRemove) {
        removeImage(i);
      }
    }
  }

  public static void removeAllGroupImage(int[] noRemoveGroup) {
    for (int i = 0; i < imgGroup.length; i++) {
      boolean isRemove = true;
      for (int j = 0; j < noRemoveGroup.length; j++) {
        if (noRemoveGroup[j] == i) {
          isRemove = false;
          break;
        }
      }
      if (isRemove) {
        imgGroup[i] = null;
      }
    }
  }

//  public static void removeAllImage() {
//    imgs = null;
//    buff = null;
//    imgGroup = null;
//    imgs = new Image[FILESNAME.length][4];
//    buff = new Image[FILESNAME.length];
//    imgGroup = new Image[5][][];
//    System.gc();
//    //remove single image
//    for (int i = 0; i < imgs.length; i++) {
//      removeImage(i);
//    }
//  }

//画string
  public static void addString(byte type, String str, int x, int y, int anchor,
                               int color, int drawLevel) {
    Tools.str[Tools.curIndex] = str;
    Tools.type[Tools.curIndex] = (byte) type;
    Tools.x[Tools.curIndex] = (short) x;
    Tools.y[Tools.curIndex] = (short) y;
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.color[Tools.curIndex] = (int) color;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    setIndex();
  }

//画圆角矩形和圆
  public static void addARC(byte type, int x, int y, int w, int h, int rw,
                            int rh,
                            boolean isFill, int anchor, int color,
                            int drawLevel) {
    if (isDraw(x, y, w, h, anchor)) {
      Tools.type[Tools.curIndex] = (byte) type;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.w[Tools.curIndex] = (short) w;
      Tools.h[Tools.curIndex] = (short) h;
      Tools.rw[Tools.curIndex] = (short) rw;
      Tools.rh[Tools.curIndex] = (short) rh;
      Tools.isFill[Tools.curIndex] = isFill;
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.color[Tools.curIndex] = (int) color;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      setIndex();
    }
  }

//画直线
  public static void addLine(byte type, int x, int y, int w, int h,
                             int anchor,
                             int color, int drawLevel) {
    if (isDraw(x, y, w, h, anchor)) {
      Tools.type[Tools.curIndex] = (byte) type;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.w[Tools.curIndex] = (short) w;
      Tools.h[Tools.curIndex] = (short) h;
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.color[Tools.curIndex] = (int) color;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      setIndex();
    }
  }

//画矩形
  public static void addRect(byte type, int x, int y, int w, int h,
                             boolean isFill,
                             int anchor, int color, int drawLevel) {
    //0x0   0xffffff
    if (isDraw(x, y, w, h, anchor)) {
      Tools.type[Tools.curIndex] = (byte) type;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.w[Tools.curIndex] = (short) w;
      Tools.h[Tools.curIndex] = (short) h;
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.color[Tools.curIndex] = (int) color;
      Tools.isFill[Tools.curIndex] = isFill;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      setIndex();
    }
  }

  public static void addImage(int imgIndex, int x, int y,
                              int anchor, byte trans, int drawLevel) {
    if (isDraw(x, y, Tools.getImage(imgIndex).getWidth(),
               Tools.getImage(imgIndex).getHeight(), anchor)) {
      Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.clipX[Tools.curIndex] = (short) 0;
      Tools.clipY[Tools.curIndex] = (short) 0;
      Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex).getWidth();
      Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex).getHeight();

      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.trans[Tools.curIndex] = trans;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
      setIndex();
    }
  }

  public static void addZoomImage(byte type, byte imgIndex, int x, int y,
                                  int zoom, int anchor, byte trans,
                                  int drawLevel) {

    Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
    Tools.x[Tools.curIndex] = (short) x;
    Tools.y[Tools.curIndex] = (short) y;
    Tools.clipX[Tools.curIndex] = (short) zoom;
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.trans[Tools.curIndex] = trans;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    Tools.type[Tools.curIndex] = (byte) type;
    setIndex();
  }

  public static void addImage(int imgIndex, int x, int y, int clipX, int clipY,
                              int clipW, int clipH,
                              int anchor, byte trans, int drawLevel) {

    if (isDraw(x, y, clipW, clipH, anchor)) {

      Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.clipX[Tools.curIndex] = (short) clipX;
      Tools.clipY[Tools.curIndex] = (short) clipY;
      Tools.clipW[Tools.curIndex] = (short) clipW;
      Tools.clipH[Tools.curIndex] = (short) clipH;
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.trans[Tools.curIndex] = trans;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
      setIndex();
    }
  }

  public static void addShandowString(byte type, String str, int x, int y,
                                      int anchor, int rgb, int rgbRound,
                                      int drawLevel) {
    Tools.str[Tools.curIndex] = str;
    Tools.type[Tools.curIndex] = (byte) type;
    Tools.x[Tools.curIndex] = (short) (x + 1);
    Tools.y[Tools.curIndex] = (short) y;
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.color[Tools.curIndex] = (int) rgb;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    setIndex();
    Tools.str[Tools.curIndex] = str;
    Tools.type[Tools.curIndex] = (byte) type;
    Tools.x[Tools.curIndex] = (short) (x - 1);
    Tools.y[Tools.curIndex] = (short) (y);
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.color[Tools.curIndex] = (int) rgb;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    setIndex();
    Tools.str[Tools.curIndex] = str;
    Tools.type[Tools.curIndex] = (byte) type;
    Tools.x[Tools.curIndex] = (short) (x);
    Tools.y[Tools.curIndex] = (short) (y + 1);
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.color[Tools.curIndex] = (int) rgb;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    setIndex();
    Tools.str[Tools.curIndex] = str;
    Tools.type[Tools.curIndex] = (byte) type;
    Tools.x[Tools.curIndex] = (short) (x);
    Tools.y[Tools.curIndex] = (short) (y - 1);
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.color[Tools.curIndex] = (int) rgb;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    setIndex();
    Tools.str[Tools.curIndex] = str;
    Tools.type[Tools.curIndex] = (byte) type;
    Tools.x[Tools.curIndex] = (short) (x);
    Tools.y[Tools.curIndex] = (short) (y);
    Tools.anchor[Tools.curIndex] = (int) anchor;
    Tools.color[Tools.curIndex] = (int) rgbRound;
    Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
    setIndex();
  }

  public static void addImage(int imgIndex, int x, int y, byte[] clipRect,
                              int anchor, byte trans, int drawLevel) {
    if (isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
      Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.clipX[Tools.curIndex] = (short) bToi(clipRect[0]);
      Tools.clipY[Tools.curIndex] = (short) bToi(clipRect[1]);
      Tools.clipW[Tools.curIndex] = (short) bToi(clipRect[2]);
      Tools.clipH[Tools.curIndex] = (short) bToi(clipRect[3]);
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.trans[Tools.curIndex] = trans;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
      setIndex();
    }
  }

  public static void addImage(int imgIndex, int x, int y, short[] clipRect,
                              int anchor, byte trans, int drawLevel) {
    if (isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
      Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.clipX[Tools.curIndex] = (short) clipRect[0];
      Tools.clipY[Tools.curIndex] = (short) clipRect[1];
      Tools.clipW[Tools.curIndex] = (short) clipRect[2];
      Tools.clipH[Tools.curIndex] = (short) clipRect[3];
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.trans[Tools.curIndex] = trans;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
      setIndex();
    }
  }

  public static void addImage(int imgIndex, int x, int y, int[] clipRect,
                              int anchor, byte trans, int drawLevel) {
    if (isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
      Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
      Tools.x[Tools.curIndex] = (short) x;
      Tools.y[Tools.curIndex] = (short) y;
      Tools.clipX[Tools.curIndex] = (short) clipRect[0];
      Tools.clipY[Tools.curIndex] = (short) clipRect[1];
      Tools.clipW[Tools.curIndex] = (short) clipRect[2];
      Tools.clipH[Tools.curIndex] = (short) clipRect[3];
      Tools.anchor[Tools.curIndex] = (int) anchor;
      Tools.trans[Tools.curIndex] = trans;
      Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
      Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
      setIndex();
    }
  }

//图片翻转
  //type = 0 垂直镜像 ; type = 1 水平镜像 ;type = 2 中心镜像
  //type = 3 左转90度 ; type = 4 右转90度
  public static void drawClipImage(Graphics g, int index, int x, int y,
                                   int clipX,
                                   int clipY, int clipW, int clipH,
                                   int anchor, int trans) {
//    index = index >= 0 ? index : 127 - index;
    Image img = index >= GRUOP_MAX ?
        (index < 1000 ? getImageFromGroup(index, TRANS_NONE) :
         Tools.buff[index % 1000]) :
        getImage(index);
    int mode = javax.microedition.lcdui.game.Sprite.TRANS_NONE;
    switch (trans) {
      case Tools.TRANS_HORIZONTAL:
        mode = javax.microedition.lcdui.game.Sprite.TRANS_MIRROR;
        break;
      case Tools.TRANS_VERTICAL:
        mode = javax.microedition.lcdui.game.Sprite.TRANS_MIRROR_ROT180;
        break;
      case Tools.TRANS_CENTER:
        mode = javax.microedition.lcdui.game.Sprite.TRANS_ROT180;
        break;
    }
    if (clipW <= 0 || clipH <= 0 || clipX < 0 || clipY < 0) {
      return;
    }
    if (clipX + clipW > img.getWidth()) {
      clipW = img.getWidth() - clipX;
    }
    if (clipY + clipH > img.getHeight()) {
      //    System.out.println(index+"!!!!!!!!!!!!!!clipX: " +clipX+" clipY: "+clipY+" clipW: "+clipW+" clipH:"+clipH +" imgH:"+img.getHeight());
      clipH = img.getHeight() - clipY;
      //   System.out.println(index+"@@@@@@@@@@@@@@@clipX: " +clipX+" clipY: "+clipY+" clipW: "+clipW+" clipH:"+clipH+" imgH:"+img.getHeight());
    }
    g.drawRegion(img, clipX, clipY, clipW, clipH, mode, x, y, anchor);
  }

  public final static boolean isDraw(int dx, int dy, int dw, int dh, int anchor) {
    switch (anchor) {
      case Graphics.TOP | Graphics.HCENTER:
        return true;

      case Graphics.LEFT | Graphics.TOP:
        if ( (dx - GameMap.setOffX > -dw &&
              dx - GameMap.setOffX < MyGameCanvas.SCREEN_WIDTH) &&
            (dy - GameMap.setOffY > -dh &&
             dy - GameMap.setOffY < MyGameCanvas.SCREEN_HEIGHT)) {
          return true;
        }
        break;
      case Graphics.LEFT | Graphics.BOTTOM:
        if ( (dx - GameMap.setOffX > -dw &&
              dx - GameMap.setOffX < MyGameCanvas.SCREEN_WIDTH) &&
            (dy - GameMap.setOffY > 0 &&
             dy - GameMap.setOffY < MyGameCanvas.SCREEN_HEIGHT + dh)) {
          return true;
        }
        break;
    }
    return false;
  }

  /*
   * 碰撞检查
   */
  public static boolean hit(int x1, int y1, int w1, int h1, int x2, int y2,
                            int w2, int h2) {
    y1 = y1 - h1;
    y2 = y2 - h2;
    return! (x1 >= x2 + w2 || x2 >= x1 + w1 || y1 >= y2 + h2 || y2 >= y1 + h1);
  }

  //两个human碰撞检查

  /**
   * drawColorString 以HCENTER|TOP的方式画一个彩色的字符串
   *
   * @param g Graphics
   * @param string String 字符串
   * @param x int x坐标
   * @param y int y坐标
   */

//  static Image strBuff = Image.createImage(MyGameCanvas.SCREEN_WIDTH,
//                                           MyGameCanvas.strHeight + 2);

  public static void drawColorString(Graphics g, String string, int x, int y,
                                     int anchor, int color) {
//    if (color == 0xffffff) {
    g.setClip(0, 0, MyGameCanvas.SCREEN_WIDTH,
              MyGameCanvas.SCREEN_HEIGHT);
    g.setColor(color);
    g.drawString(string, x, y, anchor);
//    }
//    else {
//      g.setColor(color);
//      g.setClip(GameMap.setOffX, y, MyGameCanvas.SCREEN_WIDTH, 20);
//      g.drawString(string, x, y, anchor); //字的上半部分
//      g.setClip(GameMap.setOffX, y + 8, MyGameCanvas.SCREEN_WIDTH, 20);
//      g.drawString(string, x, y, anchor); //字的下半部分
//      g.setClip(GameMap.setOffX, GameMap.setOffY, MyGameCanvas.SCREEN_WIDTH,
//                MyGameCanvas.SCREEN_HEIGHT);
//    }

  }

//  public static void drawColorStr(Graphics g, String string, int x, int y,
//                                  int anchor, int color) {
//    g.setColor(color);
//    g.setClip(GameMap.setOffX, y, MyGameCanvas.SCREEN_WIDTH, 20);
//    g.drawString(string, x, y, anchor); //字的上半部分
//    g.setClip(GameMap.setOffX, y + 8, MyGameCanvas.SCREEN_WIDTH, 20);
//    g.drawString(string, x, y, anchor); //字的下半部分
//    g.setClip(GameMap.setOffX, GameMap.setOffY, MyGameCanvas.SCREEN_WIDTH,
//              MyGameCanvas.SCREEN_HEIGHT);
//
//  }

  public static final byte TYPE_ROUND_RECT = 0;
  public static final byte TYPE_RECT = 1;
  public static final byte TYPE_ARC = 2;
  public static final byte TYPE_LINE = 3;
  public static final byte TYPE_STRING = 4;
  public static final byte TYPE_IMG = 5;
//  public static final byte TYPE_ZOOMIMG = 6;
  private static void setIndex() {
//    System.out.println(max_obj);
    drawObj[max_obj++] = curIndex;
    if (++curIndex == MAX) {
      curIndex = 0;
    }
  }

  public static final int MAX = 600;
  static int curIndex = 0;
  public static int[] drawObj = new int[MAX];
  public static short max_obj = 0;
  static short[] clipX = new short[MAX];
  static short[] clipY = new short[MAX];
  static short[] clipW = new short[MAX];
  static short[] clipH = new short[MAX];
  static short[] x = new short[MAX];
  static short[] y = new short[MAX];
  static short[] w = new short[MAX];
  static short[] h = new short[MAX];
  static short[] rw = new short[MAX];
  static short[] rh = new short[MAX];
  static short[] drawLevel = new short[MAX]; //绘图等级
  static short[] imgIndex = new short[MAX]; //图片索引
  static int[] anchor = new int[MAX];
  static byte[] trans = new byte[MAX];
  static boolean[] isFill = new boolean[MAX];
  static int[] color = new int[MAX];
  static String[] str = new String[MAX];
  static byte[] type = new byte[MAX];

  /*public static void drawMe(Graphics g, int index) {
    int y;
    switch (Tools.type[index]) {
      case TYPE_ARC:
        y = Tools.y[index];
        if (Tools.anchor[index] != (Graphics.TOP | Graphics.LEFT)) {
          y = Tools.y[index] - Tools.h[index];
        }
        g.setColor(Tools.color[index]);
        if (isFill[index]) {
          g.fillArc(Tools.x[index], y, Tools.w[index], Tools.h[index],
                    Tools.rw[index], Tools.rh[index]);
        }
        else {
          g.drawArc(Tools.x[index], y, Tools.w[index], Tools.h[index],
                    Tools.rw[index], Tools.rh[index]);
        }
        break;
      case TYPE_IMG:
        Tools.drawClipImage(g, Tools.imgIndex[index], Tools.x[index],
                            Tools.y[index],
                            Tools.clipX[index], Tools.clipY[index],
                            Tools.clipW[index],
                            Tools.clipH[index],
                            Tools.anchor[index], Tools.trans[index]);
//        else {
//          Tools.drawDGClipImage(g, Tools.imgIndex[index], Tools.x[index],
//                                Tools.y[index],
//                                Tools.clipX[index], Tools.clipY[index],
//                                Tools.clipW[index],
//                                Tools.clipH[index],
//                                Tools.anchor[index]);
//        }
        break;
      case TYPE_LINE:
        g.setColor(Tools.color[index]);
        g.drawLine(Tools.x[index], Tools.y[index], Tools.w[index],
                   Tools.h[index]);
        break;
      case TYPE_RECT:
        y = Tools.y[index];
        if (Tools.anchor[index] != (g.TOP | g.LEFT)) {
          y = Tools.y[index] - Tools.h[index];
        }
        g.setColor(Tools.color[index]);
        if (Tools.isFill[index]) {
          g.fillRect(Tools.x[index], y, Tools.w[index],
                     Tools.h[index]);
        }
        else {
          g.drawRect(Tools.x[index], y, Tools.w[index],
                     Tools.h[index]);
        }
        break;
      case TYPE_ROUND_RECT:
        y = Tools.y[index];
        if (Tools.anchor[index] != (g.TOP | g.LEFT)) {
          y = Tools.y[index] - Tools.h[index];
        }
        g.setColor(Tools.color[index]);
        if (Tools.isFill[index]) {
          g.fillRoundRect(Tools.x[index], y, Tools.w[index],
                          Tools.h[index], Tools.rw[index], Tools.rh[index]);
        }
        else {
          g.drawRoundRect(Tools.x[index], y, Tools.w[index],
                          Tools.h[index], Tools.rw[index], Tools.rh[index]);
        }
        break;
      case TYPE_STRING:
        Tools.drawColorString(g, Tools.str[index], Tools.x[index],
                              Tools.y[index], Tools.anchor[index],
                              Tools.color[index]);
        Tools.str[index] = null;
        break;
    }
     }*/

  public static void drawMe(Graphics g, int index) {
    int endx = Tools.x[index] - GameMap.setOffX;
    int endy = Tools.y[index] - GameMap.setOffY;
    int y;
    switch (Tools.type[index]) {
      case TYPE_ARC:
        g.setClip(0, 0, MyGameCanvas.SCREEN_WIDTH, MyGameCanvas.SCREEN_HEIGHT);
        y = endy;
        if (Tools.anchor[index] != (Graphics.TOP | Graphics.LEFT)) {
          y = endy - Tools.h[index];
        }
        g.setColor(Tools.color[index]);
        if (isFill[index]) {
          g.fillArc(endx, y, Tools.w[index], Tools.h[index],
                    Tools.rw[index], Tools.rh[index]);
        }
        else {
          g.drawArc(endx, y, Tools.w[index], Tools.h[index],
                    Tools.rw[index], Tools.rh[index]);
        }
        break;
      case TYPE_IMG:
        Tools.drawClipImage(g, Tools.imgIndex[index], endx,
                            endy, Tools.clipX[index], Tools.clipY[index],
                            Tools.clipW[index], Tools.clipH[index],
                            Tools.anchor[index], Tools.trans[index]);
        break;
      case TYPE_RECT:
        g.setClip(0, 0, MyGameCanvas.SCREEN_WIDTH, MyGameCanvas.SCREEN_HEIGHT);
        y = endy;
        if (Tools.anchor[index] != (g.TOP | g.LEFT)) {
          y = endy - Tools.h[index];
        }

        g.setColor(Tools.color[index]);
        if (Tools.isFill[index]) {
          g.fillRect(endx, y, Tools.w[index],
                     Tools.h[index]);

        }
        else {
          g.drawRect(endx, y, Tools.w[index],
                     Tools.h[index]);
        }
        break;
      case TYPE_ROUND_RECT:
        g.setClip(0, 0, MyGameCanvas.SCREEN_WIDTH, MyGameCanvas.SCREEN_HEIGHT);
        y = endy;
        if (Tools.anchor[index] != (g.TOP | g.LEFT)) {
          y = endy - Tools.h[index];
        }
        g.setColor(Tools.color[index]);
        if (Tools.isFill[index]) {

          g.fillRoundRect(endx, y, Tools.w[index],
                          Tools.h[index], Tools.rw[index], Tools.rh[index]);
        }
        else {
          g.drawRoundRect(endx, y, Tools.w[index],
                          Tools.h[index], Tools.rw[index], Tools.rh[index]);
        }
        break;
      case TYPE_STRING:

        Tools.drawColorString(g, Tools.str[index], endx,
                              endy,
                              Tools.anchor[index], Tools.color[index]);
        Tools.str[index] = null;
        break;
    }
  }

  public static int bToi(byte byte0) {
    int i = byte0;
    if (byte0 < 0) {
      i += 256;
    }
    return i;
  }

//读切片数据
  public final static byte TILE_LT_PASS_LR = 15;
  public final static byte TILE_LT_PASS_T = 14;
  public final static byte TILE_LT_PASS_B = 13;

  public final static byte TILE_LB_PASS_LR = 12;
  public final static byte TILE_LB_PASS_T = 11;
  public final static byte TILE_LB_PASS_B = 10;

  public final static byte TILE_RT_PASS_LR = 9;
  public final static byte TILE_RT_PASS_T = 8;
  public final static byte TILE_RT_PASS_B = 7;

  public final static byte TILE_RB_PASS_LR = 6;
  public final static byte TILE_RB_PASS_T = 5;
  public final static byte TILE_RB_PASS_B = 4;

  public final static byte TILE_SPEEL = 3;
  public final static byte TILE_LD_REBOUND = 2;
  public final static byte TILE_HIT = 1;
  public final static byte TILE_EMPTY = 0;

//获得切片属性 val－切片索引 bit－位数
  public static boolean getProperties(short val, byte bit) {
    return (val >> bit & 0x01) == 1;
  }

  public static void createImage(String[] str, int startIndex) {
    InputStream is = "".getClass().getResourceAsStream("/imgMapPackage.dat");
    String[] mapName = null;
    byte[] imgData = null;
    short allLen = 0;
    try {
      char mark = (char) readShort(is);
      if (mark != 'A') {
        System.out.println("map file format error");
        return;
      }
      allLen = readShort(is);
      mapName = new String[allLen];
      for (int i = 0; i < mapName.length; i++) {
        int nameLen = is.read();
        byte[] contentBuff = new byte[nameLen];
        is.read(contentBuff);
        mapName[i] = new String(contentBuff, "UTF-8"); //name
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    imgGroup[startIndex] = null;
    imgGroup[startIndex] = new Image[str.length];
    int[] index = new int[str.length];
    int[] oIndex = new int[str.length];
    for (int i = 0; i < str.length; i++) {
      for (int j = 0; j < mapName.length; j++) {
        if (str[i].equals(mapName[j])) {
          index[i] = oIndex[i] = j;
        }
      }
    }

    for (int i = 0; i < index.length; i++) {
      for (int j = i + 1; j < index.length; j++) {
        if (index[i] > index[j]) {
          index[i] = index[i] ^ index[j];
          index[j] = index[i] ^ index[j];
          index[i] = index[i] ^ index[j];
        }
      }
    }

    try {
      int[] offsetArray = new int[allLen << 1];
      for (int i = 0; i < offsetArray.length; i++) {
        offsetArray[i] = readInt(is);
      }

      int count = 0;
      for (int i = 0; i < index.length; i++) {
        byte[] imgData2 = null;
        int offset = offsetArray[index[i] * 2];
        int len = offsetArray[index[i] * 2 + 1] - offset;

        int pos = offset - count;
        imgData2 = new byte[len];
        is.skip(pos);
        is.read(imgData2, 0, len);

        for (int j = 0; j < oIndex.length; j++) {
          if (oIndex[j] == index[i]) {
            imgGroup[startIndex][j] = Image.createImage(imgData2, 0,
                imgData2.length);

            break;
          }
        }

        count = len + offset;
      }
      is.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  public final static long sqrt(long v) { //结果/100
    if (v > 0) {
      v *= 10000;
      long r = 10000;
      long c;
      do {
        c = r;
//        r = v / r + r >> 1;
        r = (v / r + r) / 2;
//        System.out.println("r:: "+r+"      c : " + c);
      }
      while (r < c);

      return c;
    }
    else {
      return 0;
    }
  }

  public static final int drgee[] = {
      0, 174, 342, 500, 643, 766, 866, 940, 985, 1000,
  };
  public int sin(int v) {
    if (180 - v % 360 >= 0) {
      return drgee[9 - (Math.abs(90 - v % 360) / 10)];
    }
    else {
      return drgee[9 - (Math.abs(270 - v % 360) / 10)] * ( -1);
    }
  }

  public int cos(int v) {
    if (90 - v % 360 >= 0 || v % 360 - 270 >= 0) {
      if (90 - v % 360 >= 0) {
        return drgee[9 - (v % 360) / 10];
      }
      else {
        return drgee[ ( (v % 360) % 90) / 10];
      }
    }
    else {
      if (180 - v % 360 >= 0) {
        return drgee[ ( (v % 360) - 90) / 10] * ( -1);
      }
      else {
        return drgee[9 - ( (v % 360) % 90) / 10] * ( -1);
      }
    }
  }

//  public static void drawnumframe(int imgid, int x, int y, int cilpX, int clipY,
//                                  int drawlever) {
//    Tools.addObject(imgid, x, y, numData, Tools.BOTTOM, Tools.TRANS_NONE,
//                    drawlever);
//  }
  public final static int RIGHT = 1;
  public final static int LEFT = 3;
  public final static int UP = 0;
  public final static int DOWN = 2;
  public static void drawnum(int num, int imgid, int type, int x, int y,
                             int cilpX,
                             int cilpY, int space, int anchor, int drawlever) {
    if (anchor == RIGHT) {
      Tools.addImage(imgid, x, y, (num % 10) * cilpX, type * cilpY, cilpX,
                     cilpY,
                     Tools.BOTTOM, Tools.TRANS_NONE, drawlever);
      if (num / 10 > 0) {
        drawnum(num / 10, imgid, type, x - cilpX - space, y, cilpX, cilpY,
                space,
                RIGHT, drawlever);
      }
    }
    else {
      int number = num;
      int seat = getSeat(number) - 1;
      int step = 0;
      while (seat >= 0) {
        Tools.addImage(imgid, x + step, y,
                       (number / square(10, seat)) * cilpX, type * cilpY,
                       cilpX, cilpY,
                       Tools.BOTTOM, Tools.TRANS_NONE, drawlever);
        number = number % square(10, seat);
        seat--;
        step += cilpX + space;
      }
    }
  }

  static int getSeat(int num) {
    int seat = 1;
    int number = num;
    while (number / 10 > 0) {
      seat++;
      number = number / 10;
    }
    return seat;
  }

  static int square(int base, int exponent) {
    int pro = 1;
    for (int i = 0; i < exponent; i++) {
      pro *= base;
    }
    return pro;
  }

  public final static int readInt(InputStream is) throws Exception {
    return (is.read() << 24) | (is.read() << 16) | (is.read() << 8) | (is.read());
  }

  public final static short readShort(InputStream is) throws Exception {
    return (short) ( (is.read() << 8) | (is.read()));

  }

//  public static int sToi(short byte0) {
//    int i = byte0;
//    if (byte0 < 0) {
//      i += 65536;
//    }
//    return i;
//  }

  public static Image getImage(int index) {
//     index = index >= 0 ? index : 127 - index;
    Image img = (Image) imgs[index];
    if (img == null) {
      imgs[index] = createImage(FILESNAME[index]);
      img = imgs[index];
    }
    return img;
  }

  private static final int[] SIN = {
      0, 1143, 2287, 3429, 4571, 5711, 6850, 7986, 9120, 10252, 11380, 12504,
      13625, 14742, 15854, 16961, 18064, 19160, 20251, 21336, 22414, 23486,
      24550, 25606, 26655, 27696, 28729, 29752, 30767, 31772, 32767, 33753,
      34728, 35693, 36647, 37589, 38521, 39440, 40347, 41243, 42125, 42995,
      43852, 44695, 45525, 46340, 47142, 47929, 48702, 49460, 50203, 50931,
      51643, 52339, 53019, 53683, 54331, 54963, 55577, 56175, 56755, 57319,
      57864, 58393, 58903, 59395, 59870, 60326, 60763, 61183, 61583, 61965,
      62328, 62672, 62997, 63302, 63589, 63856, 64103, 64331, 64540, 64729,
      64898, 65047, 65176, 65286, 65376, 65446, 65496, 65526, 65536
  };

// angle is in degrees/10, i.e. 0..36 for full circle
  public static int sineTimes256(int angle) { //三角函数查表值sin
    //angle /= 10; // 360 degrees
    angle += 3600;
    angle %= 360;

    if (angle <= 90) { // 0..90 degrees
      return SIN[angle];
    }
    else if (angle <= 180) { // 90..180 degrees
      return SIN[180 - angle];
    }
    else if (angle <= 270) { // 180..270 degrees
      return -SIN[angle - 180];
    }
    else { // 270..360 degrees
      return -SIN[360 - angle];
    }
  }

}