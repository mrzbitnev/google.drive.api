import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;

import java.io.IOException;

public class PrintFilesInFolderModifiedDate {

    /**
     * Print the folder by id.
     *
     * @return An List of ChildReference object.
     * @throws IOException
     */
    static ChildList printFolder(Drive service, String folderId, String tipe, String modifiedDate) throws IOException {
        ChildList result;

        Drive.Children.List firstRequest = service.children().list(folderId)
                .setQ("mimeType= '" + tipe + "' and modifiedDate <= '" + modifiedDate + "'" );
        do {
            result = firstRequest.execute();
            for (ChildReference child : result.getItems()) {
                File file = service.files().get(child.getId()).execute();
                System.out.printf("Found file: %s (%s): %s\n", file.getTitle(), file.getId(), file.getCreatedDate());
            }
            firstRequest.setPageToken(result.getNextPageToken());
        } while (firstRequest.getPageToken() != null && firstRequest.getPageToken().length() > 0);
            return result;
    }
}

