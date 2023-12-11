import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        int res = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String[] nums = line.split(": ")[1].replace(",", "").split(" ");
            int redNumber = 0;
            int greenNumber = 0;
            int blueNumber = 0;

            int currentRedNumber = 0;
            int currentGreenNumber = 0;
            int currentBlueNumber = 0;
            nums[nums.length-1] = nums[nums.length-1] + ";";
            for (int i = 0; i < nums.length; i++) {
                if (nums[i].contains("blue")) {
                    currentBlueNumber += Integer.parseInt(nums[i - 1]);
                } else if (nums[i].contains("red")) {
                    currentRedNumber += Integer.parseInt(nums[i - 1]);
                } else if (nums[i].contains("green")) {
                    currentGreenNumber += Integer.parseInt(nums[i - 1]);
                }

                if (nums[i].contains(";")) {
                    if (currentBlueNumber > blueNumber) {
                        blueNumber = currentBlueNumber;
                    }
                    if (currentGreenNumber > greenNumber) {
                        greenNumber = currentGreenNumber;
                    }
                    if (currentRedNumber > redNumber) {
                        redNumber = currentRedNumber;
                    }

                    currentRedNumber = 0;
                    currentGreenNumber = 0;
                    currentBlueNumber = 0;
                }
            }
            res += redNumber*blueNumber*greenNumber;
        }

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}