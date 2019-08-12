package enigma.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ScramblerTest {
    
    private Scrambler scrambler;

    @BeforeEach
    void setUp() {
        /*
         * 0 -> 25
         * 1 -> 24
         * 2 -> 23
         * :
         * 23 -> 2
         * 24 -> 1
         * 25 -> 0
         * 
         * のように対角線状に変換するスクランブラー.
         */
        Map<IOPosition, IOPosition> map = new HashMap<>();
        for (int i=0, j=25; i<26; i++, j--) {
            IOPosition from = IOPosition.of(Modulo26.of(i));
            IOPosition to = IOPosition.of(Modulo26.of(j));
            map.put(from, to);
        }
        scrambler = new Scrambler(map);
    }

    @Test
    void オフセットと回転数を加味した双方向の変換ができる() {
        // シフト量 = 9
        Offset offset = Offset.of(5);
        RotateAmount rotateAmount = RotateAmount.of(4);
        
        /* InOut Scrambler
         * 0     17 -> 8  
         * 1     18 -> 7  
         * 2     19 -> 6  
         * 3     20 -> 5  
         * 4     21 -> 4  
         * 5     22 -> 3  
         * 6     23 -> 2  
         * 7     24 -> 1  
         * 8     25 -> 0  
         * 9     0  -> 25 
         * 10    1  -> 24 
         * 11    2  -> 23 
         * 12    3  -> 22 
         * 13    4  -> 21 
         * 14    5  -> 20 
         * 15    6  -> 19 
         * 16    7  -> 18 
         * 17    8  -> 17 
         * 18    9  -> 16 
         * 19    10 -> 15 
         * 20    11 -> 14 
         * 21    12 -> 13 
         * 22    13 -> 12 
         * 23    14 -> 11 
         * 24    15 -> 10 
         * 25    16 -> 9  
         */
        
        IOPosition scrambled = scrambler.scramble(IOPosition.of(Modulo26.of(0)), offset, rotateAmount);
        assertThat(scrambled).isEqualTo(IOPosition.of(Modulo26.of(17)));

        IOPosition reversed = scrambler.reverse(IOPosition.of(Modulo26.of(17)), offset, rotateAmount);
        assertThat(reversed).isEqualTo(IOPosition.of(Modulo26.of(0)));
        
        scrambled = scrambler.scramble(IOPosition.of(Modulo26.of(13)), offset, rotateAmount);
        assertThat(scrambled).isEqualTo(IOPosition.of(Modulo26.of(4)));

        reversed = scrambler.reverse(IOPosition.of(Modulo26.of(4)), offset, rotateAmount);
        assertThat(reversed).isEqualTo(IOPosition.of(Modulo26.of(13)));
    }
}