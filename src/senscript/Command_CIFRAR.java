package senscript;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import crypto.ECC;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_CIFRAR extends Command {

	protected String arg1,arg2 = "" ;
	
	public Command_CIFRAR(SensorNode sensor, String arg1,String arg2) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
		this.arg2= arg2;
	}

	@Override
	public double execute() {
		
		String x_str = sensor.getScript().getVariableValue(arg2);
		String[] tab = sensor.getScript().getVector("autos");
		String val = (String) tab[Double.valueOf(x_str).intValue()];
		byte[] key =ECC.hexStringToByteArray(val);
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