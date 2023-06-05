import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    
    public static String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        String delimiter = ",";
        String[] lines = numbers.split("\n");

        if (lines[0].startsWith("//")) {
            delimiter = lines[0].substring(2);
            numbers = lines[1];
        }

        String[] numArray = numbers.split("[" + delimiter + "\n]");
        List<String> negatives = new ArrayList<>();
        int sum = 0;

        for (String num : numArray) {
            if (num.isEmpty()) {
                return "Number expected but EOF found";
            } else if (!num.matches("-?\\d+")) {
                return "'" + delimiter + "' expected but '" + num + "' found at position " + numbers.indexOf(num);
            }

            int n = Integer.parseInt(num);
            if (n < 0) {
                negatives.add(num);
            } else {
                sum += n;
            }
        }

        if (!negatives.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder("Negative not allowed: ");
            for (int i = 0; i < negatives.size(); i++) {
                errorMsg.append(negatives.get(i));
                if (i != negatives.size() - 1) {
                    errorMsg.append(", ");
                }
            }
            return errorMsg.toString();
        }

        return String.valueOf(sum);
    }

    public static void main(String[] args) {
        System.out.println(add(""));  
        System.out.println(add("1"));  
        System.out.println(add("1.1,2.2"));  
        System.out.println(add("1\n2,3"));
        System.out.println(add("175.2,\n35"));  
        System.out.println(add("1,3,"));  
        System.out.println(add("//;\n1;2"));  
        System.out.println(add("//|\n1|2|3")); 
        System.out.println(add("//sep\n2sep3"));  
        System.out.println(add("//|\n1|2,3"));  
        System.out.println(add("-1,2"));  
        System.out.println(add("2,-4,-5"));  
        System.out.println(add("-1,,-2"));  
                                            
    }
}
