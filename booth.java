import java.util.*;

public class booth {

    // for positive numbers only
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char Qn, Qn1, tempCh = '0', sign;

        String strAC = "00000"; // AC

        System.out.print("Multiplicand = "); // inputs
        int BR = input.nextInt();
        System.out.print("Multiplier = ");
        int QR = input.nextInt();

        StringBuffer strbufQR = new StringBuffer(Integer.toBinaryString(Math.abs(QR)));
        StringBuffer strbufBR = new StringBuffer(Integer.toBinaryString(Math.abs(BR)));

        String strQR = setBinaryNum(strbufQR); // user-defined function
        String strBR = setBinaryNum(strbufBR); // user-defined function

        // if negative
        if (QR < 0)
            strQR = twoscomplement(strQR);
        if (BR < 0)
            strBR = twoscomplement(strBR);

        String strCompBR = twoscomplement(strBR);

        String ACQR = strAC.concat(strQR); // works right
        StringBuffer strbufACQR = new StringBuffer(strAC.concat(strQR));

        System.out.println("BR: " + strBR);
        System.out.println("QR: " + strQR);
        System.out.println("  SC    AC      QR");

        for (int SC = 5; SC > 0; SC--) {
            // logic of booth.
            System.out.println("  " + SC + "   " + strAC + "   " + strQR);

            Qn = ACQR.charAt(9);
            Qn1 = tempCh;

            if (Qn == '0' && Qn1 == '1') {
                strAC = (strbufACQR.substring(0, 5)).toString();
                strQR = (strbufACQR.substring(5)).toString();

                strAC = binaryAdd(strAC, strBR);

                ACQR = strAC.concat(strQR);
            }

            else if (Qn == '1' && Qn1 == '0') {
                strAC = (strbufACQR.substring(0, 5)).toString();
                strQR = (strbufACQR.substring(5)).toString();

                strAC = binaryAdd(strAC, strCompBR);

                ACQR = strAC.concat(strQR);
            }

            // ashr
            sign = ACQR.charAt(0);
            strbufACQR = new StringBuffer(ACQR);
            strbufACQR = ashr(strbufACQR);
            strbufACQR.deleteCharAt(0);
            strbufACQR.insert(0, sign);
            ACQR = strbufACQR.toString(); // ACQR updated.

            tempCh = Qn;

        }

        System.out.println("  " + "0" + "   " + ACQR.substring(0, 5) + "   " + ACQR.substring(5, 10));
        System.out.println("Result: " + ACQR);
        input.close();
    }

    static String setBinaryNum(StringBuffer strbuf) {

        for (; strbuf.length() < 5;) {
            strbuf.insert(0, "0");
        }

        return strbuf.toString();

    }

    static String ashr(String str) {
        StringBuffer strbuf = new StringBuffer(str);

        for (int i = 0; i < 9; i++) {
            strbuf.append(strbuf.charAt(0));
            strbuf.deleteCharAt(0);
        }

        return strbuf.toString();
    }

    static StringBuffer ashr(StringBuffer strbuf) {

        for (int i = 0; i < 9; i++) {
            strbuf.append(strbuf.charAt(0));
            strbuf.deleteCharAt(0);
        }

        return strbuf;
    }

    static String binaryAdd(String A, String B) {
        int c, carry = 0;
        StringBuffer ans = new StringBuffer(""), strbufA = new StringBuffer(A), strbufB = new StringBuffer(B);

        for (int i = 5; i > 0; i--) {

            StringBuffer tempA = new StringBuffer(strbufA.substring(i - 1)); // one digit at a time in sequence from A
            tempA.setLength(1);

            StringBuffer tempB = new StringBuffer(strbufB.substring(i - 1)); // corresponding one digit at a time in
                                                                             // sequence from B
            tempB.setLength(1);

            c = carry + Integer.parseInt(tempA.toString()) + Integer.parseInt(tempB.toString()); // binary addition
            if (c == 0) {
                carry = 0;
                ans.insert(0, 0);
            } else if (c == 1) {
                carry = 0;
                ans.insert(0, 1);
            } else if (c == 2) {
                carry = 1;
                ans.insert(0, 0);
            } else if (c == 3) {
                carry = 1;
                ans.insert(0, 1);
            }
            // throw error if its not 0,1,2 or 3,
        }
        return ans.toString();
    }

    static String twoscomplement(String A) {
        StringBuffer ans = new StringBuffer("");
        int flag = 0;

        for (int i = 5; i > 0; i--) {

            StringBuffer tempA = new StringBuffer(A.substring(i - 1));
            tempA.setLength(1);
            int digit = Integer.parseInt(tempA.toString());

            if (flag == 0) {
                ans.insert(0, tempA.toString());
            }

            else {
                if (digit == 0)
                    ans.insert(0, "1");
                else
                    ans.insert(0, "0");
            }

            if (digit == 1)
                flag++;

        }
        return ans.toString();
    }

}