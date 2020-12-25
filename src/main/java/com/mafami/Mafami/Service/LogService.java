package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Repository.LogRepo;

/**
* @author root {1:25:08 PM}:
 * @version Creation time: Nov 8, 2020 1:25:08 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Service
public class LogService {

	@Autowired
	private LogRepo logRepo;
	
	public LogEntity findOneById(String id) {
		return logRepo.findOneById(id);
	}

	public Page<LogEntity> getAll() {
		Pageable pageable = PageRequest.of(0, 10);
		return logRepo.findAll(pageable);
//		return logRepo.findAll(Sort.by(Sort.Direction.DESC, "time"));
	}
	
	public List<LogEntity> getAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return logRepo.findAll(pageable).getContent();
	}

	public LogEntity save(LogEntity entity) {
		return logRepo.save(entity);
	}

	public void delete(String id) {
		logRepo.delete(logRepo.findOneById(id));
	}
	
	public void deleteAll() {
		logRepo.deleteAll();
	}
	
	
}
