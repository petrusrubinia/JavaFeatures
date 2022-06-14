package com.company.utilities;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadFileTest {

    @Test
    public void fileName_whenGetsStudents_thenNotThrowExeption() {
        ReadFile file = new ReadFile();
        String studentFileName = "C:\\Users\\rubinia.petrus\\Documents\\IGrow\\JavaFeatures\\src\\com\\company\\resources\\Student.csv";

        assertDoesNotThrow(() -> file.getStudents(studentFileName));
    }

    @Test
    public void fileName_whenGetsStudents_thenThrowException() {
        ReadFile file = new ReadFile();
        String wrongFileName = "C:\\Users\\rubinia.petrus\\Documents\\IGrow\\JavaFeatures\\src\\com\\company\\resources\\WrongFileName.csv";

        assertThrows(FileNotFoundException.class, () -> file.getStudents(wrongFileName));
    }

    @Test
    public void fileName_whenGetsCatalog_thenNotThrowExeption() {
        ReadFile file = new ReadFile();
        String catalogFileName = "C:\\Users\\rubinia.petrus\\Documents\\IGrow\\JavaFeatures\\src\\com\\company\\resources\\Catalog.csv";

        assertDoesNotThrow(() -> file.getCatalog(catalogFileName));
    }

    @Test
    public void fileName_whenGetsCatalog_thenThrowException() {
        ReadFile file = new ReadFile();
        String wrongFileName = "C:\\Users\\rubinia.petrus\\Documents\\IGrow\\JavaFeatures\\src\\com\\company\\resources\\WrongFileName.csv";

        assertThrows(FileNotFoundException.class, () -> file.getCatalog(wrongFileName));
    }
}