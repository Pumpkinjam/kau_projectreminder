package com.example.MemoI;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.time.*;
import java.util.Vector;

public class Todo {

    private static long n = 0;

    // primary key for the Instance
    public final long instanceNum;  // instanceNum NOT NULL, not displayed for user
                                    // in general case, this can't be null
    public String title;            // title NOT NULL

    public String description;
    public static final String
            filepath = "/Android/data/com.example.MemoI/files/",
            filename = "todos.csv",
            fileprefix = "todos",
            filesuffix = ".csv";

    public static final String[] fileDirArray = {"/Android", "/data", "/com.example.MemoI", "/files"};

    private LocalDateTime timing;

    // TODO: find appropriate class for location
    Object location;

    public final class NullIntegrityException extends RuntimeException {
        NullIntegrityException() {
            super("title cannot be null");
        }
    }

    // lots of... constructors... for null proccessing
    Todo(String title) {
        this(title, null, null, null);
    }

    Todo(String title, String description) {
        this(title, description, null, null);
    }

    Todo(String title, String description, int y, int m, int d, int H, int M) {
        this(title, description,
                LocalDateTime.of(LocalDate.of(y,m,d), LocalTime.of(H,M)),
                null);
    }

    Todo(String title, String description, LocalDateTime timing) {
        this(title, description, timing, null);
    }

    Todo(String title, String description, LocalDateTime timing, Object location) {
        if (title == null) throw new NullIntegrityException();

        n++;
        this.title = title;
        this.description = description;
        this.timing = timing;
        this.location = location;
        this.instanceNum = n;

        // File file = new File(filepath, filename);
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
    // TODO: in csv, we can't save character ","...
    public String toCsvFormat() {
        String res = "";
        res += instanceNum + ", ";
        res += title + ", ";
        res += description + ", ";
        res += timing + ", ";
        res += location.toString();

        return res + "\n";
    }


    public static Path getDirPath() {
        //return (Path) Paths.get("/storage", "emulated", "0", "Android", "data", "com.example.MemoI", "files");
        return Paths.get(filepath);
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

    // toString() Override
    @Override
    public String toString() {
        return "<Object Todo#" + instanceNum + ">" +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nTiming: " + timing +
                "\bLocation" + location;
    }
}
