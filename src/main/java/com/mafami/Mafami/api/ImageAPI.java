package com.mafami.Mafami.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Utils.FileUtils;
import com.mafami.Mafami.model.Image;

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
	
	@PostMapping(consumes = MediaType.ALL_VALUE)
	public String upload(@RequestBody Image base64Image) {
		System.out.println("In Image API");
		return fileUtils.decoder(base64Image.getImage(), "ImageAPI");
	}
	
}
