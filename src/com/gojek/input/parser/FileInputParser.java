package com.gojek.input.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileInputParser extends AbstractParser {

	String filePath = null;

	public FileInputParser(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void process() throws Exception {
		File inputFile = new File(filePath);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		while ((line = br.readLine()) != null) {
			validateAndProcess(line);
		}
	}

}
