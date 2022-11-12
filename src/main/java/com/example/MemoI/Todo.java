package com.example.projectreminder;

import java.io.File;
import java.util.StringTokenizer;
import java.time.*;
import java.util.Vector;

public class Todo {

    private static int n = 0;

    // primary key for the Instance, not displayed for user
    public final int instanceNum;
    public String title, description;

    private String filepath, filename;
    private LocalDateTime timing;

    // TODO: find appropriate class for location
    Object location;

    Todo(String title, String description, LocalDateTime timing) {
        n++;
        this.title = title;
        this.description = description;
        this.timing = timing;
        this.instanceNum = n;

        File file = new File(filepath, filename);
    }

    public static Todo of(String csv) {
        StringTokenizer st = new StringTokenizer(csv, ", ");
        Vector<String> tokens = new Vector<>();
        while (st.hasMoreTokens()) tokens.add(st.nextToken());

        st = new StringTokenizer(tokens.get(3), "-T:");
        int y = Integer.parseInt(st.nextToken()),
            m = Integer.parseInt(st.nextToken()),
            d = Integer.parseInt(st.nextToken()),
            H = Integer.parseInt(st.nextToken()),
            M = Integer.parseInt(st.nextToken());


        return new Todo(
                tokens.get(1),
                tokens.get(2),
                LocalDateTime.of(LocalDate.of(y,m,d), LocalTime.of(H, M))
        );

    }

    /* formatting to
     * "{instanceNum}, {title}, {description}, {timing}, {location}\n"
     */
    public String toCsvFormat() {
        String res = "";
        res += instanceNum + ", ";
        res += title + ", ";
        res += description + ", ";
        res += timing + ", ";
        res += location.toString();

        return res + "\n";
    }

    // get/set -ters
    public void setTitle(String t) { this.title = t; }
    public void setDescription(String d) { this.description = d; }

    public void setTiming(LocalDateTime timing) {
        this.timing = timing;
    }

    public LocalDateTime getTiming() {
        return this.timing;
    }

    public void setDate(LocalDate date) {
        this.timing = LocalDateTime.of(
                date,
                LocalTime.of(timing.getHour(), timing.getMinute())
        );
    }

    public void setDate(int y, int m, int d) {
        this.setDate(LocalDate.of(y, m, d));
    }

    public void setTime(LocalTime time) {
        this.timing = LocalDateTime.of(
                LocalDate.of(timing.getYear(),timing.getMonth(), timing.getDayOfMonth()),
                time
        );
    }

    public void setTime(int h, int m) {
        this.setTime(LocalTime.of(h, m));
    }
}
