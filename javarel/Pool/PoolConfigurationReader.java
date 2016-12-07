
package javarel.Pool;

import org.json.simple.JSONObject;

import javarel.resources.JSONFileReader;


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