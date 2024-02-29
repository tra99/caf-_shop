package shop.coffee.backend.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.coffee.backend.entity.Image;
import shop.coffee.backend.entity.Item;
import shop.coffee.backend.repository.ImageRepository;
import shop.coffee.backend.repository.ItemRepository;
import shop.coffee.backend.util.ImageUtil;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ItemRepository itemRepository;

    private final Map<Long, ByteArrayOutputStream> uploadBuffers = new ConcurrentHashMap<>();
    private final Map<Long, String> uploadFileNames = new ConcurrentHashMap<>();

    public void initiateUpload(MultipartFile file, Long itemId, String fileName) {
        // Create a new entry in the uploadBuffers map for the item ID
        uploadBuffers.put(itemId, new ByteArrayOutputStream());

        // Save the provided fileName for later use
        if (fileName != null && !fileName.isEmpty()) {
            uploadFileNames.put(itemId, fileName);
        } else {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
    }

    public void processChunk(byte[] bytes, Long itemId, int chunkIndex) {
        ByteArrayOutputStream buffer = uploadBuffers.get(itemId);

        if (buffer != null) {
            try {
                buffer.write(bytes);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        } else {
            throw new IllegalStateException("Upload buffer not found for item ID: " + itemId);
        }
    }

    public String finalizeUpload(Long itemId) {
        try {
            ByteArrayOutputStream buffer = uploadBuffers.remove(itemId);
            String fileName = uploadFileNames.remove(itemId); // Retrieve the saved fileName

            if (buffer != null && fileName != null) {
                byte[] imageData = buffer.toByteArray();

                Optional<Item> optionalItem = itemRepository.findById(itemId);

                if (optionalItem.isPresent()) {
                    Item item = optionalItem.get();
                    Image image = imageRepository.save(Image.builder()
                            .name(fileName) // Use the saved fileName
                            .type("image/png") // Set the appropriate content type
                            .imageData(ImageUtil.compressImage(imageData))
                            .item(item)
                            .build()
                    );

                    if (image != null) {
                        return "Upload completed successfully for item ID: " + itemId;
                    } else {
                        throw new IllegalStateException("Failed to save image for item ID: " + itemId);
                    }
                } else {
                    throw new IllegalStateException("Item not found for ID: " + itemId);
                }
            } else {
                throw new IllegalStateException("Upload buffer or fileName not found for item ID: " + itemId);
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            return "Failed to finalize upload for item ID: " + itemId + ". Reason: " + e.getMessage();
        }
    }

    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);

        if (dbImageData.isPresent()) {
            return ImageUtil.decompressImage(dbImageData.get().getImageData());
        }

        return null;
    }

    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    public Image createOrUpdate(Image image) {
        return imageRepository.save(image);
    }

    public void deleteImageById(Long id) {
        imageRepository.deleteById(id);
    }
}
