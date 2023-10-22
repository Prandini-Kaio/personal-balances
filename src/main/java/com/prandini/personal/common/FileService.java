package com.prandini.personal.common;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
@CommonsLog
public class FileService {


    @Value("/src/main/resources/tmp/")
    private String tmp_path;

    public File createFile(String fileName, String content) throws IOException {
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(fileName).concat(".txt"));
        try(BufferedWriter writer =  new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }

        return file;
    }

    public File createFile(MultipartFile multipartFile, String pathString, String filename){
        Path path = Path.of(getFullFilename(pathString.concat(filename)));
        try{
            File newFile = path.toFile();
            File parent = newFile.getParentFile();
            if(parent != null && !parent.exists() && !parent.mkdirs())
                throw new RuntimeException("Falha ao criar diretórios do arquivo desejado.");

            multipartFile.transferTo(path);
            return path.toFile();
        }catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public File createFile(MultipartFile multipartFile, String pathString){
        return createFile(multipartFile, pathString, multipartFile.getOriginalFilename());
    }

    public File createFile(File file, String path) {
        return this.createFile(file, path, file.getName());
    }

    public File createFile(File file, String path, String filename) {
        try{
            File newFile = new File(getFullFilename(path.concat("/").concat(filename)));
            File parent = newFile.getParentFile();
            if(parent != null && !parent.exists() && !parent.mkdirs())
                throw new RuntimeException("Falha ao criar diretórios do arquivo desejado.");

            newFile.createNewFile();
            Files.copy(Path.of(file.getAbsolutePath()), Path.of(getFullFilename(path.concat("/").concat(file.getName()))), REPLACE_EXISTING);
            return newFile;
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public File createExcelFile(String fileName, XSSFWorkbook workbook) throws IOException {
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(fileName).concat(".xlsx"));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        }
        return file;
    }

    public File createPDFFile(String fileName) {
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(fileName).concat(".pdf"));
        return file;
    }

    public boolean deleteTmpFile(String path){
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(path));
        return deleteTmpFile(file);
    }

    public boolean deleteTmpFile(File file){
        return file.delete();
    }

    public File createTmpFile(InputStream is, String filename) throws Exception{
        try{
            File tmp = new File(getFullFilename(filename));
            try (FileOutputStream outputStream = new FileOutputStream(tmp)) {
                int read;
                byte[] bytes = new byte[1024];
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }

            return tmp;
        }catch(IOException | NullPointerException e){
            throw new Exception("Erro criando arquivo temporário: " + e.getMessage(), e);
        }
    }

    public File createEmptyTmpFile(String filename){
        return new File(getFullFilename(filename));
    }

    public File getFile() {
        return null;
    }

    public String getFullFilename(String filename) {
        return System.getProperty("user.dir").concat(tmp_path).concat(filename);
    }
}