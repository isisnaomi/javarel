
package javarel.Pool;

import org.json.simple.JSONObject;
<<<<<<< HEAD
import resources.JSONFileReader;
=======
import javarel.resources.JSONFileReader;
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b

class PoolConfigurationReader {

    private String configurationFilePath = "/Users/codeams/Documents/Code/Java/database-pool/configuration.json";

    private JSONFileReader jsonFileReader = new JSONFileReader();

    private JSONObject configurationObject;


    PoolConfigurationReader() {

        this.configurationObject = this.jsonFileReader.getJSONObject( this.configurationFilePath );

    }

    int getBlockSize() {

        int blockSize = Math.toIntExact( (Long) this.configurationObject.get( "blockSize" ) );
        return blockSize;

    }

    int getMaxPoolSize() {

        int blockSize = this.getBlockSize();
        int maxPoolSize = Math.toIntExact( (Long) this.configurationObject.get( "maxPoolSize" ) );

        boolean isMaxPoolSizeMultipleOfBlockSize = ( maxPoolSize % blockSize ) != 0;

        if ( ! isMaxPoolSizeMultipleOfBlockSize ) {
            maxPoolSize = maxPoolSize - ( maxPoolSize % blockSize );
        }

        return maxPoolSize;

    }

}