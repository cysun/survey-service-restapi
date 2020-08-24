package survey.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import survey.exception.AddingQuestionError;
import survey.model.core.File;
import survey.model.core.User;
import survey.model.core.dao.FileDao;
import survey.model.core.dao.UserDao;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FileDao fileDao;

	@GetMapping("/{file_id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Resource> downloadFile(@PathVariable Long file_id) {

		// has to change later if we want to do authorization
		// User user = userDao.getUser(1);

		// Load file from database
		File file = fileDao.getFile(file_id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getFileData()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> uploadFile(
			@RequestParam(value = "file", required = true) MultipartFile file) {

		// has to change later if we want to do authorization
		User user = userDao.getUser(1);
		try {
			

			 File newFile = fileDao.uploadFile(file, user);
			 Map<String, String> jsonFile = new HashMap<>();
			 jsonFile.put("url", newFile.getUrl());
			
			 return jsonFile;

		} catch (Exception e) {
			System.out.println("hereerr");
			throw new AddingQuestionError(e.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("/{file_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeFile(@PathVariable Long file_id) {
		fileDao.deleteFile(file_id, userDao.getUser(1));
	}
}
