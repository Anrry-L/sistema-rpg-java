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
                System.out.printf("O que %s irá fazer?\n", aliado.getNome());
                System.out.println("1 - Atacar");
                System.out.println("2 - Habilidade Especial");

                int escolha = s.nextInt();
                s.nextLine();

                switch (escolha){
                    case 1 :
                        System.out.printf("Quem %s irá atacar?\n", aliado.getNome());
                        String inimigoEscolhido = s.nextLine();

                        for (Personagem inimigo : inimigos) {

                        if (inimigoEscolhido.equalsIgnoreCase(inimigo.getNome())) {
                            encontrado = true;
                            int danoAliado = aliado.atacar(aliado.getAtqBase());
                            if (inimigo.getMachucado()) {
                                inimigo.setVida(inimigo.getVida() - (danoAliado + 3));
                                System.out.printf("%s deu %d de dano (+3) em %s\n", aliado.getNome(), danoAliado, inimigo.getNome());
                            }
                            else {
                                inimigo.setVida(inimigo.getVida() - danoAliado);
                                System.out.printf("%s deu %d de dano em %s\n", aliado.getNome(), danoAliado, inimigo.getNome());
                            }
                            if (inimigo.getVida() <= 0) {
                                System.out.printf("%s morreu!\n", inimigo.getNome());
                                inimigos.remove(inimigo);
                                break;
                            }
                        }
                    } break;

                    case 2 : encontrado = true; aliado.habilidadeEspecial(aliados, inimigos, encontrado); break;

                    default : System.out.println("Opção inválida");
                }
            }

            if(!encontrado){
                System.out.println("Inimigo não existe");
            }

        } else {
            System.out.println("Não existem aliados em campo.");
        }

        if(!inimigos.isEmpty() || !aliados.isEmpty()) {

            System.out.println("Turno dos inimigos!");
            System.out.println("----------------------");

            for (Personagem inimigo : inimigos) {
                inimigo.setCansado(false);
                int rng = r.nextInt(0, 2);
                System.out.println(rng);

                if (rng == 0) {

                    int danoInimigo = inimigo.atacar(inimigo.getAtqBase());
                    Personagem aliadoEscolhido = aliados.get(r.nextInt(aliados.size()));
                    if (aliadoEscolhido.getMachucado()) {
                        aliadoEscolhido.setVida(aliadoEscolhido.getVida() - (danoInimigo + 3));
                        System.out.printf("%s deu %d de dano (+3) em %s\n", inimigo.getNome(), danoInimigo, aliadoEscolhido.getNome());
                    }
                    else {
                        aliadoEscolhido.setVida(aliadoEscolhido.getVida() - danoInimigo);
                        System.out.printf("%s deu %d de dano em %s\n", inimigo.getNome(), danoInimigo, aliadoEscolhido.getNome());
                    }

                    if (aliadoEscolhido.getVida() <= 0) {
                        System.out.printf("%s morreu!\n", aliadoEscolhido.getNome());
                        aliados.remove(aliadoEscolhido);
                        break;

                    }
                }
                else {
                    if (!inimigo.getCansado()) {

                        Personagem aliadoParaRemover = null;

                        if (inimigo.getClasse().equals(ClassePersonagem.GUERREIRO)) {

                            int danoInimigo = inimigo.atacar(inimigo.getAtqBase());
                            System.out.println(inimigo.getNome() + " usou GOLPE GIRATÓRIO!");
                            System.out.printf("%s deu %d de dano em TODOS OS ALIADOS\n", inimigo.getNome(), danoInimigo);

                            for (Personagem aliado : aliados) {
                                aliado.setVida(aliado.getVida() - danoInimigo);
                                if (aliado.getVida() <= 0) {
                                    System.out.printf("%s morreu!\n", aliado.getNome());
                                    aliadoParaRemover = aliado;
                                    break;
                                }
                            }

                        } else if (inimigo.getClasse().equals(ClassePersonagem.MAGO)) {

                            int danoInimigo = inimigo.atacar(inimigo.getAtqBase()) * 2;
                            Personagem aliadoEscolhido = aliados.get(r.nextInt(aliados.size()));
                            System.out.println(inimigo.getNome() + " usou EXPLOSION!");
                            aliadoEscolhido.setVida(aliadoEscolhido.getVida() - danoInimigo);
                            System.out.printf("%s deu %d de dano em %s\n", inimigo.getNome(), danoInimigo, aliadoEscolhido.getNome());

                            if (aliadoEscolhido.getVida() <= 0) {
                                System.out.printf("%s morreu!\n", aliadoEscolhido.getNome());
                                aliadoParaRemover = aliadoEscolhido;
                            }

                        } else if (inimigo.getClasse().equals(ClassePersonagem.ARQUEIRO)) {

                            int danoInimigo = inimigo.atacar(inimigo.getAtqBase());
                            Personagem aliadoEscolhido = aliados.get(r.nextInt(aliados.size()));
                            System.out.println(inimigo.getNome() + " usou TIRO CERTEIRO!");
                            aliadoEscolhido.setVida(aliadoEscolhido.getVida() - danoInimigo);
                            System.out.printf("%s deu %d de dano em %s\n", inimigo.getNome(), danoInimigo, aliadoEscolhido.getNome());
                            aliadoEscolhido.setMachucado(true);

                            if (aliadoEscolhido.getVida() <= 0) {
                                System.out.printf("%s morreu!\n", aliadoEscolhido.getNome());
                                aliadoParaRemover = aliadoEscolhido;
                            }

                        } else if (inimigo.getClasse().equals(ClassePersonagem.BARDO)) {

                            Personagem inimigoEscolhido = inimigos.get(r.nextInt(inimigos.size()));
                            System.out.println(inimigo.getNome() + " usou MÚSICA PARA OS MEUS OUVIDOS!");
                            inimigoEscolhido.setVida(inimigoEscolhido.getVida() + 6);
                            if (inimigoEscolhido.getVida() >= 20) {
                                inimigoEscolhido.setVida(20);
                            }
                        }

                        if (aliadoParaRemover != null) {
                            aliados.remove(aliadoParaRemover);
                        }

                        inimigo.setCansado(true);
                    }
                }
            }
        } else {
            System.out.println("Não existem inimigos ou aliados em campo.");
        }
    }
}