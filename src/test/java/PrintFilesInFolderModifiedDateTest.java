import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import org.junit.Assert;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class PrintFilesInFolderModifiedDateTest {
        private static final String FOLDER_ID = "1cMyDPQA_za0RHX1d1pihnX_OIm5EXt4G";
        private static final String TYPE = "application/pdf";
        private static final String MODIFIEDDATE = "2018-04-14T04:32:08.977Z";

    @org.junit.Test
    public void printFolderTest() {
        int indexChild = 0;

        try {
            ChildList list = PrintFilesInFolderModifiedDate.printFolder(DriveQuickstart.getDrive(),
                    FOLDER_ID, TYPE, MODIFIEDDATE);
            for (ChildReference child : list.getItems()) {
                indexChild++;
            }
            Assert.assertNotEquals(0, indexChild);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}