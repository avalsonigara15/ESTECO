import java.util.ArrayList;
import java.util.List;

public class StringMultiply {
    public static String multiply(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            String[] parts = numbers.split("\n", 2);
            delimiter = parts[0].substring(2);
            numbers = parts[1];
        }

        String[] numberArray = numbers.split("[" + delimiter + "\n]");
        List<Double> negativeNumbers = new ArrayList<>();
        double product = 1;
        StringBuilder errorBuilder = new StringBuilder();

        for (int i = 0; i < numberArray.length; i++) {
            String num = numberArray[i];
            if (num.isEmpty()) {
                if (i == numberArray.length - 1) {
                    errorBuilder.append("Number expected but EOF found");
                } else {
                    errorBuilder.append("Number expected but '\\n' found at position ").append(numbers.indexOf("\n") + 1);
                }
            } else {
                double n;
                try {
                    n = Double.parseDouble(num);
                } catch (NumberFormatException e) {
                    errorBuilder.append("Invalid number: ").append(num);
                    continue;
                }

                if (n < 0) {
                    negativeNumbers.add(n);
                }
                product *= n;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            errorBuilder.append("Negative not allowed: ");
            for (int i = 0; i < negativeNumbers.size(); i++) {
                if (i > 0) {
                    errorBuilder.append(", ");
                }
                errorBuilder.append(negativeNumbers.get(i));
            }
        }

        if (errorBuilder.length() > 0) {
            return errorBuilder.toString();
        } else {
            return Double.toString(product);
        }
    }


    public static void main(String[] args) {
        System.out.println(multiply(""));  
        System.out.println(multiply("1"));  
        System.out.println(multiply("1.1,2.2"));  
        System.out.println(multiply("1\n2,3"));  
        System.out.println(multiply("175.2,\n35"));  
        System.out.println(multiply("1,3,")); 
        System.out.println(multiply("//;\n1;2"));  
        System.out.println(multiply("//|\n1|2|3"));  
        System.out.println(multiply("//sep\n2sep3"));  
        System.out.println(multiply("//|\n1|2,3"));  
        System.out.println(multiply("-1,2"));  
        System.out.println(multiply("2,-4,-5"));  
        System.out.println(multiply("-1,,-2"));  
                                            
    }
}

