package personagens;

import enums.ClassePersonagem;

import java.util.ArrayList;

public class Guerreiro extends Personagem {

    @Override
    public int atacar(int atqBase) {
        return atqBase + 5;
    }

    @Override
    public void habilidadeEspecial(ArrayList<Personagem> aliados ,ArrayList<Personagem> inimigos, boolean encontrado){

        for (Personagem aliado : aliados)

            if (!aliado.getCansado()) {

                if (aliado.getClasse().equals(ClassePersonagem.GUERREIRO)) {
                    System.out.printf("%s usou o GOLPE GIRATÓRIO!\n", aliado.getNome());
                    System.out.printf("%s deu %d de dano em TODOS OS INIMIGOS\n", aliado.getNome(), aliado.atacar(aliado.getAtqBase()));
                    for (Personagem inimigo : inimigos) {
                        inimigo.setVida(inimigo.getVida() - atacar(getAtqBase()));
                        aliado.setCansado(true);

                        if (inimigo.getVida() <= 0) {
                            System.out.printf("%s morreu!\n", inimigo.getNome());
                            inimigos.remove(inimigo);
                            break;
                        }
                    }
                }
                else{
                    System.out.println();
                }
            }
        else {
                System.out.printf("%s está cansado\n" ,aliado.getNome());
            }
    }
}
