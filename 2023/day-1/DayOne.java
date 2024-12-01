import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DayOne{

    public static void main(String[] args){
        final String fileName = args[0];

        Integer verificationCode =  getVerificationCodeFromFile(fileName);
        System.out.println("Verification code = " + verificationCode);
    }

    private static Integer getVerificationCodeFromFile(final String fileName) {
        String line = "";
        int total = 0; 

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter("./output.txt"))){
            while((line = reader.readLine()) != null){
                Integer numbers = getNumbers(line, writer);
                total += numbers;
            }
        } catch (Exception exception){
            System.out.println("Error reading file.");
        }
        return total;
    }

    private static Integer getNumbers(String line, BufferedWriter writer) throws IOException{
        char first = (char) 0;
        char last = (char) 0;
        Integer result;

        writer.write("Line from file: " + line + "\n");
        String numbersFromLine = replaceTextNumbersWithInteger(line)
                                   .toLowerCase().replaceAll("[a-z]", "");
        writer.write("Read Line: " + numbersFromLine + "\n");

        if(numbersFromLine.length() > 1){
            first = numbersFromLine.charAt(0);
            last = numbersFromLine.charAt(numbersFromLine.length() - 1);
        } else {
            first = numbersFromLine.charAt(0);
            last = numbersFromLine.charAt(0);
        }

        try {
            result = Integer.parseInt(String.valueOf(first) + String.valueOf(last));
            writer.write("Result: " + result + "\n");
        } catch (NumberFormatException exception) {
            System.out.println(line);
            System.out.println("Not an accaptable line");
            return 0;
        };

        return result;
    }

    private static String replaceTextNumbersWithInteger(String line) {
        for (TEXT_NUMBERS number : TEXT_NUMBERS.values()) {
            line =  line.replaceAll(number.toString(), number.getValue());
        }
        return line;
    }

    public static enum TEXT_NUMBERS{
        one("1"),
        two("2"),
        three("3"),
        four("4"),
        five("5"),
        six("6"),
        seven("7"),
        eight("8"),
        nine("9");
        
        String value; 

        TEXT_NUMBERS(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }
}