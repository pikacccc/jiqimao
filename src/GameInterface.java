public abstract class GameInterface {

  public final static byte STATUS_STOP = 0;
  public final static byte STATUS_MOVE = 1;

//  public final static byte STATUS_ICESTOP = 8;

  public final static byte DIR_UP = 0;
  public final static byte DIR_RIGHT = 1;
  public final static byte DIR_DOWN = 2;
  public final static byte DIR_LEFT = 3;

  public byte curStatus;
  public byte dir=90;
  public int x;
  public int y;
  public int w;
  public int h;

  public short HP_MAX;
  public short MP_MAX;

  public short attack;
  public short defend;

  public boolean isMir;
  public byte speed;

  public int index;
  public byte[] motion;
  public int rx, ry;
  public abstract void move();

  public abstract void paint();

}
