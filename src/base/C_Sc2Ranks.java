package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;

public class C_Sc2Ranks {
    /**
     * general-purpose method to get the http response
     * 
     * @param urlString
     * @return
     * @throws IOException
     */
    public static String getData( String urlString ) throws IOException {
        URL url = new URL( urlString );
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader( new InputStreamReader( yc.getInputStream() ) );
        StringBuilder inputLine = new StringBuilder();
        String line;
        
        while ( true ) {
            if( ( line = in.readLine() ) != null ) {
                inputLine.append( line );
            }
            else {
                break;
            }
        }
        in.close();
        return inputLine.toString();
    }
    
    public static ContainerFactory getContainerFactory() {
        return new ContainerFactory() {
            @Override
            public List<Object> creatArrayContainer() {
                return new LinkedList<Object>();
            }

            @Override
            public Map<Object, Object> createObjectContainer() {
                return new LinkedHashMap<Object, Object>();
            }
        };
    }
}
