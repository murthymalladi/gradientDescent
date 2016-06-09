package com.murthy.domains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dmalladi on 7/6/16.
 */
public class ReadData {

    private ArrayList<Data> dataList;

    public ReadData() {
        dataList = new ArrayList<Data>();
    }
    public static void main(String[] args) {
        ReadData read = new ReadData();
        read.readingData();
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }
    public  void readingData() {
        String fileName = "trainingData/ex1data1.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        /*try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        BufferedReader br = null;
        Data trainingData;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine();
            String[] nextLine = null;
            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
             //   System.out.print(data[0] + "    " + data[1]);
             //   System.out.println();
                trainingData = new Data();
                trainingData.x = Double.parseDouble(data[0]);
                trainingData.y = Double.parseDouble(data[1]);
                dataList.add(trainingData);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
