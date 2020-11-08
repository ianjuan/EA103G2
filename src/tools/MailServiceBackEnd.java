package tools;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmployeeVO;

public class MailServiceBackEnd extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, EmployeeVO empVO) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送.)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "ea103g2@gmail.com";
	     final String myGmail_password = "G2ea103!!!";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress("\"I-ZU "+MimeUtility.encodeText("愛租")+"\" <ea103g2@gmail.com>"));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
//		   message.setContent("貼上HTML","text/html; charset=utf-8");
		   message.setContent("<!DOCTYPE html>\r\n" + 
		   		"<html lang=\"en\">\r\n" + 
		   		"<body>\r\n" + 
		   		"      <div style=\"background:#fafafa;background-color:#fafafa;margin:0px auto;max-width:600px\">\r\n" + 
		   		"        \r\n" + 
		   		"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#fafafa;background-color:#fafafa;width:100%\">\r\n" + 
		   		"          <tbody>\r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td style=\"direction:ltr;font-size:0px;padding:15px 0;text-align:center\">\r\n" + 
		   		"                \r\n" + 
		   		"            \r\n" + 
		   		"      <div class=\"m_-148630044245761943mj-column-px-160\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\r\n" + 
		   		"        \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"vertical-align:top;padding:0;padding-top:10px\">\r\n" + 
		   		"              \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        \r\n" + 
		   		"            <tbody><tr>\r\n" + 
		   		"              <td align=\"center\" style=\"font-size:0px;padding:0;word-break:break-word\">\r\n" + 
		   		"                \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"width:160px\">\r\n" + 
		   		"              \r\n" + 
		   		"          \r\n" + 
		   		"      <img alt=\"\" height=\"auto\" src=\"https://i.imgur.com/Zg1K2YF.png\\\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px\" width=\"160\" class=\"CToWUd\">\r\n" + 
		   		"    \r\n" + 
		   		"        </a>\r\n" + 
		   		"      \r\n" + 
		   		"            </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"    \r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          \r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td align=\"center\" style=\"font-size:0px;padding:0;word-break:break-word\">\r\n" + 
		   		"                \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"width:1px\">\r\n" + 
		   		"              \r\n" + 
		   		"    \r\n" + 
		   		"            </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"    \r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          \r\n" + 
		   		"      </tbody></table>\r\n" + 
		   		"    \r\n" + 
		   		"            </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"    \r\n" + 
		   		"      </div>\r\n" + 
		   		"    \r\n" + 
		   		"          \r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          </tbody>\r\n" + 
		   		"        </table>\r\n" + 
		   		"        \r\n" + 
		   		"      </div>\r\n" + 
		   		"\r\n" + 
		   		"      <div style=\"margin:0px auto;max-width:600px\">\r\n" + 
		   		"        \r\n" + 
		   		"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%\">\r\n" + 
		   		"          <tbody>\r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td style=\"direction:ltr;font-size:0px;padding:0;text-align:center\">\r\n" + 
		   		"                \r\n" + 
		   		"            \r\n" + 
		   		"      <div class=\"m_-148630044245761943mj-column-per-100\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\r\n" + 
		   		"        \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"vertical-align:top;padding:0\">\r\n" + 
		   		"              \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        \r\n" + 
		   		"            <tbody><tr>\r\n" + 
		   		"              <td style=\"font-size:0px;padding:0;word-break:break-word\">\r\n" + 
		   		"                \r\n" + 
		   		"      <p style=\"border-top:solid 5px #ff6446;font-size:1;margin:0px auto;width:100%\">\r\n" + 
		   		"      </p>\r\n" + 
		   		"      \r\n" + 
		   		"      \r\n" + 
		   		"    \r\n" + 
		   		"    \r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          \r\n" + 
		   		"      </tbody></table>\r\n" + 
		   		"    \r\n" + 
		   		"            </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"    \r\n" + 
		   		"      </div>\r\n" + 
		   		"    \r\n" + 
		   		"          \r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          </tbody>\r\n" + 
		   		"        </table>\r\n" + 
		   		"        \r\n" + 
		   		"      </div>\r\n" + 
		   		"    \r\n" + 
		   		"      \r\n" + 
		   		"      \r\n" + 
		   		"        \r\n" + 
		   		"          \r\n" + 
		   		"    \r\n" + 
		   		"      \r\n" + 
		   		"      <div style=\"margin:0px auto;max-width:600px\">\r\n" + 
		   		"        \r\n" + 
		   		"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%\">\r\n" + 
		   		"          <tbody>\r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td style=\"direction:ltr;font-size:0px;padding:0 35px;padding-bottom:0;padding-top:30px;text-align:center\">\r\n" + 
		   		"                \r\n" + 
		   		"            \r\n" + 
		   		"      <div class=\"m_-148630044245761943mj-column-per-100\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\r\n" + 
		   		"        \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"vertical-align:top;padding:0\">\r\n" + 
		   		"              \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        \r\n" + 
		   		"            <tbody><tr>\r\n" + 
		   		"              <td align=\"left\" style=\"font-size:0px;padding:0;word-break:break-word\">\r\n" + 
		   		"                \r\n" + 
		   		"      <div style=\"font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:15px;line-height:25px;text-align:left;color:#333333\"><div>\r\n" + 
		   		"				<b style=\"color:#ff6446;font-size:18px\"><br>編號："+empVO.getEmp_no()+" "+empVO.getEmp_name()+" 你好</b><br><br>\r\n" + 
		   		"			</div>\r\n" + 
		   		"			<div>\r\n" + 
		   		"你的新密碼為：asdjasodj" + 
		   		"			</div></div>\r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"      </tbody></table>\r\n" + 
		   		"            </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>  \r\n" + 
		   		"      </div>\r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          </tbody>\r\n" + 
		   		"        </table>\r\n" + 
		   		"      </div>\r\n" + 
		   		"      <div style=\"margin:0px auto;max-width:600px\">\r\n" + 
		   		"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%\">\r\n" + 
		   		"          <tbody>\r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td style=\"direction:ltr;font-size:0px;padding:0 35px;padding-bottom:40px;padding-top:40px;text-align:center\">    \r\n" + 
		   		"      <div class=\"m_-148630044245761943mj-column-per-100\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"vertical-align:top;padding:0\">\r\n" + 
		   		"              \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        \r\n" + 
		   		"            <tbody><tr>\r\n" + 
		   		"              <td align=\"center\" style=\"font-size:0px;padding:0;padding-top:0px;word-break:break-word\">\r\n" + 
		   		"                \r\n" + 
		   		"      <table cellpadding=\"0\" cellspacing=\"0\" width=\"180\" border=\"0\" style=\"color:#000000;font-family:Ubuntu,Helvetica,Arial,sans-serif;font-size:13px;line-height:22px;table-layout:auto;width:180px;border:none\">\r\n" + 
		   		"        \r\n" + 
		   		"			<tbody><tr>\r\n" + 
		   		"				<td>\r\n" + 
		   		"					<div style=\"background:#ff6446;border-radius:50px;text-align:center;margin:0 auto\">\r\n" + 
		   		"						<a href=\"#;url=https%253A%252F%252F#%252F19425-6657&amp;m_id=GaO1P2\" style=\"width:180px;background:none repeat scroll 0 0 #ff6446;border-radius:50px;color:#fff;display:inline-block;font-size:16px;padding:8px 10px;text-align:center;text-decoration:underline;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=#/action/edm/click.php?edm_id%3DaGZadDhJWVk2ZnkxS2NvN2RR%26url%3Dhttps%25253A%25252F%25252F#%25252F19425-6657%26m_id%3DGaO1P2&amp;source=gmail&amp;ust=1603353788198000&amp;usg=AFQjCNG04iZsEqJjDFWH0Q2knlDUUEltlQ\">\r\n" + 
		   		"							點我回首頁\r\n" + 
		   		"						</a>\r\n" + 
		   		"					</div>\r\n" + 
		   		"				</td>\r\n" + 
		   		"			</tr>\r\n" + 
		   		"      </tbody></table>\r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>     \r\n" + 
		   		"      </tbody></table>\r\n" + 
		   		"            </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"      </div>   \r\n" + 
		   		"              </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          </tbody>\r\n" + 
		   		"        </table>\r\n" + 
		   		"        \r\n" + 
		   		"      </div>\r\n" + 
		   		"                   \r\n" + 
		   		"            <div style=\"margin:0px auto;max-width:600px\">\r\n" + 
		   		"        \r\n" + 
		   		"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%\">\r\n" + 
		   		"          <tbody>\r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td style=\"direction:ltr;font-size:0px;padding:0;text-align:center\">\r\n" + 
		   		"                \r\n" + 
		   		"            \r\n" + 
		   		"      <div class=\"m_-148630044245761943mj-column-per-100\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\r\n" + 
		   		"        \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"vertical-align:top;padding:0\">\r\n" + 
		   		"              \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        \r\n" + 
		   		"            <tbody><tr>\r\n" + 
		   		"              <td align=\"center\" style=\"font-size:0px;padding:0;word-break:break-word\">\r\n" + 
		   		"                \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"width:600px\">\r\n" + 
		   		"                      <a>\r\n" + 
		   		"                <div class=\"a6S\" dir=\"ltr\" style=\"opacity: 0.01; left: 578px; top: 946px;\"><div id=\":448\" class=\"T-I J-J5-Ji aQv T-I-ax7 L3 a5q\" title=\"下載\" role=\"button\" tabindex=\"0\" aria-label=\"下載附件「」\" data-tooltip-class=\"a1V\"><div class=\"aSK J-J5-Ji aYr\"></div></div></div>\r\n" + 
		   		"            </a>\r\n" + 
		   		"                  </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"                  </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          \r\n" + 
		   		"      </tbody></table>\r\n" + 
		   		"                </td>\r\n" + 
		   		"          </tr>\r\n" + 
		   		"        </tbody>\r\n" + 
		   		"      </table>\r\n" + 
		   		"          </div>\r\n" + 
		   		"                                </td>\r\n" + 
		   		"            </tr>\r\n" + 
		   		"          </tbody>\r\n" + 
		   		"        </table>\r\n" + 
		   		"      </div>\r\n" + 
		   		"      <div style=\"background:#f2f2f2;background-color:#f2f2f2;margin:0px auto;max-width:600px\">\r\n" + 
		   		"        \r\n" + 
		   		"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#f2f2f2;background-color:#f2f2f2;width:100%\">\r\n" + 
		   		"          <tbody>\r\n" + 
		   		"            <tr>\r\n" + 
		   		"              <td style=\"direction:ltr;font-size:0px;padding:0;text-align:center\">\r\n" + 
		   		"                \r\n" + 
		   		"            \r\n" + 
		   		"      <div class=\"m_-148630044245761943mj-column-per-100\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\r\n" + 
		   		"        \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        <tbody>\r\n" + 
		   		"          <tr>\r\n" + 
		   		"            <td style=\"vertical-align:top;padding:0 0 15px\">\r\n" + 
		   		"              \r\n" + 
		   		"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\r\n" + 
		   		"        \r\n" + 
		   		"            <tbody><tr>\r\n" + 
		   		"              <td align=\"center\" style=\"font-size:0px;padding:0;word-break:break-word\">\r\n" + 
		   		"                \r\n" +
		   		"</body>\r\n" + 
		   		"</html>","text/html; charset=utf-8");
		   
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
//		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功:"+to);
     }catch (MessagingException | UnsupportedEncodingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			  String to = req.getParameter("emp_mail");
		      String subject = "重置密碼通知";
		      EmployeeVO empVO = (EmployeeVO) req.getAttribute("empVO");
		      System.out.println("有跑到信箱這"+to);
		      System.out.println(empVO);
		      MailServiceBackEnd mailServiceBackEnd = new MailServiceBackEnd();
		      mailServiceBackEnd.sendMail(to, subject, empVO);
		      res.sendRedirect(req.getContextPath()+"/back-end/emp/wait.jsp");

		}

}
