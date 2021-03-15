package org.com.deliveries.utils;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
	
	@Test
	public void whenUseFilterFilesByNameAndExtensionMethodTheListMustToBeFiletered() throws Exception {
		//arranges
		List<Path> inputFileList = new ArrayList<>();
		Path path1 = Paths.get("in01.txt");
		Path path2 = Paths.get("out01.txt");
		Path path3 = Paths.get("in01.pdf");		
		inputFileList.add(path1);
		inputFileList.add(path2);
		inputFileList.add(path3);
		//act
		inputFileList = Utils.filterFilesByNameAndExtension(inputFileList, "in", "txt");
		//assert
		assertEquals(inputFileList.size(),1);	
		assertEquals(inputFileList.get(0).getFileName().toString(), "in01.txt");
			}

}
