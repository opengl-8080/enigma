package enigma.domain;

import java.util.Objects;

/**
 * 暗号化ローター.
 */
public class EncryptionRotor implements Rotor {
    private final Offset offset;
    private final Scrambler scrambler;
    private final Rotor nextRotor;
    private RotateAmount rotateAmount;

    EncryptionRotor(Offset offset, Scrambler scrambler, Rotor nextRotor) {
        this.offset = Objects.requireNonNull(offset);
        this.scrambler = Objects.requireNonNull(scrambler);
        this.nextRotor = Objects.requireNonNull(nextRotor);
        this.rotateAmount = RotateAmount.ZERO;
    }

    @Override
    public IOPosition input(IOPosition position) {
        IOPosition scrambled = this.scrambler.scramble(position, this.offset, this.rotateAmount);
        IOPosition reflected = this.nextRotor.input(scrambled);
        IOPosition reversed = this.scrambler.reverse(reflected, this.offset, this.rotateAmount);
        
        this.rotate();
        
        return reversed;
    }

    @Override
    public void rotate() {
        this.rotateAmount = this.rotateAmount.rotate();
        
        if (this.rotateAmount.isZero()) {
            this.nextRotor.rotate();
        }
    }
}
