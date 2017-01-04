package miditest;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import javax.sound.midi.Transmitter;

public class MyReceiver implements Receiver{
	Transmitter t;
	Receiver r;
	public MyReceiver(Transmitter t, Receiver r){
		this.t = t;
		this.r = r;
		t.setReceiver(this);//transmitter of piano should send to me.
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		t.close();
	}
	@Override
	public void send(MidiMessage message, long timeStamp) {
		// i have received message, now mirror and back
		ShortMessage m = new ShortMessage();
		/*
		//if(message.getClass().equals(ShortMessage.class)){
			ShortMessage sm = (ShortMessage) message;
			if(sm.getCommand()==ShortMessage.NOTE_ON){
				try{
					m.setMessage(ShortMessage.NOTE_ON, 0, 60, 93);
				} catch (Exception e){}
			}
		//}
		 */
		byte[] mes = message.getMessage();
		
		if(mes[0]==-112){
			
			byte key = mes[1];
			byte dk = (byte) (key-62);
			byte mKey = (byte) (key-dk-dk);
			//62 - mirror around this key
			
			try{
				if(mes[2]>0){
					m.setMessage(ShortMessage.NOTE_ON, 0, mKey, mes[2]);//93
				} else {
					m.setMessage(ShortMessage.NOTE_OFF, 0, mKey, mes[2]);
				}
			} catch (Exception e){}
			r.send(m, timeStamp);
			System.out.println(mes[0]+" "+mes[1]+" "+mes[2]);
		} else {
			//r.send(m, timeStamp);
		}
	//	r.send(m, -1);//send music from piano back to its receiver.
		//@SuppressWarnings("unused")
		//int a = 0;
		
	}

}
