import java.io.*;
import java.util.Properties;

/**
 * Created by niuxianghui on 17/3/20.
 */
public class MyProperties {
    private String user;
    private String passwd;
    private String domin;
    private String title;
    private String bodyString;
    private String bodyFile;
    private String receive;
    private String cc;
    private String htmlString;

    @SuppressWarnings("Since15")
    public MyProperties() {
        try{
            Properties props = new Properties();
            InputStream inputStream = new BufferedInputStream(new FileInputStream("key.properties"));
            props.load(inputStream);
            for (String it : props.stringPropertyNames()) {
                switch (it){
                    case "user": this.user = props.getProperty(it); break;
                    case "passwd" : this.passwd = props.getProperty(it); break;
                    case "domin": this.domin = props.getProperty(it); break;
                    case "title": this.title = props.getProperty(it);break;
                    case "bodyFile": this.bodyFile = props.getProperty(it); break;
                    case "receive": this.receive = props.getProperty(it); break;
                    case "cc" : this.cc = props.getProperty(it);break;
                    default:System.out.println("switch default."); break;
                }
            }
            inputStream.close();
            BufferedReader reader = null;
            if (this.bodyFile != null) {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.bodyFile)));
                String body = "";
                String buffer;
                while ((buffer = reader.readLine()) != null) {
                    body += buffer;
                }
                this.bodyString = body;
            }
            if (reader != null){
                reader.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDomin() {
        return domin;
    }

    public void setDomin(String domin) {
        this.domin = domin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    public String getBodyFile() {
        return bodyFile;
    }

    public void setBodyFile(String bodyFile) {
        this.bodyFile = bodyFile;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getHtmlString() {
        return htmlString;
    }

    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }
}
