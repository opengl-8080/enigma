package enigma.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 入出力位置.
 */
public class IOPosition implements Serializable {
    private final Modulo26 value;

    public static IOPosition of(int value) {
        return new IOPosition(Modulo26.of(value));
    }
    
    public static IOPosition of(Modulo26 value) {
        return new IOPosition(value);
    }

    private IOPosition(Modulo26 value) {
        this.value = value;
    }

    Modulo26 getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IOPosition that = (IOPosition) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "IOPosition{" +
                "value=" + value +
                '}';
    }
    
    IOPosition plus(int n) {
        return new IOPosition(this.value.plus(n));
    }

    IOPosition plus(Offset offset, RotateAmount rotateAmount) {
        return new IOPosition(this.value.plus(offset.getValue()).plus(rotateAmount.getValue()));
    }
    
    IOPosition minus(Offset offset, RotateAmount rotateAmount) {
        return new IOPosition(this.value.minus(offset.getValue()).minus(rotateAmount.getValue()));
    }
}
