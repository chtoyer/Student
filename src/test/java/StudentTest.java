import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class StudentTest {

    @Test
    void testEncapsulation() {
        Student student = new Student("Ivan");
        student.addGrade(5);
        List<Integer> grades = student.getGrades();
        assertThrows(UnsupportedOperationException.class, () -> {
            grades.add(4);
        });
    }

    @Test
    void testAddGradeValid() {
        Student student = new Student("Ivan");
        student.addGrade(2);
        student.addGrade(5);
        assertEquals(2, student.getGrades().size());
        assertEquals(5, student.getGrades().get(1));
    }

    @Test
    void testAddGradeInvalid() {
        Student student = new Student("Ivan");
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(1));
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(6));
    }

    @Test
    void testNameAccessors() {
        Student student = new Student("Ivan");
        assertEquals("Ivan", student.getName());
        student.setName("Petr");
        assertEquals("Petr", student.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Student s1 = new Student("Ivan");
        s1.addGrade(5);
        Student s2 = new Student("Ivan");
        s2.addGrade(5);
        Student s3 = new Student("Petr");

        assertEquals(s1, s2);
        assertNotEquals(s1, s3);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void testToString() {
        Student student = new Student("Ivan");
        student.addGrade(5);
        String result = student.toString();
        assertTrue(result.contains("Ivan"));
        assertTrue(result.contains("5"));
    }
}