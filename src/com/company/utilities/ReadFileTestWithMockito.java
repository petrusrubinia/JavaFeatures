package com.company.utilities;

import com.company.entities.Catalog;
import com.company.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReadFileTestWithMockito {

    @Mock ReadFile file;

    @Test
    public void mockReadFromFile_whenGetsStudentID_thenExpectedID() throws IOException {
        String uniqueFilename = "filename";
        String uniqueID = "id1";

        when(file.getStudents(uniqueFilename)).thenReturn(new ArrayList<Student>() {{
            add(new Student(uniqueID, "name", "surname1", "email@yahoo.com", "address"));
        }});

        assert file.getStudents(uniqueFilename).get(0).getId().equals(uniqueID);
    }

    @Test
    public void mockReadFromFile_whenGetsClassName_thenExpectedClassName() throws IOException {
        String uniqueFilename = "filename";
        String className = "className";

        when(file.getCatalog(uniqueFilename)).thenReturn(new ArrayList<Catalog>() {{
            add(new Catalog("studentID", className, 10));
        }});

        assert file.getCatalog(uniqueFilename).get(0).getClassName().equals(className);
    }

}
