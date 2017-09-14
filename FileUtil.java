import java.io.File;

class FileUtil {
	public File getDir(){
		return new File(this.getClass().getResource("").getFile());
	}
}
