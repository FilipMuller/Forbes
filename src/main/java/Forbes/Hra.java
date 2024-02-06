package Forbes;

import java.util.Random;
import java.util.Scanner;

public class Hra {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Vítejte u hazardního automatu se pěti válci!");
        System.out.print("Zadejte počáteční množství peněz: ");
        int pocatecniMnozstviPenez = scanner.nextInt();
        int aktualniMnozstviPenez = pocatecniMnozstviPenez;

        while (true) {
            System.out.println("Aktuální množství peněz: " + aktualniMnozstviPenez);

            System.out.print("Zadejte výši sázky (nebo libovolný znak pro ukončení): ");
            String userInput = scanner.next();

            int sazka;
            try {
                sazka = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Hra ukončena. Děkujeme za hraní!");
                break;
            }

            if (sazka <= 0 || sazka > aktualniMnozstviPenez) {
                System.out.println("Nemáte dostatek peněz na tuto sázku. Zadejte nižší částku.");
                continue;
            }

            Valec[] valce = new Valec[5];

            for (int i = 0; i < valce.length; i++) {
                valce[i] = new Valec();
            }

            int[] hodyValcu = new int[valce.length];
            for (int i = 0; i < valce.length; i++) {
                hodyValcu[i] = valce[i].otocValec();
            }

            aktualniMnozstviPenez = zobrazVysledek(hodyValcu, sazka, aktualniMnozstviPenez);
        }

        System.out.println("Zbývající zůstatek: " + aktualniMnozstviPenez);
    }

    private static int zobrazVysledek(int[] hodyValcu, int sazka, int aktualniMnozstviPenez) {
        System.out.print("Výsledky válců: ");
        for (int hod : hodyValcu) {
            System.out.print(hod + " ");
        }
        System.out.println();

        int stejnaCislaVedleSebe = 1;
        int maxStejnychCisel = 1;
        for (int i = 1; i < hodyValcu.length; i++) {
            if (hodyValcu[i] == hodyValcu[i - 1]) {
                stejnaCislaVedleSebe++;
                maxStejnychCisel = Math.max(maxStejnychCisel, stejnaCislaVedleSebe);
            } else {
                stejnaCislaVedleSebe = 1;
            }
        }

        int vyhra = 0;
        if (maxStejnychCisel == 2) {
            vyhra = sazka * 2;
            System.out.println("Gratulujeme, vyhráli jste! Vyhra x2: " + vyhra);
        } else if (maxStejnychCisel == 3) {
            vyhra = sazka * 3;
            System.out.println("Gratulujeme, vyhráli jste! Vyhra x3: " + vyhra);
        } else if (maxStejnychCisel == 4) {
            vyhra = sazka * 4;
            System.out.println("Gratulujeme, vyhráli jste! Vyhra x4: " + vyhra);
        } else if (maxStejnychCisel == 5) {
            vyhra = sazka * 5;
            System.out.println("Gratulujeme, vyhráli jste jackpot! Vyhra x5: " + vyhra);
        } else {
            System.out.println("Bohužel, prohráli jste. Ztráta: " + sazka);
            aktualniMnozstviPenez -= sazka;

            if (aktualniMnozstviPenez <= 0) {
                System.out.println("Bohužel, nemáte dostatek peněz pro další kolo. Hra končí.");
                System.exit(0);
            }
        }

        aktualniMnozstviPenez += vyhra;
        return aktualniMnozstviPenez;
    }
}