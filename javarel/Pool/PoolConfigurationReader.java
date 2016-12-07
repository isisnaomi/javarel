
package javarel.Pool;

import org.json.simple.JSONObject;
import java.io.File;

import javarel.resources.JSONFileReader;


class PoolConfigurationReader {

    private String configurationFilePath = this.getConfigurationFilePath();

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

    private String getConfigurationFilePath() {

        String filePath = new File("").getAbsolutePath();
        String configurationFilePath = filePath + "../../config/Pool_config.json";

        return configurationFilePath;

    }

}