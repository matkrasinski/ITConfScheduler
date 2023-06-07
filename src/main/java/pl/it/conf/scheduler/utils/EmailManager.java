package pl.it.conf.scheduler.utils;

import pl.it.conf.scheduler.dto.ReservationDto;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    public static void sendEmail(LocalDateTime date, String email, ReservationDto content) {
        String path = System.getProperty("user.home") + "/notifications.txt";

        List<String> lines = formatContent(date, email, content);

        try (FileWriter fileWriter = new FileWriter(path)){
            for (String line : lines)
                fileWriter.write(line + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> formatContent(LocalDateTime date, String email, ReservationDto content) {
        List<String> lines = new ArrayList<>();
        String pattern = "dd-MM-yyyy HH:mm:ss";

        lines.add("=========================================================================");
        lines.add("Email sent : " + date.format(DateTimeFormatter.ofPattern(pattern)));
        lines.add("To : " + email + "\n");

        lines.add("Start time : " + content.getStartTime());
        lines.add("End time : " + content.getEndTime());
        lines.add("Theme : " + content.getTheme());
        lines.add("Your reservation id : " + content.getReservationId());

        lines.add("=========================================================================" + "\n");

        return lines;
    }

}
