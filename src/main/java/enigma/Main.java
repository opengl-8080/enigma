package enigma;

import enigma.domain.Enigma;
import enigma.domain.Key;
import enigma.domain.Plugboard;
import enigma.domain.Scrambler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scrambler> scramblerList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            Path path = Path.of("./build/scrambler" + (i+1));
            Scrambler scrambler;
            if (Files.exists(path)) {
                scrambler = Scrambler.load(path);
            } else {
                scrambler = Scrambler.newInstance(new SecureRandom());
                scrambler.store(path);
            }
            scramblerList.add(scrambler);
        }
        
        Scrambler firstScrambler = scramblerList.get(0);
        Scrambler secondScrambler = scramblerList.get(1);
        Scrambler thirdScrambler = scramblerList.get(2);
        
        Enigma encryption = Enigma.newInstance(Key.D, firstScrambler, Key.K, secondScrambler, Key.F, thirdScrambler, Main::initializePlugboard);

        String encrypted = encryption.input("helloxxworld");

        System.out.println(encrypted);
        
        // tfgoegiokk
        Enigma decryption = Enigma.newInstance(Key.D, firstScrambler, Key.K, secondScrambler, Key.F, thirdScrambler, Main::initializePlugboard);

        String plainText = decryption.input(encrypted);

        System.out.println(plainText.replace("xx", " "));
    }
    
    private static void initializePlugboard(Plugboard plugboard) {
        plugboard.exchange(Key.A, Key.R);
        plugboard.exchange(Key.E, Key.K);
        plugboard.exchange(Key.O, Key.T);
        plugboard.exchange(Key.P, Key.Q);
        plugboard.exchange(Key.Z, Key.M);
        plugboard.exchange(Key.Y, Key.C);
    }
}
