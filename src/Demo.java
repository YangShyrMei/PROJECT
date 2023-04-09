import java.io.File;
import java.io.FileInputStream;

import com.cloudmersive.client.EditPdfApi;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.model.PdfMetadata;
import com.cloudmersive.client.model.SetPdfMetadataRequest;

public class Demo {
	public static void main(String args[])throws Exception {


EditPdfApi apiInstance = new EditPdfApi();

SetPdfMetadataRequest request = new SetPdfMetadataRequest(); // SetPdfMetadataRequest | 

try {

    byte[] result = apiInstance.editPdfSetMetadata(request);

    System.out.println(result);

} catch (ApiException e) {

    System.err.println("Exception when calling EditPdfApi#editPdfSetMetadata");

    e.printStackTrace();

}
	}
}
