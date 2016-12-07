package javarel.Log;

import javarel.Log.exceptions.ClassNotFoundException;

/**
 * Created by shado on 06/12/2016.
 */
public interface LoggerMessenger {

    public void encode(Class clss, int level, String msg ) throws ClassNotFoundException;
}
