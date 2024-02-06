package Forbes;

import java.util.Random;
import java.util.Scanner;

public class Valec {
    private int hod;

    public int otocValec() {
        Random random = new Random();
        hod = random.nextInt(6) + 1;
        return hod;
    }

    public int getHod() {
        return hod;
    }

}
