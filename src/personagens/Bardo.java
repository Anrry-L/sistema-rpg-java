package personagens;

import enums.ClassePersonagem;
import java.util.Scanner;
import java.util.ArrayList;

public class Bardo extends Personagem {

    Scanner s = new Scanner(System.in);

    @Override
    public int atacar(int atqBase) {
        return atqBase; // ainda sem bônus
    }

    @Override
    public void habilidadeEspecial(ArrayList<Personagem> aliados, ArrayList<Personagem> inimigos, boolean encontrado) {
        encontrado = false;
        for (Personagem aliado : aliados) {
            if (!aliado.getCansado()) {
                if (aliado.getClasse().equals(ClassePersonagem.BARDO)) {
                    System.out.println(aliado.getNome() + " usou MÚSICA PARA MEUS OUVIDOS");
                    System.out.println("Escolha um aliado para ser curado em 6 pontos de HP: ");
                    String aliadoEscolhido = s.nextLine();

                    for (Personagem aliadoC : aliados) {
                        if (aliadoEscolhido.equalsIgnoreCase(aliadoC.getNome())) {
                            encontrado = true;
                            aliadoC.setVida(aliadoC.getVida() + 6);
                            if (aliadoC.getVida() >= 20) {
                                aliadoC.setVida(20);
                            }
                            System.out.println(aliadoC.getNome() + " foi curado!");
                            aliadoC.setCansado(true);
                            if (aliadoC.getVida() > 20) {
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println(aliado.getNome() + " está cansado");
                }
            }
            if (!encontrado) {
                System.out.println("Aliado não existe");
            }
        }
    }
}
