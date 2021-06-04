package json_xml_iva;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthException;
import net.oauth.signature.OAuthSignatureMethod;
import static org.codehaus.groovy.runtime.EncodingGroovyMethods.decodeBase64;

public class RSA extends OAuthSignatureMethod {

    protected void initialize(String name, OAuthAccessor accessor)
            throws OAuthException {
        super.initialize(name, accessor);
    }

    protected String getSignature(String baseString) throws OAuthException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected boolean isValid(String signature, String baseString) throws OAuthException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static PrivateKey getPrivateKeyFromPem(String privateKeyObject)
            throws GeneralSecurityException {
        KeyFactory fac = KeyFactory.getInstance("RSA");
        EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(decodeBase64(privateKeyObject));
        return fac.generatePrivate(privKeySpec);
    }

    public static String getHash(String Message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        PrivateKey priv = null;

        System.out.println("RSA >>>>> " + Message);

        try {
//            priv = getPrivateKeyFromPem(ChaveIVA.PRIVATE_KEY);
            priv = getPrivateKeyFromPem("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKxeykd+uXOw6VdV\n"
                    + "uPZTV4NVtqEIU9pvFNL1tnJIBBQNytMvxhBVUWBtWHhz6OI/k8+hEFXh5K6Ica/k\n"
                    + "/pLH3wvOqZWkk/4xp/UPZcDLuna5ljsSriG+dFDS4zfuuzxGeLUzkNt8f3Ilvg1n\n"
                    + "HvcxsjH2yanrZ8lqPLokKLojG1QVAgMBAAECgYAKfSop95gpZhaM7fZ3ZtLodwrJ\n"
                    + "+cvAewn1XGZhxegZoE07VKjkzYxKJd1AVy1QtzWzaMWptDlKNZtVbMXhALCd2bU0\n"
                    + "5USQIEAEZ2BYmyrfnZVwSlSdYihh98gWgvXpAK+PgMQ5Kt9O973X+O5Gycw5AApg\n"
                    + "MKjPBISx5dSJH5lzcQJBAN7sdzNXz2AexmIJXmZaqD+Jc0pbCW77GxH7vO17XrBl\n"
                    + "kq0CRbVkX1N2rOo/eCVEqbBVSxN7TTkOkLj5WcXHbF8CQQDF8hpukuG8NvlO0j44\n"
                    + "/IO6LMGihXC33Zler503ZqPnLZHxZBgEszhe79Yq0aYSp2H/4f8hS8SEIrV0BAym\n"
                    + "FdQLAkEAh0YlG2HotGJYbftTbncK6+tjq7ZMpiz4g1QolE+t6QrqE9RyKp2q/wHf\n"
                    + "8hHrvWnKMve/JCZJeRsulSCy6kKWrwJAW2oVnt8idMOGm0opBjRHuWEWA7JQfflO\n"
                    + "7RV5K66xHwBCmBI7rV//NojYhWnQomi+r2d1ZbESDlvhJLUCCmmmRQJBAMjHo09I\n"
                    + "VwdfJ3x60n9bGGX5ZExExoiORRulT6fqlHHMmQNBbtQWcGvJhWlQ0C0PuNp0aJb5\n"
                    + "usBsq67FQC8lsE0=");

            System.out.println("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKxeykd+uXOw6VdV\n"
                    + "uPZTV4NVtqEIU9pvFNL1tnJIBBQNytMvxhBVUWBtWHhz6OI/k8+hEFXh5K6Ica/k\n"
                    + "/pLH3wvOqZWkk/4xp/UPZcDLuna5ljsSriG+dFDS4zfuuzxGeLUzkNt8f3Ilvg1n\n"
                    + "HvcxsjH2yanrZ8lqPLokKLojG1QVAgMBAAECgYAKfSop95gpZhaM7fZ3ZtLodwrJ\n"
                    + "+cvAewn1XGZhxegZoE07VKjkzYxKJd1AVy1QtzWzaMWptDlKNZtVbMXhALCd2bU0\n"
                    + "5USQIEAEZ2BYmyrfnZVwSlSdYihh98gWgvXpAK+PgMQ5Kt9O973X+O5Gycw5AApg\n"
                    + "MKjPBISx5dSJH5lzcQJBAN7sdzNXz2AexmIJXmZaqD+Jc0pbCW77GxH7vO17XrBl\n"
                    + "kq0CRbVkX1N2rOo/eCVEqbBVSxN7TTkOkLj5WcXHbF8CQQDF8hpukuG8NvlO0j44\n"
                    + "/IO6LMGihXC33Zler503ZqPnLZHxZBgEszhe79Yq0aYSp2H/4f8hS8SEIrV0BAym\n"
                    + "FdQLAkEAh0YlG2HotGJYbftTbncK6+tjq7ZMpiz4g1QolE+t6QrqE9RyKp2q/wHf\n"
                    + "8hHrvWnKMve/JCZJeRsulSCy6kKWrwJAW2oVnt8idMOGm0opBjRHuWEWA7JQfflO\n"
                    + "7RV5K66xHwBCmBI7rV//NojYhWnQomi+r2d1ZbESDlvhJLUCCmmmRQJBAMjHo09I\n"
                    + "VwdfJ3x60n9bGGX5ZExExoiORRulT6fqlHHMmQNBbtQWcGvJhWlQ0C0PuNp0aJb5\n"
                    + "usBsq67FQC8lsE0=");
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Prepare signature.
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initSign(priv);
        sign.update(Message.getBytes());
        //Sign the data with private key.
        byte[] realSig = sign.sign();
        //encode Signature
        String encodedSig = Base64.getEncoder().encodeToString(realSig);
        return encodedSig;
    }

    private static String getData(String data) {

        String[] vet = getCompData(data);
        return vet[0];
    }

    private static String getDataTime(String data) {

//        String datav = data.substring(0, 19);
        String[] vet = getCompData(data);
        return vet[0] + "T" + getHora(vet[1]);
    }

    private static String getHora(String hora) {

        if (!hora.isEmpty()) {

            String part[] = hora.trim().split(":");

            if (part.length > 2) {
                String hh = part[0];
                String mm = part[1];
                String ss = part[2];

                int hh_aux = Integer.parseInt(hh);
                int mm_aux = Integer.parseInt(mm);
                int ss_aux = Integer.parseInt(ss);

                if (hh_aux < 10) {
                    hh = "0" + hh_aux;
                }
                if (mm_aux < 10) {
                    mm = "0" + mm_aux;
                }
                if (ss_aux < 10) {
                    ss = "0" + ss_aux;
                }

                hora = hh + ":" + mm + ":" + ss;

                return hora;
            }
        }

        return hora;
    }

    private static String[] getCompData(String data) {

        String[] vet = data.split(" ");
        return vet;
    }

    public static String getTotal(String total) {

        if (!total.trim().isEmpty()) {

            int index = total.indexOf(".");

            String partDecimal = total.substring(index + 1);

            double totalFactura = Double.parseDouble(total);

            int part_inteira = (int) totalFactura;
//            double part_decimal = (totalFactura - part_inteira);

            int part_decimal = Integer.parseInt(partDecimal);
            if (part_decimal < 10) {

                String aux = String.valueOf(part_decimal);

//                String aux1 = aux.substring(2);
//
////                if (parts.length > 1) {
////
////                    String aux1 = parts[1];
//                if (aux1.length() > 2) {
//
//                    aux = aux1.substring(0, 2);
//                } else {
//                    aux = aux1;
//                }
                //  }
                if (aux.length() < 2) {

                    total = part_inteira + "." + aux + "0";

                    return total;
                }
            }

        }
        return total;
    }

    public static String executeAlgRSA(String data, String referenciaFactura, String totalFactura, String hashAnterior) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        String dataRegisto = getData(data);
        String dataTime = getDataTime(data);

        referenciaFactura = OrganizarRefFactura.saft(referenciaFactura);
        totalFactura = getTotal(totalFactura);

        String input = null;
        if (hashAnterior != null) {
            input = dataRegisto + ";" + dataTime + ";" + referenciaFactura + ";" + totalFactura + ";" + hashAnterior;
        } else {
            input = dataRegisto + ";" + dataTime + ";" + referenciaFactura + ";" + totalFactura;
        }

        System.out.println("INPUT >>>>> " + input);

        return getHash(input);

    }

    public static String executeAlgRSA1(String data, String hora, String referenciaFactura, String totalFactura, String hashAnterior) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // totalFactura = getTotal(totalFactura);
        String input = null;
        if (hashAnterior != null) {
            input = data + ";" + data + "T" + hora + ";" + referenciaFactura + ";" + totalFactura + ";" + hashAnterior;
        } else {
            input = data + ";" + data + "T" + hora + ";" + referenciaFactura + ";" + totalFactura;
        }

        System.out.println(input);

        return getHash(input);

    }

    public static String getValorCaracterHash(String str) {
        return "" + str.charAt(0) + str.charAt(10) + str.charAt(20) + str.charAt(30);
    }

    public static void main(String[] args) throws GeneralSecurityException, OAuthException {
        //data da Factura +;+data e hora da factura separados po T+;+Numero da factura+;+Total da factura;Hash do documento anterior
        String hash = getHash("iY0pB9D/xHZ12Y31FZGTZiAv6Zl137/VpMGf+BUk/FDKW0AWgsPLXV9mEfdOcNAIY8eYdLeBR8/VakU8dVOwVx8T1XZu6VW5XYPQ9NGJmwPM+sUgdmjPGVWAvAPYhAFUP512vct/HRFTdqevcAQjl+UeoG52KXqVEcWv7ZbvObE=");
        //INPUT >>>>> 2020-09-24;2020-09-24T09:22:34;PP Z002020/2;0.00;cRnFDSoF1Bweg8r8FpVvafw8J4Ll+MC3ZtuR6GQyc5ApKfIgpcGn/QfCxS8xIytv7aDhYhSgUP5kDzulHUI5532RyCqE39EnKWplQgYywO7sjptZC2S31f2LYy94QMbSKKUXRyrsbydXGzHNwYYSOZIrjG7DJ1ptRaYlJU/6mtQ=
        System.out.println(hash);

    }

}
