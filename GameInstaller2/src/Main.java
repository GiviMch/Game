import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Путь к папке savegames
        String savePath = "/Users/givi/Games/savegames";

        // 1. Создаем три экземпляра GameProgress
        GameProgress progress1 = new GameProgress(100, 3, 1, 0.0);
        GameProgress progress2 = new GameProgress(85, 5, 2, 150.5);
        GameProgress progress3 = new GameProgress(50, 10, 5, 500.0);

        // 2. Сохраняем объекты в файлы
        String save1 = savePath + "/save1.dat";
        String save2 = savePath + "/save2.dat";
        String save3 = savePath + "/save3.dat";

        GameSaver.saveGame(save1, progress1);
        GameSaver.saveGame(save2, progress2);
        GameSaver.saveGame(save3, progress3);

        // 3. Архивируем файлы
        List<String> filesToZip = new ArrayList<>();
        filesToZip.add(save1);
        filesToZip.add(save2);
        filesToZip.add(save3);

        String zipPath = savePath + "/saves.zip";
        GameZipper.zipFiles(zipPath, filesToZip);

        // 4. Удаляем файлы, не лежащие в архиве
        FileCleaner.deleteFiles(filesToZip);

        // 5. Разархивируем файлы
        String unzipPath = savePath + "/unzipped";
        GameLoader.openZip(zipPath, unzipPath);

        // 6. Десериализуем один из файлов
        String saveFilePath = unzipPath + "/save2.dat";
        GameProgress progress = GameLoader.openProgress(saveFilePath);
        if (progress != null) {
            System.out.println("Состояние игры: " + progress);
        }

        System.out.println("Процесс завершен: файлы сохранены, заархивированы, разархивированы и десериализованы.");
    }
}