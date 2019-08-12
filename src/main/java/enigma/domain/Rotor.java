package enigma.domain;

/**
 * ローター.
 */
public interface Rotor {
    
    IOPosition input(IOPosition position);
    
    void rotate();
}
