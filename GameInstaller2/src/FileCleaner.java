import java.io.File;
import java.util.List;

public class FileCleaner {

    // Метод для удаления файлов
    public static void deleteFiles(List<String> files) {
        for (String file : files) {
            File fileToDelete = new File(file);
            if (fileToDelete.delete()) {
                System.out.println("Файл удален: " + file);
            } else {
                System.out.println("Не удалось удалить файл: " + file);
            }
        }
    }
}