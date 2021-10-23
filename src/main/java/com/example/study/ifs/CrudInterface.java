package com.example.study.ifs;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.study.model.network.Header;

public interface CrudInterface<Req, Res> {

	public Header<Res> create(Header<Req> request);
	
	public Header<Res> read(Long id);
	
	public Header<List<Res>> search(Pageable pageable);
	
	public Header<Res> update(Header<Req> request);
	
	public Header delete(Long id);
	
}
