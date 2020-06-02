import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        String f2l = "RUBIK/F2L";
        String oll = "RUBIK/OLL";
        String pll = "RUBIK/PLL";
        test.getFileName(f2l);
        test.getFileName(oll);
        test.getFileName(pll);
    }

    public void getFileName(String cfop) {
        File file = new File(this.getClass().getClassLoader().getResource(cfop).getPath());
        System.out.print(cfop + "s" + "=");
        for (File f : file.listFiles()) {
            System.out.print(f.getName().toLowerCase() + "#");
        }
        System.out.println();
    }
}
