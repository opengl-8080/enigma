package enigma.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 法を26とした整数.
 */
public class Modulo26 implements Serializable {
    private final int value;
    
    public static Modulo26 of(int value) {
        return new Modulo26(value);
    }

    private Modulo26(int value) {
        this.value = Math.floorMod(value, 26);
    }
    
    boolean isZero() {
        return this.value == 0;
    }
    
    int getValue() {
        return this.value;
    }

    Modulo26 plus(Modulo26 other) {
        return this.plus(other.value);
    }
    
    Modulo26 minus(Modulo26 other) {
        return this.plus(-1 * other.value);
    }

    Modulo26 plus(int n) {
        return new Modulo26(this.value + n);
    }
    
    @Override
    public String toString() {
        return "Modulo26{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo26 modulo26 = (Modulo26) o;
        return value == modulo26.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
