package senscript;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import crypto.ECC;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_CIFRAR extends Command {

	protected String arg1 = "" ;
	
	public Command_CIFRAR(SensorNode sensor, String arg1) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
	}

	@Override
	public double execute() {
		String llaveSimSer=sensor.getScript().getVariableValue("$sec") ;
		byte[] key =ECC.hexStringToByteArray(llaveSimSer);
		SecretKey llaveSimetricaServ= new SecretKeySpec(key, 0, key.length, "AES");
		
		byte[] textoCifrado=ECC.cifrar(llaveSimetricaServ, arg1);
		String v = ECC.bytesToHex(textoCifrado) ;
		sensor.getScript().addVariable("cifrado", v);
		return 0;
	}

	@Override
	public String toString() {
		return "GET TIME";
	}

}