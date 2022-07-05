package com.javarush.task.task31.task3111;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName = null;
    private String partOfContent = null;
    private int minSize = -1;
    private int maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        boolean containsName = true;
        boolean containsContent = true;
        boolean moreMin = true;
        boolean lessMax = true;

        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) containsName = false;
        if (partOfContent != null && !(new String(content).contains(partOfContent))) containsContent = false;
        if (minSize >= 0 && content.length < minSize) moreMin = false;
        if (maxSize >= 0 && content.length > maxSize) lessMax = false;

        if (containsName && containsContent && moreMin && lessMax) foundFiles.add(file);
        return FileVisitResult.CONTINUE;
    }
}
