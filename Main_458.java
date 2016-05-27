

class Main {
    public static void main(String[] args) {
        int charIntFormat;
        try {
            while ((charIntFormat = System.in.read()) != -1) {
                decoder(charIntFormat);
            }
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
        }
        System.out.flush();
    }
    
    private static void decoder(int charIntFormat) {
        if(charIntFormat == 10) {
            System.out.println();
        } else {
            char decorderChar = (char) (charIntFormat-7);
            System.out.write(charIntFormat);
            System.out.println(charIntFormat);
        }
    }
}