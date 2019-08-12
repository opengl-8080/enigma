package enigma.domain;

import java.util.Objects;

/**
 * 回転量.
 */
public class RotateAmount {
    public static final RotateAmount ZERO = RotateAmount.of(0);
    private final Modulo26 value;
    
    public static RotateAmount of(int value) {
        return new RotateAmount(Modulo26.of(value));
    }

    private RotateAmount(Modulo26 value) {
        this.value = Objects.requireNonNull(value);
    }

    Modulo26 getValue() {
        return this.value;
    }
    
    public boolean isZero() {
        return this.value.isZero();
    }
    
    public RotateAmount rotate() {
        return new RotateAmount(this.value.plus(1));
    }

    @Override
    public String toString() {
        return "ShiftAmount{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RotateAmount that = (RotateAmount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
