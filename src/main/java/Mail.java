/**
 * Created by niuxianghui on 17/3/19.
 */
import com.github.rjeschke.txtmark.Processor;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mail {
    //private static Logger logger = LoggerFactory.getLogger(Mail.class);

    public static boolean sendEmail(String title, String receive, String cc, String body, String user, String passwd, String domin) {

        Boolean flag = false;
        try {
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP1); // your server version
            ExchangeCredentials credentials = new WebCredentials(user,passwd,domin); // change them to your email username, password, email domain
            service.setCredentials(credentials);
            service.setUrl(new URI("https://ex2010.elong.cn/EWS/Exchange.asmx"));
            EmailMessage msg = new EmailMessage(service);
            msg.setSubject(title); //email subject
            msg.setBody(MessageBody.getMessageBodyFromText(body)); //email body
            msg.getToRecipients().add(receive); //email receiver
            if (!cc.equals("")){
                msg.getCcRecipients().add(cc);
            }
            msg.send(); //send email
            System.out.println("邮件发送成功！");
            msg.save(new FolderId(WellKnownFolderName.Drafts));
            System.out.println("邮件保存成功！");
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;

    }
    public static void main(String args[]){
        MyProperties myProps = new MyProperties();
//        if (myProps.getBodyString() != null){
//            String htmlString =  Processor.process(myProps.getBodyString());
//            myProps.setHtmlString(htmlString);
//        }
        if (myProps.getTitle() == null || myProps.getTitle().equals("DailyReport")){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            Date now = new Date();
            String title = simpleDateFormat.format(now) + "日报";
            sendEmail(title, myProps.getReceive(), myProps.getCc(), myProps.getBodyString(), myProps.getUser(), myProps.getPasswd(), myProps.getDomin());
        }else {
            sendEmail(myProps.getTitle(), myProps.getReceive(), myProps.getCc(), myProps.getBodyString(), myProps.getUser(), myProps.getPasswd(), myProps.getDomin());
        }
    }


}
