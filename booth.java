import java.util.*;

public class booth {

    // for positive numbers only
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int QR, BR;
        int Qn, Qn1, temp = 0;

        String strAC = "00000";
        System.out.print("Multiplicand = ");
        BR = input.nextInt();
        System.out.print("Multiplier = ");
        QR = input.nextInt();

        String rawStrQR = Integer.toBinaryString(Math.abs(QR));
        StringBuffer strbufQR = new StringBuffer(rawStrQR);

        String strQR = setBinaryNum(strbufQR);

        String ACQR = strAC.concat(strQR);
        System.out.println(ACQR);
        /////////////

        for (int SC = 5; SC > 0; SC--) {
            System.out.println("LOOP " + SC);
            Qn1 = temp;
            Qn = Character.getNumericValue(ACQR.charAt(9));
            temp = Qn;

            System.out.println(Qn);
            System.out.println(Qn1);
            System.out.println(temp);

            if (Qn != Qn1) {

                if (Qn == 1) {
                    int temp2 = (Integer.parseInt(ACQR.substring(0, 4), 2) - BR);

                    System.out.println("SUB LOGIC strAC"+ temp2);
                }

                else {
                    strAC = Integer.toBinaryString(Integer.parseInt(ACQR.substring(0, 4), 2) + BR);
                    System.out.println("ADD LOGIC str AC : "+ ACQR);
                }
            }

            System.out.println("ashr");

            ACQR = strAC.concat(strQR);
            System.out.println("ACQRold : "+ACQR);
            StringBuffer strbufACQR = new StringBuffer(ACQR);
            String sign = strbufACQR.substring(0, 1); // ashr sign preservation

            strbufACQR = ashr(ACQR); // ACQR updated

            strbufACQR.deleteCharAt(0); // sign updated
            strbufACQR.insert(0, sign);

            ACQR = strbufACQR.toString(); // ACQR updated.
            System.out.println("ACQR : "+ACQR);

        }

        System.out.println("answer = " + ACQR);
        // System.out.println(Integer.parseInt(ACQR,2));
        input.close();
    }

    static String setBinaryNum(StringBuffer strbuf) {

        for (; strbuf.length() < 5;) {
            strbuf.insert(0, "0");
        }

        return strbuf.toString();

    }

    static StringBuffer ashr(String str) {
        StringBuffer strbuf = new StringBuffer(str);

        for (int i = 0; i < 9; i++) {
            char c = strbuf.charAt(0);
            strbuf.append(c);
            strbuf.deleteCharAt(0);
        }

        return strbuf;
    }
}