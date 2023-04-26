package group.gan.exception;

/**
 * @author: fengyuxiang
 * @ClassName: InvalidTokenChoose
 * @version: 1.0
 * @description:
 * @create: 26/4/2023
 */
public class InvalidTokenChoose extends Exception {
    private String msg;

    public InvalidTokenChoose(String message) {
        super(message);
        this.msg = message;
    }

    @Override
    public String toString() {
        return ""+msg+"";
    }
}
