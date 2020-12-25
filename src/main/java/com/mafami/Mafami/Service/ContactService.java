package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Repository.ContactRepo;

/**
* @author root {1:54:20 PM}:
 * @version Creation time: Nov 8, 2020 1:54:20 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Service
public class ContactService {

	@Autowired
	private ContactRepo contactRepo;

	public ContactEntity findOneById(String id) {
		return contactRepo.findOneById(id);
	}

	public List<ContactEntity> findAll() {
		return contactRepo.findAll();
	}

	public List<ContactEntity> findAllBySite(String site) {
		return contactRepo.findAllBySite(site);
	}

	public Page<ContactEntity> findAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "time"));
		return contactRepo.findAll(pageable);
	}

	public ContactEntity save(ContactEntity entity) {
		return contactRepo.save(entity);
	}

	public void delete(String id) {
		contactRepo.delete(contactRepo.findOneById(id));
	}

	public void deleteAllBySite(String site) {
		List<ContactEntity> listContacts = contactRepo.findAllBySite(site);
		contactRepo.deleteAll(listContacts);
	}

	public void deleteAll() {
		contactRepo.deleteAll();
	}

}
