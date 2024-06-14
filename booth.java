import java.util.*;

// import javax.swing.text.AbstractDocument.BranchElement;

public class booth {

    static Scanner input = new Scanner(System.in);
    static int QR_input, BR_input;

    static String strBR;
    static StringBuffer BR;

    static StringBuffer BR_2s_comp;

    static String strQR;
    static String QR;

    static StringBuffer AC;
    static char Qn1 = '0', Qn;
    static int sc = 4;

    public static void main(String[] args) {

        System.out.print("Multiplicand = ");
        BR_input = input.nextInt();
        System.out.print("Multiplier = ");
        QR_input = input.nextInt();


        if(BR_input  > 7 || QR_input > 7){
            sc = 5;
        }
            
        strBR = Integer.toBinaryString(BR_input);
        strBR = String.format(sc == 5 ?  "%5s" : "%4s", strBR).replace(' ', '0');
            
        BR = new StringBuffer(strBR);

        BR_2s_comp = getTwosComplement(BR);

        strQR = Integer.toBinaryString(QR_input);
        QR = String.format(sc == 5 ? "%5s" :"%4s", strQR).replace(' ', '0');
        // QR = new StringBuffer(strQR);
        Qn = QR.charAt(QR.length() - 1);

        AC = new StringBuffer(sc == 5 ? "00000" : "0000");

        System.out.println(BR_2s_comp);

        // getNextOperation();

        for (int i = 0; i < sc; i++) {
            System.out.println("Pass: " + (i + 1));
            getNextOperation();
            System.out.println();
        }

        System.out.println(AC.append(QR));
        System.out.println(Integer.parseInt(AC.toString(), 2));
    }

    static void getNextOperation() {
        if ((Qn + "" + Qn1).equals("01")) {
            binaryAdder(true);
        } else if ((Qn + "" + Qn1).equals("10")) {
            binaryAdder(false);
        } else {
            arithmaticShiftRight();
        }

    }

    static void binaryAdder(boolean add) {

        boolean carry = false;
        String AC_instance = new StringBuilder(AC.toString()).reverse().toString();
        String BR_instance = add ? new StringBuilder(BR.toString()).reverse().toString() : new StringBuilder(BR_2s_comp.toString()).reverse().toString();
        int n = AC_instance.length() > BR_instance.length() ? AC_instance.length() : BR_instance.length();
        char[] result = new char[n];

        for (int i = 0; i < n; i++) {
            if (AC_instance.charAt(i) == BR_instance.charAt(i)) {
                if (AC_instance.charAt(i) == '1') {
                    if (carry) {
                        result[i] = '1';
                        carry = true;
                    } else {
                        result[i] = '0';
                        carry = true;
                    }
                } else {
                    if (carry) {
                        result[i] = '1';
                        carry = false;
                    } else {
                        result[i] = '0';
                    }
                }
            } else {
                if (carry) {
                    result[i] = '0';
                    carry = true;
                } else {
                    result[i] = '1';
                }
            }
        }

        AC = new StringBuffer(new String(result)).reverse();
        arithmaticShiftRight();
    }

    static void arithmaticShiftRight() {

        StringBuffer oldString = AC.append(QR);

        StringBuffer shiftedString = new StringBuffer(oldString);

        shiftedString.setCharAt(0, oldString.charAt(0));
        Qn1 = oldString.charAt(oldString.length() - 1);
        Qn = oldString.charAt(oldString.length() - 2);

        for (int i = 1; i < oldString.length(); i++) {
            shiftedString.setCharAt(i, oldString.charAt(i - 1));
        }

        shiftedString.setLength(oldString.length());
        System.out.println("oldString: " + oldString);
        System.out.println("shifted: " + shiftedString);

        QR = shiftedString.substring(sc ==5 ? 5 :4);
        AC = new StringBuffer(shiftedString.substring(0, sc == 5 ? 5 : 4));

        System.out.println("AC: " + AC);
        System.out.println("QR: " + QR);
    }

    static StringBuffer getTwosComplement(StringBuffer x) {
        int n = x.length();
        char[] twos_complement = new char[n];
        boolean flag = false;
        for (int i = n - 1; i >= 0; i--) {
            if (!flag) {
                if (x.charAt(i) == '1') {
                    twos_complement[i] = '1';
                    flag = true;
                } else {
                    twos_complement[i] = x.charAt(i);
                }
            } else {
                twos_complement[i] = (x.charAt(i) == '0') ? '1' : '0';
            }

        }
        return new StringBuffer(new String(twos_complement));
    }

}
