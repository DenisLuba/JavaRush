package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString); // Создаём объект URL, куда передаётся ссылка в интернете на файл
        InputStream inputStream = url.openStream(); // У объекта URL открывается поток на чтение файла - InputStream
        Path result = downloadDirectory.resolve(Paths.get(url.getFile()).getFileName()); // Получаем путь, по которому будет закачан файл, включая имя
        Path tempFile = Files.createTempFile("temp-", null); // Создаём файл для временного хранения файла из интернета
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING); // Копируем данные из inputStream в tempFile
        Files.move(tempFile, result, StandardCopyOption.REPLACE_EXISTING); // Перемещаем файл из tempFile по результирующему пути

        return result; // Возвращаем результирующий путь
    }
}
