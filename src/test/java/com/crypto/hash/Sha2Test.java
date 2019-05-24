package src.test.java.com.crypto.hash;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import src.main.java.com.crypto.hash.Sha2;


public class Sha2Test {

    /**
     * The following test vectors for the SHA-2 family are taken from:
     * https://www.di-mgt.com.au/sha_testvectors.html
     */
    private static byte[][] vector;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("@BeforeClass setUpClass");

        StringBuilder builder = new StringBuilder();
        vector = new byte[5][];

        vector[0] = "abc".getBytes();
        vector[1] = "".getBytes();
        vector[2] = "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq".getBytes();
        vector[3] = "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu".getBytes();
        for (int i = 0; i < 1000_000; i++) {
            builder.append("a");
        }
        vector[4] = builder.toString().getBytes();

    }

    @Test
    public void TestSha224Vector1() {
        String digest = "23097d223405d8228642a477bda255b32aadbce4bda0b3f7e36c9da7";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA224(vector[0]));
    }

    @Test
    public void TestSha224Vector2() {
        String digest = "d14a028c2a3a2bc9476102bb288234c415a2b01f828ea62ac5b3e42f";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA224(vector[1]));
    }

    @Test
    public void TestSha224Vector3() {
        String digest = "75388b16512776cc5dba5da1fd890150b0c6455cb4f58b1952522525";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA224(vector[2]));
    }

    @Test
    public void TestSha224Vector4() {
        String digest = "c97ca9a559850ce97a04a96def6d99a9e0e0e2ab14e6b8df265fc0b3";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA224(vector[3]));
    }

    @Test
    public void TestSha224Vector5() {
        String digest = "20794655980c91d8bbb4c1ea97618a4bf03f42581948b2ee4ee7ad67";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA224(vector[4]));
    }


    @Test
    public void TestSha256Vector1() {
        String digest = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA256(vector[0]));
    }

    @Test
    public void TestSha256Vector2() {
        String digest = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA256(vector[1]));
    }

    @Test
    public void TestSha256Vector3() {
        String digest = "248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA256(vector[2]));
    }

    @Test
    public void TestSha256Vector4() {
        String digest = "cf5b16a778af8380036ce59e7b0492370b249b11e8f07a51afac45037afee9d1";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA256(vector[3]));
    }

    @Test
    public void TestSha256Vector5() {
        String digest = "cdc76e5c9914fb9281a1c7e284d73e67f1809a48a497200e046d39ccc7112cd0";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA256(vector[4]));
    }

    @Test
    public void TestSha384Vector1() {
        String digest = "cb00753f45a35e8bb5a03d699ac65007272c32ab0eded1631a8b605a43ff5bed8086072ba1e7cc2358baeca134c825a7";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA384(vector[0]));
    }

    @Test
    public void TestSha384Vector2() {
        String digest = "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA384(vector[1]));
    }

    @Test
    public void TestSha384Vector3() {
        String digest = "3391fdddfc8dc7393707a65b1b4709397cf8b1d162af05abfe8f450de5f36bc6b0455a8520bc4e6f5fe95b1fe3c8452b";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA384(vector[2]));
    }

    @Test
    public void TestSha384Vector4() {
        String digest = "09330c33f71147e83d192fc782cd1b4753111b173b3b05d22fa08086e3b0f712fcc7c71a557e2db966c3e9fa91746039";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA384(vector[3]));
    }

    @Test
    public void TestSha384Vector5() {
        String digest = "9d0e1809716474cb086e834e310a4a1ced149e9c00f248527972cec5704c2a5b07b8b3dc38ecc4ebae97ddd87f3d8985";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA384(vector[4]));
    }


    @Test
    public void TestSha512Vector1() {
        String digest = "ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA512(vector[0]));
    }

    @Test
    public void TestSha512Vector2() {
        String digest = "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA512(vector[1]));
    }

    @Test
    public void TestSha512Vector3() {
        String digest = "204a8fc6dda82f0a0ced7beb8e08a41657c16ef468b228a8279be331a703c33596fd15c13b1b07f9aa1d3bea57789ca031ad85c7a71dd70354ec631238ca3445";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA512(vector[2]));
    }

    @Test
    public void TestSha512Vector4() {
        String digest = "8e959b75dae313da8cf4f72814fc143f8f7779c6eb9f7fa17299aeadb6889018501d289e4900f7e4331b99dec4b5433ac7d329eeb6dd26545e96e55b874be909";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA512(vector[3]));
    }

    @Test
    public void TestSha512Vector5() {
        String digest = "e718483d0ce769644e2e42c7bc15b4638e1f98b13b2044285632a803afa973ebde0ff244877ea60a4cb0432ce577c31beb009c5c2c49aa2e4eadb217ad8cc09b";
        Assert.assertEquals("message digests are not equal", digest, Sha2.SHA512(vector[4]));
    }

    @Test
    public void TestInputByteArrayNotAltered() {
        byte[] array = vector[2];
        Sha2.SHA224(array);
        Assert.assertEquals("user vector altered", array, vector[2]);
    }
}
