package sistema;

import personagens.*;
import enums.ClassePersonagem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Party {

    Random r = new Random();
    Scanner s = new Scanner(System.in);

    ArrayList<Personagem> aliados = new ArrayList<>();
    ArrayList<Personagem> inimigos = new ArrayList<>();

    public void adicionarAliado() {

        System.out.println("ESCOLHA UMA CLASSE: ");
        System.out.println("1 - GUERREIRO ");
        System.out.println("2 - MAGO");
        System.out.println("3 - ARQUEIRO");
        System.out.println("4 - BARDO");

        int escolha = s.nextInt();
        s.nextLine();

        Personagem personagem = null;

        switch (escolha){
            case 1 : personagem = new Guerreiro(); personagem.setClasse(ClassePersonagem.GUERREIRO); break;
            case 2 : personagem = new Mago(); personagem.setClasse(ClassePersonagem.MAGO); break;
            case 3 : personagem = new Arqueiro(); personagem.setClasse(ClassePersonagem.ARQUEIRO); break;
            case 4 : personagem = new Bardo(); personagem.setClasse(ClassePersonagem.BARDO); break;
            default: System.out.println("Opção inválida");
        }

        aliados.add(personagem);

        System.out.println("Qual será o nome do seu " + personagem.getClasse().toString().toLowerCase() + "?");
        personagem.setNome(s.nextLine());

        System.out.println(personagem);

    }

    public void adicionarInimigo(){
        System.out.println("ESCOLHA UMA CLASSE: ");
        System.out.println("1 - GUERREIRO ");
        System.out.println("2 - MAGO");
        System.out.println("3 - ARQUEIRO");
        System.out.println("4 - BARDO");

        int escolha = s.nextInt();
        s.nextLine();

        Personagem personagem = null;

        switch (escolha){
            case 1 : personagem = new Guerreiro(); personagem.setClasse(ClassePersonagem.GUERREIRO); break;
            case 2 : personagem = new Mago(); personagem.setClasse(ClassePersonagem.MAGO); break;
            case 3 : personagem = new Arqueiro(); personagem.setClasse(ClassePersonagem.ARQUEIRO); break;
            case 4 : personagem = new Bardo(); personagem.setClasse(ClassePersonagem.BARDO); break;
            default: System.out.println("Opção inválida");
        }

        inimigos.add(personagem);

        System.out.println("Qual será o nome do " + personagem.getClasse().toString().toLowerCase() + " inimigo?");
        personagem.setNome(s.nextLine());

        System.out.println(personagem);
    }

    public void listarPersonagens(){

        System.out.println("Aliados: ");
        for(Personagem aliado : aliados){
            System.out.println(aliado);
        }
        System.out.println("Inimigos: ");
        for(Personagem inimigo : inimigos){
            System.out.println(inimigo);
        }
    }

    public void batalhaTurno(){
        boolean encontrado = false;

        if (!aliados.isEmpty()){
            System.out.println("Turno dos aliados!");
            System.out.println("-------------------");

            for (Personagem aliado : aliados) {
                System.out.printf("Quem o %s irá atacar?\n", aliado.getNome());
                String inimigoEscolhido = s.nextLine();

                for (Personagem inimigo : inimigos) {
                    if (inimigoEscolhido.equalsIgnoreCase(inimigo.getNome())) {
                        encontrado = true;
                        int danoAliado = aliado.atacar(aliado.getAtqBase());
                        inimigo.setVida(inimigo.getVida() - danoAliado);
                        System.out.printf("%s deu %d de dano em %s\n", aliado.getNome(), danoAliado, inimigo.getNome());

                        if (inimigo.getVida() <= 0) {
                            System.out.printf("%s morreu!\n", inimigo.getNome());
                            inimigos.remove(inimigo);
                            break;
                        }
                    }
                }
            }

            if(!encontrado){
                System.out.println("Inimigo não existe");
            }

        } else {
            System.out.println("Não existem aliados em campo.");
        }

        if(!inimigos.isEmpty()) {

            System.out.println("Turno dos inimigos!");
            System.out.println("----------------------");

            for (Personagem inimigo : inimigos) {
                int danoInimigo = inimigo.atacar(inimigo.getAtqBase());
                Personagem aliadoEscolhido = aliados.get(r.nextInt(aliados.size()));
                aliadoEscolhido.setVida(aliadoEscolhido.getVida() - danoInimigo);
                System.out.printf("%s deu %d de dano em %s\n", inimigo.getNome(), danoInimigo, aliadoEscolhido.getNome());

                if (aliadoEscolhido.getVida() <= 0) {
                    System.out.printf("%s morreu!\n", aliadoEscolhido.getNome());
                    aliados.remove(aliadoEscolhido);
                    break;
                }
            }

        } else {
            System.out.println("Não existem inimigos em campo.");
        }
    }
}
