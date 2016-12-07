
package javarel.resources;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class JSONFileReader {

    public JSONFileReader() {

    }

    public JSONObject getJSONObject( String JSONFileUrl ) throws IOException, ParseException {

        JSONParser JSONParser = new JSONParser();

        FileReader fileReader = new FileReader( JSONFileUrl );
        Object object = JSONParser.parse( fileReader );
        JSONObject jsonObject = ( JSONObject ) object;

        return jsonObject;

    }

}