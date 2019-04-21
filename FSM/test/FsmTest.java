import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class FsmTest {
    @Test
    public void multipleTest() throws FsmExcepton, IOException {
        Fsm fcm = new Fsm("./test/multTest.txt");
        assertTrue(fcm.checkSequence("bbbaaa"));
        assertTrue(fcm.checkSequence("ababbababbbaaa"));
        assertTrue(fcm.checkSequence("ab"));
        assertTrue(fcm.checkSequence("abab"));
        assertTrue(fcm.checkSequence("bbb"));
        assertTrue(fcm.checkSequence("bbbbbbbbbbbbba"));
        assertFalse(fcm.checkSequence("aba"));
        assertFalse(fcm.checkSequence("abb"));
    }
    @Test(expected = FsmExcepton.class)
    public void wrongFormat() throws IOException, FsmExcepton {
        Fsm fcm = new Fsm("./test/wrongTest.txt");
    }
    @Test
    public void startEndEquals() throws FsmExcepton, IOException {
        Fsm fcm = new Fsm("./test/startEnd.txt");
        assertTrue(fcm.checkSequence("y"));
        assertTrue(fcm.checkSequence("xx"));
        assertTrue(fcm.checkSequence("zzzz"));
        assertTrue(fcm.checkSequence("xyzx"));
        assertTrue(fcm.checkSequence("xxyxx"));

        assertFalse(fcm.checkSequence("xy"));
        assertFalse(fcm.checkSequence("yxx"));
        assertFalse(fcm.checkSequence("xxyyxxz"));
        assertFalse(fcm.checkSequence("xxy"));
    }
}
