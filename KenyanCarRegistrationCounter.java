import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author moses
 */
public class KenyanCarRegistrationCounter {
    public static void main(String[] args) {
        String startPlate = "KAA 001A";
        String endPlate = "KAA 999Z";

        List<String> countedPlates = countPlatesBetween(startPlate, endPlate);

        System.out.println("Cars counted: "+ countedPlates.size());
          for (String plate : countedPlates) {
              System.out.println(plate);
        }
    }

    private static List<String> countPlatesBetween(String startPlate, String endPlate) {
        List<String> countedPlates = new ArrayList<>();
        
        while (!startPlate.equals(endPlate)) {
            char[] plateChars = startPlate.toCharArray();
            if (!skipSequence(plateChars[1], plateChars[2])) {
                countedPlates.add(startPlate);
            }
            startPlate = incrementPlate(startPlate);
        }
        
        if(startPlate.equalsIgnoreCase(endPlate)){
            char[] plateChars = startPlate.toCharArray();
            if (!skipSequence(plateChars[1], plateChars[2])) {
                countedPlates.add(startPlate);
            }
        }

        return countedPlates;
    }

    private static String incrementPlate(String plate) {
        char[] plateChars = plate.toCharArray();

        int numbers = Integer.parseInt(plateChars[4] + "" + plateChars[5] + "" + plateChars[6]);
        char letter = plateChars[7];

        if (skipSequence(plateChars[1], plateChars[2])) {
            plateChars[2]++;
            letter = 'A';
        } else {
            if (numbers == 999) {
                numbers = 1;
                if (letter == 'Z') {
                    letter = 'A';
                    plateChars[2]++; 
                } else {
                    letter++;
                }
            } else {
                numbers++;
            }
        }

        numbers = Math.min(999, numbers);

        String incrementedNumbers = String.format("%03d", numbers);

        plateChars[4] = incrementedNumbers.charAt(0);
        plateChars[5] = incrementedNumbers.charAt(1);
        plateChars[6] = incrementedNumbers.charAt(2);
        plateChars[7] = letter;

        return new String(plateChars);
    }

    private static boolean skipSequence(char secondLetter, char thirdLetter) {
        Set<String> sequencesToSkip = new HashSet<>();
        sequencesToSkip.add("KAF");
        sequencesToSkip.add("KAO");
        sequencesToSkip.add("KDF");

        String currentSequence = "K" + secondLetter + thirdLetter;
        
        return sequencesToSkip.contains(currentSequence);
    }
}

