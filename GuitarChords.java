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
    public void playChord ( MidiChannel channel,  int volume ) {
	System.out.printf ( "%5s: ", name );
	for ( int i = 0; i < notes.length; ++i) {
	    if ( notes[i] > 0 ) {
		channel.noteOn ( notes[i], volume );
		System.out.printf ( "%d ", notes[i]);
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
	gc[ 0] = new GuitarChords ( "CMaj", 00, 60, 64, 67, 72, 76 );
	//                                  X   C   E   G   C   E
	gc[ 1] = new GuitarChords ( "C7",   00, 60, 64, 70, 72, 76 );
	//                                  X   C   E   Bb  C   E
	gc[ 2] = new GuitarChords ( "DMaj", 00, 00, 62, 69, 74, 78 );
	//                                  X   X   D   A   D   F#
	gc[ 3] = new GuitarChords ( "Dm",   00, 00, 62, 69, 74, 77 );
	//                                  X   X   D   A   D   F
	gc[ 4] = new GuitarChords ( "D7",   00, 00, 62, 69, 72, 78 );
	//                                  X   X   D   A   C   F#
	gc[ 5] = new GuitarChords ( "EMaj", 52, 59, 64, 68, 71, 76 );
	//                                  E   B   E   G#  B   E
	gc[ 6] = new GuitarChords ( "Em",   52, 59, 64, 67, 71, 76 );
	//                                  E   B   E   G   B   E
	gc[ 7] = new GuitarChords ( "E7",   52, 59, 62, 68, 71, 76 );
	//                                  E   B   D   G#  B   E
	gc[ 8] = new GuitarChords ( "GMaj", 55, 59, 62, 67, 71, 79 );
	//                                  G   B   D   G   B   G
	gc[ 9] = new GuitarChords ( "G7",   55, 59, 62, 67, 71, 77 );
	//                                  G   B   D   G   B   F
	gc[10] = new GuitarChords ( "AMaj", 00, 57, 64, 69, 73, 76 );
	//                                  X   A   E   A   C#  E
	gc[11] = new GuitarChords ( "Am",   00, 57, 64, 69, 72, 76 );
	//                                  X   A   E   A   C   E
	gc[12] = new GuitarChords ( "A7",   00, 57, 64, 67, 73, 76 );
	//                                  X   A   E   G   C#  E
	gc[13] = new GuitarChords ( "B7",   00, 59, 63, 69, 71, 78 );
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
