package pl.itacademy.schedule;

import com.jayway.jsonpath.JsonPath;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

public class CalendarificHolidaysChecker implements HolidaysChecker {
    @Override
    public List<LocalDate> getHolidays(int year) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("https://calendarific.com/api/v2/holidays?api_key=650b72b1c2dfb7232f2b874208b2ede9e1bb42c6&country=PL&year=2020&type=national");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        String jsonResponse = invocationBuilder.get(String.class);
        System.out.println(jsonResponse);

        List<String> dates = JsonPath.read(jsonResponse,
                "$.response.holidays[*].date.iso");
        dates.forEach(System.out::println);

        return null;
    }
}