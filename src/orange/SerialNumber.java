package orange;

import java.math.BigInteger;

/**
 * The Serial Number class assigns a serial number.
 *
 * @author Andrew Hwang
 * @version 1.1 Sept 24, 2015
 */
public class SerialNumber implements Comparable<SerialNumber>{

    /**
     * Fields:
     * BigInteger field labeled serialNumber.
     */
    BigInteger serialNumber;

    /**
     * Assigns a serial number of type BigInteger to that of which it is called on.
     * @param serialNumber
     */
    public SerialNumber(BigInteger serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the serial number.
     * @return the product's serial number.
     */
    public BigInteger getSerialNumber(){
        return this.serialNumber;
    }

    /**
     * Calculates the greatest common divisor of two serial numbers.
     * @param other
     * @return greatest common divisor of the two serial numbers.
     */
    public BigInteger gcd(SerialNumber other){
        return this.getSerialNumber().gcd(other.getSerialNumber());
    }

    /**
     * Calculates the modulus of two serial numbers.
     * @param other
     * @return modulus of the two serial numbers.
     */
    public BigInteger mod(SerialNumber other){
        return this.getSerialNumber().mod(other.getSerialNumber());
    }

    /**
     * Tests whether the bit at the specified index is set within the serial number.
     * @param bit
     * @return true if the bit is set or false if the bit is not set.
     */
    public boolean testBit(int bit){
        return this.getSerialNumber().testBit(bit);
    }

    /**
     * Checks if a serial number is even.
     * Does so by having the current serial number mod by 2 and check if it is zero.
     * @return true if the mod is zero or false if it is not.
     */
    public boolean isEven(){
        if (this.mod(new SerialNumber(BigInteger.valueOf(2))).equals(BigInteger.ZERO))
            return true;
        else
            return false;
    }

    /**
     * Checks if a serial number is odd.
     * Does so by having the current serial number mod by 2 and check if it is zero.
     * @return true if the mod is not zero or false if it is.
     */
    public boolean isOdd(){
        if (this.mod(new SerialNumber(BigInteger.valueOf(2))).equals(BigInteger.ONE))
            return true;
        else
            return false;
    }

    /**
     * Overrides compareTo to compare two serial numbers.
     * @param o
     * @return negative integer, 0, positive integer
     * if this serial number is less than, equal to, or greater than the specified serial number.
     */
    @Override
    public int compareTo(SerialNumber o) {
        return this.serialNumber.compareTo(o.getSerialNumber());
    }
}
