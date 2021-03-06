package com.example.uploadanddowloadfile.service;

import com.example.uploadanddowloadfile.models.Document;
import com.example.uploadanddowloadfile.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ImplDocumentService implements IDocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<Document> getAllFile() {
        List<Document> listDocs = documentRepository.findAll();
        return listDocs;
    }

    @Override
    public void downloadFile(Long id, HttpServletResponse response) throws Exception {
        Optional<Document> file = documentRepository.findById(id);
        if (!file.isPresent()){
            throw new Exception("Could not find document with ID: "+id);
        }
        Document fileDownload = file.get();

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+fileDownload.getName();

        response.setHeader(headerKey,headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(fileDownload.getContent());
        outputStream.close();

    }

    @Override
    public void deleteFile(Long id) {
        boolean exists = documentRepository.existsById(id);
        if (exists){
            documentRepository.deleteById(id);
        }else {
            System.out.println("Cannot find Id! file not exists!");
        }
    }

    @Override
    public Document uploadFile(MultipartFile file) throws IOException {
        float fileSize = file.getSize()/1_000_000.0f;
        if (fileSize >2.0f){
            throw new IOException("File must be <=2Mb");
        }try{

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Document document = new Document();
            document.setName(fileName);
            document.setContent(file.getBytes());
            document.setSize(file.getSize());
            document.setUploadTime(new Date());

            return documentRepository.save(document);
        }catch (Exception exception){
            throw  new RuntimeException("Cannot update file!",exception);
        }
    }
}
