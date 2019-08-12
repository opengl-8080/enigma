package enigma.domain;

/**
 * リフレクター(反射ローター).
 */
public class Reflector implements Rotor {

    @Override
    public IOPosition input(IOPosition position) {
        return position.plus(13);
    }

    @Override
    public void rotate() {
        // Reflector does not rotate.
    }
}
