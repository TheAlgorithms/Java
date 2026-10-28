package com.thealgorithms.ciphers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Merkle signature scheme (MSS) turns a one-time signature scheme into a many-time scheme.
 *
 * <p>Key generation creates {@code 2^h} Winternitz one-time key pairs and hashes each WOTS public
 * key into a leaf of a binary hash tree of height {@code h}. The tree root is the single MSS public
 * key. Every signature uses the next unused leaf, so one key pair can sign {@code 2^h} messages.
 *
 * <p>A signature contains the leaf index, the WOTS signature, the WOTS public key of that leaf and
 * the authentication path: the {@code h} sibling hashes needed to recompute the root from the leaf.
 * The verifier checks the WOTS signature, hashes the WOTS public key into the leaf and walks up the
 * tree with the authentication path. Including the WOTS public key in the signature is an
 * educational simplification; XMSS instead recomputes it from the WOTS signature.
 *
 * <p>MSS is the core of XMSS (RFC 8391). This implementation is educational and must not be used in
 * production.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Merkle_signature_scheme">Wikipedia: Merkle signature scheme</a>
 *
 * @author dilaraacetin
 * @see WinternitzSignature
 * @see LamportSignature
 */
public final class MerkleSignatureScheme {

    // SHA-256 output length in bytes
    private static final int HASH_BYTES = 32;
    private static final int MIN_HEIGHT = 2;
    private static final int MAX_HEIGHT = 10;
    private static final int DEFAULT_HEIGHT = 4;
    private static final int DEFAULT_W = 16;

    private final int height;
    private final WinternitzSignature[] oneTimeKeys;
    // tree[level][node]: level 0 holds the leaves, level h holds the root
    private final byte[][][] tree;
    private int nextLeafIndex;

    /**
     * Generates a new key pair with height {@code h = 4} (16 signatures) and {@code w = 16}.
     */
    public MerkleSignatureScheme() {
        this(DEFAULT_HEIGHT, DEFAULT_W);
    }

    /**
     * Generates a new key pair that can sign {@code 2^h} messages.
     *
     * @param h the tree height; must be between 2 and 10
     * @param w the Winternitz parameter of the one-time keys; must be 4, 16 or 256
     * @throws IllegalArgumentException if {@code h} or {@code w} is not supported
     */
    public MerkleSignatureScheme(int h, int w) {
        if (h < MIN_HEIGHT || h > MAX_HEIGHT) {
            throw new IllegalArgumentException("h must be between " + MIN_HEIGHT + " and " + MAX_HEIGHT + ", got " + h);
        }
        this.height = h;
        int leafCount = 1 << h;
        oneTimeKeys = new WinternitzSignature[leafCount];
        tree = new byte[h + 1][][];
        tree[0] = new byte[leafCount][];
        for (int i = 0; i < leafCount; i++) {
            oneTimeKeys[i] = new WinternitzSignature(w);
            tree[0][i] = hashPublicKey(oneTimeKeys[i].getPublicKey());
        }
        for (int level = 1; level <= h; level++) {
            tree[level] = new byte[tree[level - 1].length / 2][];
            for (int i = 0; i < tree[level].length; i++) {
                tree[level][i] = hash(tree[level - 1][2 * i], tree[level - 1][2 * i + 1]);
            }
        }
    }

    /**
     * Returns a copy of the public key, which is the root of the Merkle tree.
     *
     * @return the 32-byte tree root
     */
    public byte[] getPublicKey() {
        return tree[height][0].clone();
    }

    /**
     * Returns how many messages this key pair can still sign.
     *
     * @return the number of unused one-time keys
     */
    public int remainingSignatures() {
        return oneTimeKeys.length - nextLeafIndex;
    }

    /**
     * Signs a message with the next unused one-time key.
     *
     * @param message the message to sign
     * @return the Merkle signature
     * @throws IllegalArgumentException if the message is null
     * @throws IllegalStateException if all {@code 2^h} one-time keys have been used
     */
    public MerkleSignature sign(byte[] message) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        if (nextLeafIndex >= oneTimeKeys.length) {
            throw new IllegalStateException("All " + oneTimeKeys.length + " signatures of this key pair have been used");
        }
        int leafIndex = nextLeafIndex++;
        WinternitzSignature oneTimeKey = oneTimeKeys[leafIndex];
        byte[][] wotsSignature = oneTimeKey.sign(message);

        // collect the sibling of the current node on every level, from the leaf up to the root
        byte[][] authPath = new byte[height][];
        int index = leafIndex;
        for (int level = 0; level < height; level++) {
            authPath[level] = tree[level][index ^ 1];
            index >>= 1;
        }
        return new MerkleSignature(leafIndex, wotsSignature, oneTimeKey.getPublicKey(), authPath);
    }

    /**
     * Verifies a Merkle signature against a public key (tree root). The tree height is taken from
     * the length of the authentication path.
     *
     * @param message the signed message
     * @param signature the signature to check
     * @param publicKey the tree root of the signer
     * @param w the Winternitz parameter used to create the key pair; must be 4, 16 or 256
     * @return true if the signature is valid for the message and public key, false otherwise
     * @throws IllegalArgumentException if an argument is null, {@code w} is unsupported, or the
     *     public key or WOTS part of the signature is malformed
     */
    public static boolean verify(byte[] message, MerkleSignature signature, byte[] publicKey, int w) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        if (signature == null) {
            throw new IllegalArgumentException("signature must not be null");
        }
        if (publicKey == null || publicKey.length != HASH_BYTES) {
            throw new IllegalArgumentException("publicKey must be exactly " + HASH_BYTES + " bytes long");
        }
        if (!WinternitzSignature.verify(message, signature.wotsSignature, signature.wotsPublicKey, w)) {
            return false;
        }

        byte[] node = hashPublicKey(signature.wotsPublicKey);
        int index = signature.leafIndex;
        for (byte[] sibling : signature.authPath) {
            // an even index is a left child, an odd index a right child
            node = (index & 1) == 0 ? hash(node, sibling) : hash(sibling, node);
            index >>= 1;
        }
        return MessageDigest.isEqual(node, publicKey);
    }

    /**
     * A Merkle signature: leaf index, WOTS signature, WOTS public key and authentication path.
     * All arrays are copied on construction and on access.
     */
    public static final class MerkleSignature {
        private final int leafIndex;
        private final byte[][] wotsSignature;
        private final byte[][] wotsPublicKey;
        private final byte[][] authPath;

        /**
         * Creates a signature from its parts.
         *
         * @param leafIndex the index of the leaf used for signing
         * @param wotsSignature the Winternitz signature of the message
         * @param wotsPublicKey the Winternitz public key of the leaf
         * @param authPath the sibling hashes from the leaf up to the root
         * @throws IllegalArgumentException if an argument is null, the authentication path length is
         *     not between 2 and 10, an authentication path value is not 32 bytes long, or the leaf
         *     index is outside the tree
         */
        public MerkleSignature(int leafIndex, byte[][] wotsSignature, byte[][] wotsPublicKey, byte[][] authPath) {
            if (authPath == null || authPath.length < MIN_HEIGHT || authPath.length > MAX_HEIGHT) {
                throw new IllegalArgumentException("authPath must contain between " + MIN_HEIGHT + " and " + MAX_HEIGHT + " values");
            }
            for (byte[] sibling : authPath) {
                if (sibling == null || sibling.length != HASH_BYTES) {
                    throw new IllegalArgumentException("authPath values must be exactly " + HASH_BYTES + " bytes long");
                }
            }
            if (leafIndex < 0 || leafIndex >= 1 << authPath.length) {
                throw new IllegalArgumentException("leafIndex must be between 0 and " + ((1 << authPath.length) - 1) + ", got " + leafIndex);
            }
            this.leafIndex = leafIndex;
            this.wotsSignature = deepCopy(wotsSignature, "wotsSignature");
            this.wotsPublicKey = deepCopy(wotsPublicKey, "wotsPublicKey");
            this.authPath = deepCopy(authPath, "authPath");
        }

        /**
         * @return the index of the leaf used for signing
         */
        public int getLeafIndex() {
            return leafIndex;
        }

        /**
         * @return a copy of the Winternitz signature
         */
        public byte[][] getWotsSignature() {
            return deepCopy(wotsSignature, "wotsSignature");
        }

        /**
         * @return a copy of the Winternitz public key of the leaf
         */
        public byte[][] getWotsPublicKey() {
            return deepCopy(wotsPublicKey, "wotsPublicKey");
        }

        /**
         * @return a copy of the authentication path, ordered from the leaf level up to the root
         */
        public byte[][] getAuthPath() {
            return deepCopy(authPath, "authPath");
        }
    }

    /**
     * Hashes a WOTS public key into a Merkle leaf. Shared by key generation and verification.
     */
    private static byte[] hashPublicKey(byte[][] wotsPublicKey) {
        return hash(wotsPublicKey);
    }

    private static byte[] hash(byte[]... parts) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            for (byte[] part : parts) {
                digest.update(part);
            }
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("SHA-256 is required by the Java SE specification", e);
        }
    }

    private static byte[][] deepCopy(byte[][] values, String name) {
        if (values == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        byte[][] copy = new byte[values.length][];
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                throw new IllegalArgumentException(name + " values must not be null");
            }
            copy[i] = values[i].clone();
        }
        return copy;
    }
}
