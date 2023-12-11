package src;

import src.Converter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


        List<Converter> seedToSoil = new ArrayList<>();
        List<Converter> soilToFertilizer = new ArrayList<>();
        List<Converter> fertilizerToWater = new ArrayList<>();
        List<Converter> waterToLight = new ArrayList<>();
        List<Converter> lightToTemperature = new ArrayList<>();
        List<Converter> temperatureToHumidity = new ArrayList<>();
        List<Converter> humidityToLocation = new ArrayList<>();

        String[] line1 = bufferedReader.readLine().replaceAll("\\s+$", "").split("\\s+");
        List<Long> startingSeeds = Arrays.stream(line1).filter(a -> !a.contains(":")).map(Long::parseLong).collect(Collectors.toList());
        bufferedReader.readLine();
        fillMap(bufferedReader, seedToSoil);
        fillMap(bufferedReader, soilToFertilizer);
        fillMap(bufferedReader, fertilizerToWater);
        fillMap(bufferedReader, waterToLight);
        fillMap(bufferedReader, lightToTemperature);
        fillMap(bufferedReader, temperatureToHumidity);
        fillMap(bufferedReader, humidityToLocation);
        long result = Long.MAX_VALUE;

        List<Long> seeds = IntStream.range(0, startingSeeds.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(startingSeeds::get)
                .collect(Collectors.toList());

        List<Long> ranges = IntStream.range(0, startingSeeds.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(startingSeeds::get)
                .collect(Collectors.toList());

        List<Long> actualSeeds = new ArrayList<>();

        for (int i = 0; i < seeds.size(); i++) {
            for (long seed = seeds.get(i); seed < seeds.get(i) + ranges.get(i); seed++) {

//            }
//            for (Long seed : actualSeeds) {
                long mapped = findMapping(seed, seedToSoil);
                mapped = findMapping(mapped, soilToFertilizer);
                mapped = findMapping(mapped, fertilizerToWater);
                mapped = findMapping(mapped, waterToLight);
                mapped = findMapping(mapped, lightToTemperature);
                mapped = findMapping(mapped, temperatureToHumidity);
                mapped = findMapping(mapped, humidityToLocation);

                if (mapped < result) {
                    result = mapped;
                }
            }
        }


        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static List<Long> buildList(long a, long b) {
        List<Long> res = new ArrayList<>();
        for (long i = a; i < b; i++) {
            res.add(i);
        }
        return res;
    }

    private static long findMapping(Long seed, List<Converter> converters) {
        long res = seed;
        for (Converter converter : converters) {
            if (converter.canBeConverted(seed)) {
                res = converter.convert(res);
            }
        }
        return res;
    }

    private static void fillMap(BufferedReader bufferedReader, List<Converter> map) throws IOException {
        bufferedReader.readLine();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String[] converter = line.replaceAll("\\s+$", "").split(" ");
            map.add(new Converter(Long.parseLong(converter[0]), Long.parseLong(converter[1]), Long.parseLong(converter[2])));
        }
    }
}