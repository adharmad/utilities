
package util.jarmd5;
/**
 * BASE64Encoder.java
 * Created on May 28, 2004
 * @author Amol Dharmadhikari <adharma@cs.usfca.edu>
 *
 * Code and Comments
 */



public class BASE64Encoder {
  public String encode(byte[] raw) {
    return Base64.encode(raw);
  }
}