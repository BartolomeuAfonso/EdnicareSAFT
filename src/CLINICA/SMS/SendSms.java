/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.SMS;

/**
 *
 * @author Probook
 */
import java.net.*;
import java.io.*;

public class SendSms {

    static public void main(String[] args) {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("ednicare", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("mindvision", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("Bom dia", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=44123123123";

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
