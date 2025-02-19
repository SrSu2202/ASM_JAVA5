package fpl.asm_java5.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {
    public String saveImage(MultipartFile file) {
        Path fPath = Paths.get("images");
        String filename = String.format("%s.%s", (new Date()).getTime(), file.getContentType().split("/")[1]);
        try{
            if (!Files.exists( fPath)) {
                Files.createDirectories( fPath);
            }
            Files.copy(file.getInputStream(), fPath.resolve(filename));
            return filename;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<String> saveImagesProduct(List<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();

        try {
            Path filePath = Paths.get("images");
            Files.createDirectories(filePath);

            for (MultipartFile file : files) {
                String fileName = String.format("%s.%s", System.currentTimeMillis(),
                        file.getContentType().split("/")[1]);
                Files.copy(file.getInputStream(), filePath.resolve(fileName));
                fileNames.add(fileName);

                Thread.sleep(5);
            }
        } catch (Exception e) {}
        return fileNames;
    }
}
