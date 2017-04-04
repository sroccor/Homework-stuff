public class BigInteger{

    private int[] number;
    private boolean negative;

    public static final BigInteger ZERO = new BigInteger("0"); // a classwide constant for zero
    public static final BigInteger ONE = new BigInteger("1");// a classwide constant for one
    public static final BigInteger TEN = new BigInteger("10"); // a classwide constant for ten

    public BigInteger ( String val ){ // one of several java.math.BigInteger constructors
        String trimmedVal = val.trim();
        //System.out.println(trimmedVal);
        if (trimmedVal.matches(".*[a-z].*") || trimmedVal.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException();
        } else if (trimmedVal.charAt(0) == '-') {
            this.negative = true;
            trimmedVal = trimmedVal.substring(1);
        } else if (trimmedVal.charAt(0) == '+') {
            trimmedVal = trimmedVal.substring(1);
            this.negative = false;
        } else {
            this.negative = false;
        }

        if (trimmedVal.charAt(0) == '0') {
            while (trimmedVal.charAt(0) == '0' && trimmedVal.length() > 1) {
                trimmedVal = trimmedVal.substring(1);
            }
        } //System.out.println(trimmedVal);
        
        this.number = new int[trimmedVal.length()];
        for (int i = 0 ; i < trimmedVal.length() ; i++ ) {
            this.number[i] = Character.getNumericValue(trimmedVal.charAt(i));
        }
    }

    public BigInteger plus ( BigInteger val ){ // returns a BigInteger whose value is this + val
        throw new UnsupportedOperationException();
        //TODO: Complete this method!
    }

    public BigInteger minus ( BigInteger val ){ // returns a BigInteger whose value is this - val
        throw new UnsupportedOperationException();
        //TODO: Complete this method!
    }

    public BigInteger times ( BigInteger val ){ // returns a BigInteger whose value is this * val
        throw new UnsupportedOperationException();
        //TODO: Complete this method!
    }

    public BigInteger divideBy ( BigInteger val ){ // returns a BigInteger whose value is this / val
        throw new UnsupportedOperationException();
        //TODO: Complete this method!
    }

    public BigInteger remainder ( BigInteger val ){ // returns a BigInteger whose value is this % val
        throw new UnsupportedOperationException();
        //TODO: Complete this method!
    }

    @Override
    public String toString (){ // returns the decimal string represention of this BigInteger
        String result =  "";
        if (this.negative == true) {
            result += "-";
        }
        for (int i = 0; i < this.number.length ; i++ ) {
            result += this.number[i];
        }
        System.out.println(result);
        return result;
    }

    public boolean greaterThan ( BigInteger val ){ // returns true if this BigInteger is numerically greater than val
        if (val.negative && !this.negative) {
            return true;
        } else if (this.negative && !val.negative) {
            return false;
        } else if ( !this.negative && !val.negative ) {
            if (this.number.length > val.number.length) {
                return true;
            } else if (val.number.length > this.number.length) {
                return false;
            } else if (this.number.length == val.number.length) {
                for (int i = 0 ; i < this.number.length ; i++ ) {
                    if (this.number[i] > val.number[i]) {
                        return true;
                    } else if (val.number[i] > this.number[i]) {
                        return false;
                    } else if (val.number[i] == this.number[i]) {

                    }
                }
            }
        } else if (this.negative && val.negative ) {
            if (this.number.length > val.number.length) {
                return false;
            } else if (val.number.length > this.number.length) {
                return true;
            } else if (this.number.length == val.number.length) {
                for (int i = 0 ; i < this.number.length ; i++ ) {
                    if (this.number[i] > val.number[i]) {
                        return false;
                    } else if (val.number[i] > this.number[i]) {
                        return true;
                    }
                }
            }
        } return false;
    }

    public boolean lessThan ( BigInteger val ){ // returns true if this BigInteger is numerically less than val
        return !this.greaterThan(val) && !this.equals(val);
    }

    public boolean equals ( Object x ){ // returns true iff x is a BigInteger whose value is numerically equal to this BigInteger
        if ( x instanceof BigInteger) {
            BigInteger object = (BigInteger) x;
            return this.toString().equals(object.toString());
        } else {
            return false;
        }
    }

    public static BigInteger valueOf ( long val ){ // "static factory" for constructing BigIntegers out of longs
        BigInteger result = new BigInteger(Long.toString(val));
        return result ;
    }
}
