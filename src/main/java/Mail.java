/**
 * Created by niuxianghui on 17/3/19.
 */
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import java.net.URI;
public class Mail {
    //private static Logger logger = LoggerFactory.getLogger(Mail.class);

    public static boolean sendEmail(String title, String receve, String cc, String body) {

        Boolean flag = false;
        try {
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP1); // your server version
            ExchangeCredentials credentials = new WebCredentials("","",""); // change them to your email username, password, email domain
            service.setCredentials(credentials);
            service.setUrl(new URI("https://ex2010.elong.cn/EWS/Exchange.asmx"));
            EmailMessage msg = new EmailMessage(service);
            msg.setSubject(title); //email subject
            msg.setBody(MessageBody.getMessageBodyFromText(body)); //email body
            msg.getToRecipients().add(receve); //email receiver
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
        if (args.length == 4){
            sendEmail(args[0], args[1], args[2], args[3]);
        }else {
            System.out.println("参数错误! ");
        }
    }


}
