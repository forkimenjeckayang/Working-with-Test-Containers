import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bismark.test.TestApplication;
import com.bismark.test.com.bismark.Student;
import com.bismark.test.com.bismark.StudentService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=TestApplication.class)
@EnabledIfSystemProperty(named = "test-profile", matches = "unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaveStudentMethodTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void saveStudentMethod(){

        //arrange
        Student student = Student.builder()
               .name("Forkim Akwichenullk")
               .email("forkim.akwichek@gmail.")
               .build();
               

        //act
        Student savedStudent = studentService.addStudent(student);

        //assert
        Assertions.assertThat(savedStudent.getId()).isNotNull();
    }

}
