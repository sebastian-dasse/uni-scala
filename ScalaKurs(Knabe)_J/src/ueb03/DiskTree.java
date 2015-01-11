package ueb03;

import java.io.File;

public class DiskTree {
	/**
	 * Listet ein Verzeichnis und alle seine Tochterverzeichnisse in Baumform auf.
	 * @params args Kommandozeilenargumente. Argument 0 wird als Verzeichnisname aufgefasst.
	 */
	public static void main(final String[] args) {
		if (args.length <= 0) {
			throw new IllegalArgumentException("Main has to be called with one argument specifying the directory path.");
		}
		new DiskTree().execute(new File(args[0]));
	}

	private void execute(final File file) {
		execute(file, "");
	}
	
	private void execute(final File file, final String prefix) {
		if (!file.exists()) {
			throw new IllegalArgumentException("This is not a vali path '" + file + "'");
		}
		System.out.println(prefix + file);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				execute(f, prefix + "|  ");
			}
		}
	}
}
