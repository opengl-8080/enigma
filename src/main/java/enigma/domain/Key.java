package enigma.domain;

import java.util.List;
import java.util.Objects;

/**
 * キー.
 */
public class Key {
    static final int MAX_COUNT = 26;
    
    public static final Key A = new Key('a');
    public static final Key B = new Key('b');
    public static final Key C = new Key('c');
    public static final Key D = new Key('d');
    public static final Key E = new Key('e');
    public static final Key F = new Key('f');
    public static final Key G = new Key('g');
    public static final Key H = new Key('h');
    public static final Key I = new Key('i');
    public static final Key J = new Key('j');
    public static final Key K = new Key('k');
    public static final Key L = new Key('l');
    public static final Key M = new Key('m');
    public static final Key N = new Key('n');
    public static final Key O = new Key('o');
    public static final Key P = new Key('p');
    public static final Key Q = new Key('q');
    public static final Key R = new Key('r');
    public static final Key S = new Key('s');
    public static final Key T = new Key('t');
    public static final Key U = new Key('u');
    public static final Key V = new Key('v');
    public static final Key W = new Key('w');
    public static final Key X = new Key('x');
    public static final Key Y = new Key('y');
    public static final Key Z = new Key('z');
    
    public static List<Key> all() {
        return List.of(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z);
    }
    
    public static Key of(char c) {
        return new Key(c);
    }
    
    public static Key of(Modulo26 value) {
        char key = (char)(value.getValue() + 'a');
        return new Key(key);
    }

    private final char value;

    private Key(char value) {
        if (!('a' <= value && value <= 'z')) {
            throw new IllegalArgumentException("value は a-z のいずれか");
        }
        this.value = value;
    }
    
    public char getChar() {
        return this.value;
    }
    
    Modulo26 toNumber() {
        return Modulo26.of(this.value - 'a');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Key{" +
                "value=" + value +
                '}';
    }
}
