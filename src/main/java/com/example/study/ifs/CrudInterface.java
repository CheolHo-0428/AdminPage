package com.example.study.ifs;

import com.example.study.model.network.Header;

public interface CrudInterface {

	public Header create();
	
	public Header read(Long id);
	
	public Header update();
	
	public Header delete(Long id);
}
