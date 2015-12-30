package x12;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface Parser {
	EDI parse(File source) throws FormatException, IOException;
	EDI parse(String source) throws FormatException;
	EDI parse(InputStream source) throws FormatException, IOException;
}