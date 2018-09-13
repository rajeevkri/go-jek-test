package com.gojek.input.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLineParser extends AbstractParser {

	@Override
	public void process() throws Exception {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String inputString = bufferRead.readLine();
			validateAndProcess(inputString);
		}
	}

}
