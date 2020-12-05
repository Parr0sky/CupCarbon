package senscript;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import crypto.*;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_LLAVESR extends Command {

	protected String arg1 = "" ;
	protected String arg2 = "" ;

	public Command_LLAVESR(SensorNode sensor, String arg1, String arg2) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
		this.arg2= arg2;
	}

	@Override
	public double execute() {
		KeyPair llaves= PK.generateKey();
		if (llaves !=null) {
			PrivateKey prk=llaves.getPrivate();
			PublicKey pk= llaves.getPublic();


			String v = PK.bytesToHex(prk.getEncoded());
			String v2= PK.bytesToHex(pk.getEncoded());
			sensor.getScript().addVariable(arg1, v);
			sensor.getScript().addVariable(arg2, v2);
			return 0;
		}
		else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "GET TIME";
	}

}