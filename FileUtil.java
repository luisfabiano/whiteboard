import java.io.File;

class FileUtil {
	public File getDir(){ // TODO add code to here
		return new File(this.getClass().getResource("").getFile());
	}
}
