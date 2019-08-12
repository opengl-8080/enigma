package enigma.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * スクランブラー.
 */
public class Scrambler implements Serializable {
    private final Map<IOPosition, IOPosition> map;
    private final Map<IOPosition, IOPosition> reverseMap;
    
    public static Scrambler newInstance(Random random) {
        List<Integer> randomIndexes = IntStream.range(0, 26).boxed().collect(Collectors.toList());
        Collections.shuffle(randomIndexes, random);

        Map<IOPosition, IOPosition> map = new HashMap<>();
        
        for (int i=0; i<26; i++) {
            IOPosition one = IOPosition.of(i);
            IOPosition other = IOPosition.of(randomIndexes.get(i));
            map.put(one, other);
        }
        
        return new Scrambler(map);
    }
    
    Scrambler(Map<IOPosition, IOPosition> map) {
        this.map = map;
        Map<IOPosition, IOPosition> reversed = new HashMap<>();
        for (Map.Entry<IOPosition, IOPosition> entry : map.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }
        this.reverseMap = reversed;
    }
    
    public IOPosition scramble(IOPosition input, Offset offset, RotateAmount rotateAmount) {
        IOPosition shifted = input.minus(offset, rotateAmount);
        IOPosition scrambled = this.map.get(shifted);
        return scrambled.plus(offset, rotateAmount);
    }
    
    public IOPosition reverse(IOPosition input, Offset offset, RotateAmount rotateAmount) {
        IOPosition shifted = input.minus(offset, rotateAmount);
        IOPosition reversed = this.reverseMap.get(shifted);
        return reversed.plus(offset, rotateAmount);
    }
    
    public void store(Path path) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE))) {
            out.writeObject(this);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public static Scrambler load(Path path) {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path, StandardOpenOption.READ))) {
            return ((Scrambler) in.readObject());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
