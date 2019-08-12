package enigma.domain;

/**
 * ランプボード.
 */
public class LampBoard {
    private Key currentKey;
    
    public void lightUp(IOPosition position) {
        this.currentKey = Key.of(position.getValue());
    }
    
    public Key getCurrentKey() {
        return this.currentKey;
    }
}
