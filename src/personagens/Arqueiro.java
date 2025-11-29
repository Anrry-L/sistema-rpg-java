package personagens;

public class Arqueiro extends Personagem {

    @Override
    public int atacar(int atqBase){
        return atqBase + 3;
    }

}
