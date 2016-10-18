import javax.sound.midi.*;

public class BassGuitar {
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

	/*
	  define all strings from drop D on a Bass (26) up to
	  24th (?) fret on a Guitar (88)
	 */
	System.out.printf("current channel is %d\n", chan[0].getProgram());
	chan[0].programChange(0, 27); // Guitar
	System.out.printf("current channel is %d\n", chan[0].getProgram());
	int sleepTime = 1000;
	int volume = 100;
	for (int i = 26; i <= 88; ++i) {
	    System.out.println(i);
	    chan[0].noteOn ( i, volume );
	    Thread.sleep(sleepTime);
	}
	chan[0].allNotesOff();
    }
}
