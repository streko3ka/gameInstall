import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {
    static Date date = new Date();
    static SimpleDateFormat dateTime = new SimpleDateFormat("dd.mm.yyyy hh-mm");

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        File directory = new File("/Users/admin/Games");
        List<File> folders = Arrays.asList(
                new File(directory, "src"),
                new File(directory, "res"),
                new File(directory, "savedGames"),
                new File(directory, "tmp"),
                new File(directory + "\\src\\main"),
                new File(directory + "\\src\\test"),
                new File(directory + "\\res\\drawables"),
                new File(directory + "\\res\\vectors"),
                new File(directory + "\\res\\icons")
        );
        folders.stream()
                .forEach(d -> {
                            if (d.mkdir()) {
                                stringBuilder.append(dateTime.format(date) + " создана директория " + d.getName());
                                stringBuilder.append("\n");
                                System.out.println("Создана директория");
                            } else {
                                stringBuilder.append(dateTime.format(date) + " ошибка создания директории " + d.getName());
                                System.out.println("Директория не создана из-за ошибки");
                            }
                        }
                );
        List<File> files = Arrays.asList(
                new File(directory + "\\src\\main\\Main.java"),
                new File(directory + "\\src\\main\\Utils.java"),
                new File(directory, "\\tmp\\tmp.txt")
        );
        files.stream()
                .forEach(f -> {
                            try {
                                if (f.createNewFile()) {
                                    stringBuilder.append(dateTime.format(date) + " создан файл " + f.getName());
                                    stringBuilder.append("\n");
                                } else {
                                    stringBuilder.append(dateTime.format(date) + " ошибка создания файла " + f.getName());
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "\\tmp\\tmp.txt"))) {
            writer.write(stringBuilder.toString());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}