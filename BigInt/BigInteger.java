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
            this.number[i] = Character.getNumericValue(trimmedVal.charAt(trimmedVal.length()- i - 1));
        }
    }

    public BigInteger (int[] number2, boolean negative2){
        if (number2 == null) {
            throw new NullPointerException();
        }

        int counter = 0 ;
        for (int i = number2.length - 1 ; i >= 1 ; i-- ) {
            if (number2[i] == 0) {
                counter++;
            } else {
                break;
            }
        }

        if (counter > 0) {
            int[] resultTrim = new int[number2.length - counter] ;
            for (int i = 0 ; i < resultTrim.length ; i++ ) {
                resultTrim[i] = number2[i];
            }
            number2 = resultTrim;
        }
        this.number = number2;
        this.negative = negative2;
    }

    public BigInteger plus ( BigInteger val ){ // returns a BigInteger whose value is this + val
        System.out.println(" number one " + this.toString() + " number 2 " + val.toString());
        int[] result = null;
        boolean resultNegative = false;
        int carry = 0;
        if (this.negative == val.negative) {
            resultNegative = this.negative;
            if (this.number.length == val.number.length) {
                result = new int[this.number.length + 1];
                for (int i = 0 ; i < this.number.length ; i++ ) {
                    result[i] = carry + this.number[i] + val.number[i];
                    if (result[i] >= 10) {
                        result[i] -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                }
                if (carry == 1) {
                    result[this.number.length] = carry;
                } else {
                    result[this.number.length] = 0;
                }
            } else if (this.number.length > val.number.length) {
                result = new int[this.number.length + 1];
                for (int i = 0 ; i < val.number.length ; i++ ) {
                    result[i] = carry + this.number[i] + val.number[i];
                    if (result[i] >= 10) {
                        result[i] -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                }
                for (int i = val.number.length ; i < this.number.length ; i++ ) {
                    result[i] = carry + this.number[i];
                    if (result[i] >= 10) {
                        result[i] -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                }
                if (carry == 1) {
                    result[this.number.length] = carry;
                } else {
                    result[this.number.length] = 0;
                }
            } else if (val.number.length > this.number.length) {
                result = new int[val.number.length + 1];
                for (int i = 0 ; i < this.number.length ; i++ ) {
                    result[i] = carry + this.number[i] + val.number[i];
                    if (result[i] >= 10) {
                        result[i] -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                }
                for (int i = this.number.length ; i < val.number.length ; i++ ) {
                    result[i] = carry + val.number[i];
                    if (result[i] >= 10) {
                        result[i] -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                }
                if (carry == 1) {
                    result[val.number.length] = carry;
                } else {
                    result[val.number.length] = 0;
                }
            }
        } else if (this.negative == false && val.negative == true) {
            val.negative = false;
            return this.minus(val);
        } else if (this.negative == true && val.negative == false) {
            this.negative = false;
            return val.minus(this);
        }
        return new BigInteger(result, resultNegative);
    }

    public BigInteger minus ( BigInteger val ){ // returns a BigInteger whose value is this - val
        System.out.println(" number one " + this.toString() + " number 2 " + val.toString());
        int[] result = null;
        boolean resultNegative = false;
        int carry = 0;

        if (val.greaterThan(this)) {
            return val.minus(this);
        }

        if (this.negative == val.negative) {
            resultNegative = this.negative;
            if (this.number.length == val.number.length) {
                result = new int[this.number.length];
                if (this.number[0] < val.number[0]) {
                    carry = 10;
                }
                for (int i = 0 ; i < this.number.length ; i++ ) {
                    if (this.number[i] == 0 && !(this.number[i] == val.number[i])) {
                        carry = 10;
                    }else if (this.number[i] == 0 && this.number[i] == val.number[i]) {
                        carry = 0;
                    }
                    result[i] = carry + this.number[i] - val.number[i];
                    if (this.number[i] < val.number[i] && this.number[i++] < val.number[i++]) {
                        carry = 9;
                    } else if (this.number[i] < val.number[i] && !(this.number[i++] < val.number[i++])
                                    || this.number[i] == 0 && !(this.number[i] == val.number[i])) {
                        carry = -1;
                    } else {
                        carry = 0;
                    }
                }
            }
        }
        System.out.println(new BigInteger(result, resultNegative).toString() + "result" );
        return new BigInteger(result, resultNegative);
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
            result += this.number[this.number.length - 1 - i];
        }
        //System.out.println(result);
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
                for (int i = this.number.length - 1 ; i >= 0 ; i--) {
                    if (this.number[i] > val.number[i]) {
                        return true;
                    } else if (val.number[i] > this.number[i]) {
                        return false;
                    }
                }
            }
        } else if (this.negative && val.negative ) {
            if (this.number.length > val.number.length) {
                return false;
            } else if (val.number.length > this.number.length) {
                return true;
            } else if (this.number.length == val.number.length) {
                for (int i = this.number.length - 1 ; i >= 0 ; i--) {
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
