package shop.coffee.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import shop.coffee.backend.service.ImageService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadImage(
        @RequestParam("image") MultipartFile file,
        @RequestParam("item_id") Long itemId,
        @RequestHeader(value = "X-Chunk-Index", defaultValue = "0") int chunkIndex,
        @RequestHeader(value = "X-Total-Chunks", defaultValue = "1") int totalChunks,
        @RequestParam(value = "fileName", defaultValue = "") String fileName) throws IOException {
        try {
            if (chunkIndex == 0) {
                // First chunk, initialize the upload

                // Ensure fileName is not null or empty before initiating upload
                if (fileName == null || fileName.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("File name cannot be null or empty");
                }

                service.initiateUpload(file, itemId, fileName);
            }
            // Process the chunk
            service.processChunk(file.getBytes(), itemId, chunkIndex);

            if (chunkIndex == totalChunks - 1) {
                // Last chunk, finalize the upload
                String uploadImage = service.finalizeUpload(itemId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(uploadImage);
            }

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Object> downloadImage(@PathVariable String fileName) {
        try {
            byte[] imageData = service.downloadImage(fileName);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Image not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteImageById(@PathVariable long id) {
        try {
            service.deleteImageById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Image not found for id " + id);
        }
    }
    
}
