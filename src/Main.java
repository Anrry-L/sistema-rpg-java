import sistema.Party;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        Party pt = new Party();
        boolean running = true;
        int escolhaAcao;

        while (running) {
            System.out.println("     ---MENU---");
            System.out.println("1 - Adicionar aliado");
            System.out.println("2 - Adicionar inimigo");
            System.out.println("3 - Listar personagens");
            System.out.println("4 - Turno de combate");
            System.out.println("5 - Encerrar");

            escolhaAcao = s.nextInt();
            s.nextLine();

            switch (escolhaAcao){
                case 1 : pt.adicionarAliado(); break;
                case 2 : pt.adicionarInimigo(); break;
                case 3 : pt.listarPersonagens(); break;
                case 4 : pt.batalhaTurno(); break;
                case 5 : running = false; break;
                default : System.out.println("Opção inválida");
            }
        }
    }
}
