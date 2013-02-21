package edsdk.a;
import com.sun.jna.Structure;
/**
 * <i>native declaration : canonsdk-2.9\Windows\EDSDK\Header\EDSDKTypes.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class EdsRect extends Structure {
	/// C type : EdsPoint
	public EdsPoint point;
	/// C type : EdsSize
	public EdsSize size;
	public EdsRect() {
		super();
		initFieldOrder();
	}
	protected void initFieldOrder() {
		setFieldOrder(new java.lang.String[]{"point", "size"});
	}
	/**
	 * @param point C type : EdsPoint<br>
	 * @param size C type : EdsSize
	 */
	public EdsRect(EdsPoint point, EdsSize size) {
		super();
		this.point = point;
		this.size = size;
		initFieldOrder();
	}
	public static class ByReference extends EdsRect implements Structure.ByReference {

	};
	public static class ByValue extends EdsRect implements Structure.ByValue {

	};
}
