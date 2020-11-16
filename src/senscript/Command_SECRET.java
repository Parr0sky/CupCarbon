package senscript;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.bouncycastle.util.encoders.Hex;

import crypto.*;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_SECRET extends Command {

	protected String arg1 = "" ;
	protected String arg2 = "" ;

	public Command_SECRET(SensorNode sensor, String arg1, String arg2) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
		this.arg2= arg2;
	}

	@Override
	public double execute() {
		byte[] uno = ECC.hexStringToByteArray(sensor.getScript().getVariableValue(arg1));
		
		if (uno !=null) {
			String eteSech=sensor.getScript().getVariableValue("$priv");
			byte[] secreto = ECC.doECDH( ECC.hexStringToByteArray(eteSech), uno);

			String v = ECC.bytesToHex(secreto) ;
			sensor.getScript().addVariable("sec", v);
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