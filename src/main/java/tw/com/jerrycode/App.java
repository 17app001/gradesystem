package tw.com.jerrycode;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // System.out.println("中文測試");
        // System.out.println("Hello World!");
        GradeSystem system = new GradeSystem();

        system.run(true);

        try {
            String chinese = "56.5";
            double score = Double.valueOf(chinese);
            System.out.println(score);
        } catch (java.lang.NumberFormatException e) {
            System.out.println(e);
        }
    }
}
