import java.util.Scanner;
import java.util.Random;
public class blackjck {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int[] cartas = new int[52];
        int puntosJugador = 0, puntosCasa = 0, indiceCarta = 0, temp;
        String opcion, valorAs;
        boolean seguirJugando = true;

        inicializarMazo(cartas);
        barajarCartas(cartas, random);

        indiceCarta = 0;
        puntosJugador = cartas[indiceCarta];
        if (puntosJugador == 11) {
            System.out.print("Has sacado un As. Quieres que sea 1 o 11? (1/11): ");
            valorAs = input.next();
            if (valorAs.equals("1")) {
                puntosJugador = 1;
            }
        }
        indiceCarta++;

        puntosCasa = cartas[indiceCarta];
        if (puntosCasa == 11) {
            System.out.print("La casa ha sacado un As. Quieres que sea 1 o 11? (1/11): ");
            valorAs = input.next();
            if (valorAs.equals("1")) {
                puntosCasa = 1;
            }
        }
        indiceCarta++;

        puntosJugador = turnoJugador(cartas, input, puntosJugador, indiceCarta, seguirJugando);

        puntosCasa = turnoCasa(cartas, input, puntosCasa, indiceCarta);

        mostrarResultados(puntosJugador, puntosCasa);
    }

    public static void inicializarMazo(int[] cartas) {
        for (int i = 0; i < 52; i++) {
            if (i % 13 == 0) {
                cartas[i] = 11;
            } else if (i % 13 >= 10) {
                cartas[i] = 10;
            } else {
                cartas[i] = (i % 13) + 1;
            }
        }
    }

    public static void barajarCartas(int[] cartas, Random random) {
        int temp;
        for (int i = 0; i < 52; i++) {
            int j = random.nextInt(52);
            temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
    }

    public static int turnoJugador(int[] cartas, Scanner input, int puntosJugador, int indiceCarta, boolean seguirJugando) {
        String opcion, valorAs;
        do {
            puntosJugador += cartas[indiceCarta];
            if (cartas[indiceCarta] == 11) {
                System.out.print("Has sacado un As. Quieres que sea 1 o 11? (1/11): ");
                valorAs = input.next();
                if (valorAs.equals("1")) {
                    puntosJugador += 1;
                } else {
                    puntosJugador += 11;
                }
            }
            indiceCarta++;

            System.out.println("Tus puntos: " + puntosJugador);

            if (puntosJugador < 21) {
                System.out.print("¿Quieres otra carta? (s/n): ");
                opcion = input.next();
                if (opcion.equals("n")) {
                    seguirJugando = false;
                }
            } else {
                seguirJugando = false;
            }
        } while (puntosJugador < 21 && seguirJugando);

        return puntosJugador;
    }

    public static int turnoCasa(int[] cartas, Scanner input, int puntosCasa, int indiceCarta) {
        String valorAs;
        while (puntosCasa < 17 && indiceCarta < 52) {
            puntosCasa += cartas[indiceCarta];
            if (cartas[indiceCarta] == 11) {
                System.out.print("La casa ha sacado un As. Quieres que sea 1 o 11? (1/11): ");
                valorAs = input.next();
                if (valorAs.equals("1")) {
                    puntosCasa += 1;
                } else {
                    puntosCasa += 11;
                }
            }
            indiceCarta++;
        }
        return puntosCasa;
    }

    public static void mostrarResultados(int puntosJugador, int puntosCasa) {
        System.out.println("Tus puntos finales: " + puntosJugador);
        System.out.println("Puntos finales de la casa: " + puntosCasa);

        if (puntosJugador > 21) {
            System.out.println("Te has pasado de 21. Pierdes.");
        } else if (puntosCasa > 21) {
            System.out.println("La casa se ha pasado de 21. Ganas.");
        } else if (puntosJugador > puntosCasa) {
            System.out.println("Ganas.");
        } else if (puntosJugador < puntosCasa) {
            System.out.println("Pierdes.");
        } else {
            System.out.println("Empate.");
        }
    }
}
