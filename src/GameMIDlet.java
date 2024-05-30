import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GameMIDlet
    extends MIDlet
    implements Runnable {
  static short sleepTime = 150;
  static GameMIDlet instance;
  MyGameCanvas displayable = new MyGameCanvas();
  Thread thread;

  public GameMIDlet() {
    instance = this;
    thread = new Thread(this);
    thread.start();
  }

  public void startApp() {
    Display.getDisplay(this).setCurrent(displayable);
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
  }

  public static void quitApp() {
    instance.destroyApp(true);
    instance.notifyDestroyed();
    instance = null;
  }


  public void run() {
    while (true) {
      try {
        thread.sleep(sleepTime);
        displayable.repaint();
      }
      catch (Exception e) {}
    }
  }

}
