package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private Properties props;

    public Props(){
        try (InputStream input = new FileInputStream("src/test/resources/credentials.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            props = prop;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getPassword(){
        return props.getProperty("password");
    }

    public String getUsername(){
        return props.getProperty("username");
    }

    public DriverSelector getDriverSelector(){
        String mode = props.getProperty("mode");
        return DriverSelector.getByName(mode);
    }

}
