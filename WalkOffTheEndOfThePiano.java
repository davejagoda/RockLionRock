import javax.sound.midi.*;

public class WalkOffTheEndOfThePiano {
    static public void main (String[] args) throws InterruptedException {
	Synthesizer synth = null;
	try {
	    synth = MidiSystem.getSynthesizer ();
	    synth.open ();
	} catch ( MidiUnavailableException ex) {
	    System.out.println ( "Midi Unavailable Exception");
	}
	Soundbank soundbank = synth.getDefaultSoundbank();
	Instrument[] instr = soundbank.getInstruments();
	MidiChannel[] chan = synth.getChannels();
	System.out.printf("current channel is %d\n", chan[0].getProgram());
	chan[0].programChange(0, 1); // Acoustic Grand Piano
	System.out.printf("current channel is %d\n", chan[0].getProgram());
	int sleepTime = 1000;
	int volume = 100;
	for (int i = 0; i <= 127; ++i) {
	    System.out.println(i);
	    chan[0].noteOn ( i, volume );
	    Thread.sleep(sleepTime);
	}
	chan[0].allNotesOff();
    }
}
