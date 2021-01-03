package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Service.PostService;
import com.mafami.Mafami.Utils.FileUtils;

@RestController
@RequestMapping("/api//post")
public class PostAPI {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private LogService logService;
	
	@GetMapping
	public ResponseEntity<List<PostEntity>> getAll() {
		return ResponseEntity.ok(postService.findAll());
	}
	
	@GetMapping("/page/{numberOfPage}")
	public Page<PostEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return postService.findAllByPage(numberOfPage);
	}
	
	@GetMapping("/{site}")
	public ResponseEntity<List<PostEntity>> getAllBySite(@PathVariable("site") String site) {
		return ResponseEntity.ok(postService.findAllBySite(site));
	}

	
	@GetMapping("/{site}/{id}")
	public ResponseEntity<PostEntity> getOne(@PathVariable("id") String id) {
		return ResponseEntity.ok(postService.findOneById(id));
	}
	
	
	@PostMapping("/{site}")
	public ResponseEntity<PostEntity> saveOne(@PathVariable("site") String site, @RequestBody PostEntity entity) throws Exception {
		entity.setSite(site);
		
		try {
			String URL = fileUtils.decoder(entity.getThumbnail(), "outputFile");
			entity.setThumbnail(URL);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String author = entity.getAuthor().getUsername();
		String content = author  + " đã thêm bài viết " + "<b>" + entity.getTitle() + "</b>" + " lúc " + (df.parse(sf_log.format(Calendar.getInstance().getTime())))+ " vào bài viết của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return ResponseEntity.ok(postService.save(entity));
	}
	
	@PutMapping("/{site}/{id}")
	public PostEntity saveOneById(@PathVariable("site") String site, @PathVariable String id, @RequestBody PostEntity postEntity) throws Exception {
		postEntity.setId(id);
		postEntity.setSite(site);
		
		if( postEntity.getThumbnail() != null ) {
			String URL = fileUtils.decoder(postEntity.getThumbnail(), "outputFile");
			postEntity.setThumbnail(URL);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String author = postEntity.getAuthor().getUsername();
		String content = author  + " đã sửa bài viết " + postEntity.getTitle() + " lúc " + (df.parse(sf_log.format(Calendar.getInstance().getTime())))+ " trong bài viết của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return postService.save(postEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id, @RequestBody(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		PostEntity postEntity = postService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa món " + postEntity.getTitle() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		postService.delete(id);
	}
	
	@DeleteMapping("/{site}/{id}")
	public void deleteOneByIdBySite(@PathVariable("site") String site, @PathVariable("id") String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		PostEntity postEntity = postService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa bài viết " + postEntity.getTitle() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		postService.delete(id);
	}
	
	@DeleteMapping("/all/{site}")
	public void deleteAllBySite(@PathVariable("site") String site) {
		postService.deleteAll();
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		postService.deleteAll();
	}
	
	
	
}
