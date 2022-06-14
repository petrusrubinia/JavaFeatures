package com.company.utilities;

import com.company.entities.Catalog;
import com.company.entities.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFile {

    public List<Student> getStudents(String filename) throws IOException {
        return readCSVData(filename).stream().map(array -> new Student(array[0],array[1], array[2], array[3],array[4])).collect(Collectors.toList());
    }

    public List<Catalog> getCatalog(String filename) throws IOException {
        return readCSVData(filename).stream().map(array -> new Catalog(array[0],array[1], Float.parseFloat(array[2]))).collect(Collectors.toList());
    }

    protected ArrayList<String[]> readCSVData(String absolutePath) throws IOException{
        final ArrayList<String[]> allRows = new ArrayList<>();
        File csvFile = null;
        BufferedReader bufferedReader = null;
        csvFile = new File(absolutePath);
        bufferedReader = new BufferedReader(new FileReader(csvFile));
        String row = "";
        bufferedReader.readLine();
        bufferedReader.readLine();

        while ((row = bufferedReader.readLine()) != null) {
            if (row.isEmpty() || row.trim().isEmpty()) {
                continue;
            }
            String[] entry = row.split(",", -1);
            allRows.add(entry);
        }
        return allRows;
    }
}
