package sunnyplantwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class AddTo17Digit {
	public static void main(String[] args) {
		String inputFolder = "D:\\test_files\\17digit_input\\";
		String outputFile = "D:\\test_files\\17digit_output\\output.csv";

		try {
			processCsvFiles(inputFolder, outputFile);
			System.out.println("-----Process finished------" + new Date());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void processCsvFiles(String inputFolder, String outputFile) throws IOException {
		Path inputFolderPath = Paths.get(inputFolder);

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(inputFolderPath, "*.{csv,txt}")) {
			for (Path filePath : directoryStream) {

				processCsv(filePath.toString(), outputFile);
			}
		}

	}

	public static void processCsv(String inputFile, String outputFile) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

			String line;
			while ((line = reader.readLine()) != null) {

				String modifiedValue = addZeros(line);
				writer.write(modifiedValue); // 重新組合 CSV 行
				writer.newLine();

			}
		}
	}

	public static String addZeros(String input) {
		int targetLength = 17;
		int inputLength = input.length();

		if (inputLength < targetLength) {
			int zerosToAdd = targetLength - inputLength;
			String zeros = "00000000000000000".substring(0, zerosToAdd);
			return zeros + input;
		}

		return input;
	}
}
