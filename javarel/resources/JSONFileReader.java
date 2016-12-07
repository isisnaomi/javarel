<<<<<<< HEAD
package Resources;
=======
package javarel.resources;
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b

/* import org.json.simple.JSONArray; */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader {

    public JSONFileReader() {

    }

    public JSONObject getJSONObject( String JSONFileUrl ) {

        JSONParser JSONParser = new JSONParser();

        try {

            FileReader fileReader = new FileReader( JSONFileUrl );
            Object object = JSONParser.parse( fileReader );
            JSONObject jsonObject = ( JSONObject ) object;

            return jsonObject;

        } catch ( IOException | ParseException exception ) {

            exception.printStackTrace();
            return new JSONObject();

        }

    }

}