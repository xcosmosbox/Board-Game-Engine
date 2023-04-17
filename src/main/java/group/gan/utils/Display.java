package group.gan.utils;

/**
 * @author: fengyuxiang
 * @ClassName: Display
 * @version: 1.0
 * @description:
 * @create: 14/4/2023
 */
public class Display {
    public String getNewLine(){
        return "\n";
    }

    public void displayView(String str){
        System.out.println(str);
    }

    /**
     * On the left side of the output page information,
     * you need to use this function if you have not reserved three spaces in advance
     * @param str
     */
    public void displayFormattedView(String str){
        String msg = String.format("%3s"," ");
        msg += str;
        System.out.println(msg);

    }

    /**
     * For general simple messages, you need to use this function for output
     * @param str
     */
    public void displayMessage(String str){
        String msg = String.format("%3s"," ");
        msg += str;
        System.out.println(msg);
    }
}
