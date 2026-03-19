import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> grades = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    public void addGrade(int grade) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://localhost:5352/checkGrade?grade=" + grade);
            try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
                HttpEntity entity = httpResponse.getEntity();
                String responseString = EntityUtils.toString(entity);
                if (!Boolean.parseBoolean(responseString)) {
                    throw new IllegalArgumentException(grade + " is wrong grade");
                }
                grades.add(grade);
            }
        }
    }

    public int raiting() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            int sum = grades.stream().mapToInt(x -> x).sum();
            HttpGet request = new HttpGet("http://localhost:5352/educ?sum=" + sum);
            try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
                HttpEntity entity = httpResponse.getEntity();
                return Integer.parseInt(EntityUtils.toString(entity));
            }
        }
    }
}