package enigma.domain;

import java.util.Objects;
import java.util.function.Consumer;

public class Enigma {
    private final Plugboard plugboard;
    
    public static Enigma newInstance(
            Key firstKey,
            Scrambler firstScrambler,
            Key secondKey,
            Scrambler secondScrambler,
            Key thirdKey,
            Scrambler thirdScrambler,
            Consumer<Plugboard> plugboardInitializer) {
        
        Reflector reflector = new Reflector();
        
        EncryptionRotor thirdRotor = new EncryptionRotor(toOffset(firstKey), firstScrambler, reflector);
        EncryptionRotor secondRotor = new EncryptionRotor(toOffset(secondKey), secondScrambler, thirdRotor);
        EncryptionRotor firstRotor = new EncryptionRotor(toOffset(thirdKey), thirdScrambler, secondRotor);

        Plugboard plugboard = new Plugboard(firstRotor);
        plugboardInitializer.accept(plugboard);
        
        return new Enigma(plugboard);
    }
    
    private static Offset toOffset(Key key) {
        return Offset.of(key.toNumber());
    }

    private Enigma(Plugboard plugboard) {
        this.plugboard = Objects.requireNonNull(plugboard);
    }
    
    public String input(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            Key key = Key.of(c);
            Key result = this.plugboard.input(key);
            sb.append(result.getChar());
        }
        return sb.toString();
    }
}
