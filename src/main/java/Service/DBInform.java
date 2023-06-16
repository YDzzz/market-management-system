package Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DBInform {
    static String url = null;
    static String password = null;
    static String userName = null;

    static {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        url = properties.getProperty("url");
        password = properties.getProperty("password");
        userName = properties.getProperty("userName");
    }

    public static String getUrl() {
        return url;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUserName() {
        return userName;
    }
}
