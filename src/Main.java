//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.opencv.core.Core; // Import the Core class from the OpenCV library
import org.opencv.core.Mat; // Import the Mat class for handling images
import org.opencv.core.Size; // Import the Size class for resizing images
import org.opencv.highgui.HighGui; // Import the HighGui class for displaying images and handling user input
import org.opencv.imgcodecs.Imgcodecs; // Import the Imgcodecs class for reading and writing image files
import org.opencv.imgproc.Imgproc; // Import the Imgproc class for image processing operations

public class Main {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Read the input image
        String inputImagePath = "D:/rt.jfif"; // Path to the input image file
        Mat inputImage = Imgcodecs.imread(inputImagePath); // Read the image using Imgcodecs

        // Check if the image was loaded successfully
        if (inputImage.empty()) {
            System.out.println("Failed to load the input image.");
            return; // Exit the program if the image could not be loaded
        }

        // Get the original size of the input image
        int originalWidth = inputImage.cols(); // Get the width of the input image
        int originalHeight = inputImage.rows(); // Get the height of the input image
        System.out.println("Original image size: " + originalWidth + "x" + originalHeight); // Print the original image size

        // Set the desired size for the subsampled image
        int desiredWidth = 2000; // Set the desired width for the subsampled image
        int desiredHeight = 1500; // Set the desired height for the subsampled image

        // Subsample the image gradually from its original size to the desired size
        Mat subsampledImage = inputImage.clone(); // Create a copy of the input image to perform subsampling
        double scaleFactorX = (double) desiredWidth / originalWidth; // Calculate the scale factor for width
        double scaleFactorY = (double) desiredHeight / originalHeight; // Calculate the scale factor for height

        // Create a window to display the subsampled image
        String windowName = "Subsampled Image"; // Name of the window to display the subsampled image
        HighGui.namedWindow(windowName, HighGui.WINDOW_NORMAL); // Create a window with the specified name and normal window mode

        for (int i = originalWidth; i >= desiredWidth; i--) {
            int currentWidth = (int) (originalHeight * scaleFactorX); // Calculate the current width based on the scale factor
            int currentHeight = (int) (originalHeight * scaleFactorY); // Calculate the current height based on the scale factor

            // Resize the image to the current size
            Imgproc.resize(subsampledImage, subsampledImage, new Size(currentWidth, currentHeight)); // Resize the subsampled image

            // Display the resized image in the window
            HighGui.imshow(windowName, subsampledImage); // Display the subsampled image in the window
            HighGui.waitKey(5); // Wait for 50 milliseconds to display the image
        }

        // Wait until a key is pressed to close the window
        HighGui.waitKey(0); // Wait until a key is pressed

        // Release resources
        inputImage.release(); // Release the memory used by the input image
        subsampledImage.release(); // Release the memory used by the subsampled image
        HighGui.destroyAllWindows(); // Destroy all windows created by OpenCV
    }
}
