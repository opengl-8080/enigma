package enigma.domain;

import java.util.Objects;

/**
 * オフセット.
 */
public class Offset {
    private final Modulo26 value;

    public static Offset of(Modulo26 value) {
        return new Offset(value);
    }
    
    public static Offset of(int value) {
        return new Offset(Modulo26.of(value));
    }
    
    private Offset(Modulo26 value) {
        this.value = Objects.requireNonNull(value);
    }

    Modulo26 getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Offset{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offset offset = (Offset) o;
        return value == offset.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
