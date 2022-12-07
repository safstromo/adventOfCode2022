package day7;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7 {
	public static void main(String[] args) {

		List<InputFile> files = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/day7/problemData.txt"))) {
			files = reader.lines().map(InputFile::getFile).collect(Collectors.toList());
			files.removeIf(Objects::isNull);
		} catch (Exception e) {
			e.getMessage();
		}

		System.out.println(files);

		String test = "$ cd /\n" +
				"$ ls\n" +
				"dir a\n" +
				"14848514 b.txt\n" +
				"8504156 c.dat\n" +
				"dir d\n" +
				"$ cd a\n" +
				"$ ls\n" +
				"dir e\n" +
				"29116 f\n" +
				"2557 g\n" +
				"62596 h.lst\n" +
				"$ cd e\n" +
				"$ ls\n" +
				"584 i\n" +
				"$ cd ..\n" +
				"$ cd ..\n" +
				"$ cd d\n" +
				"$ ls\n" +
				"4060174 j\n" +
				"8033020 d.log\n" +
				"5626152 d.ext\n" +
				"7214296 k";

		List<InputFile> test2 = test.lines().map(InputFile::getFile).collect(Collectors.toList());
		test2.removeIf(Objects::isNull);
		System.out.println(test2);
	}

	class InputDir {
		String name;
		static List<InputFile> files = new ArrayList<>();
		static List<InputDir> dir = new ArrayList<>();
		static Pattern pattern = Pattern.compile("(\\d+)\\s(\\S+)");

		InputDir getFile(String line) {
			var matcher = pattern.matcher(line);
			if (matcher.matches())
				return new InputDir(matcher.group(1));
			return null;
		}

		public InputDir(String name) {
			this.name = name;
		}

		public InputDir(String name, InputFile files, List<InputDir> dir) {
			this.name = name;
		}

		public static List<InputDir> getDir() {
			return dir;
		}

		public static List<InputFile> getFiles() {
			return files;
		}

		private static void printSize() {
			int dirSize = 0;

			for (InputFile i : files) {
				dirSize = dirSize + i.size();
			}

		}


	}

}


record InputFile(int size, String name) {

	static Pattern pattern = Pattern.compile("(\\d+)\\s(\\S+)");

	static InputFile getFile(String line) {
		var matcher = pattern.matcher(line);
		if (matcher.matches())
			return new InputFile(Integer.parseInt(matcher.group(1)),
					matcher.group(2));
		return null;
	}

}

