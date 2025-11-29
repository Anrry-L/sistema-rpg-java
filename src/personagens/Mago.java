package personagens;

import java.util.Random;

public class Mago extends Personagem {

    Random r = new Random();

    @Override
    public int atacar(int atqBase) {
        return atqBase + r.nextInt(0, 11);
    }

}
