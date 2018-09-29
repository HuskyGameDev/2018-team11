package lutebox.util;

public class BitField {

    private long[] bits = new long[1]; 

    public boolean get(int index) {
        int i = index >>> 6; 
        
        if (i >= bits.length) return false; 
        
        return (bits[i] & (1L << (index & 63))) != 0; 
    }
    
    public void set(int index, boolean value) {
        if (value) {
            set(index); 
        }
        else {
            clear(index); 
        }
    }
    
    public void set(int index) {
        int i = index >>> 6; 
        checkSize(i + 1); 
        
        bits[i] |= 1L << (index & 63); 
    }
    
    public void clear(int index) {
        int i = index >>> 6; 
        checkSize(i + 1); 
        
        bits[i] &= ~(1L << (index & 63)); 
    }
    
    public void toggle(int index) {
        int i = index >>> 6; 
        checkSize(i + 1); 
        
        bits[i] ^= 1L << (index & 63); 
    }
    
    public void clear() {
        for (int i = 0; i < bits.length; i++) {
            bits[i] = 0; 
        }
    }
    
    public boolean intersects(BitField other) {
        int size = Math.min(bits.length, other.bits.length); 
        
        for (int i = 0; i < size; i++) {
            if ((bits[i] & other.bits[i]) != 0) return true; 
        }
        
        return false; 
    }
    
    public boolean containsAll(BitField other) {
        for (int i = bits.length; i < other.bits.length; i++) {
            if (other.bits[i] != 0) return false; 
        }
        
        int size = Math.min(bits.length, other.bits.length); 
        
        for (int i = 0; i < size; i++) {
            if ((bits[i] & other.bits[i]) != other.bits[i]) return false; 
        }
        
        return true; 
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        
        sb.append("["); 
        
        boolean first = true; 
        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < 64; j++) {
                if ((bits[i] & (1L << j)) != 0) {
                    if (!first) sb.append(", ");
                    sb.append(i * 64 + j); 
                    first = false; 
                }
            }
        }
        
        sb.append("]"); 
        
        return sb.toString(); 
    }
    
    @Override 
    public boolean equals(Object other) {
        if (other == this) return true; 
        if (other == null) return false; 
        if (!(other instanceof BitField)) return false; 
        
        BitField bf = (BitField) other; 
        
        return this.containsAll(bf) && bf.containsAll(this); 
    }
    
    @Override
    public int hashCode() {
        int hash = 0; 
        
        for (long val : bits) {
            hash = hash * 31 + Long.hashCode(val); 
        }
        
        return hash; 
    }
    
    private void checkSize(int minSize) {
        if (bits.length < minSize) {
            long[] newBits = new long[minSize]; 
            for (int i = 0; i < bits.length; i++) {
                newBits[i] = bits[i]; 
            }
            bits = newBits; 
        }
    }
    
    public static void main(String[] args) {
        BitField a = new BitField(); 
        BitField b = new BitField(); 
        
        a.set(1); 
        a.set(2); 
        a.set(64);
        
        b.set(1); 
        b.set(2); 
        
        System.out.println(a);
        System.out.println(b); 
        System.out.println(a.intersects(b));
        System.out.println(a.containsAll(b)); 
        System.out.println(b.containsAll(a));
    }
    
}
