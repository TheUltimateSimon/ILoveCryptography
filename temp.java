import java.math.BigInteger;

public class temp {
    public static void main(String[] args) {
        // Create the elliptic curve and generator point
        EllipticCurve myCurve = new EllipticCurve(new BigInteger("65543"), new BigInteger("12271"), new BigInteger("36523"));
        Point G = new Point(myCurve, new BigInteger("34555"), new BigInteger("18773"));
    
        // Points sent by Alice and Bob
        Point pointSentByAlice = new Point(myCurve, new BigInteger("26035"), new BigInteger("25302"));
        Point pointSentByBob = new Point(myCurve, new BigInteger("19563"), new BigInteger("2605"));
    
        // Find Alice's private key
        int counterAlice = 1;
        Point currentPoint = new Point(G);
        while (!currentPoint.equals(pointSentByAlice)) {
            currentPoint = currentPoint.add(G);
            counterAlice++;
        }
        System.out.println("Alice's private key: " + counterAlice);
    
        // Find Bob's private key
        int counterBob = 1;
        currentPoint = new Point(G);
        while (!currentPoint.equals(pointSentByBob)) {
            currentPoint = currentPoint.add(G);
            counterBob++;
        }
        System.out.println("Bob's private key: " + counterBob);
        System.out.println("Shared Key: " + (pointSentByAlice.multiply(BigInteger.valueOf(counterBob))));
        System.out.println("Shared Key: " + (pointSentByBob.multiply(BigInteger.valueOf(counterAlice))));
        Point sharedKey = (pointSentByAlice.multiply(BigInteger.valueOf(counterBob)));
        BigInteger x = sharedKey.returnX();
        String newX = x.toString(2);
        System.out.println(newX);
        String selected = newX.substring(0, 16);
        System.out.println("16 bits of DES key: " + selected);
    }
    
}
