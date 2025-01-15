package api;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTokenSolver {
    private static final Logger LOGGER = Logger.getLogger(ClientTokenSolver.class.getName());
    private static final int DEFAULT_LOG_INTERVAL = 100000;

    public static void main(String[] args){
        //log time taken
        long start = System.currentTimeMillis();
        System.out.println("test: " + solveHashCash("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef", 20));
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + "ms");

    }
    public static String solveHashCash(String prefix, int length) {
        try {
            byte[] prefixBytes = hexStringToByteArray(prefix);
            byte[] seed = generateSeed();
            return solveHashCash(prefixBytes, length, seed, DEFAULT_LOG_INTERVAL);
        } catch (NoSuchAlgorithmException e) {
            //LOGGER.log(Level.SEVERE, "SHA-1 algorithm not found", e);
            return null;
        }
    }

    private static String solveHashCash(byte[] prefix, int length, byte[] seed, int logInterval) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        long l1 = ByteBuffer.wrap(seed).getLong();
        long l2 = 0;
        int iterationCount = 0;

        while (true) {
            sha1.reset();
            sha1.update(prefix);
            sha1.update(longToBytes(l1));
            sha1.update(longToBytes(l2));
            byte[] digest = sha1.digest();

            iterationCount++;

            if (iterationCount % logInterval == 0) {
                //LOGGER.log(Level.FINE, "Iteration: {0}, l1: {1}, l2: {2}, Digest: {3}",
                        //new Object[]{iterationCount, l1, l2, bytesToHex(digest)});
            }

            long bufLong = ByteBuffer.wrap(digest, 12, 8).getLong();

            if (Long.numberOfTrailingZeros(bufLong) >= length) {
                //LOGGER.log(Level.INFO, "Found matching hash after {0} iterations: {1}",
                //        new Object[]{iterationCount, bufLong});
                return bytesToHex(longToBytes(l1)) + bytesToHex(longToBytes(l2));
            }

            l1++;
            l2++;
        }
    }

    private static byte[] generateSeed() throws NoSuchAlgorithmException {
        byte[] fullSeed = MessageDigest.getInstance("SHA-1").digest(new byte[0]);
        byte[] seed = new byte[8];
        System.arraycopy(fullSeed, 12, seed, 0, 8);
        //LOGGER.log(Level.FINE, "Generated Seed: {0}", bytesToHex(seed));
        return seed;
    }

    private static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
