public class Vector2D {
    public float x, y;

    public Vector2D() {
    }

    public Vector2D(float x, float y) {
        set(x, y);
    }

    public Vector2D(Vector2D v) {
        set(v);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D set(Vector2D v) {
        x = v.x;
        y = v.y;
        return this;
    }

    /**
     * Negates this vector and returns this.
     */
    public Vector2D negi() {
        return neg(this);
    }

    /**
     * Sets out to the negation of this vector and returns out.
     */
    public Vector2D neg(Vector2D out) {
        out.x = -x;
        out.y = -y;
        return out;
    }

    /**
     * Returns a new vector that is the negation to this vector.
     */
    public Vector2D neg() {
        return neg(new Vector2D());
    }

    /**
     * Multiplies this vector by s and returns this.
     */
    public Vector2D muli(float s) {
        return mul(s, this);
    }

    /**
     * Sets out to this vector multiplied by s and returns out.
     */
    public Vector2D mul(float s, Vector2D out) {
        out.x = s * x;
        out.y = s * y;
        return out;
    }

    /**
     * Returns a new vector that is a multiplication of this vector and s.
     */
    public Vector2D mul(float s) {
        return mul(s, new Vector2D());
    }

    /**
     * Divides this vector by s and returns this.
     */
    public Vector2D divi(float s) {
        return div(s, this);
    }

    /**
     * Sets out to the division of this vector and s and returns out.
     */
    public Vector2D div(float s, Vector2D out) {
        out.x = x / s;
        out.y = y / s;
        return out;
    }

    /**
     * Returns a new vector that is a division between this vector and s.
     */
    public Vector2D div(float s) {
        return div(s, new Vector2D());
    }

    /**
     * Adds s to this vector and returns this.
     */
    public Vector2D addi(float s) {
        return add(s, this);
    }

    /**
     * Sets out to the sum of this vector and s and returns out.
     */
    public Vector2D add(float s, Vector2D out) {
        out.x = x + s;
        out.y = y + s;
        return out;
    }

    /**
     * Returns a new vector that is the sum between this vector and s.
     */
    public Vector2D add(float s) {
        return add(s, new Vector2D());
    }

    /**
     * Multiplies this vector by v and returns this.
     */
    public Vector2D muli(Vector2D v) {
        return mul(v, this);
    }

    /**
     * Sets out to the product of this vector and v and returns out.
     */
    public Vector2D mul(Vector2D v, Vector2D out) {
        out.x = x * v.x;
        out.y = y * v.y;
        return out;
    }

    /**
     * Returns a new vector that is the product of this vector and v.
     */
    public Vector2D mul(Vector2D v) {
        return mul(v, new Vector2D());
    }

    /**
     * Divides this vector by v and returns this.
     */
    public Vector2D divi(Vector2D v) {
        return div(v, this);
    }

    /**
     * Sets out to the division of this vector and v and returns out.
     */
    public Vector2D div(Vector2D v, Vector2D out) {
        out.x = x / v.x;
        out.y = y / v.y;
        return out;
    }

    /**
     * Returns a new vector that is the division of this vector by v.
     */
    public Vector2D div(Vector2D v) {
        return div(v, new Vector2D());
    }

    /**
     * Adds v to this vector and returns this.
     */
    public Vector2D addi(Vector2D v) {
        return add(v, this);
    }

    /**
     * Sets out to the addition of this vector and v and returns out.
     */
    public Vector2D add(Vector2D v, Vector2D out) {
        out.x = x + v.x;
        out.y = y + v.y;
        return out;
    }

    /**
     * Returns a new vector that is the addition of this vector and v.
     */
    public Vector2D add(Vector2D v) {
        return add(v, new Vector2D());
    }

    /**
     * Adds v * s to this vector and returns this.
     */
    public Vector2D addsi(Vector2D v, float s) {
        return adds(v, s, this);
    }

    /**
     * Sets out to the addition of this vector and v * s and returns out.
     */
    public Vector2D adds(Vector2D v, float s, Vector2D out) {
        out.x = x + v.x * s;
        out.y = y + v.y * s;
        return out;
    }

    /**
     * Returns a new vector that is the addition of this vector and v * s.
     */
    public Vector2D adds(Vector2D v, float s) {
        return adds(v, s, new Vector2D());
    }

    /**
     * Subtracts v from this vector and returns this.
     */
    public Vector2D subi(Vector2D v) {
        return sub(v, this);
    }

    /**
     * Sets out to the subtraction of v from this vector and returns out.
     */
    public Vector2D sub(Vector2D v, Vector2D out) {
        out.x = x - v.x;
        out.y = y - v.y;
        return out;
    }

    /**
     * Returns a new vector that is the subtraction of v from this vector.
     */
    public Vector2D sub(Vector2D v) {
        return sub(v, new Vector2D());
    }

    /**
     * Returns the squared length of this vector.
     */
    public float lengthSq() {
        return x * x + y * y;
    }

    /**
     * Returns the length of this vector.
     */
    public float length() {
        return (float) StrictMath.sqrt(x * x + y * y);
    }

    /**
     * Rotates this vector by the given radians.
     */
    public void rotate(float radians) {
        float c = (float) StrictMath.cos(radians);
        float s = (float) StrictMath.sin(radians);

        float xp = x * c - y * s;
        float yp = x * s + y * c;

        x = xp;
        y = yp;
    }

    /**
     * Normalizes this vector, making it a unit vector. A unit vector has a length
     * of 1.0.
     */
    public void normalize() {
        float lenSq = lengthSq();

        if (lenSq > 0.000001) {
            float invLen = 1.0f / (float) StrictMath.sqrt(lenSq);
            x *= invLen;
            y *= invLen;
        }
    }

    /**
     * Sets this vector to the minimum between a and b.
     */
    public Vector2D mini(Vector2D a, Vector2D b) {
        return min(a, b, this);
    }

    /**
     * Sets this vector to the maximum between a and b.
     */
    public Vector2D maxi(Vector2D a, Vector2D b) {
        return max(a, b, this);
    }

    /**
     * Returns the dot product between this vector and v.
     */
    public float dot(Vector2D v) {
        return dot(this, v);
    }

    /**
     * Returns the squared distance between this vector and v.
     */
    public float distanceSq(Vector2D v) {
        return distanceSq(this, v);
    }

    /**
     * Returns the distance between this vector and v.
     */
    public float distance(Vector2D v) {
        return distance(this, v);
    }

    /**
     * Sets this vector to the cross between v and a and returns this.
     */
    public Vector2D cross(Vector2D v, float a) {
        return cross(v, a, this);
    }

    /**
     * Sets this vector to the cross between a and v and returns this.
     */
    public Vector2D cross(float a, Vector2D v) {
        return cross(a, v, this);
    }

    /**
     * Returns the scalar cross between this vector and v. This is essentially the
     * length of the cross product if this vector were 3d. This can also indicate
     * which way v is facing relative to this vector.
     */
    public float cross(Vector2D v) {
        return cross(this, v);
    }

    public static Vector2D min(Vector2D a, Vector2D b, Vector2D out) {
        out.x = (float) StrictMath.min(a.x, b.x);
        out.y = (float) StrictMath.min(a.y, b.y);
        return out;
    }

    public static Vector2D max(Vector2D a, Vector2D b, Vector2D out) {
        out.x = (float) StrictMath.max(a.x, b.x);
        out.y = (float) StrictMath.max(a.y, b.y);
        return out;
    }

    public static float dot(Vector2D a, Vector2D b) {
        return a.x * b.x + a.y * b.y;
    }

    public static float distanceSq(Vector2D a, Vector2D b) {
        float dx = a.x - b.x;
        float dy = a.y - b.y;

        return dx * dx + dy * dy;
    }

    public static float distance(Vector2D a, Vector2D b) {
        float dx = a.x - b.x;
        float dy = a.y - b.y;

        return (float) StrictMath.sqrt(dx * dx + dy * dy);
    }

    public static Vector2D cross(Vector2D v, float a, Vector2D out) {
        out.x = v.y * a;
        out.y = v.x * -a;
        return out;
    }

    public static Vector2D cross(float a, Vector2D v, Vector2D out) {
        out.x = v.y * -a;
        out.y = v.x * a;
        return out;
    }

    public static float cross(Vector2D a, Vector2D b) {
        return a.x * b.y - a.y * b.x;
    }

    /**
     * Returns an array of allocated Vector2D of the requested length.
     */
    public static Vector2D[] arrayOf(int length) {
        Vector2D[] array = new Vector2D[length];

        while (--length >= 0) {
            array[length] = new Vector2D();
        }

        return array;
    }

    public String toString() {
        return "(" + x + ", " + y + ");    ";
    }
}
