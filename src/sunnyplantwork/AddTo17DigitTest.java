package sunnyplantwork;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddTo17DigitTest {

	private static Path localFileInputPath = Paths.get("D:\\test_files\\17digit_input");
	private static Path localFileOutputPath = Paths.get("D:\\test_files\\17digit_output");
	private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		if (Files.exists(localFileInputPath)) {
			Files.newDirectoryStream(localFileInputPath).forEach(file -> {
				try {
					Files.delete(file);

				} catch (IOException ioex) {
					ioex.getMessage();
				}
			});
		}

		if (Files.exists(localFileOutputPath)) {
			Files.newDirectoryStream(localFileOutputPath).forEach(file -> {
				try {
					Files.delete(file);

				} catch (IOException ioex) {
					ioex.getMessage();
				}
			});
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_Main_csv() throws Exception {

		Files.deleteIfExists(localFileInputPath.resolve("input.csv"));

		Path inputFilePath = Files.createFile(localFileInputPath.resolve("input.csv"));
		Path outputFilePath = Files.createFile(localFileOutputPath.resolve("output.csv"));

		try (BufferedWriter br = new BufferedWriter(new FileWriter(inputFilePath.toString(), StandardCharsets.UTF_8))) {

			br.write("GF00004");
			br.newLine(); // 換行
			br.write("KGF00004");
			br.newLine(); // 換行
			br.write("FFFGF00004");
			br.newLine(); // 換行
			br.write("FFFGF000045");
			br.newLine(); // 換行
			br.write("FFFGF000045FFFGF000045FFFGF000045");

		}

		AddTo17Digit.main(null);

		try (BufferedReader bf = new BufferedReader(
				new InputStreamReader(new FileInputStream(outputFilePath.toString()), CHARSET_UTF8))) {
			String actualCSV;
			int numberCounter = 0;
			String[] actualSplit = new String[10];

			while ((actualCSV = bf.readLine()) != null) {

				actualSplit[numberCounter] = actualCSV;
				numberCounter++;

			}

			assertThat(actualSplit[0], is("0000000000GF00004"));
			assertThat(actualSplit[1], is("000000000KGF00004"));
			assertThat(actualSplit[2], is("0000000FFFGF00004"));
			assertThat(actualSplit[3], is("000000FFFGF000045"));
			assertThat(actualSplit[4], is("FFFGF000045FFFGF000045FFFGF000045"));

		}

		this.deleteTempFile();

	}

	@Test
	void test_Main_txt() throws Exception {

		Files.deleteIfExists(localFileInputPath.resolve("input.txt"));

		Path inputFilePath = Files.createFile(localFileInputPath.resolve("input.txt"));
		Path outputFilePath = Files.createFile(localFileOutputPath.resolve("output.csv"));

		try (BufferedWriter br = new BufferedWriter(new FileWriter(inputFilePath.toString(), StandardCharsets.UTF_8))) {

			br.write("GF00004");
			br.newLine(); // 換行
			br.write("KGF00004");
			br.newLine(); // 換行
			br.write("FFFGF00004");
			br.newLine(); // 換行
			br.write("FFFGF000045");
			br.newLine(); // 換行
			br.write("FFFGF000045FFFGF000045FFFGF000045");

		}

		AddTo17Digit.main(null);

		try (BufferedReader bf = new BufferedReader(
				new InputStreamReader(new FileInputStream(outputFilePath.toString()), CHARSET_UTF8))) {
			String actualCSV;
			int numberCounter = 0;
			String[] actualSplit = new String[10];

			while ((actualCSV = bf.readLine()) != null) {

				actualSplit[numberCounter] = actualCSV;
				numberCounter++;

			}

			assertThat(actualSplit[0], is("0000000000GF00004"));
			assertThat(actualSplit[1], is("000000000KGF00004"));
			assertThat(actualSplit[2], is("0000000FFFGF00004"));
			assertThat(actualSplit[3], is("000000FFFGF000045"));
			assertThat(actualSplit[4], is("FFFGF000045FFFGF000045FFFGF000045"));

		}

		this.deleteTempFile();

	}

	private void deleteTempFile() throws IOException {

		if (Files.exists(localFileInputPath)) {
			Files.newDirectoryStream(localFileInputPath).forEach(file -> {
				try {
					Files.delete(file);

				} catch (IOException ioex) {
					ioex.getMessage();
				}
			});
		}

		if (Files.exists(localFileOutputPath)) {
			Files.newDirectoryStream(localFileOutputPath).forEach(file -> {
				try {
					Files.delete(file);

				} catch (IOException ioex) {
					ioex.getMessage();
				}
			});
		}

	}

}
