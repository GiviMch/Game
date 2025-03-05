import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GameLoader {

    // Метод для распаковки архива
    public static void openZip(String zipFilePath, String outputFolderPath) {
        try (FileInputStream fis = new FileInputStream(zipFilePath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            // Создаем папку для разархивированных файлов, если её нет
            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                File outputFile = new File(outputFolderPath + File.separator + entryName);

                // Записываем разархивированный файл
                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }

                System.out.println("Файл разархивирован: " + entryName);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при разархивации: " + e.getMessage());
        }
    }

    // Метод для десериализации файла
    public static GameProgress openProgress(String filePath) {
        GameProgress progress = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            progress = (GameProgress) ois.readObject(); // Десериализация
            System.out.println("Файл десериализован: " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при десериализации: " + e.getMessage());
        }
        return progress;
    }
}