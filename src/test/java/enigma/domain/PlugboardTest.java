package enigma.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PlugboardTest {

    @Test
    void name() {
        Plugboard plugboard = new Plugboard(new TestRotor());
        plugboard.exchange(Key.A, Key.X);
        plugboard.exchange(Key.C, Key.D);
        plugboard.exchange(Key.K, Key.E);

        // A => X -> Y => Y
        assertThat(plugboard.input(Key.A)).isEqualTo(Key.Y);
        // B => B -> C => C
        assertThat(plugboard.input(Key.B)).isEqualTo(Key.C);
        // C => D -> E => K
        assertThat(plugboard.input(Key.C)).isEqualTo(Key.K);
    }
    
    static class TestRotor implements Rotor {

        @Override
        public IOPosition input(IOPosition position) {
            return position.plus(1);
        }

        @Override
        public void rotate() {}
    }
}