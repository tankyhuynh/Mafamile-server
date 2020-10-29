package com.mafami.Mafami.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Utils.FileUtils;

/**
* @author root {6:37:38 PM}:
 * @version Creation time: Oct 29, 2020 6:37:38 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@RestController
@RequestMapping("/api/image")
public class ImageAPI {

	@Autowired
	private FileUtils fileUtils;
	
	@PostMapping
	public String upload(@RequestBody String base64Image) {
		return fileUtils.decoder(base64Image, "ImageAPI");
	}
	
}
