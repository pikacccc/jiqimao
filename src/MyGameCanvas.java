import javax.microedition.lcdui.*;
import java.io.*;
import javax.microedition.rms.*;


/**
 * <p>Title: 采用低级接口界面的游戏主框架</p>
 * <p>Description: 游戏的主菜单以及帮助设置等界面均采用低级接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author KDM
 * @version 1.0
 */

public final class MyGameCanvas
        extends Canvas {
    public static byte startRank = 0;
    //屏幕尺寸
    public static final int SCREEN_WIDTH = 240;
    public static final int SCREEN_HEIGHT = 320;

    //字体 Moto机型不可用
    protected static final Font FONT_SMALL = Font.getFont(0, 0, 8);
    protected static final Font FONT_MEDIUM = Font.getFont(0, 0, 0);
    protected static final Font FONT_LARGE = Font.getFont(0, 0, 16);

    //键值 Nokia机型
    public final static int KEY_UP = -1;
    public final static int KEY_DOWN = -2;
    public final static int KEY_LEFT = -3;
    public final static int KEY_RIGHT = -4;
    public final static int KEY_LS = -6;
    public final static int KEY_RS = -7;
    public final static int KEY_OK = -5;
    public final static int KEY_BACK = -11;
//  //键值 Moto E398/V600i机型
//  public final static int KEY_UP = -1;
//  public final static int KEY_DOWN = -6;
//  public final static int KEY_LEFT = -2;
//  public final static int KEY_RIGHT = -5;
//  public final static int KEY_LS = -21;
//  public final static int KEY_RS = -22;
//  public final static int KEY_OK = -20;
//  public final static int KEY_BACK = -11;
    //键值 Moto V300/V600机型
//  public final static int KEY_UP = 1;
//  public final static int KEY_DOWN = 6;
//  public final static int KEY_LEFT = 2;
//  public final static int KEY_RIGHT = 5;
//  public final static int KEY_LS = 21;
//  public final static int KEY_RS = 22;
//  public final static int KEY_OK = 20;
//  public final static int KEY_BACK = -11;

    public final static byte KEY_0 = 48;
    public final static byte KEY_1 = 49;
    public final static byte KEY_2 = 50;
    public final static byte KEY_3 = 51;
    public final static byte KEY_4 = 52;
    public final static byte KEY_5 = 53;
    public final static byte KEY_6 = 54;
    public final static byte KEY_7 = 55;
    public final static byte KEY_8 = 56;
    public final static byte KEY_9 = 57;
//  public final static byte KEY_STAR = 42;
//  public final static byte KEY_SHARP = 35;

    protected final static byte ST_M = -3;
    protected final static byte ST_SP = -2; //游戏logo
    protected final static byte ST_CP = -1; //游戏logo
    protected final static byte ST_OPEN = 0; //游戏logo
    protected final static byte ST_LOAD = 1; //游戏进度条
    protected final static byte ST_MENU = 2; //游戏菜单
    protected final static byte ST_MIDMENU = 3; //游戏中途菜单
    protected final static byte ST_SETUP = 4; //游戏选项
    protected final static byte ST_HELP = 5; //游戏帮助
    protected final static byte ST_ABOUT = 6; //游戏关于
    protected final static byte ST_PLAY = 7; //游戏中
    protected final static byte ST_OVER = 8; //游戏结束
    protected final static byte ST_PAUSE = 9; //游戏暂停
    protected final static byte ST_TALK = 10; //对话状态,此时游戏处于暂停状态
    protected final static byte ST_IN_DOOR_EFFECT = 11; //进迷宫效果
    protected final static byte ST_START_MAZE = 13;
    protected final static byte ST_START_EFFECT = 14;
    protected final static byte ST_PASS_EFFECT = 15;
    protected final static byte ST_READY = 16;
    protected final static byte ST_PASS_GAME = 18;
    protected final static byte ST_QUIT = 19;
    protected final static byte ST_LOAD1 = 20;
    protected final static byte ST_EXIT = 21;

    //当前游戏状态
    static byte gameStatus = ST_M;
    static byte lastStatus = ST_M;
    //当前状态下的索引
    static int index = 0;

    GameEngine engine;
    static MyGameCanvas me;
    GameMap map;

    public MyGameCanvas() {
        setFullScreenMode(true);
        me = this;
        engine = new GameEngine();
    }

    public static void setST(byte st) {
        index = 0;
        lastStatus = gameStatus;
        gameStatus = st;

    }

    private void drawMenubg() {
        Tools.addRect(Tools.TYPE_RECT, engine.map.setOffX,
                engine.map.setOffY, SCREEN_WIDTH,
                SCREEN_HEIGHT, true, Tools.TOP, 1, 1);

    }

    private void drawQiut(Graphics g) {

        Tools.addRect(Tools.TYPE_RECT, engine.map.setOffX,
                engine.map.setOffY, SCREEN_WIDTH,
                SCREEN_HEIGHT, true, Tools.TOP, 0, 0);

        Tools.addString(Tools.TYPE_STRING, "正在退出......", SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / 2,
                Tools.TOP, 0xffffff, 1);
        Tools.addString(Tools.TYPE_STRING, "正在fanhui......", SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / 2,
                Tools.TOP, 0xffffff, 1);
        if (++index > 7) {
            GameMIDlet.quitApp();
        }
    }

    private void drawExit(Graphics g) {
        g.setFont(FONT_SMALL);
        Tools.addString(Tools.TYPE_STRING, "正在返回主界面", SCREEN_WIDTH >>> 1,
                SCREEN_HEIGHT >>> 1,
                g.TOP | g.HCENTER, 0xffffff, 1);
        if (++index > 7) {
            setST(ST_MENU);
        }
    }

    String[] menuWords = {
            "继续游戏", "开始游戏", "游戏帮助", "游戏关于", "退出游戏"
    };

    public void drawMenu(Graphics g) {
        drawMenubg();
//    Tools.addString(Tools.TYPE_STRING, "主菜单", SCREEN_WIDTH >>> 1,
//                    SCREEN_HEIGHT >>> 1,
//                    g.TOP | g.HCENTER, 0xffffff, 1);
        for (int i = 0; i < menuWords.length; i++) {
            Tools.addString(Tools.TYPE_STRING, menuWords[i], SCREEN_WIDTH / 2,
                    SCREEN_HEIGHT / menuWords.length * i,
                    g.TOP | g.HCENTER, index == i ? 0xff0000 : 0xffffff, 1);
//      System.out.println("i="+ i);
//      System.out.println(index);
        }

    }

    private void drawMidMenu(Graphics g) {
        drawMenubg();

    }

    //清屏
    public void drawCleanScreen(Graphics g, int c) {
        g.setColor(c);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private void drawPassGame() {
        if (++index > 25) {
            setST(ST_MENU);
        } else {
            int y = Math.min(index * 5, MyGameCanvas.SCREEN_HEIGHT / 2);
            Tools.addString(Tools.TYPE_STRING, "祝喜过关！",
                    MyGameCanvas.SCREEN_WIDTH / 2, y,
                    Graphics.TOP | Graphics.HCENTER, 0x123456, 0);
        }
    }

    int openIndex;

    //CG
    private void drawOpen(Graphics g) {
        drawMenubg();
        drawOpenBG();
        drawOpencloud();
        drawOpenTree();
        drawOpenWord();
        drawOpenRole();
        drawOpenFlower();
    }

    //山
    int xBg = 0;
    int xBgl = -240;

    private void drawOpenBG() {
        if (xBg > 240) {
            xBg = 0;
        }
        if (xBgl > 0) {
            xBgl = -240;
        }
        xBg++;
        xBgl++;
        Tools.addImage(Tools.IMG_KJ_BJ, xBg, 0, Tools.TOP, Tools.TRANS_NONE, 1);
        Tools.addImage(Tools.IMG_KJ_BJ, xBgl, 0, Tools.TOP, Tools.TRANS_NONE, 1);
    }

    //云
    int xCloud = -60;
    int xCloudl = -300;

    private void drawOpencloud() {
        if (xCloud > 240) {
            xCloud = -60;
        }
        if (xCloudl > 240) {
            xCloudl = -300;
        }
        xCloud += 24;
        xCloudl += 48;
        Tools.addImage(Tools.IMG_KJ_C1, xCloud, 20, Tools.TOP, Tools.TRANS_NONE, 2);
        Tools.addImage(Tools.IMG_KJ_C2, xCloudl, 60, Tools.TOP, Tools.TRANS_NONE, 2);
    }

    //树
    int xTree = 200;
    int xTreel = 0;

    private void drawOpenTree() {
        if (xTree > 240) {
            xTree = 0;
        }
        if (xTreel > 240) {
            xTreel = 0;
        }
        byte[] treeClip = {
                86, 6, 70, 23};
//    xTree += 64;
//    xTreel += 64;
        Tools.addImage(Tools.IMG_KJ_T, xTree, 86, Tools.TOP, Tools.TRANS_NONE, 3);
        Tools.addImage(Tools.IMG_KJ_T, xTreel, 105, treeClip, Tools.TOP,
                Tools.TRANS_NONE, 3);
        xTree += 64;
        xTreel += 64;

    }

    //字
    int xWord = -214;
    //  int xWordl = -430;
    int xWordLeft = 214;

    private void drawOpenWord() {
        if (xWord > 60) {
            xWord = -214;
            xWordLeft = 214;
        }
        if (xWord > -154) {
            xWordLeft--;
        }
        int[] wordClip = {
                0, 0, xWordLeft, 107};
        xWord++;
        Tools.addImage(Tools.IMG_KJ_W, xWord, 5, wordClip, Tools.TOP,
                Tools.TRANS_NONE, 4);
//    xWord++;
    }

    //角色
    int xRoleTime = 0;
    int yRole = 0;

    private void drawOpenRole() {
        byte roleClip[][] = {
                {
                        0, 0, 48, 49}
                , {
                48, 0, 48, 49}
                , {
                96, 0, 48, 49}
        };
        int x = xRoleTime % 3;
        yRole = xRoleTime % 2;
        xRoleTime++;
        Tools.addImage(Tools.IMG_KJ_R, 100, 30 - yRole, roleClip[x], Tools.TOP,
                Tools.TRANS_NONE, 4);
    }

    //花瓣
    int fTime = 0;
    int xFlower = 0;
    int yFlower = 0;
    int xFlower1 = -3;
    int yFlower1 = -7;
    int xFlower2 = 5;
    int yFlower2 = -2;
    int xFlower3 = 20;
    int yFlower3 = -13;
    int xFlower4 = 15;
    int yFlower4 = -7;
    int x = 0;

    private void drawOpenFlower() {

        fTime++;
        byte[][] flowerClip = {
                {
                        0, 0, 5, 5}
                , {
                5, 0, 5, 5}
                , {
                10, 0, 5, 5}
                , {
                18, 0, 8, 5}
                , {
                24, 0, 6, 5}
                , {
                29, 0, 5, 5}
                , {
                35, 0, 6, 5}
        };

        x = fTime % 7;

        xFlower += 6;
        yFlower += 11;
        if (xFlower > 240) {
            xFlower = -5;
        }
        if (yFlower > 120) {
            yFlower = -5;
        }

        Tools.addImage(Tools.IMG_KJ_F, xFlower, yFlower,
                flowerClip[x],
                Tools.TOP,
                Tools.TRANS_NONE, 5);
        xFlower1 += 3;
        yFlower1 += 7;
        if (xFlower1 > 240) {
            xFlower1 = -3;
        }
        if (yFlower1 > 120) {
            yFlower1 = -7;
        }

        Tools.addImage(Tools.IMG_KJ_F, xFlower1, yFlower1,
                flowerClip[x],
                Tools.TOP,
                Tools.TRANS_NONE, 5);

        xFlower2 += 8;
        yFlower2 += 19;
        if (xFlower2 > 240) {
            xFlower2 = 5;
        }
        if (yFlower2 > 120) {
            yFlower2 = -2;
        }

        Tools.addImage(Tools.IMG_KJ_F, xFlower2, yFlower2,
                flowerClip[x],
                Tools.TOP,
                Tools.TRANS_NONE, 5);
        xFlower3 += 11;
        yFlower3 += 9;
        if (xFlower3 > 240) {
            xFlower3 = 20;
        }
        if (yFlower3 > 120) {
            yFlower3 = -13;
        }

        Tools.addImage(Tools.IMG_KJ_F, xFlower3, yFlower3,
                flowerClip[x],
                Tools.TOP,
                Tools.TRANS_NONE, 5);
        xFlower4 += 24;
        yFlower4 += 3;
        if (xFlower4 > 240) {
            xFlower4 = 15;
        }
        if (yFlower4 > 120) {
            yFlower4 = -7;
        }

        Tools.addImage(Tools.IMG_KJ_F, xFlower4, yFlower4,
                flowerClip[x],
                Tools.TOP,
                Tools.TRANS_NONE, 5);

    }

    public void drawM() {
        Tools.addImage(Tools.IMG_MONTERNET, 0, 0, Tools.TOP, Tools.TRANS_NONE, 0);
        if (++index >= 30) {
            setST(ST_SP);
        }
    }

    protected void paint(Graphics g) {
//    System.out.println(""  + index);
        g.setFont(FONT_SMALL);
        switch (gameStatus) {
            case ST_EXIT:
                drawCleanScreen(g, 0);
                drawExit(g);
                break;
            case ST_QUIT:
                drawCleanScreen(g, 0);
                drawQiut(g);
                break;
            case ST_MIDMENU:
                drawMidMenu(g);
                break;
            case ST_SP:
                Tools.removeImage(Tools.IMG_SP);
                setST(ST_CP);
                break;
            case ST_M:
                setST(ST_SP);
                break;
            case ST_LOAD1:
                Tools.removeImage(Tools.IMG_LOGO);
                setST(ST_OPEN);
                break;
            case ST_CP:
                Tools.removeImage(Tools.IMG_CP);
                setST(ST_LOAD1);
                break;
            case ST_OPEN:
//                drawOpen(g);
                setST(ST_MENU);
                break;
            case ST_MENU:
                drawCleanScreen(g, 0);
                drawMenu(g);
                break;
            case ST_LOAD:
                drawLoad(g);
                break;
            case ST_HELP:
                drawCleanScreen(g, 0);
                drawHelp(g);
                break;
            case ST_START_EFFECT:
                engine.drawGame(g, false);
                drawStartEffect(g);
                break;
            case ST_PASS_EFFECT:
                drawPassEffect(g);
                break;
            case ST_ABOUT:
                drawCleanScreen(g, 0);
                drawAbout(g);
                break;
            case ST_READY:
                drawReady(g);
                break;
            case ST_PLAY:
                engine.runGame();
                engine.drawGame(g, true);
                engine.gameTime++;
                if (isBreak) {
                    setST(ST_PAUSE);
                    isBreak = false;
                }
                break;
            case ST_PAUSE:
                g.setColor(0xffffff);
                g.drawString("暂 停", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2,
                        g.TOP | g.HCENTER);
                isBreak = false;
                break;
            case ST_PASS_GAME:
                drawCleanScreen(g, 0);
                drawPassGame();
                break;

        }
        drawAll(g);
    }

    private void drawReady(Graphics g) {
        engine.role.move();
        setST(ST_PLAY);
        engine.role.isAuto = false;
        engine.role.setStatus(GameInterface.STATUS_MOVE);
        engine.drawGame(g, true);
    }

    private void drawLoad(Graphics g) {
        if (index == 4) {
            engine.initGame();
        }
        if (index == 5) {
            setST(ST_START_EFFECT);
        }
        index++;
    }

    private void drawPassEffect(Graphics g) {
        engine.map.setOff(0, 0);
        if (index < 24) {
            Tools.addRect(Tools.TYPE_RECT, 0,
                    0, index * 4,
                    220, true, g.TOP | g.LEFT, 0, 200);

            Tools.addRect(Tools.TYPE_RECT,
                    0 + SCREEN_WIDTH - index * 4,
                    0, index * 4,
                    220, true, g.TOP | g.LEFT, 0, 200);
        } else {
            setST(ST_LOAD);
        }
        index++;
    }

    private void drawStartEffect(Graphics g) {
        if (index < 24) {
            Tools.addRect(Tools.TYPE_RECT, engine.map.setOffX,
                    engine.map.setOffY,
                    (SCREEN_WIDTH >>> 1) - (index - 1) * 4,
                    240, true, g.TOP | g.LEFT, 0, 200);
            Tools.addRect(Tools.TYPE_RECT,
                    engine.map.setOffX + (SCREEN_WIDTH >>> 1) +
                            (index - 1) * 4,
                    engine.map.setOffY,
                    (SCREEN_WIDTH >>> 1) - (index - 1) * 4,
                    240, true, g.TOP | g.LEFT, 0, 201);

        } else {
            setST(ST_READY);
        }
        index++;
    }

    private final static String[] strAbout = {

    };

    private void drawAbout(Graphics g) {
        drawMenubg();
    }

//帮助信息
    /**
     * drawHelp 帮助菜单
     *
     * @param g Graphics
     */
    public static final String[] helpInfo = {

    };
    String[] helpTop = {
            "故事介绍", "操作介绍", "修炼", "注灵",
            "附灵", "炼化"};
    String[] helpLow = {
            "1234567890123aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa123123",
            "1234567890123bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb123123",
            "1234567890123cccccccccccccccccccccccccc123123",
            "1234567890123ddddd123123",
            "1234567890123eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee123123",
            "1234567890123fffffffffffffffffffffffffffffffffffffffffffffffffffffffff123123"
    };
    public static int startLine = 0;
    public static int endLine = 4;

    private void drawHelp(Graphics g) {
        drawMenubg();
        Tools.addString(Tools.TYPE_STRING, helpTop[index], SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / menuWords.length,
                g.TOP | g.HCENTER, 0xff0000, 1);
        int lineNum = 0;
//int pagNum = 0;
//int addTal = 0;
        lineNum = helpLow[index].length() % 10 == 0 ? helpLow[index].length() / 10 :
                helpLow[index].length() / 10 + 1;
        if (lineNum < 4) {
            for (int i = 0; i < lineNum; i++) {

                if (i == lineNum - 1) {
                    Tools.addString(Tools.TYPE_STRING,
                            helpLow[index].substring(i * 10),
                            SCREEN_WIDTH / 2,
                            SCREEN_HEIGHT / menuWords.length +
                                    (i - startLine + 1) * 30,
                            g.TOP | g.HCENTER, 0xff00ff, 1);
                } else {
                    Tools.addString(Tools.TYPE_STRING,
                            helpLow[index].substring(i * 10, i * 10 + 10),
                            SCREEN_WIDTH / 2,
                            SCREEN_HEIGHT / menuWords.length +
                                    (i - startLine + 1) * 30,
                            g.TOP | g.HCENTER, 0xff00ff, 1);

                }
            }
        }
        if (lineNum >= 4) {
            for (int i = startLine; i < endLine; i++) {
                if (i * 10 + 10 < helpLow[index].length()) {
                    Tools.addString(Tools.TYPE_STRING,
                            helpLow[index].substring(i * 10, i * 10 + 10),
                            SCREEN_WIDTH / 2,
                            SCREEN_HEIGHT / menuWords.length +
                                    (i - startLine + 1) * 30,
                            g.TOP | g.HCENTER, 0xff00ff, 1);
                } else {
                    Tools.addString(Tools.TYPE_STRING,
                            helpLow[index].substring(i * 10),
                            SCREEN_WIDTH / 2,
                            SCREEN_HEIGHT / menuWords.length +
                                    (i - startLine + 1) * 30,
                            g.TOP | g.HCENTER, 0xff00ff, 1);

                }
            }
        }
        Tools.addString(Tools.TYPE_STRING, Integer.toString(index + 1) + "/6",
                SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / menuWords.length + 6 * 30,
                g.TOP | g.HCENTER, 0xff0000, 1);

//    int lineNum = 0;
        int pagNum = 0;
        int addTal = 0;
//    lineNum = helpLow[index].length() % 10 == 0 ? helpLow[index].length() / 10 :
//        helpLow[index].length() / 10 + 1;
        pagNum = lineNum - 4;
        addTal = startLine * 105 / pagNum;
//System.out.println(lineNum +"  "+pagNum +"  "+ addTal);

        Tools.addRect(Tools.TYPE_RECT, SCREEN_WIDTH / 2 + 30,
                SCREEN_HEIGHT / menuWords.length + 30, 10,
                lineNum > 4 ? 3 * 30 + 15 : 0, true, Tools.TOP, 0xff0ff00, 1);

        Tools.addRect(Tools.TYPE_RECT, SCREEN_WIDTH / 2 + 30,
                addTal == 0 ? SCREEN_HEIGHT / menuWords.length + 30 + addTal :
                        SCREEN_HEIGHT / menuWords.length + 30 + addTal - 15, 10,
                lineNum > 4 ? 15 : 0, true, Tools.TOP, 0xff000ff, 1);

    }

    static boolean keyPressed;

    public void keyPressed(int keyCode) {
        keyPressed = true;
        switch (gameStatus) {
            case ST_M:
                setST(ST_SP);
                break;
            case ST_SP:
                Tools.removeImage(Tools.IMG_SP);
                setST(ST_CP);
                break;
            case ST_CP:
                Tools.removeImage(Tools.IMG_CP);
                setST(ST_LOAD1);
                break;
            case ST_LOAD1:
                Tools.removeImage(Tools.IMG_LOGO);
                setST(ST_OPEN);
                break;
            case ST_OPEN:
                setST(ST_MENU);
                break;
            case ST_MIDMENU:
                switch (keyCode) {
                    case KEY_UP:
                    case KEY_2:
                        break;
                    case KEY_DOWN:
                    case KEY_8:
                        break;
                    case KEY_OK:
                    case KEY_LS:
                        break;
                }
                break;
            case ST_MENU:
                switch (keyCode) {
                    case KEY_UP:
                    case KEY_LEFT:
                        if (index > 0) {
                            index--;
                        } else {
                            index = 4;
                        }
                        break;
                    case KEY_DOWN:
                    case KEY_RIGHT:
                        if (index < 4) {
                            index++;
                        } else {
                            index = 0;
                        }
                        break;
                    case KEY_OK:
                    case KEY_LS:
                        switch (index) {
                            case 0:
                                Tools.removeImage(Tools.IMG_LOGO);
                                setST(ST_LOAD);
                                break;
                            case 1: //进入游戏
                                engine.gameRank = 0;
                                try {
                                    writeDB();
                                } catch (Exception ex1) {
                                }
                                Tools.removeImage(Tools.IMG_LOGO);
                                setST(ST_LOAD);
                                break;
                            case 2: //进入帮助信息
//                                setST(ST_HELP);
                                break;
                            case 3: //进入关于
//                                setST(ST_ABOUT);
                                break;
                            case 4: //退出游戏
                                setST(ST_QUIT);
                                break;
                        }
                        break;
                }
                break;
            case ST_ABOUT:
                break;
            case ST_PLAY:
                engine.ctrl(keyCode);
                switch (keyCode) {
                    case KEY_RS:
                        setST(ST_PAUSE);
                        break;
                    case KEY_LS:
                        setST(ST_QUIT);
                        break;
                }
                break;
            case ST_PAUSE:
                switch (keyCode) {
                    case KEY_RS:
                        setST(ST_PLAY);
                        break;
                }
                break;
            case ST_HELP:
                break;
            case ST_EXIT:
                break;
            case ST_TALK:
                break;
        }
    }

    public void keyReleased(int keyCode) {
        keyPressed = false;
        if (gameStatus == ST_PLAY) {
            engine.ctrlReleased(keyCode);
        }
    }

    private void drawAll(Graphics g) {
        sort();
        for (int i = 0; i < Tools.max_obj; i++) {
            Tools.drawMe(g, Tools.drawObj[i]);
        }
        Tools.max_obj = 0;
    }

    private void sort() {
        int cout = 0;
        int temp;
        int j;
        for (int i = 1; i < Tools.max_obj; i++) {
            if (Tools.drawLevel[Tools.drawObj[i]] <
                    Tools.drawLevel[Tools.drawObj[i - 1]]) {
                temp = Tools.drawObj[i];
                j = i - 1;
                do { //从右向左在有序区R[1．．i-1]中查找R[i]的插入位置
//          cout++;
                    Tools.drawObj[j + 1] = Tools.drawObj[j]; //将关键字大于R[i].key的记录后移
                    j--;
                    if (j < 0) {
                        break;
                    }
                }
                while (Tools.drawLevel[temp] < Tools.drawLevel[Tools.drawObj[j]]); //当R[i].key≥R[j].key时终止
                Tools.drawObj[j + 1] = temp;
            }
        }
    }

    boolean isBreak = false;

    public void showNotify() {
        if (gameStatus == ST_PAUSE) {
            setST(ST_PLAY);
            isBreak = true;
            repaint();
        }
    }

    public void hideNotify() {
        if (gameStatus == ST_PLAY) {
            setST(ST_PAUSE);
            repaint();
        }
    }

    /**
     * 记录存储与读取
     */
    RecordStore db;
    private Object canvas;

    public void delDB() {
        try {
            db.deleteRecordStore("Squirrel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开数据库
     */
    private void openDB() {

        try {
            db = db.openRecordStore("Squirrel", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入档案
     *
     * @throws Exception
     */

    public void writeDB() throws Exception {
        this.delDB();
        this.openDB();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(engine.gameRank);

        byte[] data = baos.toByteArray();
        baos.close();
        dos.close();
        db.addRecord(data, 0, data.length);
        db.closeRecordStore();
    }

    /**
     * 读取存档
     *
     * @return int
     */

    public boolean readDB() {
        openDB();
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(db.getRecord(1));
            DataInputStream dis = new DataInputStream(bais);

            bais.close();
            dis.close();
            engine.gameRank = dis.readByte();

            db.closeRecordStore();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
