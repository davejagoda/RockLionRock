import java.util.Scanner;
import javax.sound.midi.*;

public class GuitarChords {
    // fields
    public String name;
    public int[] notes;
    // constructor
    //    public GuitarChords ( String chordName, int[] chordNotes) {
    public GuitarChords ( String chordName, int... chordNotes) {
	name = chordName;
	notes = chordNotes;
    }
    // methods
    public void playChord ( MidiChannel channel,  int volume ) throws InterruptedException {
	System.out.printf ( "%5s: ", name );
	for ( int i = 0; i < notes.length; ++i) {
	    if ( notes[i] > 0 ) {
		channel.noteOn ( notes[i], volume );
		System.out.printf ( "%d ", notes[i]);
		Thread.sleep(50);
	    } else {
		System.out.printf ( "00 ");
	    }
	}
	System.out.printf ( "volume: %d\n", volume);
    }
    static public void main (String[] args) throws InterruptedException {
	Scanner console = new Scanner ( System.in);
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
	chan[0].programChange(0, 27); // electric guitar = MIDI value + 1
	System.out.printf("current channel is %d\n", chan[0].getProgram());

	GuitarChords[] gc = new GuitarChords[14];
	gc[ 0] = new GuitarChords ( "CMaj", 00, 48, 52, 55, 60, 64 );
	//                                  X   C   E   G   C   E
	gc[ 1] = new GuitarChords ( "C7",   00, 48, 52, 58, 60, 64 );
	//                                  X   C   E   Bb  C   E
	gc[ 2] = new GuitarChords ( "DMaj", 00, 00, 50, 57, 62, 66 );
	//                                  X   X   D   A   D   F#
	gc[ 3] = new GuitarChords ( "Dm",   00, 00, 50, 57, 62, 65 );
	//                                  X   X   D   A   D   F
	gc[ 4] = new GuitarChords ( "D7",   00, 00, 50, 57, 60, 66 );
	//                                  X   X   D   A   C   F#
	gc[ 5] = new GuitarChords ( "EMaj", 40, 47, 52, 56, 59, 64 );
	//                                  E   B   E   G#  B   E
	gc[ 6] = new GuitarChords ( "Em",   40, 47, 52, 55, 59, 64 );
	//                                  E   B   E   G   B   E
	gc[ 7] = new GuitarChords ( "E7",   40, 47, 50, 56, 59, 64 );
	//                                  E   B   D   G#  B   E
	gc[ 8] = new GuitarChords ( "GMaj", 43, 47, 50, 55, 59, 67 );
	//                                  G   B   D   G   B   G
	gc[ 9] = new GuitarChords ( "G7",   43, 47, 50, 55, 59, 65 );
	//                                  G   B   D   G   B   F
	gc[10] = new GuitarChords ( "AMaj", 00, 45, 52, 57, 61, 64 );
	//                                  X   A   E   A   C#  E
	gc[11] = new GuitarChords ( "Am",   00, 45, 52, 57, 60, 64 );
	//                                  X   A   E   A   C   E
	gc[12] = new GuitarChords ( "A7",   00, 45, 52, 55, 61, 64 );
	//                                  X   A   E   G   C#  E
	gc[13] = new GuitarChords ( "B7",   00, 47, 51, 57, 59, 66 );
	//                                  X   B   D#  A   B   F#
	int sleepTime = 1000;
	for (int i = 0; i < gc.length; ++i) {
	    gc[i].playChord ( chan[0], 100 );
	    Thread.sleep(sleepTime);
	}
	chan[0].allNotesOff();

	System.out.println("here come some random 4 chord songs");
	console.next();
	gc[8].playChord ( chan[0], 100 );
	Thread.sleep(sleepTime);
	gc[6].playChord ( chan[0], 100 );
	Thread.sleep(sleepTime);
	gc[0].playChord ( chan[0], 100 );
	Thread.sleep(sleepTime);
	gc[4].playChord ( chan[0], 100 );
	Thread.sleep(sleepTime);
	chan[0].allNotesOff();
	System.out.println("OK, that wasn't really random");
	console.next();
	while ( 1 == 1 ) {
	    for (int i = 0; i < 4; ++i) {
		int randomChord = (int)(Math.random() * gc.length);
		//	    System.out.println(randomChord);
		gc[randomChord].playChord ( chan[0], 100 );
		Thread.sleep(sleepTime);
	    }
	    Thread.sleep(sleepTime * 2);
	    System.out.println();
	}

    }
}
