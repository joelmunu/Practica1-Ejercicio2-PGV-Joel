import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int windowNumber = 0;
        boolean error = true;
        final int PROCESS_LIFETIME = 500;

        while (error) {
            System.out.print("Introduce el número de ventanas de Bloc de Notas que quieres abrir: ");
            windowNumber = sc.nextInt();
            sc.nextLine();

            if (windowNumber < 0) {
                System.out.println("Error: Debes introducir un número positivo");
            } else {
                error = false;
            }
        }

        if (windowNumber == 0) {
            System.out.println("Programa finalizado, no has abierto ninguna ventana del Bloc de Notas");
        } else {

            ProcessBuilder process = new ProcessBuilder();
            process.command("cmd.exe", "/c", "notepad");

            try {
                int processNumber = 0;

                while (processNumber < windowNumber) {
                    System.out.println("\nHora de inicio del proceso nº " + (processNumber + 1) +": " + LocalDateTime.now());

                    Process notepad = process.start();
                    notepad.waitFor(500, TimeUnit.MILLISECONDS);
                    notepad.destroyForcibly();

                    System.out.println("Hora de finalización del proceso nº " + (processNumber + 1) +": " + LocalDateTime.now());
                    processNumber++;
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}