package src.exception;

/**
 * @author: fengyuxiang
 * @ClassName: InvalidMenuChoose
 * @version: 1.0
 * @description:
 * @create: 14/5/2023
 */
public class InvalidMenuChoose extends Exception{

    private String msg;

    public InvalidMenuChoose(String msg){
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "" + msg + "";
    }
}
