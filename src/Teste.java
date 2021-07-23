
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Teste {

    public final static SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    public final static String closeTime = "19:00";

    public static void main(String[] args) throws ParseException {
//        Scanner reader = new Scanner(System.in);
//        System.out.println("What time is it? ");
//        String presentTime = reader.next();
//        boolean answer = checkIfClosed(presentTime);
//        String textRepresentation = null;
//        if (answer == true) {
//            textRepresentation = "Nope! We're closed!";
//        } else {
//            textRepresentation = "It's open!";
//        }
//        System.out.println(textRepresentation);
//        reader.close();
//        System.out.println("Ba");
//        isIntervalOfHours("19:05", "10:20");
//        isComparaHora("10:05", "05:20");
        // getHoraMaior("19:40");
        String ba = "";
        getHorAndMinute(ba);
        getHoraMaior("18:40");
    }
//
//    public static boolean checkIfClosed(String time) {
//        try {
//            Date present = parser.parse(time);
//            Date closed = parser.parse(closeTime);
//            if (present.after(closed)) {
//                return true;
//            }
//        } catch (ParseException e) {
//            // Invalid date was entered
//        }
//        return false;
//    }

    public static boolean isIntervalOfHours(String horaAbre, String horaFecha) {
        String horaAtual = new SimpleDateFormat("HH:mm").format(new Date().getTime());// Pega hora atual do Sistema
        System.out.println("Edna");
        Integer horarioFecha[] = getHorAndMinute(horaFecha);
        Integer horarioAbre[] = getHorAndMinute(horaAbre);

        if (horarioFecha[0] < horarioAbre[0]) {
            Integer horarioAtual[] = getHorAndMinute(horaAtual);

            if (horarioAtual[0] <= horarioFecha[0]) {
                if (horarioAtual[1] >= horarioFecha[1] && horarioAtual[0] >= horarioFecha[0]) {
                    System.out.println("Em dia");
                    return true;

                } else {
                    return false;
                }
            } else {
                System.out.println("Hora:");
                horaFecha = "23:59";
            }
        }
//        System.out.println("Chegou");
//        try {
//            System.out.println("Entrou");
//            if (isComparaHora(horaAtual, horaFecha) && !isComparaHora(horaAtual, horaAbre)) {
//                System.out.println("Validou");
//                return true;
//            } else {
//                return false;
//            }
//        } catch (ParseException ex) {
//            System.out.println("Horário Inválido...");
//            return false;
//        }
        return true;
    }

    public static boolean getHoraMaior(String horaFecha) {
        String horaAtual = new SimpleDateFormat("HH:mm").format(new Date().getTime());// Pega hora atual do Sistema
        Integer horarioFecha[] = getHorAndMinute(horaFecha);
        Integer horarioAtual[] = getHorAndMinute(horaAtual);

        if (horarioFecha[0] >= horarioAtual[0]) {

            System.out.println("Em dia");
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Não é permitido emitir factura com hora inferior que a hora da Ultima Factura, verifica a Hora do Computador do computador!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

//    public static Integer[] getHorAndMinute(String hora) {
//        int horario, minutos;
//
//        horario = Integer.parseInt(hora.substring(0, 2));
//        minutos = Integer.parseInt(hora.substring(3, 5));
//        System.out.println("Hora:" + horario);
//        System.out.println("Minuto:" + minutos);
//        return new Integer[]{horario, minutos};
//    }
    public static Integer[] getHorAndMinute(String hora) {
        int horario, minutos;
        if (hora.equals("")) {
            String horaAtual = new SimpleDateFormat("HH:mm").format(new Date().getTime());
            horario = Integer.parseInt(horaAtual.substring(0, 2));
            minutos = Integer.parseInt(horaAtual.substring(3, 5));
            System.out.println("Hora:" + horario);
            System.out.println("Minuto:" + minutos);

        } else {
            horario = Integer.parseInt(hora.substring(0, 2));
            minutos = Integer.parseInt(hora.substring(3, 5));
        }

        return new Integer[]{horario, minutos};
    }

    private static boolean isComparaHora(String horaAtual, String horaFecha) throws ParseException {
        boolean fechaCaixa = false;
        if (!new SimpleDateFormat("HH:mm").parse(horaFecha).before(new SimpleDateFormat("HH:mm").parse(horaAtual))) {
            System.out.println("Passou");
            fechaCaixa = true;
        }
        return fechaCaixa;
    }

}
