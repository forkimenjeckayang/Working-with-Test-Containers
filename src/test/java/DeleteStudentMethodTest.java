import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bismark.test.TestApplication;
import com.bismark.test.com.bismark.Student;
import com.bismark.test.com.bismark.StudentRepository;
import com.bismark.test.com.bismark.StudentService;


@SpringBootTest(classes=TestApplication.class)
@EnabledIfSystemProperty(named = "test-profile", matches = "unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DeleteStudentMethodTest {
    int a = 10;
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudentMethod(){

        //arrange
        Student student = Student.builder()
               .name("Forkim Akwichenullk")
               .email("forkim.akwichek@gmail.")
               .build(); 

        //act
        Student savedStudent = studentService.addStudent(student);
        Long savedStudentId = savedStudent.getId();
        studentService.deleteStudent(savedStudentId);
        Student deletedStudent = studentRepository.findById(savedStudentId).orElse(null);

        //assert
        assertTrue(deletedStudent == null);
        
    }
} 