package senscript;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

import crypto.ECC;


import device.SensorNode;
import simulation.WisenSimulation;

public class Command_DEC extends Command {

	protected String arg1 = "" ;
	
	public Command_DEC(SensorNode sensor, String arg1) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
	}

	@Override
	public double execute() {
		String llaveSimSer=sensor.getScript().getVariableValue("$sec") ;
		byte[] key =ECC.hexStringToByteArray(llaveSimSer);
		SecretKey llaveSimetricaServ= new SecretKeySpec(key, 0, key.length, "AES");
		
		byte[] textoClaro=ECC.descifrar(llaveSimetricaServ, ECC.hexStringToByteArray(sensor.getScript().getVariableValue(arg1)));
		String v = ECC.hexstringToString(ECC.bytesToHex(textoClaro)) ;
		
		sensor.getScript().addVariable("claro", v);
		return 0;
	}

	@Override
	public String toString() {
		return "GET TIME";
	}

}