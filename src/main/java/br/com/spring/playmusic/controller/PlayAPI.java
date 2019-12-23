package br.com.spring.playmusic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlayAPI {

	@RequestMapping("/music")
	@ResponseBody
	public ResponseEntity<InputStreamResource> listenMusic() throws IOException {
			
		File videoFile = ResourceUtils.getFile("classpath:mp3/testeBruno.mp3");
		
		long songSize = Files.size(Paths.get(videoFile.toURI()));
		
		MediaType mediaType = org.springframework.http.MediaType.parseMediaType("application/octet-stream");
	
		InputStreamResource resource = new InputStreamResource(new FileInputStream(videoFile));
		
		return ResponseEntity.ok()
				.contentType(mediaType)
				.contentLength(songSize)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+ videoFile.getName()+"\"")
				.body(resource);
	}
}
