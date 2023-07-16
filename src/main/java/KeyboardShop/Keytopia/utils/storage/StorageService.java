package KeyboardShop.Keytopia.utils.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class StorageService implements IStorageService {

    private final String BUCKET_NAME = "keytopia-4691f.appspot.com";

    public String uploadFile(MultipartFile file) throws IOException {
        File fl = ResourceUtils.getFile("classpath:keytopia.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(fl));
        StorageOptions options = StorageOptions.newBuilder().setCredentials(credentials).build();
        Storage storage = options.getService();

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        storage.create(blobInfo, file.getBytes());

        URL url = storage.get(blobId).signUrl(365, TimeUnit.DAYS);
        System.out.println(url);
        return url.toString();
    }
}
