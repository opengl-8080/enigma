package enigma.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * プラグボード.
 */
public class Plugboard {
    private final Rotor rotor;
    private final Map<Key, Key> exchangeMap = new HashMap<>();

    public Plugboard(Rotor rotor) {
        this.rotor = Objects.requireNonNull(rotor);
    }

    public void exchange(Key one, Key other) {
        if (this.exchangeMap.size() / 2 == 6) {
            throw new IllegalStateException("交換を定義できるのは６つまでです");
        }
        if (one.equals(other)) {
            throw new IllegalArgumentException("同じキーは交換できません");
        }
        if (this.exchangeMap.containsKey(one)) {
            throw new IllegalArgumentException(one.getChar() + " は交換済みです");
        }
        if (this.exchangeMap.containsKey(other)) {
            throw new IllegalArgumentException(other.getChar() + " は交換済みです");
        }
        
        this.exchangeMap.put(one, other);
        this.exchangeMap.put(other, one);
    }
    
    Key input(Key inputKey) {
        Key exchanged = this.exchangeMap.getOrDefault(inputKey, inputKey);
        IOPosition position = IOPosition.of(exchanged.toNumber());
        
        IOPosition reversed = this.rotor.input(position);
        
        Key reversedKey = Key.of(reversed.getValue());
        return this.exchangeMap.getOrDefault(reversedKey, reversedKey);
    }
}
