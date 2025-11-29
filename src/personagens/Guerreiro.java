package personagens;

public class Guerreiro extends Personagem {

    @Override
    public int atacar(int atqBase) {
        return atqBase + 5;
    }
}
