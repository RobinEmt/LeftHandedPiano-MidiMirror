package miditest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;

public class main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		MidiDevice deviceT = null;
		MidiDevice deviceR = null;
		MidiDevice deviceLaptop = null;
		boolean foundFirst = false;
		for (int i = 0; i < infos.length; i++) {
		    try {
		    	if(infos[i].toString().contains("UM-1")){
		    		if(!foundFirst){
		    			deviceT = MidiSystem.getMidiDevice(infos[i]);
		    			foundFirst=true;
		    		} else {
		    		//break;
		    			deviceR = MidiSystem.getMidiDevice(infos[i]);
		    			break;
		    		}
		    	}
		    	if(infos[i].toString().equals("Microsoft MIDI Mapper")){
		    		deviceLaptop = MidiSystem.getMidiDevice(infos[i]);
		    	}
		    	
		    } catch (MidiUnavailableException e) {
		    	System.out.println("Exception occured.");
		          // Handle or throw exception...
		    }
		    //if (device instanceof Synthesizer) {
		     //   synthInfos.add(infos[i]);
		    //}
		}
		 if (!(deviceR.isOpen())) {
			    try {
			      deviceR.open();
			  } catch (MidiUnavailableException e){}
			}
		 if (!(deviceT.isOpen())) {
			    try {
			      deviceT.open();
			  } catch (MidiUnavailableException e){}
			}
		 if (!(deviceLaptop.isOpen())) {
			    try {
			    	deviceLaptop.open();
			  } catch (MidiUnavailableException e){}
			}
		//Receiver r = new DumpReceiver(System.out);
		//Transmitter t = null;
		//Receiver r = null;
			 Transmitter t = deviceT.getTransmitter();
			 Receiver lr = deviceLaptop.getReceiver();
		      //Receiver r = deviceR.getReceiver();
	      Sequencer seq = MidiSystem.getSequencer(false);
	      //Sequence sequence = MidiSystem.getSequence(new File("midifile.mid"));
	      MyReceiver myR = new MyReceiver(t, lr);
	    
			 @SuppressWarnings("unused")
				int a = 0;
		while(true){
			
		}
	      //t.setReceiver(r);

		//System.out.println(MidiSystem.getMidiDeviceInfo());
		/*seq.open();
		InputStream is = new BufferedInputStream(new FileInputStream(new File("midifile.mid")));
		seq.setSequence(is);
		
		seq.start();*/

	}

}
