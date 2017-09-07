package com.github.cunvoas.mediavideoconversion.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Commande Line Executor.
 * @author UNVOAS
 */
public class Exec {
	private static final Logger LOGGER = LoggerFactory .getLogger(Exec.class);

	public String execute(String executableBin, String[] args) {
		StringBuilder output = new StringBuilder();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		if (FileUtils.getFile(executableBin).isFile()) {
			try {
				List<String> prms = Arrays.asList(args);
				prms.add(0, executableBin);
				Process process = new ProcessBuilder(prms).start();

				is = process.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				
				String line;
				while ((line = br.readLine()) != null) {
					output.append(line);
				}
				
			} catch (IOException e) {
				LOGGER.error("excecute"+executableBin, e);
			} finally {
				IOUtils.closeQuietly(br);
				IOUtils.closeQuietly(isr);
				IOUtils.closeQuietly(is);
			}
			
		}
		return output.toString();

	}

}
