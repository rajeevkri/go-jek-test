package com.gojek;

import com.gojek.input.parser.AbstractParser;
import com.gojek.input.parser.CommandLineParser;
import com.gojek.input.parser.FileInputParser;

public class Main {

	public static void main(String[] args) {

		AbstractParser processor = null;

		if (args.length >= 1) {
			processor = new FileInputParser(args[0]);
		} else {
			processor = new CommandLineParser();
		}
		try {
			processor.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
