package com.a3infortech.importfiles.resources;

import com.a3infortech.importfiles.models.ReturnReadjustmentFile;
import com.a3infortech.importfiles.services.ImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileImportResource {

    private final ImportService importService;

    public FileImportResource(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping
    public ResponseEntity<ReturnReadjustmentFile> importFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(this.importService.prepareFile(file));
    }
}
