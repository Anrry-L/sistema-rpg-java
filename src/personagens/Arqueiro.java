package personagens;

import enums.ClassePersonagem;
import java.util.ArrayList;
import java.util.Scanner;

public class Arqueiro extends Personagem {

    Scanner s = new Scanner(System.in);

    @Override
    public int atacar(int atqBase){
        return atqBase + 3;
    }

    @Override
    public void habilidadeEspecial(ArrayList<Personagem> aliados, ArrayList<Personagem> inimigos, boolean encontrado){
        encontrado = false;
        for (Personagem aliado : aliados) {
            if (!aliado.getCansado()) {
                if (aliado.getClasse().equals(ClassePersonagem.ARQUEIRO)) {
                    System.out.printf("em quem %s usará a habilidade especial?\n", aliado.getNome());
                    String inimigoEscolhido = s.nextLine();

                    for (Personagem inimigo : inimigos) {

                        if (inimigoEscolhido.equalsIgnoreCase(inimigo.getNome())) {
                            encontrado = true;
                            int danoAliado = aliado.atacar(aliado.getAtqBase());
                            System.out.println(aliado.getNome() + " usou ATAQUE CERTEIRO!");
                            inimigo.setVida(inimigo.getVida() - danoAliado);
                            inimigo.setMachucado(true);
                            System.out.printf("%s deu %d de dano em %s e o deixou no estado MACHUCADO\n", aliado.getNome(), danoAliado, inimigo.getNome());
                            aliado.setCansado(true);

                            if (inimigo.getVida() <= 0) {
                                System.out.printf("%s morreu!\n", inimigo.getNome());
                                inimigos.remove(inimigo);
                                break;
                            }
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Inimigo não existe");
                    }
                }
            }
            else {
                System.out.println(aliado.getNome() + " está cansado");
            }
        }
    }

}
