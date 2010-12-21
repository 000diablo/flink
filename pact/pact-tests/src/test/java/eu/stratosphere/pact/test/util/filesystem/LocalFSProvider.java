package eu.stratosphere.pact.test.util.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LocalFSProvider implements FilesystemProvider {

	public boolean createDir(String dirName) throws IOException {
		File f = new File(dirName);
		if(f.exists()) {
			return false;
		}
		
		return f.mkdir();
	}

	public boolean createFile(String fileName, String fileContent) throws IOException {
		File f = new File(fileName);
		if(f.exists()) {
			return false;
		}
		
		FileWriter fw = new FileWriter(f);
		fw.write(fileContent);
		fw.close();
		
		return true;
	}
	
	public boolean copyFile(String source, String target) throws IOException {
		File t = new File(target);
		if(t.exists()) {
			return false;
		}
		
		File s = new File(source);
		
		FileWriter tw = new FileWriter(t);
		FileReader sr = new FileReader(s);
		
		char[] buffer = new char[1024];
		
		int copiedBytes = sr.read(buffer);
		while(copiedBytes > -1) {
			tw.write(buffer, 0, copiedBytes);
			copiedBytes = sr.read(buffer);
		}
		
		sr.close();
		tw.close();
		
		return true;
	}

	public boolean delete(String path, boolean recursive) throws IOException {
		File f = new File(path);
		
		if(f.isDirectory() && recursive) {
			for(String c : f.list()) {
				this.delete(c,true);
			}
			f.delete();
			
			return true;
		} else if(f.isDirectory() && !recursive) {
			return false;
		} else {
			f.delete();
			
			return true;
		}
	}

	public InputStream getInputStream(String file) throws IOException {
		return new FileInputStream(file);
	}

	public OutputStream getOutputStream(String file) throws IOException {
		return new FileOutputStream(file);
	}

	public String getTempDirPath() {
		return System.getProperty("java.io.tmpdir");
	}
	
	

	@Override
	public void start() throws Exception {
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isDir(String file) throws IOException {
		return (new File(file)).isDirectory();
	}

	@Override
	public String[] listFiles(String dir) throws IOException {
		File f = new File(dir);
		
		if(!f.isDirectory()){
			return null;
		} else {
			return f.list();
		}
	}

	@Override
	public String getURIPrefix() {
		return "file://";
	}

	
}
