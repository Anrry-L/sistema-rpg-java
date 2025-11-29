package personagens;

import enums.ClassePersonagem;
import java.util.ArrayList;

public class Personagem {

    private String nome;
    private int vida = 20;
    private int atqBase = 4;
    private ClassePersonagem classe;
    private boolean cansado = false;

    public boolean getCansado(){
        return cansado;
    }

    public void setCansado(boolean cansado){
        this.cansado = cansado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtqBase() {
        return atqBase;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public ClassePersonagem getClasse(){
        return classe;
    }

    public int atacar(int atqBase){
        return atqBase;
    }

    public void habilidadeEspecial(ArrayList<Personagem> aliados, ArrayList<Personagem> inimigos){

    }

    @Override
    public String toString(){
        return String.format("%s, o %s - %d HP\n", this.nome, this.classe, this.vida);
    }

}
