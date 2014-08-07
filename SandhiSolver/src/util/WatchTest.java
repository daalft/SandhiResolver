package util;

import java.io.File;
import de.general.log.PrintLogger;

public class WatchTest {

	public void run () {
		new Thread(new Runnable() {
		    public void run()
		    {
		    	try {
					new FileWatcher().watch(new File("./data/").toPath(), new PrintLogger());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }}).start();;  
	}
	
	public static void main(String[] args) {
		new WatchTest().run();
	}
}
