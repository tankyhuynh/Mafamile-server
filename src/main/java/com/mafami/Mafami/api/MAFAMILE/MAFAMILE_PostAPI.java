package com.mafami.Mafami.api.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_PostEntity;
import com.mafami.Mafami.Service.MAFAMILE.MFAMILE_PostService;
import com.mafami.Mafami.Utils.FileUtils_TanKy;

@RestController
@RequestMapping("/api/mafamile/post")
public class MAFAMILE_PostAPI {

	@Autowired
	private MFAMILE_PostService mFAMILE_PostService;
	
	@Autowired
	private FileUtils_TanKy fileUtils;
	
	@GetMapping
	public List<MAFAMILE_PostEntity> getAll() {
		return mFAMILE_PostService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MAFAMILE_PostEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(mFAMILE_PostService.findOneById(id));
	}
	
	@PostMapping
	public ResponseEntity<MAFAMILE_PostEntity> saveOne(@RequestBody MAFAMILE_PostEntity entity) {
		
		return ResponseEntity.ok(mFAMILE_PostService.save(entity));
	}
	
	@PutMapping("/{id}")
	public MAFAMILE_PostEntity saveOneById(@PathVariable String id, @RequestBody MAFAMILE_PostEntity mAFAMILE_PostEntity) {
		mAFAMILE_PostEntity.setId(id);
		
		return mFAMILE_PostService.save(mAFAMILE_PostEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		 mFAMILE_PostService.delete(id);
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		mFAMILE_PostService.deleteAll();
	}
	
	
	
	
	
	
	
}
