import java.util.*;

public class booth {
    static int n;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char Qn, Qn1, tempCh = '0', sign;

        System.out.print("Multiplicand = "); // inputs
        int BR = input.nextInt();
        System.out.print("Multiplier = ");
        int QR = input.nextInt();

        String binQR = Integer.toBinaryString(Math.abs(QR));
        String binBR = Integer.toBinaryString(Math.abs(BR));

        n = binBR.length() > binQR.length() ? binBR.length() : binQR.length();

        int upperBound = (int) (Math.pow(2, n - 1) - 1);
        int lowerBound = (int) -(Math.pow(2, n - 1) - 1);

        if (BR < lowerBound || BR >= upperBound ||
                QR < lowerBound || QR > upperBound)
            n++;

        StringBuffer strbufQR = new StringBuffer(binQR);
        StringBuffer strbufBR = new StringBuffer(binBR);
        StringBuffer strbufAC = new StringBuffer("");

        String strQR = setBinaryNum(strbufQR); // user-defined function
        String strBR = setBinaryNum(strbufBR); // user-defined function
        String strAC = setBinaryNum(strbufAC); // user-defined function

        // if negative
        if (QR < 0)
            strQR = twoscomplement(strQR);
        if (BR < 0)
            strBR = twoscomplement(strBR);

        String strCompBR = twoscomplement(strBR);

        String ACQR = strAC.concat(strQR); // works right
        StringBuffer strbufACQR = new StringBuffer(strAC.concat(strQR));

        System.out.println("BR: " + strBR); // output prep.
        System.out.println("QR: " + strQR);
        System.out.println("BR': " + strCompBR);
        System.out.println("n: " + n);
        System.out.println("  SC    AC      QR");

        for (int SC = n; SC > 0; SC--) {
            // logic of booth.
            System.out.println("  " + SC + "   " + ACQR.substring(0, n) + "   " + ACQR.substring(n));

            Qn = ACQR.charAt(2 * n - 1);
            Qn1 = tempCh;

            if (Qn == '0' && Qn1 == '1') {
                strAC = (strbufACQR.substring(0, n)).toString();
                strQR = (strbufACQR.substring(n)).toString();

                strAC = binaryAdd(strAC, strBR);

                ACQR = strAC.concat(strQR);
            }

            else if (Qn == '1' && Qn1 == '0') {
                strAC = (strbufACQR.substring(0, n)).toString();
                strQR = (strbufACQR.substring(n)).toString();

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

        System.out.println("  " + "0" + "   " + ACQR.substring(0, n) + "   " + ACQR.substring(n));
        System.out.println("Result: " + ACQR);
        System.out.println("Decimal: " + (int) binarytoDecimal(ACQR));

        input.close();
    }

    static String setBinaryNum(StringBuffer strbuf) {

        for (; strbuf.length() < n;) {
            strbuf.insert(0, "0");
        }

        return strbuf.toString();

    }

    static String ashr(String str) {
        StringBuffer strbuf = new StringBuffer(str);

        for (int i = 0; i < n - 1; i++) {
            strbuf.append(strbuf.charAt(0));
            strbuf.deleteCharAt(0);
        }
        return strbuf.toString();
    }

    static StringBuffer ashr(StringBuffer strbuf) {

        for (int i = 0; i < 2 * n - 1; i++) {
            strbuf.append(strbuf.charAt(0));
            strbuf.deleteCharAt(0);
        }

        return strbuf;
    }

    static String binaryAdd(String A, String B) {
        int c, carry = 0;
        StringBuffer ans = new StringBuffer(""), strbufA = new StringBuffer(A), strbufB = new StringBuffer(B);

        for (int i = n; i > 0; i--) {

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

        }
        return ans.toString();
    }

    static String twoscomplement(String A) {
        StringBuffer ans = new StringBuffer("");
        boolean flag = true;

        for (int i = n; i > 0; i--) {

            StringBuffer tempA = new StringBuffer(A.substring(i - 1));
            tempA.setLength(1);
            int digit = Integer.parseInt(tempA.toString());

            if (flag) {
                ans.insert(0, tempA.toString());
            }

            else {
                if (digit == 0)
                    ans.insert(0, "1");
                else
                    ans.insert(0, "0");
            }

            if (flag) {
                if (digit == 1)
                    flag = !flag;
            }

        }
        return ans.toString();
    }

    static double binarytoDecimal(String A) {
        double radix = 0, ans = 0;

        for (int i = 2 * n; i > 0; i--) {

            StringBuffer tempA = new StringBuffer(A.substring(i - 1)); // one digit at a time in sequence from A
            tempA.setLength(1);

            ans = ans + (Math.pow(2, radix) * Integer.parseInt(tempA.toString()));
            radix++;
        }
        return ans;

    }
}