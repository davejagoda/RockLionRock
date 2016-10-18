import java.util.Scanner;
import javax.sound.midi.*;

public class HeyYa {
    // fields
    public String name;
    public int[] notes;
    // constructor
    // public GuitarChords ( String chordName, int[] chordNotes) {
    public HeyYa ( String chordName, int... chordNotes) {
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
            } else {
                System.out.printf ( "00 ");
            }
        }
        System.out.printf ( "volume: %d\n", volume);
    }
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
        chan[0].programChange(0, 27); // electric guitar = MIDI value + 1
        System.out.printf("current channel is %d\n", chan[0].getProgram());

        GuitarChords[] gc = new GuitarChords[4];
        gc[ 0] = new GuitarChords ( "CMaj", 00, 48, 52, 55, 60, 64 );
        //                                  X   C   E   G   C   E
        gc[ 1] = new GuitarChords ( "DMaj", 00, 00, 50, 57, 62, 66 );
        //                                  X   X   D   A   D   F#
        gc[ 2] = new GuitarChords ( "EMaj", 40, 47, 52, 56, 59, 64 );
        //                                  E   B   E   G#  B   E
        gc[ 3] = new GuitarChords ( "GMaj", 43, 47, 50, 55, 59, 67 );
        //                                  G   B   D   G   B   G
        int sleepTime = 100;
        int song[] = new int[] {3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2};
        while (true) {
            for (int note = 0; note < song.length; ++note) {
                gc[song[note]].playChord ( chan[0], 100 );
                Thread.sleep(sleepTime);
            }
            System.out.println("Hey Ya!");
        }
    }
}
