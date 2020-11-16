package senscript;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

import crypto.ECC;


import device.SensorNode;
import simulation.WisenSimulation;

public class Command_DEC extends Command {

	protected String arg1,arg2 = "" ;
	
	public Command_DEC(SensorNode sensor, String arg1, String arg2) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
		this.arg2=arg2;
	}

	@Override
	public synchronized double execute() {
		String x_str = sensor.getScript().getVariableValue(arg2);
		
		String[] tab = sensor.getScript().getVector("autos");
		String val = tab[Double.valueOf(x_str).intValue()];
		
		byte[] key =ECC.hexStringToByteArray(val);
		SecretKey llaveSimetricaServ= new SecretKeySpec(key, 0, key.length, "AES");
		try {
		byte[] textoClaro=ECC.descifrar(llaveSimetricaServ, ECC.hexStringToByteArray(sensor.getScript().getVariableValue(arg1)));
		String v = ECC.hexstringToString(ECC.bytesToHex(textoClaro)) ;
		
		sensor.getScript().addVariable("claro", v);
		return 0;
		}catch (Exception e) {
			System.out.println("Entra al catch");
			sensor.getScript().addVariable("claro", "ERR");
			return 0;
		}
	}

	@Override
	public String toString() {
		return "GET TIME";
	}

}