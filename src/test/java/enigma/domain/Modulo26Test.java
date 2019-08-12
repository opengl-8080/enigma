package enigma.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Modulo26Test {

    @Test
    void 減算のテスト() {
        Modulo26 result = Modulo26.of(3).minus(Modulo26.of(10));
        assertThat(result).isEqualTo(Modulo26.of(19));
    }
}